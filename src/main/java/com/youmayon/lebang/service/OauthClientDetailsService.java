package com.youmayon.lebang.service;

import com.youmayon.lebang.domain.OauthClientDetails;
import com.youmayon.lebang.enums.ClientRole;
import com.youmayon.lebang.enums.Role;
import org.springframework.data.domain.Page;

/**
 * 渠道service类
 * Created by Jawinton on 17/05/02.
 */
public interface OauthClientDetailsService {
    /**
     * 获取app详情
     * @param id
     * @return
     */
    OauthClientDetails findOne(long id);

    /**
     * 保存app
     * @param oauthClientDetails
     * @return
     */
    OauthClientDetails save(OauthClientDetails oauthClientDetails);

    /**
     * 分页获取app列表
     * @param page
     * @param size
     * @return
     */
    Page<OauthClientDetails> list(ClientRole clientRole, int page, int size);

    /**
     * 获取app详情
     * @param clientId
     * @return
     */
    OauthClientDetails findOne(String clientId);
}
