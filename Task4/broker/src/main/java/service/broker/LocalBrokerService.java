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
@WebService(name="BrokerService",
        targetNamespace="http://core.service/",
        serviceName="BrokerService")
@SOAPBinding(style= SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL)
public class LocalBrokerService implements BrokerService {
    private List<QuotationService> quotationServices;

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

    public LocalBrokerService(List<QuotationService> quotationServices) {
        this.quotationServices = quotationServices;
    }
}
