package service;


import jakarta.xml.ws.Endpoint;
import service.auldfellas.AFQService;


public class Main {
    public static void main(String[] args) {
        System.out.println("AFQ service initiated");
        Endpoint.publish("http://0.0.0.0:9001/quotations",
        new AFQService());
    }
}
