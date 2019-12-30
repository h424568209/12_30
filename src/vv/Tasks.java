package vv;

import serverlet.*;

import java.io.IOException;
import java.net.Socket;

public class Tasks implements Runnable {
    public Socket socket  ;
    public Tasks(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Requests requests = Requests.parse(socket.getInputStream());
            Reponses responses = new Reponses();

            HttpServers server;
            if(requests.path.equals("/")){
                server = new Runserver();
            }else if(requests.path.equals("/login")){
                server = new LoginServer();
            }else if(requests.path.equals("joke.js")){
                server = new JokeServer();
            }else{
                server = new NotFoundServer();
            }
            server.doGet(requests,responses);
            responses.writeAndFlush(socket.getOutputStream());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
