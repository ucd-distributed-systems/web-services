package service.core;

import java.util.List;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

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
