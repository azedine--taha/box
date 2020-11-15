package com.emailing.box.business.user.Impl;


import com.emailing.box.business.user.IControleActionClient;
import com.emailing.box.business.user.IUserService;
import com.emailing.box.commons.context.UserContext;
import com.emailing.box.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControleACtionClient implements IControleActionClient {


    @Autowired
    IUserService userService;

    @Override
    public boolean hasNonLegacyAction(String role) {
        String userLogin = UserContext.getUserLogin();
        User user = userService.findByEmail(userLogin);
        boolean hasLegacy = user.getRoles().stream().anyMatch(r -> r.getRoleName().matches(role));
        return hasLegacy;
    }
}
