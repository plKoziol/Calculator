package pl.koziol.calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.koziol.calculator.model.ArithmeticOperation;
import pl.koziol.calculator.model.Unit;
import pl.koziol.calculator.model.ValueAndUnit;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OperationOnUnitService {
    @Autowired
    ConversionTypeService conversionTypeService;
    Optional<Integer> calculateDimension(Unit firstUnit, Unit secondUnit, ArithmeticOperation arithmeticOperation){
        switch (arithmeticOperation){
            case MULTIPLY:{
                if(firstUnit==null&&secondUnit==null){
                    return Optional.of(0);
                } else if(firstUnit!=null&&secondUnit!=null){
                    return Optional.of(2);
                } else return Optional.of(1);
            }
            case DIVIDE:{
                if(firstUnit==null&&secondUnit==null){
                    return Optional.of(0);
                } else if(firstUnit!=null&&secondUnit!=null){
                    return Optional.of(0);
                } else if(firstUnit!=null&&secondUnit==null){
                    return Optional.of(1);
                } else return Optional.empty();
            }
            default:{
                if(firstUnit==null&&secondUnit==null){
                    return Optional.of(0);
                } else if(firstUnit!=null&&secondUnit!=null){
                    return Optional.of(1);
                } else return Optional.empty();
            }
        }

    }

    ValueAndUnit chooseAndConvertUnit(@NonNull Unit targetUnit, Unit unitUsed, BigDecimal value) {
        if (unitUsed == null) {
            return new ValueAndUnit(value,null);
        } else if (targetUnit.equals(Unit.M)) {
            switch (unitUsed) {
                case M:
                    return new ValueAndUnit(value,Unit.M);
                case FT:
                    return new ValueAndUnit(conversionTypeService.convertFTToM(value),Unit.M);
                case NM:
                    return new ValueAndUnit(conversionTypeService.convertNMToM(value),Unit.M);
            }
        } else if (targetUnit.equals(Unit.FT)) {
            switch (unitUsed) {
                case M:
                    return new ValueAndUnit(conversionTypeService.convertMToFT(value),Unit.FT);
                case FT:
                    return new ValueAndUnit(value,Unit.FT);
                case NM:
                    return new ValueAndUnit(conversionTypeService.convertNMToFT(value),Unit.FT);
            }
        } else {
            switch (unitUsed) {
                case M:
                    return new ValueAndUnit(conversionTypeService.convertMToNM(value),Unit.NM);
                case FT:
                    return new ValueAndUnit(conversionTypeService.convertFTToNM(value),Unit.NM);
                case NM:
                    return new ValueAndUnit(value,Unit.NM);
            }
        }
        return null;
    }



}
