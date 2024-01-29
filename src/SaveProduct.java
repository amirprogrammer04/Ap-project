import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.jfr.Category;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SaveProduct {
    public static Map<String,Product> products=new HashMap<>();
    public static HashMap<String,Category> categories=new HashMap<>();
     public static class Category{
         private String name;
         public  Category(String name){
             this.name=name;
         }
        public static List <String> categoryNameList=new ArrayList<>();
        public Map <String,Product> productMap=new HashMap<>();


    }
    public static void saveToFileProducts(Category category,String fileName){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(category.productMap);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveToFileProducts(String fileName){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(categories.get(fileName).productMap);
        try (FileWriter writer = new FileWriter(fileName+".json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveToFileCategoryNameList
}
