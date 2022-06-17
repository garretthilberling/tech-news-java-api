package com.technews.technewsjavaapi.testModel;

import java.util.Objects;

public class Demo {
    private String name;
    private int age;

    public Demo(String name, int age) {
        this.name = name;
        this.age = age;
    }
    // We need to specify the type that will be output from the method (String)
    public String getName() {
        return name;
    }
    // We use void when a method has no output
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demo demo = (Demo) o;
        return age == demo.age &&
                Objects.equals(name, demo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
