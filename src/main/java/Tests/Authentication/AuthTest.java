package Tests.Authentication;

import Exceptions.GenaralException;
import Services.Authentication.SWAuthenticationService;
import Services.SWService;
import Utils.Responses.AuthResponse;
import Utils.Responses.IResponse;

/**
 * Created by asalvio on 09/02/2017.
 */
public class AuthTest {
    public static void main(String[] args) throws GenaralException {
        //http://swservicestest.sw.com.mx/seguridad/autenticar
        SWAuthenticationService auth;
        auth = new SWAuthenticationService("demo","12345678A","d-error");
        IResponse response;
        response =  auth.Token();
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Msg);
    }
}
