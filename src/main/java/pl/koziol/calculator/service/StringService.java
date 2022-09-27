package pl.koziol.calculator.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koziol.calculator.model.ArithmeticOperation;
import pl.koziol.calculator.model.SingleOperation;
import pl.koziol.calculator.model.Unit;
import pl.koziol.calculator.model.ValueAndUnit;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class StringService {
    @Autowired
    OperationOnUnitService operationOnUnitService;
    SingleOperation createSingleOperation(String checkedString, Unit targetUnit){
        ValueAndUnit firstNumber;
        ValueAndUnit secondNumber;
        ArithmeticOperation typeOfOperation;
        typeOfOperation = findTypOfOperation(checkedString);
        String[] stringNumber = checkedString.split("[\\+\\-\\*\\/]");
        firstNumber = convertToTargetUnitValue(stringNumber[0],targetUnit);
        secondNumber = convertToTargetUnitValue(stringNumber[1],targetUnit);
        return new SingleOperation(firstNumber,secondNumber,typeOfOperation);
    }

    String prepareStringToAnalyze(String inputData){
        return inputData.toLowerCase().replace(" ","").replace(",",".");
    }

    boolean checkPreparedString(String prepareInputData){
        String regex = "(\\d+\\.?\\d*)((m||nm||ft)?)([\\+\\-\\*\\/])(\\d+\\.?\\d*)((m||nm||ft)?)";
        return prepareInputData.matches(regex);
    }

    String createResultString(BigDecimal value, Unit unit, int dimension){
        if (dimension==0){
            return String.valueOf(value);
        } else return value +  " " + unit.fullName + "^" + dimension;
    }

    private ArithmeticOperation findTypOfOperation(String checkedString){
        char mathSymbol = checkedString.replaceAll("[^\\+\\-\\*\\/]","").charAt(0);
        return Arrays.stream(ArithmeticOperation.values())
                .filter(enumSymbol -> enumSymbol.charArithmeticOperation==mathSymbol)
                .findFirst().get();
    }

    private ValueAndUnit convertToTargetUnitValue(String distanceValue, Unit targetUnit){
        String regexOnlyNumber = "\\d+\\.?\\d*";
        String regexM = "\\d+\\.?\\d*(m)";
        String regexNM = "\\d+\\.?\\d*(nm)";
        String regexFT = "\\d+\\.?\\d*(ft)";
        BigDecimal notConvertedValue;
        if(distanceValue.matches(regexOnlyNumber)){
            notConvertedValue = new BigDecimal(distanceValue);
            return operationOnUnitService.chooseAndConvertUnit(targetUnit,null,notConvertedValue);
        } else if(distanceValue.matches(regexM)){
            notConvertedValue = new BigDecimal(distanceValue.replace("m",""));
            return operationOnUnitService.chooseAndConvertUnit(targetUnit,Unit.M,notConvertedValue);
        } else if(distanceValue.matches(regexNM)){
            notConvertedValue = new BigDecimal(distanceValue.replace("nm",""));
            return operationOnUnitService.chooseAndConvertUnit(targetUnit,Unit.NM,notConvertedValue);
        } else if(distanceValue.matches(regexFT)){
            notConvertedValue = new BigDecimal(distanceValue.replace("ft",""));
            return operationOnUnitService.chooseAndConvertUnit(targetUnit,Unit.FT,notConvertedValue);
        } else return null;

    }
}
