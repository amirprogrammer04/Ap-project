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
        int numberCategory=1;
        for (String key: SaveProduct.Category.categoryNameList){
            System.out.println(numberCategory+++") "+key);
            }
        System.out.println("Which Category's Product Do You Want to see?");
        int select=new Scanner(System.in).nextInt();
        String category=SaveProduct.Category.categoryNameList.get(select-1);

        }
    public void seeFavorite(){
        Map<String,Product> favorites=Product.getProductsFromFile(customer.info.get("UserName")+".json");
        int i=1;
        for(String see: favorites.keySet()){
            System.out.println(i+++")");
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
