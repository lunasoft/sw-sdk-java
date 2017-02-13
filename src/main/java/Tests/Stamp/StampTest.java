package Tests.Stamp;

import Exceptions.AuthException;
import Exceptions.GenaralException;
import Services.Stamp.SWStampService;


import Utils.Responses.StampResponse;

import java.io.*;


/**
 * Created by asalvio on 09/02/2017.
 */
public class StampTest {
    public static void main(String[] args) throws AuthException, GenaralException, IOException {
        File xml_file = new File("C:\\Users\\asalvio\\Documents\\WORKSPACE\\JAVA\\SW-JAVA\\src\\main\\java\\Tests\\invoice.xml");
        SWStampService api = new SWStampService("tetete","http://swservicestest.sw.com.mx/cfdi33/timbrar/");
        StampResponse response = null;
        response = (StampResponse) api.Stamp(xml_file,"v1");
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Msg);


    }
}
