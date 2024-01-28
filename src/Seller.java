import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Seller extends User{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller seller)) return false;
        return Objects.equals(info, seller.info) && Objects.equals(keyList, seller.keyList);
    }

    public void setKeyList(){
        keyList.add("SellerName");
        keyList.add("UserName");
        keyList.add("PhoneNumber");
        keyList.add("Address");
        keyList.add("E-mail Address");
        keyList.add("Password");
    }
    public static void saveToFileProducts(Seller seller,String fileName){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(seller.products);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void addProduct(Seller seller,Product product){
        SaveProduct.products=Product.getProductsFromFile();
        Scanner scanner=new Scanner(System.in);
        String str;
        boolean correct=false;
        while (!correct) {
            for (String input : Product.keyList) {
                System.out.println("Enter Product's " + input + ": ");
                str = scanner.nextLine();
                product.info.put(input, str);
            }
            System.out.println("Your Product Information is as follows:");
            for (String input : Product.keyList) {
                System.out.println(input + ": " + product.info.get(input));
            }
            boolean flag;
            do {
                flag = false;
                System.out.println("Correct?(Y/N)");
                str = scanner.next();
                if (str.equals("Y")) {
                    correct = true;
                    seller.products.put(product.HashCode(),product);
                    seller.products=Product.getProductsFromFile(seller.info.get("SellerName")+".json");
                    String key= product.HashCode();
                    SaveProduct.products.put(key,product);
                    seller.products.put(key,product);
                    Product.saveToFile();
                    Seller.saveToFileProducts(seller,seller.info.get("SellerName")+".json");
                    flag=false;
                } else if (str.equals("N")) {
                    product.info.clear();
                } else {
                    flag = true;
                }
            } while (flag);
        }

    }

}
