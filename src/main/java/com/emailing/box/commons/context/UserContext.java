package com.emailing.box.commons.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserContext {

    private static final Logger LOG = LoggerFactory.getLogger(UserContext.class);

    private static ThreadLocal<String> userLoginHolder = new ThreadLocal<>();


    public static void setUserLogin(String userLogin) {
        userLoginHolder.set(userLogin);
    }

    public static String getUserLogin() {
        String userLogin = userLoginHolder.get();
        if (userLogin == null) {
            LOG.warn("Call of getUserLogin without initialised it");
        }
        return userLogin;
    }
}
