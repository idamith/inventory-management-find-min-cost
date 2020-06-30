package com.idotrick.store;

public class OutOfStockException extends Exception {
    @Override
    public String getMessage() {
        return Constants.OUT_OF_STOCK_MSG;
    }
}
