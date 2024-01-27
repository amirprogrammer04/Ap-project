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
    protected Map<String,String> info=new HashMap<>();
    protected transient List<String> keyList=new ArrayList<>();
    public void setInfo(Product product){
        SaveProduct.products=Product.getProductsFromFile();
        setKeyList();
        Scanner scanner=new Scanner(System.in);
        String str;
        boolean correct=false;
        while (!correct) {
            for (String input : keyList) {
                System.out.println("Enter your " + input + ": ");
                str = scanner.nextLine();
                info.put(input, str);
            }
            System.out.println("Your Product Information is as follows:");
            for (String input : keyList) {
                System.out.println(input + ": " + info.get(input));
            }
            boolean flag;
            do {
                flag = false;
                System.out.println("Correct?(Y/N)");
                str = scanner.nextLine();
                if (str.equals("Y")) {
                    correct = true;
                } else if (str.equals("N")) {
                    product.info.clear();
                } else {
                    flag = true;
                }
            } while (flag);
        }

    }
    public static Map<String, Product> getProductsFromFile() {
    try (FileReader reader = new FileReader("products.json")) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Product>>(){}.getType();
        return gson.fromJson(reader, type);
    } catch (IOException e) {
        return new HashMap<>(); // Return an empty map if file does not exist or cannot be read
    }
}
    public void setKeyList(){
        keyList.add("Name");
        keyList.add("Code");
        keyList.add("Seller");
        keyList.add("Date Of Produce");
        keyList.add("Colors");
        keyList.add("Category");
        keyList.add("Quantities");
    }
    public void addProduct(Product product){
        String key= product.HashCode();
        SaveProduct.products.put(key,product);
        saveToFile();
    }
    public String HashCode() {
        String name = (String) info.get("Name");
        String code = (String) info.get("Code");
        String seller = (String) info.get("Seller");
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
    public void saveToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(SaveProduct.products);
        try (FileWriter writer = new FileWriter("products.json")) {
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
        System.out.println("Enter Your Filename");
        productName=scanner.nextLine();
        Collection<Product> productList=SaveProduct.products.values();
        int number=1;
        for (Product product: productList){
            productArrayList.add(product);
            if(product.info.get("Name").equals(productName)){
                Collection<String> information=product.info.values();
                System.out.println((number++)+")");
                for (String k: information){
                    System.out.println(k);
                }
            }
        }
        return productArrayList;
    }

}
