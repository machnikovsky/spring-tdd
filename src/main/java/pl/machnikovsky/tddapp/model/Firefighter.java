package pl.machnikovsky.tddapp.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.stream.Stream;

@Entity
public class Firefighter {

    @Id
    private int id;

    private String name;
    private String lastname;
    private Rank rank;
    private int points;


    public Firefighter(int id, String name, String lastname, Rank rank, int points) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.rank = rank;
        this.points = points;
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

    public Rank getRank() {
        return rank;
    }

    public Rank getRankByRankLevel(int rankLevel) {
        return rank.getRankByRankLevel(rankLevel);
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
