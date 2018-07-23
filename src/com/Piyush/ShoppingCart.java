package com.Piyush;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Author Piyush Sharma Git-piyush97sh
 */
public class ShoppingCart {
    private final String name;
    private final Map<InventoryItem, Integer> list;

    public ShoppingCart(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

    public int addToShoppingCart(InventoryItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inCart = list.getOrDefault(item, 0);
            list.put(item, inCart + quantity);
            return inCart;
        }
        return 0;
    }
    public InventoryItem getItem(String name){
        for(Map.Entry<InventoryItem,Integer> item: list.entrySet()){
            if(item.getKey().getName().equals(name.toUpperCase())){
                return item.getKey();
            }
        }
        return null;
    }

    public int getQuantity(InventoryItem item){
        if(item != null){
            return list.get(item);
        }
        return 0;
    }

    public boolean removeItem(String item){
        if(list.containsKey(item)){
           list.remove(item);
           return true;
        }
        return false;
    }

    // To directly print the List by just passing the Inventory list object the print function
    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + list.size() + ((list.size() == 1) ? " item" : " items") + "\n";
        double totalCost = 0.0;
        for (Map.Entry<InventoryItem, Integer> item : list.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " purchased\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }
}
