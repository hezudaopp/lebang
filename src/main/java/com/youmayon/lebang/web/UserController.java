package com.youmayon.lebang.web;

import com.youmayon.lebang.constant.SecurityConstants;
import com.youmayon.lebang.constant.LogicConstants;
import com.youmayon.lebang.domain.User;
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
 * Created by Jawinton on 16/12/14.
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

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public void logout(@RequestParam(value = "access_token") String tokenValue) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        tokenStore.removeAccessToken(accessToken);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> save(
            @Valid @RequestBody User user,
            Errors errors,
            UriComponentsBuilder ucb) {
        assertFieldError(errors);

        Assert.isTrue(!userService.isUsernameExists(user.getUsername()), "Username already exists.");
        Assert.isTrue(!userService.isMobileNoExists(user.getMobileNumber()), "Mobile number already exists.");

        HttpHeaders httpHeaders = new HttpHeaders();
        URI locationUri = ucb.path("/users/")
                .path(String.valueOf(user.getUsername()))
                .build()
                .toUri();
        httpHeaders.setLocation(locationUri);
        user.setCreatedTime(System.currentTimeMillis() / 1000);
        user.setModifiedTime(user.getCreatedTime());
        // encode password.
        user.setPassword(passwordEncoder.encode(LogicConstants.DEFAULT_PASSWORD));
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public User put(
            @PathVariable long id,
            @Valid @RequestBody User unsavedUser,
            Errors errors) {
        assertFieldError(errors);

        User savedUser = userService.findOne(id);
        Assert.notNull(savedUser, "User not found.");
        Assert.isTrue(savedUser.getUsername().equals(unsavedUser.getUsername()));

        // fields below cannot be modified.
        unsavedUser.setId(savedUser.getId());
        unsavedUser.setPassword(savedUser.getPassword());
        unsavedUser.setCreatedTime(savedUser.getCreatedTime());
        unsavedUser.setModifiedTime(System.currentTimeMillis() / 1000);
        unsavedUser.setEnabled(savedUser.getEnabled());
        return userService.save(unsavedUser);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH, consumes = "application/json")
    public User patch(
            @PathVariable long id,
            @RequestBody User unsavedUser) {
        User savedUser = userService.findOne(id);
        Assert.notNull(savedUser, "User not found.");
        savedUser.setEnabledIfNotNull(unsavedUser.getEnabled());
        savedUser.setModifiedTime(System.currentTimeMillis() / 1000);
        return userService.save(savedUser);
    }

    @RequestMapping(value="/password", method = RequestMethod.PATCH, consumes = "application/json")
    public void patch(
            @AuthenticationPrincipal User user,
            @RequestBody Map<String, String> passwordMap) {
        User savedUser = userService.findByUsername(user.getUsername());
        Assert.notNull(savedUser, "User not found.");
        String oldPassword = passwordMap.get("oldPassword");
        Assert.notNull(oldPassword, "Old password cannot be null");
        Assert.isTrue(oldPassword.length() >= PASSWORD_MIN_LENGTH && oldPassword.length() <= PASSWORD_MAX_LENGTH, "Password length is between 6 and 20.");
        Assert.isTrue(passwordEncoder.matches(oldPassword, savedUser.getPassword()), "Old password wrong.");

        String newPassword = passwordMap.get("newPassword1");
        Assert.notNull(newPassword, "New password1 cannot be null");
        Assert.isTrue(newPassword.length() >= PASSWORD_MIN_LENGTH && newPassword.length() <= PASSWORD_MAX_LENGTH, "Password length is between 6 and 20.");
        Assert.isTrue(!oldPassword.equals(newPassword), "Old password cannot equal to new password.");

        String confirmedNewPassword = passwordMap.get("newPassword2");
        Assert.notNull(confirmedNewPassword, "New password2 cannot be null");
        Assert.isTrue(confirmedNewPassword.length() >= PASSWORD_MIN_LENGTH && confirmedNewPassword.length() <= PASSWORD_MAX_LENGTH, "Password length is between 6 and 20.");
        Assert.isTrue(confirmedNewPassword.equals(newPassword), "New password1 is not equal to new password2.");

        savedUser.setPassword(passwordEncoder.encode(newPassword));
        savedUser.setModifiedTime(System.currentTimeMillis() / 1000);
        userService.save(savedUser);
    }

    @RequestMapping(method=RequestMethod.GET)
    public Page<User> list(
            @RequestParam(value = "deptId", defaultValue = "-1") long deptId,
            @RequestParam(value = "realName", defaultValue = "") String realName,
            @RequestParam(value = "mobileNumber", defaultValue = "") String mobileNumber,
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "status", defaultValue = "-1") int status,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size) {
        return userService.list(deptId, realName, mobileNumber, email, status, page, size);
    }

    @RequestMapping(value="/me", method=RequestMethod.GET)
    public User get(@AuthenticationPrincipal User user) {
        return user;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = "application/json")
    public User get(@PathVariable String username) {
        User user = userService.findByUsername(username);
        Assert.notNull(user, "User not found.");
        return user;
    }
}
