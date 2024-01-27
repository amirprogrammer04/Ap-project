import java.util.*;

public class Program {
    public static void main(String[] args) {
        System.out.println("Welcome to your home\nWhich feature do you want?");
        List <String> initialList=new ArrayList<String>();
        initialList.add("A) Login");
        initialList.add("B) Sign in");
        boolean validate=false;
        while (true) {
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
    public static void logIn(){
        List <String> list=new ArrayList<String>();
        list.add("A) Login as a Customer");
        list.add("B) Login as a Seller");
        while (true) {
            for (String str:list)
                System.out.println(str);
            Scanner scanner=new Scanner(System.in);
            String input=scanner.next();
            if (input.equals("A")) {
                Customer customer = new Customer();
                customer.findUser(customer);
            } else if (input.equals("B")) {
                Seller seller=new Seller();
                seller.findUser(seller);
            } else
                System.out.println("Please try again");
            }
        }
    public static void singIn(){
        List <String> list=new ArrayList<String>();
        list.add("A) Sign-in as a User");
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
            } else if (input.equals("B")) {
                Seller seller=new Seller();
                seller.setInfo(seller);
                seller.addUser(seller);

            } else {
                System.out.println("Invalid input\nPlease try again");
            }
        }
    }
}