package Tests.Stamp;

import Exceptions.GenaralException;
import Services.Stamp.SWStampService;
import Tests.Utils;
import Utils.Responses.IResponse;
import Utils.Responses.StampResponse;
import junit.framework.TestCase;
import org.junit.Assert;

import java.io.File;


public class SWStampServiceTest extends TestCase {

    public void testStampXML_STRING() throws Exception {
        SWStampService api = new SWStampService(Utils.dummy_token,"http://swservicestest.azurewebsites.net");
        IResponse response = null;
        response = api.Stamp(Utils.dummy_xml_string,"v1");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Data);
    }

   


    public void testStampSUCCESS() throws Exception {
        SWStampService api = new SWStampService("tetete","d-success");
        IResponse response = null;
        response = api.Stamp("XML EN STRING","v1");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Data);
    }

    public void testStampFAILURE() throws Exception {
        SWStampService api = new SWStampService("tetete","d-fail");
        IResponse response = null;
        response =  api.Stamp("XML EN STRING","v1");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Data);
    }
    public void testStampERROR() throws Exception {
        try{
            SWStampService api = new SWStampService("tetete","d-error");
            StampResponse response = null;
            response = (StampResponse) api.Stamp("XML EN STRING","v1");
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.Data);
        }
        catch(GenaralException e){
            System.out.println("Correcto excepcion lanzada");
            Assert.assertNotNull("some bad happend", e);
        }

    }

}