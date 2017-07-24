package com.youmayon.lebang.web;

import com.youmayon.lebang.constant.LogicConstants;
import com.youmayon.lebang.constant.SecurityConstants;
import com.youmayon.lebang.domain.OauthClientDetails;
import com.youmayon.lebang.enums.ClientRole;
import com.youmayon.lebang.service.OauthClientDetailsService;
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

import static com.youmayon.lebang.constant.LogicConstants.APP_SECRET_LEN;

/**
 * Created by Jawinton on 17/05/02.
 */
@RestController
@RequestMapping("/apps")
public class AppController extends BaseController {
    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    /**
     * 添加APP
     * @param oauthClientDetails
     * @param errors
     * @param ucb
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<OauthClientDetails> save(
            @Valid @RequestBody OauthClientDetails oauthClientDetails,
            Errors errors,
            UriComponentsBuilder ucb) {
        assertFieldError(errors);

        Assert.isNull(oauthClientDetailsService.findOne(oauthClientDetails.getClientId()), "Client id conflict.");

        // generate app secret
        oauthClientDetails.setClientSecret(StringUtil.generateRandomString(APP_SECRET_LEN));
        oauthClientDetails.setAuthorizedGrantTypes(SecurityConstants.APP_AUTHORIZED_GRANT_TYPES);
        oauthClientDetails.setAuthorities(ClientRole.ROLE_APP.name());
        oauthClientDetails.setScope(SecurityConstants.APP_SCOPE);
        oauthClientDetails.setAccessTokenValidity(SecurityConstants.APP_ACCESS_TOKEN_VALIDITY);
        oauthClientDetails.setResourceIds(LogicConstants.EMPTY_STRING);
//        oauthClientDetails.setWebServerRedirectUri(LogicConstants.EMPTY_STRING);
        oauthClientDetails.setAutoapprove(LogicConstants.EMPTY_STRING);

        OauthClientDetails savedOauthClientDetails = oauthClientDetailsService.save(oauthClientDetails);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI locationUri = ucb.path("/apps/")
                .path(String.valueOf(savedOauthClientDetails.getId()))
                .build()
                .toUri();
        httpHeaders.setLocation(locationUri);

        return new ResponseEntity<>(savedOauthClientDetails, httpHeaders, HttpStatus.CREATED);
    }

    /**
     * 获取APP详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OauthClientDetails get(@PathVariable long id) {
        OauthClientDetails savedOauthClientDetails = oauthClientDetailsService.findOne(id);
        Assert.notNull(savedOauthClientDetails, "App not found.");

        return savedOauthClientDetails;
    }

    /**
     * APP列表
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<OauthClientDetails> list(
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size) {
        return oauthClientDetailsService.list(ClientRole.ROLE_APP, page, size);
    }
}
