package Tests;

import Exceptions.AuthException;
import Services.Stamp.SWStampService;
import Utils.Constants;
import Utils.Responses.StampResponse;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by asalvio on 09/02/2017.
 */
public class StampTest {
    public static void main(String[] args) throws AuthException {

        SWStampService api = new SWStampService("tetete","http://swservicestest.sw.com.mx/cfdi33/timbrar/");
        StampResponse response = (StampResponse) api.Stamp(Constants.xmlTest,"v1");
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Msg);
    }
}
