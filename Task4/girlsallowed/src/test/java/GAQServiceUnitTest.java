import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.Service;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.core.ClientInfo;
import service.core.Quotation;
import service.core.QuotationService;
import service.girlsallowed.GAQService;

import javax.xml.namespace.QName;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GAQServiceUnitTest {
    @BeforeAll
    public static void setup() {
        Endpoint.publish("http://0.0.0.0:9001/quotations", new GAQService());
    }
    @Test
    public void connectionTest() throws Exception {
        URL wsdlUrl = new URL("http://localhost:9001/quotations?wsdl");
        QName serviceName =
                new QName("http://core.service/", "QuotationService");
        Service service = Service.create(wsdlUrl, serviceName);
        QName portName =
                new QName("http://core.service/", "QuotationServicePort");
        QuotationService quotationService =
                service.getPort(portName, QuotationService.class);
        Quotation quotation = quotationService
                .generateQuotation(new ClientInfo(
                        "FirstName LastName", ClientInfo.FEMALE, 49,
                        1.5494, 80, false, false));
        assertNotNull(quotation);
        assertNotNull(quotation.company);
    }
}
