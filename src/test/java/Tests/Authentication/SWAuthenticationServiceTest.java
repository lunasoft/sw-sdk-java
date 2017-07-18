package Tests.Authentication;

import Exceptions.GeneralException;
import Services.Authentication.SWAuthenticationService;
import Utils.Responses.IResponse;
import junit.framework.TestCase;
import org.junit.Assert;

/**
 * Created by asalvio on 15/02/2017.
 */
public class SWAuthenticationServiceTest extends TestCase {

    public  void testToken() throws Exception {
        SWAuthenticationService auth;
        auth = new SWAuthenticationService("mgarcia@e3creativa.com","EXITO2016","https://services.sw.com.mx");
        IResponse response;
        response =  auth.Token();
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.messageDetail);
        System.out.println(response.message);
        System.out.println(response.token);

    }


}