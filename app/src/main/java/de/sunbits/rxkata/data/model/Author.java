package de.sunbits.rxkata.data.model;

/**
 * Created by matkoch on 26/02/16.
 */
public class Author {

    private int id;
    private String name;
    private int age;

    public int getId() {

        return id;
    }

    public void setId(final int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public int getAge() {

        return age;
    }

    public void setAge(final int age) {

        this.age = age;
    }

    @Override
    public String toString() {

        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
