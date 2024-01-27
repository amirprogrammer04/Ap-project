import java.util.*;
public class Seller extends User{
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
