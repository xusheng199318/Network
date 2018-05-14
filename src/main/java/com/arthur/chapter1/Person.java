package com.arthur.chapter1;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String first, last;

    public Person(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public Person() {
    }

    public Element getXML() {
        Element person = new Element("person");
        Element firstName = new Element("first");
        Element lastName = new Element("last");
        firstName.appendChild(first);
        lastName.appendChild(last);
        person.appendChild(firstName);
        person.appendChild(lastName);
        return person;
    }



    @Override
    public String toString() {
        return "Person{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
