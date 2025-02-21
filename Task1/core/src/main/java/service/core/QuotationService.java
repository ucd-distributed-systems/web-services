package service.core;


import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

/**
 * Interface to define the behaviour of a quotation service.
 * 
 * @author Rem
 *
 */
@WebService
public interface QuotationService {
	@WebMethod
	public Quotation generateQuotation(ClientInfo info);
}
