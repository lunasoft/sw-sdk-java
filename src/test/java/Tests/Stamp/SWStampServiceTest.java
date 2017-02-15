package Tests.Stamp;

import Exceptions.GenaralException;
import Services.Stamp.SWStampService;
import Utils.Responses.IResponse;
import Utils.Responses.StampResponse;
import junit.framework.TestCase;
import org.junit.Assert;

/**
 * Created by asalvio on 15/02/2017.
 */
public class SWStampServiceTest extends TestCase {
    public void testStampSUCCESS() throws Exception {
        SWStampService api = new SWStampService("tetete","d-success");
        IResponse response = null;
        response = api.Stamp("XML EN STRING","v1");
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Msg);
    }

    public void testStampFAILURE() throws Exception {
        SWStampService api = new SWStampService("tetete","d-fail");
        IResponse response = null;
        response =  api.Stamp("XML EN STRING","v1");
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Msg);
    }
    public void testStampERROR() throws Exception {
        try{
            SWStampService api = new SWStampService("tetete","d-error");
            StampResponse response = null;
            response = (StampResponse) api.Stamp("XML EN STRING","v1");
            System.out.println(response.HttpStatusCode);
            System.out.println(response.Msg);
        }
        catch(GenaralException e){
            System.out.println("Correcto excepcion lanzada");
            Assert.assertNotNull("some bad happend", e);
        }

    }

}