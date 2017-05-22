package com.amur.areas.users.repository;


import com.amur.areas.users.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findOneByAuthority(String authority);
}
