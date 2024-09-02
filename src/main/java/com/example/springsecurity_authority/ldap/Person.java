package com.example.springsecurity_authority.ldap;

import lombok.ToString;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Attribute;

import javax.naming.Name;

@Entry(/*base = "ou=users",*/ objectClasses = {"inetOrgPerson", "organizationalPerson", "person", "top"})
@ToString
public class Person {

    @Id
    private Name id;

    @Attribute(name = "uid")
    private String uid;

    @Attribute(name = "cn")
    private String fullName;

    @Attribute(name = "sn")
    private String lastName;

    @Attribute(name = "mail")
    private String email;

    // Getters and Setters
}
