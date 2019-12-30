package serverlet;

import java.io.*;
import java.util.UUID;

public class SessionServer {
    private static String dir = "会话";
    public static String put(User user) throws IOException {
        String sessionld = UUID.randomUUID().toString();
        String filename = dir+"\\"+sessionld;
        OutputStream os = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(user);
        os.close();
        return sessionld;
    }
    public static User get(String sessionid) throws IOException {
        String filename = dir+"\\"+sessionid;

        InputStream is = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(is);

        User user = null;
        try {
            user = (User)ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        is.close();;
        return user;
    }
}
