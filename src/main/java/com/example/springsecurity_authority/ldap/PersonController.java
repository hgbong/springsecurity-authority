package com.example.springsecurity_authority.ldap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public String  getPerson(@RequestParam String uid) {
        Person personByUid = personService.getPersonByUid(uid);
        return personByUid.toString();
    }
    /**
     * http://localhost:8080/person?uid=einstein
     * result:
     *  Person(id=uid=einstein, uid=einstein, fullName=Albert Einstein, lastName=Einstein, email=einstein@ldap.forumsys.com)
     */
}
