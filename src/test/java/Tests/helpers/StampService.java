package Tests.helpers;

import java.io.IOException;
import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Responses.Stamp.SuccessV1Response;
import Utils.Responses.Stamp.SuccessV2Response;
import Utils.Responses.Stamp.SuccessV3Response;
import Utils.Responses.Stamp.SuccessV4Response;

/**
* Clase organizadora de las diferentes versiones de timbrados disponibles
* @author  Eduardo Mares
* @version 0.0.0.1
* @since   2022-05-01
*/
public class StampService extends BaseStamp {

    private boolean isToken;

    /**
    * Constructor de la clase.
    * @param isToken indica si el servicio de timbrado se autentificara con token o usuario y contraseña
    */
    public StampService(boolean isToken)
    {
        this.isToken = isToken;
    }

    @Override    
    public SuccessV1Response StampResponseV1(String fileName, String stampVersion, boolean signed,
            boolean isBase64) throws AuthException, GeneralException, IOException {

        if(stampVersion.equalsIgnoreCase("V1"))
        {
            StampV1 stampV1 = new StampV1(isToken);
            return stampV1.StampResponseV1(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("V2"))
        {
            StampV2 stampV2 = new StampV2(isToken);
            return stampV2.StampResponseV1(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("IssueV1"))
        {
            IssueV1 issueV1 = new IssueV1(isToken);
            return issueV1.StampResponseV1(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("IssueV2"))
        {
            IssueV2 issueV2 = new IssueV2(isToken);
            return issueV2.StampResponseV1(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("IssueJsonV1"))
        {
            IssueJsonV1 issueJsonV1 = new IssueJsonV1(isToken);
            return issueJsonV1.StampResponseV1(fileName, stampVersion, signed, isBase64);
        }
        else
            throw new UnsupportedOperationException();
    }

    @Override
    public SuccessV2Response StampResponseV2(String fileName, String stampVersion, boolean signed,
            boolean isBase64) throws AuthException, GeneralException, IOException  {
                
        if(stampVersion.equalsIgnoreCase("V1"))
        {
            StampV1 stampV1 = new StampV1(isToken);
            return stampV1.StampResponseV2(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("V2"))
        {
            StampV2 stampV2 = new StampV2(isToken);
            return stampV2.StampResponseV2(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("IssueV1"))
        {
            IssueV1 issueV1 = new IssueV1(isToken);
            return issueV1.StampResponseV2(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("IssueV2"))
        {
            IssueV2 issueV2 = new IssueV2(isToken);
            return issueV2.StampResponseV2(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("IssueJsonV1"))
        {
            IssueJsonV1 issueJsonV1 = new IssueJsonV1(isToken);
            return issueJsonV1.StampResponseV2(fileName, stampVersion, signed, isBase64);
        }
        else
            throw new UnsupportedOperationException();
    }

    @Override
    public SuccessV3Response StampResponseV3(String fileName, String stampVersion, boolean signed,
            boolean isBase64) throws AuthException, GeneralException, IOException {
                
        if(stampVersion.equalsIgnoreCase("V1"))
        {
            StampV1 stampV1 = new StampV1(isToken);
            return stampV1.StampResponseV3(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("V2"))
        {
            StampV2 stampV2 = new StampV2(isToken);
            return stampV2.StampResponseV3(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("IssueV1"))
        {
            IssueV1 issueV1 = new IssueV1(isToken);
            return issueV1.StampResponseV3(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("IssueV2"))
        {
            IssueV2 issueV2 = new IssueV2(isToken);
            return issueV2.StampResponseV3(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("IssueJsonV1"))
        {
            IssueJsonV1 issueJsonV1 = new IssueJsonV1(isToken);
            return issueJsonV1.StampResponseV3(fileName, stampVersion, signed, isBase64);
        }
        else
            throw new UnsupportedOperationException();
    }

    @Override
    public SuccessV4Response StampResponseV4(String fileName, String stampVersion, boolean signed,
            boolean isBase64) throws AuthException, GeneralException, IOException  {
                
        if(stampVersion.equalsIgnoreCase("V1"))
        {
            StampV1 stampV1 = new StampV1(isToken);
            return stampV1.StampResponseV4(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("V2"))
        {
            StampV2 stampV2 = new StampV2(isToken);
            return stampV2.StampResponseV4(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("IssueV1"))
        {
            IssueV1 issueV1 = new IssueV1(isToken);
            return issueV1.StampResponseV4(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("IssueV2"))
        {
            IssueV2 issueV2 = new IssueV2(isToken);
            return issueV2.StampResponseV4(fileName, stampVersion, signed, isBase64);
        }
        else if(stampVersion.equalsIgnoreCase("IssueJsonV1"))
        {
            IssueJsonV1 issueJsonV1 = new IssueJsonV1(isToken);
            return issueJsonV1.StampResponseV4(fileName, stampVersion, signed, isBase64);
        }
        else
            throw new UnsupportedOperationException();
    }    
}
