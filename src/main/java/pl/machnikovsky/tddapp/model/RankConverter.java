package pl.machnikovsky.tddapp.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class RankConverter implements AttributeConverter<Rank, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Rank rank) {
        if (rank == null) {
            return null;
        }
        return rank.getRankLevel();
    }

    @Override
    public Rank convertToEntityAttribute(Integer rankLevel) {
        if (rankLevel == null) {
            return null;
        }

        return Stream.of(Rank.values())
                .filter(c -> c.getRankLevel() == rankLevel)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
