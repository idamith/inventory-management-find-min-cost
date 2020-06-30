package com.idotrick.store.validator;

import com.idotrick.store.Location;

import java.util.logging.Logger;

public class InputValidator implements IValidator {

    private InputValidator() {
    }

    private static class SingletonHelper {
        private static final InputValidator INSTANCE = new InputValidator();
    }

    public static InputValidator getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public boolean validatePurchaseCountry(String input) {
        try {
            Location.valueOf(input);
        } catch (Exception e) {
            Logger.getGlobal().info("Invalid Purchase Country:" + input);
            return false;
        }

        return true;
    }

    public boolean validatePassportNumber(String input) {
        //TODO
        return true;
    }

    public boolean validateItemType1(String input) {
        //TODO
        return true;
    }


    private boolean validateItemUnits2(String s) {
        //TODO
        return true;
    }

    private boolean validateItemType2(String s) {
        //TODO
        return true;
    }

    private boolean validateItemUtnits1(String s) {
        //TODO
        return true;
    }

    @Override
    public boolean validate(String[] input) {
        int inputIndex = 0;

        return validatePurchaseCountry(input[inputIndex++])
                && input.length==6 ? validatePassportNumber(input[inputIndex++]) : true
                && validateItemType1(input[inputIndex++])
                && validateItemUtnits1(input[inputIndex++])
                && validateItemType2(input[inputIndex++])
                && validateItemUnits2(input[inputIndex++]);
    }
}
