package com.arthur.chapter1;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MySerializable implements Serializable{

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Molly the car", house));

        System.out.println(animals);

        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals);

        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);

        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));

        List<Animal> animals1 = (List<Animal>) in1.readObject();
        List<Animal> animals2 = (List<Animal>) in1.readObject();
        List<Animal> animals3 = (List<Animal>) in2.readObject();

        System.out.println("animals1 : " + animals1);
        System.out.println("animals2 : " + animals2);
        System.out.println("animals3 : " + animals3);

    }

    class House implements Serializable {}

    class Animal implements Serializable {
        private String name;
        private House house;

        public Animal(String name, House house) {
            this.name = name;
            this.house = house;
        }

        @Override
        public String toString() {
            return "Animal{" +
                    "name='" + name + '\'' +
                    ", house=" + house +
                    '}';
        }
    }
}
