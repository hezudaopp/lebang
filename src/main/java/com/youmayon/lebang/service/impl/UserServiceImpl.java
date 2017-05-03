package com.youmayon.lebang.service.impl;

import com.youmayon.lebang.data.UserRepository;
import com.youmayon.lebang.domain.User;
import com.youmayon.lebang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * Created by Jawinton on 17/05/02.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsernameIgnoringCase(username);
    }

    @Override
    public User findOne(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByMobileNumber(String mobile) {
        return userRepository.findByMobile(mobile);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> list(String role, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        UserFilter userFilter = new UserFilter();
        userFilter.setRole(role);
        userFilter.setStatus(status);
        Specification<User> specification = this.getWhereClause(userFilter);
        Page<User> userPage =  userRepository.findAll(specification, pageable);
        if (userPage == null || userPage.getTotalElements() == 0) {
            return userPage;
        }

        // set user dept info and post info.
        Set<String> usernames = new HashSet<>();
        for (User user : userPage) {
            if (user == null || user.getUsername() == null) {
                continue;
            }
            usernames.add(user.getUsername());
        }

        return userPage;
    }

    @Override
    public boolean isUsernameExists(String username) {
        return findByUsername(username) != null;
    }

    @Override
    public boolean isMobileNoExists(String mobileNo) {
        return findByMobileNumber(mobileNo) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
        return user;
    }

    private class UserFilter {
        private String role;
        private int status;

        public UserFilter() {}

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }

    /**
     * generate where clause dynamically.
     * @param userFilter
     * @return
     */
    private Specification<User> getWhereClause(final UserFilter userFilter){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (userFilter.getRole() != null && !userFilter.getRole().trim().isEmpty()) {
                    predicate.add(cb.equal(root.get("role").as(String.class), userFilter.getRole()));
                }
                if (userFilter.getStatus() >= 0) {
                    predicate.add(cb.equal(root.get("status").as(Integer.class), userFilter.getStatus()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
