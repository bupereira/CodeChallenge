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
     * @param a The first number to be processed
     * @param b The second number to be processed
     * @param mathematicalOperation a supported operation (in the operations package. All operations implement MathematicalOperation)
     * @return A String with the result
     */
    public String run(BigDecimal a, BigDecimal b, String mathematicalOperation) {

        try {
            final MathematicalOperation operation = getInstance(mathematicalOperation);
            return operation.execute(a, b).toString();
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
     * @return Whatever Class type was inferred from the passed in class
     * @throws ClassNotFoundException If class does not exist
     * @throws InstantiationException If can't be instantiated
     * @throws IllegalAccessException If executor gets its access denied
     * @throws InvocationTargetException if a method or constructor throws an error
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
     * @param wholeMessage The message string
     * @return The response string.
     */
    public String run(String wholeMessage) {
        BigDecimal a = null, b = null;
        String[] splitMsg = wholeMessage.split(",");
        if(splitMsg.length != 3) {
            logAndThrow("Wrong number of parameters supplied. Acceptable Parameters are (Number) a, (Number) b, (String) operation");
        }
        try {
            a = new BigDecimal(splitMsg[0]);
            b = new BigDecimal(splitMsg[1]);
        } catch(NumberFormatException e) {
            logAndThrow("One of the supplied arguments is not a number. Please check and resubmit.");
        }

        String mathematicalOperation = splitMsg[2];
        return run(a, b, mathematicalOperation);
    }

    public void logAndThrow(String errorMessage) {
        logger.error(errorMessage);
        throw new MathematicalException(errorMessage);
    }
}
