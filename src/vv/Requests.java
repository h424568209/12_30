package vv;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Requests {
    String method;
    String path;
    String version;
    public Map<String, String> parameters = new HashMap<>();
    public Map<String, String> headers = new HashMap<>();
    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", version='" + version + '\'' +
                ", parameters=" + parameters +
                ", headers=" + headers +
                '}';
    }
    static Requests parse(InputStream inputStream) throws UnsupportedEncodingException {
        Requests requests = new Requests();
        Scanner scanner = new Scanner(inputStream,"UTF-8");
        parseURLine(scanner,requests);
        parseHeaders(scanner,requests);
        return requests;
    }

    private static void parseHeaders(Scanner scanner, Requests requests) {
        String line ;
        while(!(line = scanner.nextLine()).isEmpty()) {
            String []kv = line.split(":");
            String key = kv[0].trim();
            String val = kv[1].trim();
            requests.headers.put(key,val);
        }
    }

    private static void parseURLine(Scanner scanner, Requests requests) throws UnsupportedEncodingException {
        String[] group = scanner.nextLine().split(" ");
        requests.method = group[0];
        parseUrl(group[1], requests);
        requests.version = group[2];
    }

    private static void parseUrl(String s, Requests requests) throws UnsupportedEncodingException {
        String[] group = s.split("\\?");
        requests.path = URLDecoder.decode(group[0], "UTF-8");
        if (group.length == 2) {
            String[] segment = group[1].split("&");
            for (String kvString : segment) {
                String[] kv = kvString.split("=");
                String key = URLDecoder.decode(kv[0], "UTF-8");
                String value = "";
                if (kv.length == 2) {
                    value = URLDecoder.decode(kv[1], "UTF-8");
                }
                requests.parameters.put(key, value);
            }
        }
    }
}
