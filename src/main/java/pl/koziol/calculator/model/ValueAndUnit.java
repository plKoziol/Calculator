package pl.koziol.calculator.model;

import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;


@Getter
public class ValueAndUnit {

    private BigDecimal value;
    private Unit unit;

    public ValueAndUnit(@NonNull BigDecimal value, @Nullable Unit unit) {
        this.value = value;
        this.unit = unit;
    }
}
