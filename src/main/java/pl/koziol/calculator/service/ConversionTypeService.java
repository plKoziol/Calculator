package pl.koziol.calculator.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ConversionTypeService {

    BigDecimal convertMToFT(BigDecimal valueInM){
        return valueInM.multiply(BigDecimal.valueOf(3.2808399));
    }

    BigDecimal convertMToNM(BigDecimal valueInM){
        return valueInM.multiply(BigDecimal.valueOf(0.000539956803));
    }

    BigDecimal convertNMToFT(BigDecimal valueInM){
        return valueInM.multiply(BigDecimal.valueOf(6076.11549));
    }

    BigDecimal convertNMToM(BigDecimal valueInM){
        return valueInM.multiply(BigDecimal.valueOf(1852));
    }

    BigDecimal convertFTToM(BigDecimal valueInM){
        return valueInM.multiply(BigDecimal.valueOf(0.3048));
    }

    BigDecimal convertFTToNM(BigDecimal valueInM){
        return valueInM.multiply(BigDecimal.valueOf(0.00016457883));
    }

}
