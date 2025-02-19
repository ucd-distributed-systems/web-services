package service;

import jakarta.xml.ws.Endpoint;
import service.broker.LocalBrokerService;

public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9000/broker", new LocalBrokerService());
        // list of strings representing the quotation services urls
        // retrieve the web services associated with the urls
        // attempt to call
    }
}
