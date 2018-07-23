package com.Piyush;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import java.util.Scanner;

/**
 * Author Piyush Sharma Git-piyush97sh
 */

public class Main {
    private static Scanner s;

    public static void main(String[] args) {
        s = new Scanner(System.in);
        InventoryList MyCart = new InventoryList();
        while (true) {
            try {
                System.out.println("\n-----Welcome To My Cart Grocery Store-----\n");
                System.out.println("Select Your Process\n" +
                        "1. I'm Customer Here \n" +
                        "2. I'm the Owner of the store \n" +
                        "0. where;s the Exit !! \n");
                int num = s.nextInt();
                if (num == 1) {
                    if (MyCart.PriceList().isEmpty()) {
                        System.out.println("The Store is Empty \n" +
                                "Ask the Owner about the Goods");
                        continue;
                    }
                }
                switch (num) {
                    case 1:
                        customerProcess(MyCart);
                        break;
                    case 2:
                        ownerProcess(MyCart);
                        break;
                    case 0:
                        System.out.println("Thanks For Visiting :)");
                        return;
                    default:
                        System.out.println("You Entered a invalid option");
                }
            }catch (Exception e){
                System.out.println("Some Error has Occur !!, Please Try Again");
            }
        }
    }

    private static void ownerProcess(InventoryList myCart) {
        String name = null;
        double price = 0.0;
        int quantity = 0;
        System.out.println("\n --- Welcome back Sir how can i help you with --- " +
                "\nyou can follow the following Instructions \n");
        while (true) {
            try {
                System.out.println("1. See the Available Stock in Inventory List\n" +
                        "2. Add Goods in the Inventory List \n" +
                        "3. Edit price of an item in Inventory List \n" +
                        "4. Edit the Quantity of an item in Inventory List \n" +
                        "0. Finished My work \n");
                int num = s.nextInt();
                if (num != 2 && num != 0) {
                    if (myCart.PriceList().isEmpty()) {
                        System.out.println("You hasn't added any Item or Goods in the List\n");
                        continue;
                    }
                }
                switch (num) {
                    case 1:
                        System.out.println(myCart.PriceList());
                        break;
                    case 2:
                        System.out.println("Enter the Name of the Item ");
                        s.nextLine();
                        name = s.nextLine();
                        System.out.println("Enter the Price of the item ");
                        price = s.nextDouble();
                        System.out.println("Enter the quantity of item if you have else input 0");
                        quantity = s.nextInt();
                        InventoryItem item = new InventoryItem(name, price, quantity);
                        myCart.addStock(item);
                        System.out.println(myCart);
                        break;
                    case 3:
                        System.out.println("\n----Select the item from the list----\n");
                        System.out.println(myCart);
                        System.out.println("Enter the Item Name");
                        name = s.nextLine();
                        System.out.println("Enter the item's " + name + " new price");
                        price = s.nextDouble();
                        myCart.get(name).setPrice(price);
                        System.out.println(myCart);
                        break;
                    case 4:
                        System.out.println("\n----Select the item from the list----\n");
                        System.out.println(myCart);
                        System.out.println("Enter the Item Name");
                        name = s.nextLine();
                        System.out.println("Enter the item's " + name + "Quantity");
                        quantity = s.nextInt();
                        myCart.get(name).adjustStock(quantity);
                        System.out.println(myCart);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Sorry Sir the Option you entered is invalid , please try a valid one:");
                }

            } catch (Exception e) {
                System.out.println("Some Error has Occur !! , Please Try Again");
            }
        }

    }

    private static void customerProcess(InventoryList myCart) {
        String name = null;
        InventoryItem item = null;
        int quantity = 0;
        InventoryItem temp = null;
        System.out.println("Enter your name");
        name = s.nextLine();
        ShoppingCart shoppingCart = new ShoppingCart(name + "'s Cart");
        while (true) {
            try {
                System.out.println("\n --- Welcome to My Cart Store ! , We are the best store in the city and provide best quality produces to you --- \n");
                System.out.println("\n Follow the following Instruction for the shopping \n" +
                        "1. See the available Goods or Item in the Store \n" +
                        "2. Add the item in my shopping cart or basket \n" +
                        "3. Remove item from my basket \n" +
                        "4. Increase the quantity of a item \n" +
                        "5. Decrease the quantity of a item \n" +
                        "6. Check my shopping cart or basket \n" +
                        "0. Purchase and Exit the Store\n");
                int num = s.nextInt();

                switch (num) {

                    case 1:
                        System.out.println(myCart);
                        break;
                    case 2:
                        System.out.println("Enter the Item name");
                        s.nextLine();
                        name = s.nextLine();
                        System.out.println("Enter the quantity");
                        quantity = s.nextInt();
                        if (myCart.get(name) != null) {
                            shoppingCart.addToShoppingCart(item, quantity);
                            myCart.sellStock(name, quantity);
                            System.out.println(shoppingCart);
                        } else {
                            System.out.println("We don't Sell this ");
                        }
                        break;
                    case 3:
                        System.out.println("Enter the Item name to be removed");
                        name = s.nextLine();
                        if (shoppingCart.removeItem(name)) {
                            temp = shoppingCart.getItem(name);
                            if (temp != null) {
                                temp.adjustStock(shoppingCart.getQuantity(temp));
                                System.out.println(shoppingCart);
                            }
                        } else {
                            System.out.println("Please Check the Item Name and try again");
                        }
                        break;
                    case 4:
                        System.out.println("Enter the item name who's quantity is to be increase");
                        name = s.nextLine();
                        System.out.println("enter the quantity to be added");
                        quantity = s.nextInt();
                        temp = shoppingCart.getItem(name);
                        if (temp != null) {
                            item.adjustStock(quantity);
                            System.out.println(shoppingCart);
                        } else {
                            System.out.println("Item is not present in the List \n Please Enter the Item in your basket or shopping cart");
                        }
                        break;
                    case 5:
                        System.out.println("Enter the item name who's quantity is to be decrease or reduced");
                        name = s.nextLine();
                        System.out.println("enter the quantity to be subtracted or take away");
                        quantity = s.nextInt();
                        temp = shoppingCart.getItem(name);
                        if (temp != null) {
                            if (!item.adjustStock(-quantity)) {
                                continue;
                            }
                            System.out.println(shoppingCart);
                        } else {
                            System.out.println("Item is not present in the List \n Please Enter the Item in your basket or shopping cart");
                        }
                        break;
                    case 0:
                        System.out.println("Happy Shopping");
                        return;
                    default:
                        System.out.println("You have Entered the invalid Option please enter valid one");
                }
            }catch (Exception e){
                System.out.println("Some Error has Occur !!, Please Try Again");
            }
        }
    }

}
