package com.Piyush;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author Piyush Sharma Git-piyush97sh
 */
public class InventoryList {
    private final Map<String, InventoryItem> list;

    public InventoryList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(InventoryItem item) {
        if(item != null) {
            // check if the Inventory already have quantities of this item
            InventoryItem inStock = list.getOrDefault(item.getName(), item);
            // If there are already stocks on this item, adjust the quantity
            if(inStock != item) {
                item.adjustStock(inStock.quantityInStock());
            }

            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }

    public int sellStock(String item, int quantity) {
        // Checking if the List or Inventory contains the item if yes then return the item else null
        InventoryItem inStock = list.getOrDefault(item, null);

        if((inStock != null) && (inStock.quantityInStock() >= quantity) && (quantity >0)) {
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    public InventoryItem get(String key) {
        return list.get(key);
    }

    // it is use to prevent the item to be modified or restrict the item from modification
    public Map<String, Double> PriceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for(Map.Entry<String, InventoryItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    // To directly print the List by just passing the Inventory list object the print function
    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, InventoryItem> item : list.entrySet()) {
            InventoryItem inventoryItem = item.getValue();

            double itemValue = inventoryItem.getPrice() * inventoryItem.quantityInStock();

            s = s + inventoryItem + ". There are " + inventoryItem.quantityInStock() + " in stock. Value of items: ";
            s = s + String.format("%.2f",itemValue) + "\n";
            totalCost += itemValue;
        }

        return s + "Total stock value " + String.format("%.4f",totalCost);
    }
}
