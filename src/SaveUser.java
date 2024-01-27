import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SaveUser {
    public static Map<String,User> users=new HashMap<>();
    protected static transient Set<String> usernames=new HashSet<>();
}
