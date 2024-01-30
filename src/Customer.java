import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Customer extends User {

    public void setKeyList(){
        keyList.add("FirstName");
        keyList.add("LastName");
        keyList.add("UserName");
        keyList.add("PhoneNumber");
        keyList.add("Address");
        keyList.add("E-mail Address");
        keyList.add("Password");
    }


}