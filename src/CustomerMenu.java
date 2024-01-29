import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
        }
        }catch (Exception e){
        e.printStackTrace();
        seeTheMainEnvironment();
    }
    }
    public void setKeyList(){
        keyList.add("Search Products");
        keyList.add("See Categories");
        keyList.add("See Favorite Products");
    }
    public void seeCategories(){
        Map <String, SaveProduct.Category> categoryMap=SaveProduct.categories;
        int numberCategory=1;
        for (String key: categoryMap.keySet()){
            System.out.println("Category "+key+"'s Products");
            SaveProduct.Category category=categoryMap.get(key);
            for (String string:category.productMap.keySet()){
                Product product=category.productMap.get(string);
                for(String str: product.info.keySet()){
                    System.out.println(str+": "+product.info.get(str));
                }
            }
        }
    }
    public void seeFavorite(){
        Map<String,Product> favorites=Product.getProductsFromFile(customer.info.get("UserName")+".json");
        for(String see: favorites.keySet()){
            Product product=favorites.get(see);
            for (String str: product.info.keySet()){
                System.out.println(str + ": "+product.info.get(str));
            }
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
