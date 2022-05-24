package Tests.helpers;

import Tests.Utils;
import java.io.IOException;
import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Issue.SWIssueService;
import Utils.Responses.Stamp.SuccessV1Response;
import Utils.Responses.Stamp.SuccessV2Response;
import Utils.Responses.Stamp.SuccessV3Response;
import Utils.Responses.Stamp.SuccessV4Response;

/**
* Constructor del servicio de Timbrado versión 1 con CFDI sin sellar en formato Json
 * Es necesario contar con certificados vigentes configurados en su cuenta
 * de SW, de esta manera se realiza el sellado del documento generado.
* @author  Eduardo Mares
* @version 0.0.0.1
* @since   2022-05-01
*/
public class IssueJsonV1 extends BaseStamp{

    private final SWIssueService stamp;

    /**
    * Constructor de la clase.
    * @param isToken indica si el servicio de timbrado se autentificara con token o usuario y contraseña
     * @throws AuthException exception en caso de error.
    */
    public IssueJsonV1(Boolean isToken) throws AuthException
    {        
        if(!isToken)
            stamp = new SWIssueService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
        else
            stamp = new SWIssueService(Utils.tokenSW, Utils.urlSW);     
    }
    
    public SuccessV1Response StampResponseV1(String fileName, String stampVersion, boolean signed,
            boolean isBase64) throws AuthException, GeneralException, IOException {
                return (SuccessV1Response) stamp.IssueJson(settings.getJsonCFDI(fileName, isBase64), "v1");
    }

    @Override
    public SuccessV2Response StampResponseV2(String fileName, String stampVersion, boolean signed,
            boolean isBase64) throws AuthException, GeneralException, IOException {
                return (SuccessV2Response) stamp.IssueJson(settings.getJsonCFDI(fileName, isBase64), "v2");
    }

    @Override
    public SuccessV3Response StampResponseV3(String fileName, String stampVersion, boolean signed,
            boolean isBase64) throws AuthException, GeneralException, IOException {
                return (SuccessV3Response) stamp.IssueJson(settings.getJsonCFDI(fileName, isBase64), "v3");
    }

    @Override
    public SuccessV4Response StampResponseV4(String fileName, String stampVersion, boolean signed,
            boolean isBase64) throws AuthException, GeneralException, IOException {
                return (SuccessV4Response) stamp.IssueJson(settings.getJsonCFDI(fileName, isBase64), "v4");
    }    
}
