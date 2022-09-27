package pl.koziol.calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koziol.calculator.model.*;
import pl.koziol.calculator.repository.MathOperationRepository;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;


@Service
public class BasicArithmeticOperationService {

    @Autowired
    MathOperationRepository mathOperationRepository;

    @Autowired
    OperationOnUnitService operationOnUnitService;

    @Autowired
    StringService stringService;

    public String performCalculation(String inputData, Unit targetUnit){
        String prepareInputData = stringService.prepareStringToAnalyze(inputData);
        if(!stringService.checkPreparedString(prepareInputData)){
            return null;
        } else if(existElementInRepo(prepareInputData,targetUnit)){
            return stringService.createResultString(
                    mathOperationRepository.findById(prepareInputData).get().getOperationResult()
                    ,targetUnit
                    ,mathOperationRepository.findById(prepareInputData).get().getUnitDimension()
            );
        } else {
            return createResultAndSaveInRepo(prepareInputData, targetUnit);
        }
    }

    private String createResultAndSaveInRepo(String prepareInputData, Unit targetUnit){
        SingleOperation singleOperation = stringService.createSingleOperation(prepareInputData, targetUnit);
        BigDecimal result = calculate(singleOperation);
        Integer dimension = calculateDimension(singleOperation);
        if(dimension!=null){
            mathOperationRepository.save(new MathOperation(prepareInputData,result,targetUnit,dimension));
            return stringService.createResultString(result,targetUnit,dimension);
        } else return null;
    }

    private boolean existElementInRepo(String prepareInputData, Unit targetUnit){
        if(mathOperationRepository.existsById(prepareInputData)){
            return mathOperationRepository.findById(prepareInputData).get().getTargetUnit().equals(targetUnit);
        }
        return false;
    }

    private Integer calculateDimension(SingleOperation singleOperation){
        Optional<Integer> optionalDimension = operationOnUnitService.calculateDimension(singleOperation.getFirstNumber().getUnit()
                        , singleOperation.getSecondNumber().getUnit()
                        , singleOperation.getTypeOfOperation());
        if(optionalDimension.isEmpty()){
            return null;
        } else  return optionalDimension.get();
    }

    private BigDecimal calculate(SingleOperation singleOperation){
        switch (singleOperation.getTypeOfOperation()){
            case ADD:
                return singleOperation.getFirstNumber().getValue().add(singleOperation.getSecondNumber().getValue());
            case SUBTRACT:
                return singleOperation.getFirstNumber().getValue().subtract(singleOperation.getSecondNumber().getValue());
            case  DIVIDE:
                return singleOperation.getFirstNumber().getValue().divide(singleOperation.getSecondNumber().getValue(), MathContext.DECIMAL32);
            case MULTIPLY:
                return singleOperation.getFirstNumber().getValue().multiply(singleOperation.getSecondNumber().getValue());
            default:
                System.out.println("Unsupported type of arithmetic operations");
                return null;
        }
    }
}
