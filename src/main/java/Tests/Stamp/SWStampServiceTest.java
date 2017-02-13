package Tests.Stamp;

import Services.Stamp.SWStampService;
import Tests.Helpers;
import Utils.Constants;
import Utils.Responses.StampResponse;
import junit.framework.TestCase;

import java.io.File;
import java.net.URL;

/**
 * Created by asalvio on 10/02/2017.
 */
public class SWStampServiceTest extends TestCase {
    public void testStampSTRING_USER_PASSWORD() throws Exception {
        SWStampService sw = new SWStampService("demo","12345678A","http://swservicestest.sw.com.mx/cfdi33/timbrar/");
        StampResponse response = (StampResponse) sw.Stamp(Helpers.xmlTest,"v1");
        System.out.println(response.Msg);
    }
    public void testStampSTRING_XML() throws Exception {
        SWStampService sw = new SWStampService("token","http://swservicestest.sw.com.mx/cfdi33/timbrar/");
        sw.Stamp(Helpers.xmlTest,"v1");
        StampResponse response = (StampResponse) sw.Stamp(Helpers.xmlTest,"v1");
        System.out.println(response.Msg);
    }

    public void testStampLOOP_TOKEN_UNEXPECTED_NULL() throws Exception {
        SWStampService sw = new SWStampService("demo","12345678A","http://swservicestest.sw.com.mx/cfdi33/timbrar/");
        sw.Stamp(Helpers.xmlTest,"v1");

        int requests = 50;
        int null_token = requests/2;
        int success_counter = 0;
        for (int i = 0;i<requests;i++){
            if(i==null_token){
                sw.setToken(null);
            }
            StampResponse response = (StampResponse) sw.Stamp(Helpers.xmlTest,"v1");
            if (response.HttpStatusCode==200){
                success_counter++;
            }


        }
        System.out.println(success_counter+" peticiones exitosas de "+requests+" intentos...");

    }

    public void testStampBINARY_XML () throws Exception {
        File f = new File("C:\\Users\\asalvio\\Documents\\WORKSPACE\\JAVA\\SW-JAVA\\src\\main\\java\\Tests\\invoice.xml");
        SWStampService sw = new SWStampService("token","http://swservicestest.sw.com.mx/cfdi33/timbrar/");
        sw.Stamp(Helpers.xmlTest,"v1");
        StampResponse response = (StampResponse) sw.Stamp(f,"v1");
        System.out.println(response.Msg);
    }

}