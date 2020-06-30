package com.idotrick.store;

import com.idotrick.store.Input;
import com.idotrick.store.ItemType;
import com.idotrick.store.Location;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    Input obj = Input.getInstance();

    @Test
    void validate() {
    }

    @Test
    void scanAll() {
        String sInput = "BRAZIL:B123AB1234567:IPHONE:20:IPOD:10";
        Input expected = new Input(Location.BRAZIL, "B123AB1234567");
        expected.getItemAndUnitsMap().put(ItemType.IPHONE, 20);
        expected.getItemAndUnitsMap().put(ItemType.IPOD, 10);
        obj.process(sInput);
        assertEquals(expected, obj);
    }

    @Test
    void scanWithoutOptionalValues() {
        String sInput = "BRAZIL:IPHONE:20:IPOD:10";
        Input expected = new Input(Location.BRAZIL, "");
        expected.getItemAndUnitsMap().put(ItemType.IPHONE, 20);
        expected.getItemAndUnitsMap().put(ItemType.IPOD, 10);
        obj.process(sInput);
        assertEquals(expected, obj);
    }
}