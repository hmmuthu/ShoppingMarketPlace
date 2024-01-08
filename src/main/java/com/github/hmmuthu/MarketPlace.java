package com.github.hmmuthu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MarketPlace {
    private static final String MARKET_PLACE_FILE = "MarketPlaceFile.json";
    private ArrayList<Seller> sellers = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void setSellers(ArrayList<Seller> sellers) {
        this.sellers = sellers;
    }

    /*
    public String[] sortByPrice(ArrayList<ShoppingItem> storeItems) {
        int productsCounter = storeItems.size();
        ShoppingItem[] shopItemsList = new ShoppingItem[productsCounter];
        ShoppingItem temp;

        for (int i = 0; i < storeItems.size(); i++) {
            shopItemsList[i] = storeItems.get(i);
        }

        //alphabetize the array by store name
        for (int i = 0; i < productsCounter; i++) {
            for (int j = i + 1; j < productsCounter; j++) {
                // to compare one string with other strings
                if (shopItemsList[i].getProduct().getPrice() > (shopItemsList[j].getProduct().getPrice())) {

                    // swapping
                    temp = shopItemsList[i];
                    shopItemsList[i] = shopItemsList[j];
                    shopItemsList[j] = temp;
                }
            }
        }
        //sets shoppingItemList arraylist equal to the products in array
        String[] productsArray = new String[productsCounter];
        for (int i = 0; i < productsCounter; i++) {
            storeItems.set(i, shopItemsList[i]);
            productsArray[i] = storeItems.get(i).toString();
        }

        return productsArray;
    }

    public String[] sortByName(ArrayList<ShoppingItem> storeItems) {
        //int productsCounter = shoppingItemList.size();  //3
        int productsCounter = storeItems.size();
        ShoppingItem[] shopItemsList = new ShoppingItem[productsCounter];
        ShoppingItem temp;


        for (int i = 0; i < storeItems.size(); i++) {
            shopItemsList[i] = storeItems.get(i);
        }

        //alphabetize the array by store name
        for (int i = 0; i < productsCounter; i++) {
            //productsList.remove(i);
            for (int j = i + 1; j < productsCounter; j++) {
                // to compare one string with other strings
                if (shopItemsList[i].getProduct().getName().compareTo(shopItemsList[j].getProduct().getName()) > 0) {
                    // swapping
                    temp = shopItemsList[i];
                    shopItemsList[i] = shopItemsList[j];
                    shopItemsList[j] = temp;
                }
            }
        }
        //sets shoppingItemList arraylist equal to the products in array
        for (int i = 0; i < productsCounter; i++) {
            //System.out.println(shopItemsList[i]);
            storeItems.set(i, shopItemsList[i]);
            //System.out.println(shoppingItemList.get(i).getProduct().getName());
        }


        //sets shoppingItemList arraylist equal to the products in array
        String[] productsArray = new String[productsCounter];
        for (int i = 0; i < productsCounter; i++) {
            storeItems.set(i, shopItemsList[i]);
            productsArray[i] = storeItems.get(i).toString();
        }

        return productsArray;
    }

     */

    public ArrayList<ShoppingItem> sortByPrice (ArrayList<ShoppingItem> storeItems){
        return storeItems;
    }

    public ArrayList<ShoppingItem> sortByName(ArrayList<ShoppingItem> storeItems) {
        return storeItems;
    }


    public ArrayList<ShoppingItem> getMarketPlaceItems() {
        ArrayList<ShoppingItem> shoppingItems = new ArrayList<>();

        for (Seller seller : sellers) {
            if (seller != null) {
                for (Store store : seller.getStores()) {
                    for (Product product : store.getProducts()) {
                        shoppingItems.add(new ShoppingItem(product, seller.getEmail(), store.getName()));
                    }
                }
            }
        }

        return shoppingItems;
    }

    public ArrayList<ShoppingItem> searchName(String productName) {
        ArrayList<ShoppingItem> searchedItems = new ArrayList<>();
        for (ShoppingItem i : getMarketPlaceItems()) {
            if (i.getProduct().getName().equalsIgnoreCase(productName)) {
                searchedItems.add(i);
            }
        }
        return searchedItems;
    }

    public ArrayList<ShoppingItem> searchDescription(String productDescription) {
        ArrayList<ShoppingItem> searchedItems = new ArrayList<>();
        for (ShoppingItem i : getMarketPlaceItems()) {
            if (i.getProduct().getName().equalsIgnoreCase(productDescription)) {
                searchedItems.add(i);
            }
        }
        return searchedItems;
    }

    public ArrayList<ShoppingItem> searchStore(String storeName) {
        ArrayList<ShoppingItem> searchedItems = new ArrayList<>();
        for (ShoppingItem i : getMarketPlaceItems()) {
            if (i.getStoreName().equalsIgnoreCase(storeName)) {
                searchedItems.add(i);
            }
        }
        return searchedItems;
    }

    public ArrayList<ShoppingItem> searchSeller(String sellerName) {
        ArrayList<ShoppingItem> searchedItems = new ArrayList<>();
        for (ShoppingItem i : getMarketPlaceItems()) {
            if (i.getSellerEmail().equalsIgnoreCase(sellerName)) {
                searchedItems.add(i);
            }
        }
        return searchedItems;
    }

    /**
     * loads the marketplace
     * @return
     */
    public static MarketPlace loadMarketPlace() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        MarketPlace mp = null;

        System.out.println("Loading market place...");
        try {
            FileReader reader = new FileReader(MARKET_PLACE_FILE);
            mp = gson.fromJson(reader, MarketPlace.class);
            reader.close();
            System.out.println("Loading complete");
        } catch (IOException e) {
            System.out.println("market place file not found");
        }
        if (mp == null){
            System.out.println("Creating new market place");
            mp = new MarketPlace();
        }
        return mp;
    }

    /**
     * saves marketplace
     */
    public void saveMarketPlace() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(MARKET_PLACE_FILE);
            gson.toJson(this, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MarketPlace mp = loadMarketPlace();
        while(true) {
            LogInForm logInForm = new LogInForm(mp);
            logInForm.setVisible(true);

            User loginUser = logInForm.getLoginUser();
            if (loginUser == null) {
                break;
            }

            if (loginUser instanceof Customer) {
                ((Customer)loginUser).actions(mp);
            } else {
                ((Seller)loginUser).actions(mp);
            }

            mp.saveMarketPlace();
        }
    }
}
