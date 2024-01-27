import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    public transient List<String> keyList=new ArrayList<>();
    Customer customer;
    public CustomerMenu(Customer customer){
        this.customer=customer;
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
            searchProducts();
        }
    }
    public void setKeyList(){
        keyList.add("Search Products");
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
            String answer=scanner.nextLine();
            if (answer.equals("Y"))
                customer.products.put(selectedProduct.HashCode(),selectedProduct);
        }
        seeTheMainEnvironment();
    }
}
