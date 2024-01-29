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
                seeTheMainEnvironment();
            } else if(select.equals("3")){
                boolean flag=false;
                do{
                    try{
                        flag=false;
                        int selected=0;
                ArrayList<Product> products=seeProducts(seller);
                System.out.println("Which Product's Info Do You Want to Edit?");
                selected=scanner.nextInt();
                Product product=products.get(selected-1);
                selected=1;
                ArrayList <String> list=new ArrayList<>();
                for (String sr: product.info.keySet()){
                    System.out.println(selected+++") "+sr+": "+product.info.get(sr));
                    list.add(sr);
                }
                System.out.println("Which Line Do You Want to Edit?");
                selected=scanner.nextInt();
                String key=list.get(selected-1);
                System.out.println("Enter Your Favorite Value:");
                seller.products.remove(product.HashCode());
                SaveProduct.products.remove(product.HashCode());
                String add=scanner.next();
                product.info.put(key,add);
                seller.products.put(product.HashCode(),product);
                SaveProduct.products.put(product.HashCode(),product);
                Product.saveToFile();
                Seller.saveToFileProducts(seller,seller.info.get("SellerName")+".json");
                selected=1;
                for (String sr: product.info.keySet()){
                    System.out.println(selected+++") "+sr+": "+product.info.get(sr));
                    list.add(sr);
                }
                System.out.println();
                    }catch (Exception e){
                        System.out.println("Please Enter Valid Input");
                        flag=true;
                    }
                }while(flag);
                seeTheMainEnvironment();
            }else
                throw new Exception();
        }catch (Exception e){
            System.out.println("Please Enter Valid Input.");
            seeTheMainEnvironment();
        }
    }
    public void setKeyList(){
        keyList.add("Add product");
        keyList.add("See Products");
        keyList.add("Change The Info of The Product");

    }
    public ArrayList<Product> seeProducts(Seller seller){
         Map<String,Product> products=Product.getProductsFromFile(seller.info.get("SellerName"));
         Set <String> setKeys=products.keySet();
         ArrayList<Product> returnProduct = new ArrayList<>();
         int counter=1;
         if (!products.isEmpty()){
         for (String str: setKeys){
                System.out.println(counter+++")");
                Product product=products.get(str);
                returnProduct.add(product);
                for (String string: product.info.keySet()){
                    System.out.println(string+": "+product.info.get(string));
                }
         }
         }else{
             System.out.println("No Products Found!");
         }
         return returnProduct;
    }
}
