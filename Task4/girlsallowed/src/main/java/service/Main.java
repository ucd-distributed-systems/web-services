package service;

import jakarta.xml.ws.Endpoint;
import service.girlsallowed.GAQService;

public class Main {
    public static void main(String[] args) {
        System.out.println("GAQ service initiated");
        Endpoint.publish("http://gaq-service:9003/quotations",
                new GAQService());
    }
}