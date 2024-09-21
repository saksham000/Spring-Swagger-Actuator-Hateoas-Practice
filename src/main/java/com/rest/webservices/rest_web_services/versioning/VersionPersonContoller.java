package com.rest.webservices.rest_web_services.versioning;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class VersionPersonContoller {
    
    @GetMapping("v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Saksham Sharma");
    }

    @GetMapping("v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Saksham", "Sharma"));
    }
    
    @GetMapping(value = "person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParam() {
        return new PersonV1("Saksham Sharma");
    }

    @GetMapping(value = "person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonParameter() {
        return new PersonV2(new Name("Saksham", "Sharma"));
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonHeader() {
        return new PersonV1("Saksham Sharma");
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader() {
        return new PersonV2(new Name("Saksham", "Sharma"));
    }

    @GetMapping(value = "person/header", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader() {
        return new PersonV1("Saksham Sharma");
    }

    @GetMapping(value = "person/header", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader() {
        return new PersonV2(new Name("Saksham", "Sharma"));
    }
}
