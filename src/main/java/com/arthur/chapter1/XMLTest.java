package com.arthur.chapter1;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLTest {

    @Test
    public void testXML() throws IOException {
        List<Person> people = new ArrayList<Person>();
        people.add(new Person("Dr. Bunsen", "Honeydew"));
        people.add(new Person("Gonzo", "The Great"));
        people.add(new Person("Phillip J.", "Fry"));

        Element root = new Element("people");
        for (Person p : people) {
            root.appendChild(p.getXML());
        }

        Document doc = new Document(root);
        format(System.out, doc);
        format(new BufferedOutputStream(new FileOutputStream("people.xml")), doc);
    }

    public void format(OutputStream os, Document doc) throws IOException {
        Serializer serializer = new Serializer(os, "UTF-8");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }


}
