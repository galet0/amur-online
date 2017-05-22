package com.amur.serviceImpl;

import com.amur.areas.users.entities.SocialUser;
import com.amur.areas.users.repository.SocialUserRepository;
import com.amur.areas.users.userService.RoleService;
import com.amur.areas.users.userService.SocialUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;

@Service
public class SocialUserServiceImpl implements SocialUserService {

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private RoleService roleService;

//    @Autowired
//    public SocialUserServiceImpl(SocialUserRepository socialUserRepository, RoleService roleService) {
//        this.socialUserRepository = socialUserRepository;
//        this.roleService = roleService;
//    }

    @Override
    public void registerOrLogin(User facebookUser) {
        String email = facebookUser.getEmail();
        SocialUser socialUser = this.socialUserRepository.findOneByUsername(email);
        if(socialUser == null){
            socialUser = registerUser(email);
        }

        loginUser(socialUser);
    }

    private SocialUser registerUser(String email){
        SocialUser socialUser = new SocialUser();
        socialUser.setUsername(email);
        socialUser.setProvider("FACEBOOK");
        socialUser.setAccountNonExpired(true);
        socialUser.setAccountNonLocked(true);
        socialUser.setCredentialsNonExpired(true);
        socialUser.setEnabled(true);
        socialUser.addRole(this.roleService.getDefaultRole());
        this.socialUserRepository.saveAndFlush(socialUser);
        return socialUser;
    }

    private void loginUser(SocialUser socialUser){
        Authentication authentication = new UsernamePasswordAuthenticationToken(socialUser, null, socialUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
