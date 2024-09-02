package com.example.springsecurity_authority.ldap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person getPersonByUid(String uid) {
        return personRepository.findByUid(uid);
    }
}
