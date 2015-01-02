package com.icupad.config;

import com.icupad.domain.UserDetailsUserAware;
import com.icupad.domain.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {
    @Override
    public User getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        // can not use service/repository to query for user it causes infinite loop
        return ((UserDetailsUserAware) authentication.getPrincipal()).getUser();
    }
}