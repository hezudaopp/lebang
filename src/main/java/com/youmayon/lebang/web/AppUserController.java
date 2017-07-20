package com.youmayon.lebang.web;

import com.youmayon.lebang.constant.LogicConstants;
import com.youmayon.lebang.domain.AppUser;
import com.youmayon.lebang.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Jawinton on 17/05/03.
 */
@RestController
@RequestMapping("/app_users")
public class AppUserController extends BaseController {
    @Autowired
    AppUserService appUserService;

    /**
     * 添加或修改渠道用户
     * @param appUser
     * @param errors
     * @param ucb
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<AppUser> post(
            @Valid @RequestBody AppUser appUser,
            Errors errors,
            UriComponentsBuilder ucb) {
        assertFieldError(errors);

        AppUser savedAppUser = appUserService.findByAppUserId(appUser.getAppUserId());
        long now = System.currentTimeMillis() / 1000;
        HttpStatus returnStatus;
        if (savedAppUser == null) {
            appUser.setCreatedTime(now);
            returnStatus = HttpStatus.CREATED;
        } else {
            appUser.setId(savedAppUser.getId());
            appUser.setCreatedTime(savedAppUser.getCreatedTime());
            returnStatus = HttpStatus.OK;
        }


        appUser.setModifiedTime(now);

        return new ResponseEntity<>(appUserService.save(appUser), returnStatus);
    }

    /**
     * 获取渠道用户详情
     * @param appUserId
     * @return
     */
    @RequestMapping(value = "/app_user_id/{appUserId}", method = RequestMethod.GET)
    public AppUser get(@PathVariable String appUserId) {
        AppUser appUser = appUserService.findByAppUserId(appUserId);
        Assert.notNull(appUser, "App user not found.");

        return appUser;
    }
}
