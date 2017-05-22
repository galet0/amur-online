package com.amur.areas.users.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BU")
public class BasicUser extends User{
}
