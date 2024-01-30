import java.util.*;

public class Program {
    // Warning: if you pulled this code from github, you may make some changes in the direction of FileWriter & FileReader
    public static void main(String[] args) {
        System.out.println("Welcome to your home\nWhich feature do you want?");
        List <String> initialList=new ArrayList<String>();
        initialList.add("A) Login");
        initialList.add("B) Sign in");
        boolean validate=false;
        while (!validate) {

            for (String str:initialList)
                System.out.println(str);
            Scanner scanner=new Scanner(System.in);
            String input=scanner.next();
            if (input.equals("A")) {
                logIn();
            } else if (input.equals("B")) {
                singIn();
            } else {
                System.out.println("Invalid input\nPlease try again");
            }
        }
    }
    public static void logIn() {
        List<String> list = new ArrayList<String>();
        list.add("A) Login as a Customer");
        list.add("B) Login as a Seller");
        boolean flag = false;
        do {
            for (String str:list)
                System.out.println(str);
            Scanner scanner=new Scanner(System.in);
            String input=scanner.next();
            if (input.equals("A")) {
                   Customer customer= new Customer();
                  flag=customer.findUser(customer);
                  if (flag){
                      CustomerMenu environment=new CustomerMenu(customer);
                  environment.seeTheMainEnvironment();
                  }
            } else if (input.equals("B")) {
                Seller seller=new Seller();
                flag=seller.findUser(seller);
                if (flag){
                    SellerMenu environment=new SellerMenu(seller);
                    environment.seeTheMainEnvironment();
                }
            } else {
                System.out.println("Invalid input\nPlease try again");
            }
        }while (!flag);
    }
    public static void singIn(){
        List <String> list=new ArrayList<String>();
        list.add("A) Sign-in as a Customer");
        list.add("B) Sign-in as a Seller");
        while (true) {
            for (String str:list)
                System.out.println(str);
            Scanner scanner=new Scanner(System.in);
            String input=scanner.next();
            if (input.equals("A")) {
                Customer customer=new Customer();
                customer.setInfo(customer);
                customer.addUser(customer);
                CustomerMenu environment=new CustomerMenu(customer);
                environment.seeTheMainEnvironment();
            } else if (input.equals("B")) {
                Seller seller=new Seller();
                seller.setInfo(seller);
                seller.addUser(seller);
                SellerMenu environment=new SellerMenu(seller);
                environment.seeTheMainEnvironment();
            } else {
                System.out.println("Invalid input\nPlease try again");
            }
        }
    }
}