package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.OauthClientDetailsRepository;
import com.youmayon.lebang.domain.OauthClientDetails;
import com.youmayon.lebang.enums.ClientRole;
import com.youmayon.lebang.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by Jawinton on 17/05/02.
 */
@Service
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {
    @Autowired
    OauthClientDetailsRepository oauthClientDetailsRepository;

    @Override
    public OauthClientDetails findOne(long id) {
        return oauthClientDetailsRepository.findOne(id);
    }

    @Override
    public OauthClientDetails save(OauthClientDetails oauthClientDetails) {
        return oauthClientDetailsRepository.save(oauthClientDetails);
    }

    @Override
    public Page<OauthClientDetails> list(ClientRole clientRole, int page, int size) {
        return oauthClientDetailsRepository.findByAuthorities(clientRole.name(), new PageRequest(page, size));
    }

    @Override
    public OauthClientDetails findOne(String clientId) {
        return oauthClientDetailsRepository.findFirstByClientId(clientId);
    }
}
