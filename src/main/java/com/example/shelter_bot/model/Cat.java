package com.example.shelter_bot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Class of cats.
 * @author aser161
 * @version 1.0.0
 */
@Entity
public class Cat {

    /** "ID" field */
    @Id
    @GeneratedValue
    private Long id;

    /** "Breed" field */
    private String breed;

    /** "Name" field */
    private String name;

    /** "Year Of Birth" field */
    private int yearOfBirth;

    /** "Description" field */
    private String description;

    /**
     * Constructor - creating a new object.
     * @see Cat
     */
    public Cat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return id == cat.id && yearOfBirth == cat.yearOfBirth && Objects.equals(breed, cat.breed) && Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breed, name, yearOfBirth);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", description='" + description + '\'' +
                '}';
    }
}

