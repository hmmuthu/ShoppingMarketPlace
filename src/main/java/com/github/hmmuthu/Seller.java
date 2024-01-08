package com.github.hmmuthu;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Seller extends User{
    private ArrayList<Store> stores = new ArrayList<>();

    public Seller(String email, String password, String name) {
        super(email, password, name);
        setCustomer(false);
    }

    public Seller(String email) {
        super(email);
        setCustomer(false);
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }

    public Store getStore(String storeName)  {
        int index = stores.indexOf(new Store(getEmail(), storeName));
        if (index == -1)
            return null;
        return stores.get(index);
    }

    /**
     * if store does not exist add to storelist and return true
     * otehrwise return false
     * @return
     */
    public boolean addStore(Store store) {
        if (stores.contains(store)) {
            return false;
        }

        stores.add(store);
        return true;
    }

    /**
     * if the file is in the correct format, import file data and return true. Otherwise return false.
     * @param fileName
     * @return
     */
    public boolean importFile(String fileName) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.isEmpty())
                    continue;
                List<String> values = Arrays.asList(line.split(";"));
                if (values.size() != 5) {
                    return false;
                }
                String storeName = values.get(0).trim();
                String productName = values.get(1).trim();
                String description = values.get(2).trim();
                int quantity = Integer.parseInt(values.get(3).trim());
                double price = Double.parseDouble(values.get(4).trim());

                Store store = getStore(storeName);
                if (store == null) {
                    store = new Store(storeName, getEmail());
                    addStore(store);
                }

                Product p = store.getProduct(productName);
                if (p == null) {
                    p = new Product(productName, description, quantity, price);
                    store.addProduct(p);
                } else {
                    p.setDescription(description);
                    p.setQuantity(quantity);
                    p.setPrice(price);
                }
            }
            fileScanner.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * return true if store data is exported successfully. Otherwise return false
     * @param filename
     * @return
     */
    public boolean exportFile(String filename, ArrayList<ShoppingItem> orders) {
        try{
            FileWriter f = new FileWriter(filename);
            for (ShoppingItem item : orders) {
                if (!item.getSellerEmail().equals(getEmail())) {
                    continue;
                }
                f.write(item.getProduct().getName() + " ; " +
                        item.getProduct().getPrice() + " ; " +
                        item.getProduct().getQuantity() + " ; " +
                        item.getStoreName() + "\n");
            }
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * calls UI form
     * @param mp
     */
    public void actions(MarketPlace mp) {}
}
