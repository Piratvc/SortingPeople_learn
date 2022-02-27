package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        int count = (int) persons.stream()
                .filter(Person -> Person.getAge() < 18)
                .count();

        System.out.println("Несовершеннолетних из этого списка " + count + " человек");

        List<String> soldier = persons.stream()
                .filter(Person -> Person.getAge() >=18 & Person.getAge() <= 27 & Person.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .toList();

        System.out.println("Под призыв подходят фамилии: " + soldier);


        List<Person> workers = persons.stream()
                .filter(Person -> Person.getEducation() == Education.HIGHER)
                .filter(Person -> ((Person.getAge() >= 18 & Person.getAge() <= 65 & Person.getSex() == Sex.MAN)
                        | (Person.getAge() >= 18 & Person.getAge() <= 60 & Person.getSex() == Sex.WOMAN)))
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();

        System.out.println("Рабочее население отсортированное по фамилии:" + workers);
    }
}
