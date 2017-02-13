package Tests.Authentication;

import Services.Authentication.SWAuthenticationService;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by asalvio on 10/02/2017.
 */

public class SWAuthenticationServiceTest extends TestCase {
    public void testTokenBAD_LOGIN() throws Exception {
        SWAuthenticationService sa = new SWAuthenticationService("demo","123456789","http://swservicestest.sw.com.mx/seguridad/autenticar");
        System.out.println(sa.Token());
    }

    public void testTokenEMPTYUSER() throws Exception {
        SWAuthenticationService sa = new SWAuthenticationService("","12345678A","http://swservicestest.sw.com.mx/seguridad/autenticar");
        System.out.println(sa.Token());
    }

    public void testTokenEMPTY_PASSWORD() throws Exception {
        SWAuthenticationService sa = new SWAuthenticationService("demo","","http://swservicestest.sw.com.mx/seguridad/autenticar");
        System.out.println(sa.Token());
    }

    public void testTokenBAD_URL() throws Exception {
        SWAuthenticationService sa = new SWAuthenticationService("demo","","http://swservicestest.sw.com.mx/seguridad/autenticarINCORRECT");
        System.out.println(sa.Token());
    }

    public void testTokenEMPTY_URL() throws Exception {
        SWAuthenticationService sa = new SWAuthenticationService("demo","","");
        System.out.println(sa.Token());
    }

}