package com.youmayon.lebang.web;

import com.youmayon.lebang.constant.LogicConstants;
import com.youmayon.lebang.domain.App;
import com.youmayon.lebang.service.AppService;
import com.youmayon.lebang.util.StringUtil;
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

import static com.youmayon.lebang.constant.LogicConstants.APP_SECRET_LEN;

/**
 * Created by Jawinton on 17/05/02.
 */
@RestController
@RequestMapping("/apps")
public class AppController extends BaseController {
    @Autowired
    private AppService appService;

    /**
     * 添加APP
     * @param app
     * @param errors
     * @param ucb
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<App> save(
            @Valid @RequestBody App app,
            Errors errors,
            UriComponentsBuilder ucb) {
        assertFieldError(errors);

        Assert.isNull(appService.findOne(app.getName()), "App name conflict.");

        app.setCreatedTime(System.currentTimeMillis() / 1000);
        app.setModifiedTime(app.getCreatedTime());
        // generate app secret
        app.setSecret(StringUtil.generateRandomString(APP_SECRET_LEN));
        app.setEnabled(true);

        App savedApp = appService.save(app);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI locationUri = ucb.path("/apps/")
                .path(String.valueOf(savedApp.getId()))
                .build()
                .toUri();
        httpHeaders.setLocation(locationUri);

        return new ResponseEntity<>(savedApp, httpHeaders, HttpStatus.CREATED);
    }

    /**
     * 获取APP详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public App get(@PathVariable long id) {
        App savedApp = appService.findOne(id);
        Assert.notNull(savedApp, "App not found.");

        return savedApp;
    }
    
    /**
     * 修改APP状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/enabled/{enabled}", method = RequestMethod.PATCH, consumes = "application/json")
    public App patch(
            @PathVariable long id,
            @PathVariable boolean enabled) {
        App savedApp = appService.findOne(id);
        Assert.notNull(savedApp, "App not found.");

        savedApp.setEnabled(enabled);
        savedApp.setModifiedTime(System.currentTimeMillis() / 1000);

        return appService.save(savedApp);
    }

    /**
     * APP列表
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<App> list(
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size) {
        return appService.list(page, size);
    }

    /**
     * APP列表
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<App> list() {
        return appService.list(true);
    }
}
