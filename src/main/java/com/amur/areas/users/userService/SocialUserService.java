package com.amur.areas.users.userService;


import org.springframework.social.facebook.api.User;

public interface SocialUserService {

    void registerOrLogin(User facebookUser);
}
