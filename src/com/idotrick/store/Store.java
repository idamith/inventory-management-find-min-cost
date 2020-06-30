package com.idotrick.store;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<Location, Inventory> inventories;

    private class InventoryAndPrice {
        private Inventory inventory;
        private Double price;

        public InventoryAndPrice(Inventory inventory, Double price) {
            this.inventory = inventory;
            this.price = price;
        }

        public Inventory getInventory() {
            return inventory;
        }

        public Double getPrice() {
            return price;
        }
    }

    public Store(Location... locations) {
        inventories = new HashMap<>(locations.length);

        for (Location location : locations) {
            inventories.put(location, new Inventory(location));
        }
    }

    public Inventory getInventory(Location location) {
        return inventories.get(location);
    }

    public Store addToStock(Location location, ItemType itemType, int itemCount, double itemPrice) {
        getInventory(location).addToStock(itemType, itemCount, itemPrice);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ItemType itemType : ItemType.values()) {
            for (Location location : Location.values()) {
                if (sb.length() != 0) {
                    sb.append(":");
                }

                sb.append(inventories.get(location).getStocks().get(itemType).getItemCount());
            }
        }
        return sb.toString();
    }

    public String calcMinSalesPrice(Input input) {
        String minSalePrice;
        if (isStockAvailable(input)) {
            double totalPrice = 0;
            for(ItemType itemType: ItemType.values()) {
                totalPrice += calcMinSalesPrice(itemType, input.getItemAndUnitsMap().get(itemType), input.getLocation(), input.getLocationOfPassport());
            }
            minSalePrice = Integer.toString((int) totalPrice);
        } else {
            minSalePrice = "OUT_OF_STOCK";
        }

        return minSalePrice+":"+toString();
    }

    private double calcMinSalesPrice(ItemType itemType,
                                     int itemCount,
                                     Location purchaseLocation,
                                     Location locationOfPassport) {
        int fullPackagesCount = itemCount / Constants.PACKAGE_SIZE;
        int halfPackageItemCount = itemCount % Constants.PACKAGE_SIZE;
        double totalPrice = 0;

        while (fullPackagesCount > 0) {
            totalPrice = calcMinTotalPriceAndDeductFromInventory(itemType, Constants.PACKAGE_SIZE, purchaseLocation, locationOfPassport, totalPrice);
            --fullPackagesCount;
        }

        return calcMinTotalPriceAndDeductFromInventory(itemType, halfPackageItemCount, purchaseLocation, locationOfPassport, totalPrice);
    }

    private double calcMinTotalPriceAndDeductFromInventory(ItemType itemType,
                                                           int itemCount,
                                                           Location purchaseLocation,
                                                           Location locationOfPassport,
                                                           double totalPrice) {
        if (itemCount <= 0) {
            return totalPrice;
        }

        InventoryAndPrice bestPriceInventoryAndPrice = getBestPriceInventory(itemType, itemCount, purchaseLocation, locationOfPassport);
        try {
            bestPriceInventoryAndPrice.getInventory().removeFromStock(itemType, itemCount);
        } catch (OutOfStockException e) {
            e.printStackTrace();
        }
        return totalPrice + bestPriceInventoryAndPrice.getPrice();
    }

    private InventoryAndPrice getBestPriceInventory(ItemType itemType,
                                                    int noOfItems,
                                                    Location purchaseLocation,
                                                    Location locationOfPassport) {
        if (noOfItems <= 0) {
            return new InventoryAndPrice(null, 0d);
        }

        Inventory bestPriceInventory = null;
        double bestPrice = 0, price = 0;

        for (Inventory inventory : this.inventories.values()) {
            Inventory.Stock stock = inventory.getStock(itemType);
            if (stock.getItemCount() >= noOfItems) {
                price = stock.getItemPrice() * noOfItems;

                if (purchaseLocation != inventory.getLocation()) {
                    // Shipping required
                    double shippingCost = Constants.SHIPPING_COST;
                    if (locationOfPassport == inventory.getLocation()) {
                        // Discounted shipping
                        shippingCost *= 1 - Constants.SHIPPING_DISCOUNT_RATE;
                    }
                    price += shippingCost;
                }
            }

            if (bestPrice == 0 || bestPrice > price) {
                bestPrice = price;
                bestPriceInventory = inventory;
            }
        }

        return new InventoryAndPrice(bestPriceInventory, bestPrice);
    }


    private boolean isStockAvailable(Input input) {
        boolean isStockAvailable = true;

        for(ItemType itemType: ItemType.values()) {
            isStockAvailable = false;
            int itemCount = input.getItemAndUnitsMap().get(itemType);
            int itemCountBalance = itemCount;

            for (Location location : Location.values()) {
                Inventory.Stock stock = this.getInventory(location).getStock(itemType);
                itemCountBalance -= stock.getItemCount();

                if (itemCountBalance <= 0) {
                    isStockAvailable = true;
                    break;
                }
            }
        }
        return isStockAvailable;
    }
}
