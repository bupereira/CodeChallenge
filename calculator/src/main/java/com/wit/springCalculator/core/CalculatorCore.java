package com.wit.springCalculator.core;

import com.wit.springCalculator.core.exception.MathematicalException;
import com.wit.springCalculator.operations.MathematicalOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

@Component
public class CalculatorCore {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorCore.class);

    /**
     * run - The core functionality. Is executed from its overload which receives a string, derives the operation and processes the calculation
     * @param x The first number to be processed
     * @param y The second number to be processed
     * @param mathematicalOperation a supported operation (in the operations package. All operations implement MathematicalOperation)
     * @return
     */
    public String run(BigDecimal x, BigDecimal y, String mathematicalOperation) {

        try {
            final MathematicalOperation operation = getInstance(mathematicalOperation);
            return operation.execute(x, y).toString();
        } catch (ClassNotFoundException e) {
            logAndThrow("Error finding operation: " + mathematicalOperation);
        } catch (InstantiationException e) {
            logAndThrow("Error instantiating the operation class for the " + mathematicalOperation +
                    " operation.");
        } catch (IllegalAccessException e) {
            logAndThrow("Error when accessing the operation class for " + mathematicalOperation);
        } catch (InvocationTargetException e) {
            logAndThrow("Error invoking constructor for the operation " + mathematicalOperation);
        }

        return "End";
    }

    /**
     * getInstance - Gets an instance of the chosen MathematicalOperation
     * @param mathematicalOperation the Mathematical Operation. Will look into the origin path constant defined below
     * @param <T> Whatever type it is, though currently it's only used for MathematicalOperation implementations.
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private <T> T getInstance(String mathematicalOperation) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        final String originPath = "com.wit.springCalculator.operations.";
        Class<?> operationClass = Class.forName(originPath +
                mathematicalOperation);
        return (T) operationClass.getConstructors()[0].newInstance();
    }

    /**
     *
     * Run is an overload of the run method that opens the passed in string and runs the typed parameter run method
     *
     * @param wholeMessage
     * @return The response string.
     */
    public String run(String wholeMessage) {
        BigDecimal x = null, y = null;
        String[] splitMsg = wholeMessage.split(",");
        if(splitMsg.length != 3) {
            logAndThrow("Wrong number of parameters supplied. Acceptable Parameters are (Number) x, (Number) y, (String) operation");
        }
        try {
            x = new BigDecimal(splitMsg[0]);
            y = new BigDecimal(splitMsg[1]);
        } catch(NumberFormatException e) {
            logAndThrow("One of the supplied arguments is not a number. Please check and resubmit.");
        }

        String mathematicalOperation = splitMsg[2];
        return run(x, y, mathematicalOperation);
    }

    public void logAndThrow(String errorMessage) {
        logger.error(errorMessage);
        throw new MathematicalException(errorMessage);
    }
}
