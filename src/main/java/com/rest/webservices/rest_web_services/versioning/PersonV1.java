package com.rest.webservices.rest_web_services.versioning;

public class PersonV1 {

    private String name;

    public PersonV1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Person [name=" + name + "]";
    }
}
