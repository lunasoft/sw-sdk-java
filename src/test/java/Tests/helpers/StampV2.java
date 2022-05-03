package Tests.helpers;

import Tests.Utils;
import java.io.IOException;
import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Stamp.SWStampServiceV2;
import Utils.Responses.Stamp.SuccessV1Response;
import Utils.Responses.Stamp.SuccessV2Response;
import Utils.Responses.Stamp.SuccessV3Response;
import Utils.Responses.Stamp.SuccessV4Response;

/**
* Constructor del servicio de Timbrado versión 2
* @author  Eduardo Mares
* @version 0.0.0.1
* @since   2022-05-01
*/
public class StampV2 extends BaseStamp{

    private final SWStampServiceV2 stamp;

    /**
    * Constructor de la clase.
    * @param isToken indica si el servicio de timbrado se autentificara con token o usuario y contraseña
     * @throws AuthException exception en caso de error.
    */
    public StampV2(Boolean isToken) throws AuthException
    {        
        if(!isToken)
            stamp = new SWStampServiceV2(Utils.userSW, Utils.passwordSW, Utils.urlSW);
        else
            stamp = new SWStampServiceV2(Utils.tokenSW, Utils.urlSW);     
    }
    
    public SuccessV1Response StampResponseV1(String fileName, String stampVersion, boolean signed,
            boolean isBase64) throws AuthException, GeneralException, IOException {
                return (SuccessV1Response) stamp.Stamp(settings.getCFDI(fileName, true, "4.0", isBase64), "v1", isBase64);
    }

    @Override
    public SuccessV2Response StampResponseV2(String fileName, String stampVersion, boolean signed,
            boolean isBase64) throws AuthException, GeneralException, IOException {
                return (SuccessV2Response) stamp.Stamp(settings.getCFDI(fileName, true, "4.0", isBase64), "v2", isBase64);
    }

    @Override
    public SuccessV3Response StampResponseV3(String fileName, String stampVersion, boolean signed,
            boolean isBase64) throws AuthException, GeneralException, IOException {
                return (SuccessV3Response) stamp.Stamp(settings.getCFDI(fileName, true, "4.0", isBase64), "v3", isBase64);
    }

    @Override
    public SuccessV4Response StampResponseV4(String fileName, String stampVersion, boolean signed,
            boolean isBase64) throws AuthException, GeneralException, IOException {
                return (SuccessV4Response) stamp.Stamp(settings.getCFDI(fileName, true, "4.0", isBase64), "v4", isBase64);
    }    
}
