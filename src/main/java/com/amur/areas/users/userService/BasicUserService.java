package com.amur.areas.users.userService;


import com.amur.areas.users.models.binding.users.RegistrationModel;
import com.amur.areas.users.models.view.UserDetailsView;
import com.amur.areas.users.models.view.UserSimpleView;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface BasicUserService extends UserDetailsService {

    void register(RegistrationModel registrationModel);

    UserSimpleView findOneByUsername(String username);

    UserDetailsView getOneByUsername(String username);
}
