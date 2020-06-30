package com.idotrick.store;

import com.idotrick.store.Input;
import com.idotrick.store.Location;
import com.idotrick.store.ItemType;
import com.idotrick.store.Store;

public class StoreDemo {
    public static void main(String[] args) {
        Store store = initStore();
        Input input = Input.getInstance();
        input.scan();
        System.out.println("PRICE:"+store.calcMinSalesPrice(input));
    }

    private static Store initStore() {
        Store store = new Store(Location.values());
        store.addToStock(Location.BRAZIL, ItemType.IPOD,100, 65);
        store.addToStock(Location.BRAZIL, ItemType.IPHONE,100, 100);
        store.addToStock(Location.ARGENTINA, ItemType.IPOD,100, 100);
        store.addToStock(Location.ARGENTINA, ItemType.IPHONE,50, 150);
        return store;
    }


}
