package com.youmayon.lebang.web;

import com.youmayon.lebang.constant.SecurityConstants;
import com.youmayon.lebang.constant.LogicConstants;
import com.youmayon.lebang.domain.User;
import com.youmayon.lebang.enums.Role;
import com.youmayon.lebang.enums.UserStatus;
import com.youmayon.lebang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

/**
 * Created by Jawinton on 17/05/02.
 * 用户控制器
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenStore tokenStore;

    private PasswordEncoder passwordEncoder = new StandardPasswordEncoder(SecurityConstants.PASSWORD_SECRET);

    private static final int PASSWORD_MIN_LENGTH = 32;

    private static final int PASSWORD_MAX_LENGTH = 32;

    /**
     * 退出登录
     * @param tokenValue
     */
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public void logout(@RequestParam(value = "access_token") String tokenValue) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        tokenStore.removeAccessToken(accessToken);
    }


    /**
     * 添加用户
     * @param user
     * @param errors
     * @param ucb
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> save(
            @Valid @RequestBody User user,
            Errors errors,
            UriComponentsBuilder ucb) {
        assertFieldError(errors);

        Assert.isNull(userService.findByUsername(user.getUsername()), "Username already exists.");

        user.setCreatedTime(System.currentTimeMillis() / 1000);
        user.setModifiedTime(user.getCreatedTime());
        // encode password.
        user.setPassword(passwordEncoder.encode(SecurityConstants.DEFAULT_PASSWORD));
        Assert.isTrue(Role.valueOf(user.getRole()) != null, "Role error.");
        Assert.isTrue(UserStatus.contains(user.getStatus()), "Status error.");

        User savedUser = userService.save(user);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI locationUri = ucb.path("/users/")
                .path(String.valueOf(savedUser.getId()))
                .build()
                .toUri();
        httpHeaders.setLocation(locationUri);

        return new ResponseEntity<>(savedUser, httpHeaders, HttpStatus.CREATED);
    }

    /**
     * 获取用户详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable long id) {
        User savedUser = userService.findOne(id);
        Assert.notNull(savedUser, "User not found.");

        // password 不返回
        savedUser.setPassword(null);

        return savedUser;
    }

    /**
     * 修改用户信息
     * @param id
     * @param unsavedUser
     * @param errors
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public User update(
            @PathVariable long id,
            @Valid @RequestBody User unsavedUser,
            Errors errors) {
        assertFieldError(errors);

        User savedUser = userService.findOne(id);
        Assert.notNull(savedUser, "User not found.");

        // fields below cannot be modified.
        unsavedUser.setId(savedUser.getId());
        unsavedUser.setUsername(savedUser.getUsername());
        unsavedUser.setPassword(savedUser.getPassword());
        unsavedUser.setCreatedTime(savedUser.getCreatedTime());
        unsavedUser.setModifiedTime(System.currentTimeMillis() / 1000);
        unsavedUser.setStatus(savedUser.getStatus());
        return userService.save(unsavedUser);
    }

    /**
     * 修改用户状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.PATCH, consumes = "application/json")
    public User patch(
            @PathVariable long id,
            @PathVariable int status) {
        User savedUser = userService.findOne(id);
        Assert.notNull(savedUser, "User not found.");
        Assert.notNull(UserStatus.contains(status), "User status Error.");

        savedUser.setStatus(status);
        savedUser.setModifiedTime(System.currentTimeMillis() / 1000);

        return userService.save(savedUser);
    }

    /**
     * 修改当前登录用户密码
     * @param user
     * @param passwordMap
     */
    @RequestMapping(value = "/{id}/password", method = RequestMethod.PATCH, consumes = "application/json")
    public void patch(
            @PathVariable long id,
            @AuthenticationPrincipal User user,
            @RequestBody Map<String, String> passwordMap) {
        Assert.isTrue(user.getId() == id, "Password can be only modified by owner.");

        User savedUser = userService.findByUsername(user.getUsername());
        Assert.notNull(savedUser, "User not found.");
        String oldPassword = passwordMap.get("oldPassword");
        Assert.notNull(oldPassword, "Old password cannot be null");
        Assert.isTrue(oldPassword.length() >= PASSWORD_MIN_LENGTH && oldPassword.length() <= PASSWORD_MAX_LENGTH, "Password length is between " + PASSWORD_MIN_LENGTH + " and " + PASSWORD_MAX_LENGTH + ".");
        Assert.isTrue(passwordEncoder.matches(oldPassword, savedUser.getPassword()), "Old password wrong.");

        String newPassword = passwordMap.get("newPassword1");
        Assert.notNull(newPassword, "New password1 cannot be null");
        Assert.isTrue(newPassword.length() >= PASSWORD_MIN_LENGTH && newPassword.length() <= PASSWORD_MAX_LENGTH, "Password length is between " + PASSWORD_MIN_LENGTH + " and " + PASSWORD_MAX_LENGTH + ".");
        Assert.isTrue(!oldPassword.equals(newPassword), "Old password cannot equal to new password.");

        String confirmedNewPassword = passwordMap.get("newPassword2");
        Assert.notNull(confirmedNewPassword, "New password2 cannot be null");
        Assert.isTrue(confirmedNewPassword.length() >= PASSWORD_MIN_LENGTH && confirmedNewPassword.length() <= PASSWORD_MAX_LENGTH, "Password length is between " + PASSWORD_MIN_LENGTH + " and " + PASSWORD_MAX_LENGTH + ".");
        Assert.isTrue(confirmedNewPassword.equals(newPassword), "New password1 is not equal to new password2.");

        savedUser.setPassword(passwordEncoder.encode(newPassword));
        savedUser.setModifiedTime(System.currentTimeMillis() / 1000);
        userService.save(savedUser);
    }

    /**
     * 用户列表
     * @param role
     * @param status
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<User> list(
            @RequestParam(value = "role", defaultValue = LogicConstants.EMPTY_STRING) String role,
            @RequestParam(value = "status", defaultValue = LogicConstants.NEGATIVE_ONE) int status,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size) {
        return userService.list(role, status, page, size);
    }

    /**
     * 获取当前登录用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public User get(@AuthenticationPrincipal User user) {
        return user;
    }
}
