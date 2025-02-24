package client;

import java.net.URL;
import java.text.NumberFormat;

import jakarta.xml.ws.Service;
import service.core.BrokerService;
import service.core.ClientInfo;
import service.core.Quotation;

import javax.xml.namespace.QName;

public class Main {
    /**
     * This is the starting point for the application. Here, we must
     * get a reference to the Broker Service and then invoke the
     * getQuotations() method on that service.
     *
     * Finally, you should print out all quotations returned
     * by the service.
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        BrokerService brokerService = createBrokerStub("http://0.0.0.0:9000/broker?wsdl");

        int numc = 0;
        for (ClientInfo info : clients) {
            numc++;
            System.out.println("\n\n|=================================================CLIENT INFO 0" + numc + "==================================================|");
            displayProfile(info);
            // Retrieve quotations from the broker and display them...
            int numq = 0;
            for(Quotation quotation : brokerService.getQuotations(info)) {
                numq++;
                System.out.println("\n" + "|==================================================QUOTATION 0" + numq + "===================================================|");
                displayQuotation(quotation);
            }
            // Print a couple of lines between each client
            System.out.println("                                   ********************END********************                                   " + "\n");
        }
    }

    private static BrokerService createBrokerStub(String url) throws Exception {
        BrokerService brokerService = null;
        try {
            Thread.sleep(2500);
            System.out.println("Creating broker stub");
            URL wsdlUrl = new URL(url);
            QName serviceName =
                    new QName("http://core.service/", "BrokerService");
            Service service = Service.create(wsdlUrl, serviceName);
            QName portName =
                    new QName("http://core.service/", "BrokerServicePort");
            brokerService = service.getPort(portName, BrokerService.class);
        } catch (Exception e) {
            System.out.println("Unable to create broker stub from url: " + url);
        }

        return brokerService;
    }

    /**
     * Display the client info nicely.
     *
     * @param info Client info
     */
    public static void displayProfile(ClientInfo info) {
        System.out.println("|=================================================================================================================|");
        System.out.println("|                                     |                                     |                                     |");
        System.out.println(
                "| Name: " + String.format("%1$-29s", info.name) +
                        " | Gender: " + String.format("%1$-27s", (info.gender==ClientInfo.MALE?"Male":"Female")) +
                        " | Age: " + String.format("%1$-30s", info.age)+" |");
        System.out.println(
                "| Weight/Height: " + String.format("%1$-20s", info.weight+"kg/"+info.height+"m") +
                        " | Smoker: " + String.format("%1$-27s", info.smoker?"YES":"NO") +
                        " | Medical Problems: " + String.format("%1$-17s", info.medicalIssues?"YES":"NO")+" |");
        System.out.println("|                                     |                                     |                                     |");
        System.out.println("|=================================================================================================================|");
    }
    /**
     * Display a quotation nicely - note that the assumption is that the quotation will follow
     * immediately after the profile (so the top of the quotation box is missing).
     *
     * @param quotation From quotation services
     */
    public static void displayQuotation(Quotation quotation) {
        System.out.println(
                "| Company: " + String.format("%1$-26s", quotation.company) +
                        " | Reference: " + String.format("%1$-24s", quotation.reference) +
                        " | Price: " + String.format("%1$-28s", NumberFormat.getCurrencyInstance().format(quotation.price))+" |");
        System.out.println("|=================================================================================================================|");
    }
    /**
     * Test Data
     */
    public static final ClientInfo[] clients = {
            new ClientInfo("One Two", ClientInfo.FEMALE, 49, 1.5494, 80, false, false),
            new ClientInfo("Three Four", ClientInfo.MALE, 65, 1.6, 100, true, true),
            new ClientInfo("Five Six'", ClientInfo.FEMALE, 21, 1.78, 65, false, false),
            new ClientInfo("Seven Eight", ClientInfo.MALE, 49, 1.8, 120, false, true),
            new ClientInfo("Nine Ten", ClientInfo.MALE, 55, 1.9, 75, true, false),
            new ClientInfo("Eleven Twelve", ClientInfo.MALE, 35, 0.45, 1.6, false, false)
    };
}

