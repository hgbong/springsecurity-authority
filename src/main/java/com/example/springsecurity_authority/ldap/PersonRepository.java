package com.example.springsecurity_authority.ldap;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends LdapRepository<Person> {
    Person findByUid(String uid);
}
