package com.idotrick.store;

import com.idotrick.store.validator.InputValidator;

import java.util.*;

public class Input {
    private String[] inputArray;
    private Location location;
    private String passportNumber;
    private Map<ItemType, Integer> itemAndUnitsMap = new HashMap<>();

    private Location locationOfPassport;

    private Input() {
    }

    private static class SingletonHelper {
        private static final Input INSTANCE = new Input();
    }

    public static Input getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private boolean validate() {
        return InputValidator.getInstance().validate(this.inputArray);
    }

    public void scan() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder message = new StringBuilder();
        message.append("---------------------------------------------\n")
                .append("               Store App\n")
                .append("---------------------------------------------\n")
                .append("Place order here and press enter key.\n")
                .append("INPUTFORMAT: (nospace between separators) <purchase_country>:<optional_passport_number>:<item_type>:<number_of_ units_to_be_ordered>:<item_type>:<number_of_units_to_be_ordered>\n")
                .append("ORDER:");

        System.out.print(message);
        String input = scanner.next();
        process(input);
    }

    public void process(String input) {
        splitInput(input);
        if (validate()) {
            buildInput();
        }
    }

    private void buildInput() {
        int inputIndex = 0;
        setLocation(Location.valueOf(inputArray[inputIndex++]));
        if (inputArray.length == 6) {
            setPassportNumber(inputArray[inputIndex++]);
            setLocationOfPassport();
        }

        for (ItemType t : ItemType.values()) {
            itemAndUnitsMap.put(ItemType.valueOf(inputArray[inputIndex++]), Integer.parseInt(inputArray[inputIndex++]));
        }
    }

    private void splitInput(String input) {
        inputArray = input.split(":");
    }


    public Input(Location location, String passportNumber) {
        this.location = location;
        this.passportNumber = null!= passportNumber && passportNumber.isBlank() ? null : passportNumber;
        setLocationOfPassport();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Location getLocationOfPassport() {
        return locationOfPassport;
    }

    public void setLocationOfPassport() {
        if (null != passportNumber && !passportNumber.isEmpty() && !passportNumber.isBlank()) {
            for (Location location : Location.values()) {
                if (location.toString().charAt(0) == passportNumber.charAt(0)) {
                    locationOfPassport = location;
                    break;
                }
            }
        }
    }

    public Map<ItemType, Integer> getItemAndUnitsMap() {
        return itemAndUnitsMap;
    }

    public void setItemAndUnitsMap(Map<ItemType, Integer> itemAndUnitsMap) {
        this.itemAndUnitsMap = itemAndUnitsMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Input)) return false;
        Input input = (Input) o;
        return getLocation() == input.getLocation() &&
                Objects.equals(getPassportNumber(), input.getPassportNumber()) &&
                getItemAndUnitsMap().equals(input.getItemAndUnitsMap()) &&
                getLocationOfPassport() == input.getLocationOfPassport();
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getLocation(), getPassportNumber(), getItemAndUnitsMap(), getLocationOfPassport());
        result = 31 * result + Arrays.hashCode(inputArray);
        return result;
    }
}


