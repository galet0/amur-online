package com.amur.areas.users.userServiceImpl;


import com.amur.areas.users.entities.Role;
import com.amur.areas.users.repository.RoleRepository;
import com.amur.areas.users.userService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    public static final String DEFAULT_ROLE = "ROLE_USER";

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getDefaultRole() {
        Role role = this.roleRepository.findOneByAuthority(DEFAULT_ROLE);
        return role;
    }
}
