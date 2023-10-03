package Services.Storage;

import java.io.IOException;
import java.util.UUID;
import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Storage.StorageOptionsRequest;
import Utils.Requests.Storage.StorageRequest;
import Utils.Responses.IResponse;

public class SWStorageService extends SWService {
    /**
     * Constructor de la clase.
     * 
     * @param user      correo o usuario de SW
     * @param password  password de SW.
     * @param URI       url base
     * @param URIAPI    base de la API
     * @param proxyHost ip o dominio de proxy (null si no se utiliza)
     * @param proxyPort número de puerto de proxy (cualquier valor si proxy es
     *                  ull)
     * @throws AuthException exception en caso de error.
     */
    public SWStorageService(String user, String password, String URI, String URIAPI, String proxyHost, int proxyPort)
            throws AuthException {
        super(user, password, URI, URIAPI, proxyHost, proxyPort);
    }

    /**
     * Constructor de la clase.
     * 
     * @param token     token infinito de SW.
     * @param URIAPI    url base de la API
     * @param proxyHost ip o dominio de proxy (null si no se utiliza)
     * @param proxyPort número de puerto de proxy (cualquier valor si proxy es
     *                  ull)
     */
    public SWStorageService(String token, String URIAPI, String proxyHost, int proxyPort) {
        super(token, URIAPI, proxyHost, proxyPort);
    }

    // Metodo para recuperar información de un XML timbrado con SW
    public IResponse getXml(UUID uuid) throws AuthException, GeneralException, IOException {
        StorageOptionsRequest settings = new StorageOptionsRequest(getToken(), uuid, getURIAPI(), getProxyHost(),
                getProxyPort());
        StorageRequest req = new StorageRequest();
        req.sendRequest(settings);
        return req.sendRequest(settings);
    }
}
