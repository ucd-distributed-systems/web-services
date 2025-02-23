package service;

import jakarta.xml.ws.Endpoint;
import service.dodgygeezers.DGQService;


public class Main {
    public static void main(String[] args) {
        System.out.println("DGQ service initiated");
        Endpoint.publish("http://0.0.0.0:9002/quotations",
                new DGQService());
    }
}
