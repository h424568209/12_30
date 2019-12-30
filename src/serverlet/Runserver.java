package serverlet;

import vv.Reponses;
import vv.Requests;

public class Runserver extends HttpServers {
    @Override
    public void doGet(Requests requests, Reponses responses) {
        responses.setHeadsers("Content-Type","application/javascript;charset=utf-8");
        responses.println("Hello你");
    }
}
