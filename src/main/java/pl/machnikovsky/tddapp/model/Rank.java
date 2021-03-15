package pl.machnikovsky.tddapp.model;

import java.util.stream.Stream;

public enum Rank {
    VOLUNTEER(1),
    EMT(2),
    PARAMEDIC(3),
    DRIVER(4),
    LIEUTENANT(5),
    CAPTAIN(6),
    CHIEF(7);

    private int rankLevel;

    Rank(int rankLevel) {
        this.rankLevel = rankLevel;
    }

    public Rank getRankByRankLevel(int rankLevel) {
        return Stream.of(Rank.values()).filter(rank -> rank.rankLevel == rankLevel).findFirst().orElse(null);
    }

    public int getRankLevel() {
        return rankLevel;
    }

    public void setRankLevel(int rankLevel) {
        this.rankLevel = rankLevel;
    }
}
