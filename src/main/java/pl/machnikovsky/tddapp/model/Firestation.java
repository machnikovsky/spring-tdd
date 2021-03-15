package pl.machnikovsky.tddapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Firestation {

    @Id
    private int id;

    private String city;
    private int firefightersNumber;
    private List<Firefighter> firefighters;

    public Firestation(int id, String city, int firefightersNumber, List<Firefighter> firefighters) {
        this.id = id;
        this.city = city;
        this.firefightersNumber = firefightersNumber;
        this.firefighters = firefighters;
    }

    public Firestation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getFirefightersNumber() {
        return firefightersNumber;
    }

    public void setFirefightersNumber(int firefightersNumber) {
        this.firefightersNumber = firefightersNumber;
    }

    public List<Firefighter> getFirefighters() {
        return firefighters;
    }

    public void setFirefighters(List<Firefighter> firefighters) {
        this.firefighters = firefighters;
    }
}
