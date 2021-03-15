package pl.machnikovsky.tddapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fire {

    @Id
    private int id;

    private String city;
    private int dangerLevel;
    private boolean isActive;
    private int firefightersRequired;

    public Fire(int id, String city, int dangerLevel, boolean isActive, int firefightersRequired) {
        this.id = id;
        this.city = city;
        this.dangerLevel = dangerLevel;
        this.isActive = isActive;
        this.firefightersRequired = firefightersRequired;
    }

    public Fire() {
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

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getFirefightersRequired() {
        return firefightersRequired;
    }

    public void setFirefightersRequired(int firefightersRequired) {
        this.firefightersRequired = firefightersRequired;
    }
}
