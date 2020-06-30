package com.idotrick.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    Inventory inventory;

    @BeforeEach
    void init() {
        inventory = new Inventory(Location.BRAZIL);
    }

    @Test
    void addToStockEmpty() {
        inventory.addToStock(ItemType.IPHONE, 100, 65);
        Inventory.Stock stock = inventory.getStock(ItemType.IPHONE);
        String actual = stock.toString();
        String expected = ItemType.IPHONE+":100:65.0";
        assertEquals(expected, actual);
    }

    @Test
    void addToStockExistingStock() {
        addToStockEmpty();
        inventory.addToStock(ItemType.IPHONE, 100, 55);
        Inventory.Stock stock = inventory.getStock(ItemType.IPHONE);
        String actual = stock.toString();
        String expected = ItemType.IPHONE+":200:65.0";
        assertEquals(expected, actual);
    }

    @Test
    void removeFromStockOutOfStock() throws OutOfStockException {
        addToStockEmpty();
        inventory.removeFromStock(ItemType.IPHONE,200);
        Inventory.Stock stock = inventory.getStock(ItemType.IPHONE);
        String actual = stock.toString();
        String expected = ItemType.IPHONE+":100:65.0";
        assertEquals(expected, actual);
    }

    @Test
    void removeFromStockGotEnoughStock() throws OutOfStockException {
        addToStockExistingStock();
        inventory.removeFromStock(ItemType.IPHONE,200);
        Inventory.Stock stock = inventory.getStock(ItemType.IPHONE);
        String actual = stock.toString();
        String expected = ItemType.IPHONE+":0:65.0";
        assertEquals(expected, actual);
    }
}