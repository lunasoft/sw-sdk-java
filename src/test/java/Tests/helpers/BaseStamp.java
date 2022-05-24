package Tests.helpers;

import Tests.Utils;
import java.io.IOException;
import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Responses.Stamp.SuccessV1Response;
import Utils.Responses.Stamp.SuccessV2Response;
import Utils.Responses.Stamp.SuccessV3Response;
import Utils.Responses.Stamp.SuccessV4Response;

/**
 * BaseStamp EstÃ¡ clase se utiliza como base para las pruebas 
 * unitarias de las diferentes versiones de timbrados disponibles.
* @author  Eduardo Mares
* @version 0.0.0.1
* @since   2022-05-01
 */
public abstract class BaseStamp{
        
    protected final Utils settings = new Utils();
    
    /**
     * Timbra un documento CFDI con la versiÃ³n de repsuesta 1.
     * @param xml String de la ubicaciÃ³n del archivo que contiene el CFDI.
     * @param stampVersion indica la versiÃ³n del timbrado que se utilizara.
     * @param isBase64 indica si es base64.
     * @return SuccessV1Response
     * @see StampResponseV1
     * @throws IOException exception en caso de error.
     * @throws GeneralException exception en caso de error.
     * @throws AuthException exception en caso de error.
     */
    public abstract SuccessV1Response StampResponseV1(String fileName, String stampVersion, boolean signed, boolean isBase64) throws AuthException, GeneralException, IOException;
    
    /**
     * Timbra un documento CFDI con la versión de repsuesta 2.
     * @param xml String de la ubicación del archivo que contiene el CFDI.
     * @param stampVersion indica la versión del timbrado que se utilizara.
     * @param isBase64 indica si es base64.
     * @return SuccessV2Response
     * @see StampResponseV2
     * @throws IOException exception en caso de error.
     * @throws GeneralException exception en caso de error.
     * @throws AuthException exception en caso de error.
     */
    public abstract SuccessV2Response StampResponseV2(String fileName, String stampVersion, boolean signed, boolean isBase64) throws AuthException, GeneralException, IOException;
    
    /**
     * Timbra un documento CFDI con la versión de repsuesta 3.
     * @param xml String de la ubicación del archivo que contiene el CFDI.
     * @param stampVersion indica la versión del timbrado que se utilizara.
     * @param isBase64 indica si es base64.
     * @return SuccessV3Response
     * @see StampResponseV3
     * @throws IOException exception en caso de error.
     * @throws GeneralException exception en caso de error.
     * @throws AuthException exception en caso de error.
     */
    public abstract SuccessV3Response StampResponseV3(String fileName, String stampVersion, boolean signed, boolean isBase64) throws AuthException, GeneralException, IOException;

    /**
     * Timbra un documento CFDI con la versión de repsuesta 4.
     * @param xml String de la ubicación del archivo que contiene el CFDI.
     * @param stampVersion indica la versión del timbrado que se utilizara.
     * @param isBase64 indica si es base64.
     * @return StampResponseV4
     * @see SuccessV4Response
     * @throws IOException exception en caso de error.
     * @throws GeneralException exception en caso de error.
     * @throws AuthException exception en caso de error.
     */
    public abstract SuccessV4Response StampResponseV4(String fileName, String stampVersion, boolean signed, boolean isBase64) throws AuthException, GeneralException, IOException;
}
