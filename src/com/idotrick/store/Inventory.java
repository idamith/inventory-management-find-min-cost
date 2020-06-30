package com.idotrick.store;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Location location;
    private Map<ItemType, Stock> stocks;

    public Inventory(Location location) {
        this.location = location;
        this.stocks = new HashMap<>();
    }

    public class Stock {
        private ItemType itemType;
        private int itemCount;
        private double itemPrice;

        public Stock(ItemType itemType, int itemCount, double itemPrice) {
            this.itemType = itemType;
            this.itemCount = itemCount;
            this.itemPrice = itemPrice;
        }

        public Inventory add() {
            stocks.put(this.itemType, this);
            return Inventory.this;
        }

        public ItemType getItemType() {
            return itemType;
        }

        public void setItemType(ItemType itemType) {
            this.itemType = itemType;
        }

        public int getItemCount() {
            return itemCount;
        }

        public void setItemCount(int itemCount) {
            this.itemCount = itemCount;
        }

        public double getItemPrice() {
            return itemPrice;
        }

        public void setItemPrice(double itemPrice) {
            this.itemPrice = itemPrice;
        }

        public boolean isStockAvailable(int itemCount){
            return getItemCount()>=itemCount;
        }

        @Override
        public String toString() {
            return itemType+":"+itemCount+":"+itemPrice;
        }

    }

    public Stock getStock(ItemType itemType) {
        return getStocks().get(itemType);
    }

    public synchronized void addToStock(ItemType itemType, int itemCount, double itemPrice) {
        Stock stock = getStock(itemType);

        if(stock == null) {
            new Stock(itemType, itemCount, itemPrice).add();
        } else {
            stock.setItemCount(stock.getItemCount()+itemCount);
        }
    }

    public synchronized void removeFromStock(ItemType itemType, int itemCount) throws OutOfStockException {
        Stock stock = getStock(itemType);

        if(stock.isStockAvailable(itemCount)) {
            stock.setItemCount(stock.getItemCount()-itemCount);
        } else {
            new OutOfStockException();
        }
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryLocation=" + location +
                ", stocks=" + stocks +
                '}';
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Map<ItemType, Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Map<ItemType, Stock> stocks) {
        this.stocks = stocks;
    }
}
