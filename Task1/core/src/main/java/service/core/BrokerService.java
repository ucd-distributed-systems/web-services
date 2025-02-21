package service.core;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;


/**
 * Interface for defining the behaviours of the broker service
 * @author Rem
 *
 */
@WebService
public interface BrokerService {
	@WebMethod
	public List<Quotation> getQuotations(ClientInfo info);
}
