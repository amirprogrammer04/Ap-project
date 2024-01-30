import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jdk.jfr.Category;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
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
        try (FileWriter writer = new FileWriter("C:\\Users\\pc\\Downloads\\FinalProj\\Sources\\"+fileName)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveToFileProducts(String fileName){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(categories.get(fileName).productMap);
        try (FileWriter writer = new FileWriter("C:\\Users\\pc\\Downloads\\FinalProj\\Sources\\"+fileName+".json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveToFileCategoryNameList(){
         Gson gson=new GsonBuilder().setPrettyPrinting().create();
         String json=gson.toJson(Category.categoryNameList);
        try (FileWriter writer = new FileWriter("C:\\Users\\pc\\Downloads\\FinalProj\\Sources\\"+"Category.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> getCategoryList() {
        try (FileReader reader = new FileReader("C:\\Users\\pc\\Downloads\\FinalProj\\Sources\\"+"Category.json")) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            return new ArrayList<String>();
        }
    }
    public static HashMap<String,Product> getCategoryProduct(String fileName) {
        try (FileReader reader = new FileReader("C:\\Users\\pc\\Downloads\\FinalProj\\Sources\\"+fileName+".json")) {
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String,Product>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            return new HashMap<>();
        }
    }
}
