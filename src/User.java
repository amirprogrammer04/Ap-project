import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class User {
    protected Map<String,String> info=new HashMap<>();
    protected transient List<String> keyList=new ArrayList<>();
    public Map <String,Product> products=new HashMap<>();

    public String HashCode() {
        String username = (String) info.get("UserName");
        String password = (String) info.get("Password");
        return hashcode(username, password);
    }

    public String hashcode(String username, String password) {
        String input = username + password;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            String HashCode = bytesToHex(hash);
            return HashCode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return username;
    }


    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    public void setInfo(User user){
            SaveUser.users=User.getUsersFromFile();
            Collection<User> userList=  SaveUser.users.values();
            for (User k: userList ){
                SaveUser.usernames.add(k.info.get("UserName"));
            }
            setKeyList();
            Scanner scanner = new Scanner(System.in);
            String str;
            boolean correct = false;
            while (!correct) {
                 try {
                    for (String input : keyList) {
                    boolean flag;
                     do {
                        flag =true;
                        System.out.println("Enter your " + input + ": ");
                        str = scanner.next();
                        info.put(input, str);
                        try {
                            if (input.equals("UserName")) {
                                if (!SaveUser.usernames.contains(str))
                                    SaveUser.usernames.add(str);
                                else
                                    throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println("Your Username Is Repetitious\nPlease Select Another UserName");
                            flag=false;
                        }
                    }while(!flag);
                }
                System.out.println("Your Information is as follows:");
                for (String input : keyList) {
                    System.out.println(input + ": " + info.get(input));
                }
                boolean flag=false;
                do {
                    flag=false;
                    System.out.println("Correct?(Y/N)");
                str = scanner.next();
                if (str.equals("Y")) {
                    correct = true;
                }
                else if (str.equals("N")) {
                    user.info.clear();
                }
                else{
                    flag=true;
                }
                }while (flag);
        }catch (Exception e){
                     System.out.println("You've entered unsuitable info. Please try again.");
        }
        }
    }
    public void setKeyList(){}
    public static Map<String, User> getUsersFromFile() {
        try (FileReader reader = new FileReader("C:\\Users\\pc\\Downloads\\FinalProj\\Sources\\"+"users.json")) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, User>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            return new HashMap<>(); // Return an empty map if file does not exist or cannot be read
        }
    }
    public  void addUser(User user) {
        String key = user.HashCode(); // Use hashCode as the key
        SaveUser.users.put(key, user);
        saveToFile();
    }
    public static void saveToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(SaveUser.users);
        try (FileWriter writer = new FileWriter("C:\\Users\\pc\\Downloads\\FinalProj\\Sources\\"+"users.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean findUser(User user) {
        List<String> logInInfo = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        SaveUser.users = getUsersFromFile();
        System.out.println("Enter your Username: ");
        logInInfo.add(scanner.next());
        System.out.println("Enter your Password: ");
        logInInfo.add(scanner.next());
        if (SaveUser.users.containsKey(hashcode(logInInfo.get(0),logInInfo.get(1)))) {
            User newLogIn = SaveUser.users.get(user.HashCode());
            System.out.println("Welcome :)");
            return true;
        } else {
            System.out.println("Sorry, we didn't find your account.\n" +
                    "If you registered before, please enter your info more carefully.\n");
            return false;
        }
    }
}