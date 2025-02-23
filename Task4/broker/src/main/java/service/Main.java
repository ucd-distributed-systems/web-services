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

        List<QuotationService> quotationServices = createQuotationStubs(Arrays.asList(args));
        Endpoint.publish("http://0.0.0.0:9000/broker", new LocalBrokerService(quotationServices));
        // the broker should receive a list of urls representing the locations of the quotation services
        // in theory the quotation services should already be running
        // perhaps when I instantiate the LocalBrokerService, I can pass the urls to the constructor
        // and in the constructor create the quotation service stubs
    }

    private static List<QuotationService> createQuotationStubs(List<String> urls) throws Exception {
        List<QuotationService> quotationServices = new ArrayList<>();

        for (String url : urls) {
            System.out.println("Quotation service url: " + url);
            // add try catch here and if there is an error, skip to next service
            try {
                URL wsdlUrl = new URL(url);
                System.out.println("WSDL url: " + wsdlUrl);
                QName serviceName =
                        new QName("http://core.service/", "QuotationService");
                System.out.println("Service name: " + serviceName);
                Service service = Service.create(wsdlUrl, serviceName);
                System.out.println("Service created: " + service);
                QName portName =
                        new QName("http://core.service/", "QuotationServicePort");
                System.out.println("Service port: " + portName);
                QuotationService quotationService =
                        service.getPort(portName, QuotationService.class);
                System.out.println("Quotation service: " + quotationService);
                quotationServices.add(quotationService);
            } catch (Exception e) {
                System.out.println("Failed to connect to quotation service at: " + url);
                e.printStackTrace();
            }
        }
        return quotationServices;
    }
}
