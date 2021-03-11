package pl.machnikovsky.tddapp.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class Firefighter {

    @Id
    private int id;

    private String name;
    private String lastname;
    private String rank;

    public Firefighter(int id, String name, String lastname, String rank) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.rank = rank;
    }

    public Firefighter() {
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
