package service.core;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.LinkedList;


/**
 * Interface for defining the behaviours of the broker service
 * @author Rem
 *
 */
@WebService
public interface BrokerService {
	@WebMethod
	public LinkedList<Quotation> getQuotations(ClientInfo info) throws Exception;
}
