import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Product {
    public Map<String,String> info=new HashMap<>();
    public static transient List<String> keyList=new ArrayList<>();
    public static Map<String, Product> getProductsFromFile() {
    try (FileReader reader = new FileReader("C:\\Users\\pc\\Downloads\\FinalProj\\Sources\\"+"products.json")) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Product>>(){}.getType();
        return gson.fromJson(reader, type);
    } catch (IOException e) {
        return new HashMap<>(); // Return an empty map if file does not exist or cannot be read
    }
}
public static Map<String, Product> getProductsFromFile(String fileName) {
    try (FileReader reader = new FileReader("C:\\Users\\pc\\Downloads\\FinalProj\\Sources\\"+fileName+".json")) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Product>>(){}.getType();
        return gson.fromJson(reader, type);
    } catch (IOException e) {
        return new HashMap<>(); // Return an empty map if file does not exist or cannot be read
    }
}

    public static void setKeyList(){
        keyList.add("Name");
        keyList.add("Code");
        keyList.add("Seller");
        keyList.add("Date Of Produce");
        keyList.add("Colors");
        keyList.add("Category");
        keyList.add("Price");
        keyList.add("Status");
    }
    public String HashCode() {
        String name =  info.get("Name");
        String code =  info.get("Code");
        String seller =  info.get("Seller");
        String input = name + code + seller;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return name;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    public static void saveToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(SaveProduct.products);
        try (FileWriter writer = new FileWriter("C:\\Users\\pc\\Downloads\\FinalProj\\Sources\\"+"products.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Product> findProduct(){
        ArrayList <Product> productArrayList=new ArrayList<>();
        String productName;
        Scanner scanner=new Scanner(System.in);
        SaveProduct.products=getProductsFromFile();
        System.out.println("Enter: ");
        productName=scanner.next();
        Collection<Product> productList=SaveProduct.products.values();
        int number=1;
        for (Product product: productList){
            if(product.info.get("Name").equals(productName)){
                productArrayList.add(product);
                Collection<String> information=product.info.keySet();
                System.out.println((number++)+")");
                for (String k: information){
                    if (k.equals("Name")||k.equals("Code"))
                    System.out.println(k+": "+product.info.get(k));
                }
            }
        }
        return productArrayList;
    }

}
