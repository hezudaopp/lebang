package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.OauthClientDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jawinton on 17/05/02.
 */
@Repository
public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, Long> {
    OauthClientDetails findFirstByClientId(String clientId);
    Page<OauthClientDetails> findByAuthorities(String authorities, Pageable pageable);
}
