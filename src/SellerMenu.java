import java.util.*;

public class SellerMenu {
    public transient List<String> keyList=new ArrayList<>();
    Seller seller;
    public SellerMenu(Seller seller){
        this.seller=seller;
        setKeyList();
        Product.setKeyList();
    }

    public void seeTheMainEnvironment(){
        int selectInt=1;
        for (String select: keyList){
            System.out.println(selectInt+") "+select);
            selectInt++;
        }
        String select;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Your Favorite Feature");
            select = scanner.next();
            if (select.equals("1")) {
                Product product = new Product();
                seller.addProduct(seller,product);
                seeTheMainEnvironment();
            } else if (select.equals("2")) {
                seeProducts(seller);
            } else
                throw new Exception();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setKeyList(){
        keyList.add("Add product");
        keyList.add("See Products");

    }
    public void seeProducts(Seller seller){
         Map<String,Product> products=Product.getProductsFromFile(seller.info.get("SellerName")+".json");
         Set <String> setKeys=products.keySet();
         System.out.println("1");
         int counter=1;
        System.out.println("2");
         for (String str: setKeys){
                System.out.println(counter+++") \n");
                Product product=products.get(str);
                for (String string: product.info.keySet()){
                    System.out.println(string+": "+product.info.get(string));
                }
         }

    }

}
