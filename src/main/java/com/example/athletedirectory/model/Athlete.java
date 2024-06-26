package com.example.athletedirectory.model;

import javax.persistence.*;
import com.example.athletedirectory.model.Country;

@Entity
@Table(name = "athlete")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sport")
    private String sport;

    @ManyToOne
    @JoinColumn(name = "countryid")
    private Country country;

    public Athlete() {
    }

    public Athlete(int id, String name, String sport, Country country) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}