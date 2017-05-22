package com.amur.serviceImpl;


import com.amur.areas.regions.entity.Country;
import com.amur.areas.regions.entity.Region;
import com.amur.areas.regions.service.CountryService;
import com.amur.areas.regions.service.RegionService;
import com.amur.areas.users.entities.BasicUser;
import com.amur.areas.users.entities.User;
import com.amur.areas.users.models.binding.users.RegistrationModel;
import com.amur.areas.users.models.view.UserDetailsView;
import com.amur.areas.users.models.view.UserSimpleView;
import com.amur.areas.users.repository.BasicUserRepository;
import com.amur.areas.users.repository.UserRepository;
import com.amur.areas.users.userService.BasicUserService;
import com.amur.areas.users.userService.RoleService;
import com.amur.constants.Errors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BasicUserServiceImpl implements BasicUserService {

    private final BasicUserRepository userRepository;

    private final CountryService countryService;

    private final RegionService regionService;

    private final RoleService roleService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ModelMapper modelMapper;

    @Autowired
    public BasicUserServiceImpl(BasicUserRepository userRepository,
                                CountryService countryService,
                                RegionService regionService,
                                RoleService roleService,
                                BCryptPasswordEncoder bCryptPasswordEncoder,
                                ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.countryService = countryService;
        this.regionService = regionService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }

    @Override
    public void register(RegistrationModel registrationModel) {
        BasicUser user = this.modelMapper.map(registrationModel, BasicUser.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        String countryName = registrationModel.getCountryName();
        Country country = this.countryService.findOneByName(countryName);
        user.setCountry(country);
        String regionName = registrationModel.getRegionName();
        Region region = this.regionService.findOneByName(regionName);
        user.setRegion(region);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.addRole(this.roleService.getDefaultRole());
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserSimpleView findOneByUsername(String username) {
        BasicUser user = this.userRepository.findOneByUsername(username);
        UserSimpleView userSimpleView = this.modelMapper.map(user, UserSimpleView.class);
        return userSimpleView;
    }

    @Override
    public UserDetailsView getOneByUsername(String username) {
        BasicUser basicUser = this.userRepository.findOneByUsername(username);
        UserDetailsView userDetailsView = this.modelMapper.map(basicUser, UserDetailsView.class);
        return userDetailsView;
    }
}
