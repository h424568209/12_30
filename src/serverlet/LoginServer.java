package serverlet;

import vv.Reponses;
import vv.Requests;

import java.io.IOException;

public class LoginServer extends HttpServers {
    @Override
    public void doGet(Requests requests, Reponses responses) throws IOException {
        String name = requests.parameters.get("name");
        if(name == null){
            responses.setStatus("401 Unauthorized");
            responses.println("<h1>请登录</h1>");
            return;
        }
        User currentUser = User.findUser(name);
        if(currentUser == null){
            responses.setStatus("401 Unauthorized");
            responses.println("<h1>该用户不存在</h1>");
            return;
        }
        String sessionld = SessionServer.put(currentUser);
        responses.setHeadsers("Set-Cookie","session-id="+ sessionld+";expires=Tue, 07-Apr-2020 08:46:16 GMT; Max-Age=8640000");
        responses.println("<h1>欢迎" + name +"光临</h1>");
    }
}
