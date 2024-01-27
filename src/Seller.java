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
public class Seller extends User{
    private Map <String,Products> mapOfProducts=new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller seller)) return false;
        return Objects.equals(info, seller.info) && Objects.equals(keyList, seller.keyList);
    }


    public void setKeyList(){
        keyList.add("SellerName");
        keyList.add("UserName");
        keyList.add("PhoneNumber");
        keyList.add("Address");
        keyList.add("E-mail Address");
        keyList.add("Password");
    }

}
