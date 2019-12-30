package serverlet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    String id;
    String nsetname;
    int balance;

    public User(String id, String nsetname, int balance) {
        this.id = id;
        this.nsetname = nsetname;
        this.balance = balance;
    }
    static List<User> users = new ArrayList<>();
    static {
        users.add(new User("1","小明",200));
        users.add(new User("2","小张",200));
        users.add(new User("3","小李",200));
    }
    public static  User findUser(String username){
        for(User user : users){
            if(user.nsetname.equals(username)){
                return user;
            }
        }
        return null;
    }
}
