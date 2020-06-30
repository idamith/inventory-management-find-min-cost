package com.idotrick.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    Store store;

    @BeforeEach
    void init() {
        store = new Store(Location.values());
        store.getInventory(Location.BRAZIL).addToStock(ItemType.IPOD,100, 65);
        store.getInventory(Location.BRAZIL).addToStock(ItemType.IPHONE,100, 100);
        store.getInventory(Location.ARGENTINA).addToStock(ItemType.IPOD,100, 100);
        store.getInventory(Location.ARGENTINA).addToStock(ItemType.IPHONE,50, 150);
    }

    @Test
    void testToString() {
        String expected = "100:100:100:50";
        String actual = store.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testCalcMinSalesPrice_BRAZIL_B123AB1234567_IPHONE_20_IPOD_10(){
        String sInput="BRAZIL:B123AB1234567:IPHONE:20:IPOD:10";
        Input input = Input.getInstance();
        input.process(sInput);
        String expected = "2650:90:100:80:50";
        String actual = store.calcMinSalesPrice(input);
        assertEquals(expected, actual);
    }

    @Test
    void testCalcMinSalesPrice_ARGENTINA_B123AB1234567_IPHONE_22_IPOD_10(){
        
        String sInput="ARGENTINA:B123AB1234567:IPHONE:22:IPOD:10";
        Input input = Input.getInstance();
        input.process(sInput);
        String expected = "3910:90:100:80:48";
        String actual = store.calcMinSalesPrice(input);
        assertEquals(expected, actual);
    }

    @Test
    void testCalcMinSalesPrice_BRAZIL_AAB123456789_IPHONE_125_IPOD_70(){
        
        String sInput="BRAZIL:AAB123456789:IPHONE:125:IPOD:70";
        Input input = Input.getInstance();
        input.process(sInput);
        String expected = "19260:30:100:0:25";
        String actual = store.calcMinSalesPrice(input);
        assertEquals(expected, actual);
    }

    @Test
    void testCalcMinSalesPrice_ARGENTINA_AAB123456789_IPOD_50_IPHONE_25(){
        
        String sInput="ARGENTINA:AAB123456789:IPOD:50:IPHONE:25";
        Input input = Input.getInstance();
        input.process(sInput);
        String expected = "8550:100:50:80:45";
        String actual = store.calcMinSalesPrice(input);
        assertEquals(expected, actual);
    }

    @Test
    void testCalcMinSalesPrice_BRAZIL_IPHONE_50_IPOD_150(){
        
        String sInput="BRAZIL:IPHONE:50:IPOD:150";
        Input input = Input.getInstance();
        input.process(sInput);
        String expected = "18500:0:50:50:50";
        String actual = store.calcMinSalesPrice(input);
        assertEquals(expected, actual);
    }

    @Test
    void testCalcMinSalesPrice_BRAZIL_IPHONE_250_IPOD_150(){
        
        String sInput="BRAZIL:IPHONE:250:IPOD:150";
        Input input = Input.getInstance();
        input.process(sInput);
        String expected = "OUT_OF_STOCK:100:100:100:50";
        String actual = store.calcMinSalesPrice(input);
        assertEquals(expected, actual);
    }
}