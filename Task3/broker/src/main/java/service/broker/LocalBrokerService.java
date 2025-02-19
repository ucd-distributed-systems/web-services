package service.broker;

import java.net.URL;
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
@WebService(name="QuotationService",
        targetNamespace="http://core.service/",
        serviceName="QuotationService")
@SOAPBinding(style= SOAPBinding.Style.RPC, use= SOAPBinding.Use.LITERAL)
public class LocalBrokerService implements BrokerService {
    @WebMethod
    @Override
    public LinkedList<Quotation> getQuotations(ClientInfo info) throws Exception {
        LinkedList<Quotation> quotations = new LinkedList<Quotation>();
        List<String> serviceURLs = new LinkedList<String>();
        serviceURLs.add("http://0.0.0.0:9001/quotations?wsdl");
        serviceURLs.add("http://0.0.0.0:9002/quotations?wsdl");
        serviceURLs.add("http://0.0.0.0:9003/quotations?wsdl");

        for (String name : serviceURLs) {
            if (name.contains("quotations")) {
                // create quotation services
                // generation quotation
                URL wsdlUrl = new URL(name);
                QName serviceName =
                        new QName("http://core.service/", "QuotationService");
                Service service = Service.create(wsdlUrl, serviceName);
                QName portName =
                        new QName("http://core.service/", "QuotationServicePort");
                QuotationService quotationService =
                        service.getPort(portName, QuotationService.class);
                quotations.add(quotationService.generateQuotation(info));
            }
        }
        return quotations;
    }
}
