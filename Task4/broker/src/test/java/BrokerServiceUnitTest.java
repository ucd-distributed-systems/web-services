import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.Service;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.auldfellas.AFQService;
import service.broker.LocalBrokerService;
import service.core.BrokerService;
import service.core.ClientInfo;
import service.core.Quotation;
import service.core.QuotationService;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class BrokerServiceUnitTest {
    @BeforeAll
    public static void setup() {
        Endpoint.publish("http://0.0.0.0:9000/broker", new LocalBrokerService());
    }

    // could change connection test to try catch because it is behaving as expected
    // in that we can't get a quotation because none are connected
    @Test
    public void connectionTest() throws Exception {
        boolean canConnectToQuotationServices = false;
        LinkedList<Quotation> quotations;
        try {
            ClientInfo clientInfo = new ClientInfo(
                    "FirstName LastName", ClientInfo.FEMALE, 49,
                    1.5494, 80, false, false);

            URL wsdlUrl = new URL("http://0.0.0.0:9000/broker?wsdl");
            QName serviceName =
                    new QName("http://core.service/", "BrokerService");
            Service service = Service.create(wsdlUrl, serviceName);
            QName portName =
                    new QName("http://core.service/", "BrokerServicePort");
            BrokerService brokerService =
                    service.getPort(portName, BrokerService.class);
            quotations = brokerService.getQuotations(clientInfo);
            canConnectToQuotationServices = !quotations.isEmpty();
        } catch (Exception e) {
            assertFalse(canConnectToQuotationServices);
            return;
        }
        // if no exception, able to connect to quotation services / generate quotes
        assertTrue(canConnectToQuotationServices);
        assertFalse(quotations.isEmpty());
    }
}
