import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SellerMenu {
    public transient List<String> keyList=new ArrayList<>();
    Seller seller;
    public SellerMenu(Seller seller){
        this.seller=seller;
    }
    public void seeTheMainEnvironment(){
        setKeyList();
        int selectInt=1;
        for (String select: keyList){
            System.out.println(selectInt+") "+select);
            selectInt++;
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Your Favorite Feature");
        selectInt=scanner.nextInt();
        if(selectInt==1){
            Product product=new Product();
            product.setInfo(product,seller);
            product.addProduct(product);
            seeTheMainEnvironment();
//            boolean flag=true;
//            do{
//
//            for (String properties: product.keyList){
//                System.out.print(properties+": ");
//                product.info.put(properties,scanner.nextLine());
//            }
//            System.out.println("Is This Product Properties True?(Y/N)");
//            for (String properties: product.keyList){
//                System.out.println(properties+": "+product.info.get(properties));
//            }
//            String str=scanner.nextLine();
//            if (str.equals("Y")){
//                seller.products.put(product.HashCode(),product);
//                flag=false;
//                seeTheMainEnvironment();
//            }
//            }while (flag);
        }
    }
    public void setKeyList(){
        keyList.add("Add product");
    }

}
