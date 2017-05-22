package com.amur.areas.users.entities;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "authority")
    private String authority;

    public Role() {
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
