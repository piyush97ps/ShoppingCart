package com.Piyush;

/**
 * Author Piyush Sharma Git-piyush97sh
 */
public class InventoryItem implements Comparable<InventoryItem> {
    private final String name;
    private double price;
    private int quantityStock;

    public InventoryItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;
    }

    public InventoryItem(String name, double price, int quantityStock) {
        this.name = name.toUpperCase();
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int quantityInStock() {
        return quantityStock;
    }

    public void setPrice(double price) {
        if(price > 0.0) {
            this.price = price;
        }
    }

    public boolean adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if(newQuantity >=0) {
            this.quantityStock = newQuantity;
            return true;
        }else{
            System.out.println("The Quantity is invalid");
            return false;
        }
    }

    @Override
    public int compareTo(InventoryItem o) {
        System.out.println("Entering InventoryItem.compareTo");
        if(this == o) {
            return 0;
        }

        if(o != null) {
            return this.name.compareTo(o.getName());
        }

        System.out.println("You Entered A Null Value :");
        throw new NullPointerException("You Entered NuLL Value To Compare");
    }

    @Override
    public String toString() {
        return this.name + " : price " + this.price;
    }
}
