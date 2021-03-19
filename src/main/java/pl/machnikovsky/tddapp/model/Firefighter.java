package pl.machnikovsky.tddapp.model;

import javax.persistence.*;

@Entity
public class Firefighter {

    @Id
    private int id;
    private String name;
    private String lastname;
    private Rank rank;
    private int points;
    @ManyToOne
    @JoinColumn(name = "firestation_id")
    private Firestation firestation;


    public Firefighter(int id, String name, String lastname, Rank rank, int points, Firestation firestation) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.rank = rank;
        this.points = points;
        this.firestation = firestation;
    }

    public Firefighter() {
    }

    @PreRemove
    public void removeFromFirestation() {
        if (this.firestation != null) {
            this.firestation.removeFirefighter(this);
        }
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
        return rank.getRankwiByRankLevel(rankLevel);
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

    public Firestation getFirestation() {
        return firestation;
    }

    public void setFirestation(Firestation firestation) {
        this.firestation = firestation;
    }
}
