package service;

import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.Service;
import service.broker.LocalBrokerService;
import service.core.QuotationService;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Broker service initiated");
        Endpoint.publish("http://broker-service:9000/broker", new LocalBrokerService(Arrays.asList(args)));
    }
}
