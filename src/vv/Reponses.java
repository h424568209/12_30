package vv;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Reponses {
    private String status = "200 OK";
    private Map<String,String>headsers = new HashMap<>();

    private StringBuilder bodyBuilder = new StringBuilder();
    Reponses(){
        headsers.put("Content-Type"," text/html; charset=UTF-8");
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void setHeadsers(String key, String val){
        headsers.put(key,val);
    }
    public void print(String s){
        bodyBuilder.append(s);
    }
    public void println(String s){
        bodyBuilder.append(s);
        bodyBuilder.append("\r\n");
    }
    public void writeAndFlush(OutputStream outputStream) throws IOException {
        String response = buildResponse();
        outputStream.write(response.getBytes("UTF-8"));
        outputStream.flush();
    }

    public String buildResponse() {
        StringBuilder responseBuilder = new StringBuilder();
        //响应行
        responseBuilder.append("HTTP/1.0 ");
        responseBuilder.append(status);
        responseBuilder.append("\r\n");

        //响应头
        int countLength = bodyBuilder.toString().getBytes().length;
        setHeadsers("Content-Length",String.valueOf(countLength));
        for(Map.Entry<String,String> entry:headsers.entrySet()){
            responseBuilder.append(entry.getKey());
            responseBuilder.append(": ");
            responseBuilder.append(entry.getValue());
            responseBuilder.append("\r\n");
        }
        responseBuilder.append("\r\n");
        responseBuilder.append(bodyBuilder);
        return responseBuilder.toString();
    }
}
