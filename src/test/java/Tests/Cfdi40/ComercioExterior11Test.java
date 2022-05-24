package Tests.Cfdi40;

import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import Exceptions.AuthException;
import Tests.helpers.StampService;
import Exceptions.GeneralException;
import Utils.Responses.Stamp.SuccessV1Response;
import Utils.Responses.Stamp.SuccessV2Response;
import Utils.Responses.Stamp.SuccessV3Response;
import Utils.Responses.Stamp.SuccessV4Response;

/**
* ComercioExterior11Test
* Clase para UT de los servicio de timbrado disponibles utilizando diferentes ejemplos con complemento de comercio exterior
* @author  Eduardo Mares
* @version 0.0.0.1
* @since   2022-05-01
*/
public class ComercioExterior11Test {
    
    StampService stampService = new StampService(false);

    /**
    * Timbrado de CFDI versión 4.0 de tipo ingreso con complemento comercio exterior mediante el servicio de timbrado versión 1 de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 1
    */
    @Test
    public void testStampV1ComercioExteriorResponseV1() {
        try {            
            SuccessV1Response response = stampService.StampResponseV1("src/test/resources/CFDI40/ComercioExterior11/CFDI40_ComercioExterior.xml", "V1", true, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.tfd);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.fail(ex.getMessage());
        } catch (GeneralException ex) {
            Assert.fail(ex.getMessage());
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }
    }

    /**
    * Timbrado de CFDI versión 4.0 de tipo ingreso con complemento comercio exterior con diferentes monedas mediante el servicio de timbrado versión 2 de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 2 en base64
    */
    @Test
    public void testStampV2ComercioExteriorDiferentesMonedasResponseV2B64() {
        try {            
            SuccessV2Response response = stampService.StampResponseV2("src/test/resources/CFDI40/ComercioExterior11/CFDI40_ComercioExterior_DiferentesMonedas.xml", "V2", true, true);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.tfd);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.fail(ex.getMessage());
        } catch (GeneralException ex) {
            Assert.fail(ex.getMessage());
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }
    }
    
    /**
    * Timbrado de CFDI versión 4.0 de tipo ingreso con complemento comercio exterior con diferentes monedas mediante el servicio de timbrado versión 1 sin sellar (Issue) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Test
    public void testIssueV1ComercioExteriorDiferentesMonedasResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/ComercioExterior11/CFDI40_ComercioExterior_DiferentesMonedas.xml", "IssueV1", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.fail(ex.getMessage());
        } catch (GeneralException ex) {
            Assert.fail(ex.getMessage());
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }
    }
    
    /**
    * Timbrado de CFDI versión 4.0 de tipo ingreso con complemento comercio exterior mediante el servicio de timbrado versión 2 sin sellar (Issue) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 3
    */
    @Test
    public void testIssueV2ComercioExteriorResponseV3() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/ComercioExterior11/CFDI40_ComercioExterior.xml", "IssueV2", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.fail(ex.getMessage());
        } catch (GeneralException ex) {
            Assert.fail(ex.getMessage());
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }
    }
    
    /**
    * Timbrado de CFDI versión 4.0 de tipo ingreso con complemento comercio exterior mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 3 
    */
    @Test
    public void testIssueJsonV1ComercioExteriorResponseV3() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/ComercioExterior11/CFDI40_ComercioExterior.json", "IssueJsonV1", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.fail(ex.getMessage());
        } catch (GeneralException ex) {
            Assert.fail(ex.getMessage());
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }
    }

    /**
    * Timbrado de CFDI versión 4.0 de tipo ingreso con complemento comercio exterior con diferentes monedas mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Test
    public void testIssueJsonV1ComercioExteriorDiferentesMonedasResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/ComercioExterior11/CFDI40_ComercioExterior_DiferentesMonedas.json", "IssueJsonV1", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.fail(ex.getMessage());
        } catch (GeneralException ex) {
            Assert.fail(ex.getMessage());
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }
    }    
}
