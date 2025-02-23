package service.broker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Service;
import service.core.BrokerService;
import service.core.ClientInfo;
import service.core.Quotation;
import service.core.QuotationService;

import javax.xml.namespace.QName;

/*
 * Implementation of the broker service that uses the Service Registry.
 *
 * @author Rem
 *
 */
@WebService(name="BrokerService",
        targetNamespace="http://core.service/",
        serviceName="BrokerService")
@SOAPBinding(style= SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL)
public class LocalBrokerService implements BrokerService {
    private List<QuotationService> quotationServices;
    private List<String> quotationUrls;

    @WebMethod
    @Override
    public LinkedList<Quotation> getQuotations(ClientInfo info) throws Exception {
        LinkedList<Quotation> quotations = new LinkedList<Quotation>();

        for (QuotationService quotationService : quotationServices) {
            quotations.add(quotationService.generateQuotation(info));
        }

        return quotations;
    }

    public LocalBrokerService() {};

    public LocalBrokerService(List<String> quotationUrls) throws Exception {
        this.quotationUrls = quotationUrls;
        this.quotationServices = new LinkedList<>();
        for (String url : quotationUrls) {
            QuotationService quotationService = createQuotationStub(url);
            System.out.println("Generated quotation service: " + quotationService);
            this.quotationServices.add(quotationService);
        }
    }

    private QuotationService createQuotationStub(String url) throws Exception {
        QuotationService quotationService = null;
        try {
            URL wsdlUrl = new URL(url);
            QName serviceName =
                    new QName("http://core.service/", "QuotationService");
            Service service = Service.create(wsdlUrl, serviceName);
            QName portName =
                    new QName("http://core.service/", "QuotationServicePort");
            quotationService =
                    service.getPort(portName, QuotationService.class);
        } catch (Exception e) {
            System.out.println("Unable to create quotation stub from url: " + url);
        }

        return quotationService;
    }
}
