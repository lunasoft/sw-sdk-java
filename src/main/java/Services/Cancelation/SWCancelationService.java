package Services.Cancelation;

import Services.SWService;

/**
 * Created by asalvio on 10/02/2017.
 */
public class SWCancelationService extends SWService {
    protected SWCancelationService(String user, String password, String URI) {
        super(user, password, URI);
    }

    protected SWCancelationService(String token, String URI) {
        super(token, URI);
    }
}
