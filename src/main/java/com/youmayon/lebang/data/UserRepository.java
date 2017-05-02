package com.youmayon.lebang.data;

import com.youmayon.lebang.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Jawinton on 16/12/14.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByUsernameIgnoringCase(String username);
    User findByMobile(String mobile);
}
