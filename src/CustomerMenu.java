import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class CustomerMenu {
    public transient List<String> keyList=new ArrayList<>();
    Customer customer;
    public CustomerMenu(Customer customer){
        this.customer=customer;
        setKeyList();
    }
    public void seeTheMainEnvironment(){
        int selectInt=1;
        for (String select: keyList){
            System.out.println(selectInt+") "+select);
            selectInt++;
        }
        try{
            Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Your Favorite Feature");
        selectInt=scanner.nextInt();
        if(selectInt==1){
            searchProducts();
            seeTheMainEnvironment();
        } else if(selectInt==2){
            seeCategories();
            seeTheMainEnvironment();
        } else if (selectInt == 3) {
            seeFavorite();
            seeTheMainEnvironment();
        } else if (selectInt==4) {
            if(!seeCart())
                System.out.println("Empty Cart!\n");
            seeTheMainEnvironment();
        }
        }catch (Exception e){
        System.out.println("Invalid Input!");
        seeTheMainEnvironment();
    }
    }
    public void setKeyList(){
        keyList.add("Search Products");
        keyList.add("See Categories");
        keyList.add("See Favorite Products");
        keyList.add("See Cart");
    }
    public void seeCategories(){
        int numberCategory=1;
        ArrayList <String > list=SaveProduct.getCategoryList();
        for (String key: list){
            System.out.println(numberCategory+++") "+key);
            }
        System.out.println("Which Category's Product Do You Want to see?");
        Scanner scanner=new Scanner(System.in);
        int select=scanner.nextInt();
        Map <String,Product> productMap=SaveProduct.getCategoryProduct(list.get(select-1));
        int productCounter=0;
        for(String str:productMap.keySet()){
            Product product=productMap.get(str);
            System.out.println("Product "+(++productCounter)+": ");
            for(String string: product.info.keySet()){
                System.out.println(string+": "+product.info.get(string));
            }
        }
        }
    public void seeFavorite(){
        Map<String,Product> favorites=Product.getProductsFromFile(customer.info.get("UserName"));
        int i=1;
        List<String> arrayList=new ArrayList<>();
        for(String see: favorites.keySet()){
            System.out.println(i+++")");
            arrayList.add(see);
            Product product=favorites.get(see);
            for (String str: product.info.keySet()){
                System.out.println(str + ": "+product.info.get(str));
            }
        }
        System.out.println("Do You Want To Add A Product To Your Cart?(Y/N) ");
        Scanner scanner=new Scanner(System.in);
        String input=scanner.next();
        boolean flag=false;
        do {
            flag=false;
            if (input.equals("Y")) {
                System.out.println("Enter The Number Of The Product You Want To Add To Your Cart.");
                String string = scanner.next();
                Product product = favorites.get(arrayList.get(Integer.parseInt(string) - 1));
                Map<String, Product> hashmap = getCart();
                hashmap.put(product.HashCode(), product);
//                Set<String> set=hashmap.keySet();
//                for(String s: set){
//                    System.out.println(hashmap.get(s).info.get("Name"));
//                }
                saveToFileCart(hashmap);
            } else if (input.equals("N")) {
            } else
                flag=true;
        }while (flag);
    }
    public boolean seeCart(){
        Map <String,Product> cart=getCart();
        int i=0;
        if (!cart.isEmpty()){
            for (String str: cart.keySet()){
                Product product=cart.get(str);
                System.out.println("Product "+(++i)+") ");
                System.out.println("Name: "+product.info.get("Name")+"\n"+"Code: "+product.info.get("Code"));
            }
            boolean flag=false;
            do{
                flag=false;
                System.out.println("Do You Want To Finalize Buying Your Cart?(Y/N)");
            Scanner scanner=new Scanner(System.in);
            String input=scanner.next();
            if(input.equals("Y")){
                cart.clear();
                saveToFileCart(cart);
            } else if (input.equals("N")) {
            }else
                flag=true;
            }while (flag);
                return true;
        }
        else
            return false;
    }
    public void saveToFileCart(Map<String,Product> map){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(map);
        try (FileWriter writer = new FileWriter("C:\\Users\\pc\\Downloads\\FinalProj\\Sources\\"+customer.info.get("FirstName")+"Cart"+".json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<String,Product> getCart(){
        try (FileReader reader = new FileReader("C:\\Users\\pc\\Downloads\\FinalProj\\Sources\\"+customer.info.get("FirstName")+"Cart"+".json")) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Product>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            return new HashMap<>(); // Return an empty map if file does not exist or cannot be read
        }
    }
    public void searchProducts(){
        ArrayList<Product> theFoundProducts= Product.findProduct();
        if (theFoundProducts.isEmpty()){
            System.out.println("No Such Products found.");
        }else {
            System.out.println("Which Product Do You Want to see?");
            int number=0;
            Scanner scanner=new Scanner(System.in);
            number=scanner.nextInt();
            Product selectedProduct=theFoundProducts.get(number-1);
            for(String properties: selectedProduct.info.keySet()){
                System.out.println(properties+": "+ selectedProduct.info.get(properties));
            }
            System.out.println("Do You Want To Add This Product To Your Favorite List?(Y/N)");
            String answer=scanner.next();
            if (answer.equals("Y"))
                customer.products.put(selectedProduct.HashCode(),selectedProduct);
                Seller.saveToFileProducts(customer,customer.info.get("UserName")+".json");
                seeTheMainEnvironment();
        }
    }
}
