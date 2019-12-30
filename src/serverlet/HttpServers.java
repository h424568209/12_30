package serverlet;

import vv.Reponses;
import vv.Requests;

import java.io.IOException;

public abstract class HttpServers {
    public abstract void doGet(Requests requests, Reponses responses) throws IOException;
}
