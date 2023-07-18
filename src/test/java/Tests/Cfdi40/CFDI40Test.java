package Tests.Cfdi40;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Ignore;

import java.io.IOException;
import Exceptions.AuthException;
import Tests.helpers.StampService;
import Exceptions.GeneralException;
import Utils.Responses.Stamp.SuccessV1Response;
import Utils.Responses.Stamp.SuccessV2Response;
import Utils.Responses.Stamp.SuccessV3Response;
import Utils.Responses.Stamp.SuccessV4Response;

/**
* CFDI40Test
* Clase para UT de los servicio de timbrado disponibles utilizando diferentes ejemplos de CFDI 4.0
* @author  Eduardo Mares
* @version 0.0.0.1
* @since   2022-05-01
*/
public class CFDI40Test {
    
    StampService stampService = new StampService(false);
    StampService stampServiceToken = new StampService(true);

    /**
    * Timbrado de CFDI versión 4.0 de tipo ingreso mediante el servicio de timbrado versión 1 de la libreria sdk-java18 mediante usuario y contrasena con respuesta versión 1
    */
    @Ignore
    public void testStampV1IngresoResponseV1() {
        try {            
            SuccessV1Response response = stampService.StampResponseV1("src/test/resources/CFDI40/CFDI40/CFDI40_Ingreso.xml", "V1", true, false);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso con nodo parte mediante el servicio de timbrado versión 2 de la libreria sdk-java18 mediante Token con respuesta versión 2
    */
    @Ignore
    public void testStampV2IngresoKitParteResponseV2() {
        try {            
            SuccessV2Response response = stampServiceToken.StampResponseV2("src/test/resources/CFDI40/CFDI40/CFDI40_Ingreso_KitParte.xml", "V2", true, false);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso con nodo ACuentaTerceros mediante el servicio de timbrado versión 2 de la libreria sdk-java18 mediante usuario y contrasena con respuesta versión 3 en base64
    */
    @Ignore
    public void testStampV2IngresoACuentaTercerosResponseV3B64() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/CFDI40/CFDI40_Ingreso_ACuentaTerceros.xml", "V2", true, true);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso global mediante el servicio de timbrado versión 1 sin sellar (Issue) de la libreria sdk-java18 mediante usuario y contrasena con respuesta versión 4
    */
    @Ignore
    public void testIssueV1IngresoGlobalResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/CFDI40/CFDI40_Ingreso_Global.xml", "IssueV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso global extranjero mediante el servicio de timbrado versión 2 sin sellar (Issue) de la libreria sdk-java18 mediante Token con respuesta versión 4
    */
    @Ignore
    public void testIssueV2IngresoGlobalExtranjeroResponseV2() {
        try {            
            SuccessV2Response response = stampServiceToken.StampResponseV2("src/test/resources/CFDI40/CFDI40/CFDI40_Ingreso_GlobalExtranjero.xml", "IssueV2", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo egreso mediante el servicio de timbrado versión 2 sin sellar (Issue) de la libreria sdk-java18 mediante usuario y contrasena con respuesta versión 4
    */
    @Ignore
    public void testIssueV2EgresoNotaDeCreditoResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/CFDI40/CFDI40_Egreso_NotaDeCredito.xml", "IssueV2", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo traslado mediante el servicio de timbrado versión 2 sin sellar (Issue) de la libreria sdk-java18 mediante usuario y contrasena con respuesta versión 4
    */
    @Ignore
    public void testIssueV2TrasladoResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/CFDI40/CFDI40_Traslado.xml", "IssueV2", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso mediante el servicio de timbrado versión 1 (Json) de la libreria sdk-java18 mediante usuario y contrasena con respuesta versión 1
    */
    @Ignore
    public void testIssueJsonV1IngresoResponseV1() {
        try {            
            SuccessV1Response response = stampService.StampResponseV1("src/test/resources/CFDI40/CFDI40/CFDI40_Ingreso.json", "IssueJsonV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso con nodo parte mediante el servicio de timbrado versión 1 (Json) de la libreria sdk-java18 mediante Token con respuesta versión 2
    */
    @Ignore
    public void testIssueJsonV1IngresoKitParteResponseV2() {
        try {            
            SuccessV2Response response = stampServiceToken.StampResponseV2("src/test/resources/CFDI40/CFDI40/CFDI40_Ingreso_KitParte.json", "IssueJsonV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso con nodo ACuentaTerceros mediante el servicio de timbrado versión 1 (Json) de la libreria sdk-java18 mediante usuario y contrasena con respuesta versión 3
    */
    @Ignore
    public void testIssueJsonV1IngresoACuentaTercerosResponseV3B64() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/CFDI40/CFDI40_Ingreso_ACuentaTerceros.json", "IssueJsonV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso global mediante el servicio de timbrado versión 1 (Json) de la libreria sdk-java18 mediante usuario y contrasena con respuesta versión 4
    */
    @Ignore
    public void testIssueJsonV1IngresoGlobalResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/CFDI40/CFDI40_Ingreso_Global.json", "IssueJsonV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso global extranjero mediante el servicio de timbrado versión 1 (Json) de la libreria sdk-java18 mediante Token con respuesta versión 4 
    */
    @Ignore
    public void testIssueJsonV1IngresoGlobalExtranjeroResponseV4() {
        try {            
            SuccessV4Response response = stampServiceToken.StampResponseV4("src/test/resources/CFDI40/CFDI40/CFDI40_Ingreso_GlobalExtranjero.json", "IssueJsonV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo egreso mediante el servicio de timbrado versión 1 (Json) de la libreria sdk-java18 mediante usuario y contrasena con respuesta versión 4
    */
    @Ignore
    public void testIssueJsonV1EgresoNotaDeCreditoResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/CFDI40/CFDI40_Egreso_NotaDeCredito.json", "IssueJsonV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo traslado mediante el servicio de timbrado versión 1 (Json) de la libreria sdk-java18 mediante usuario y contrasena con respuesta versión 4
    */
    @Ignore
    public void testIssueJsonV1TrasladoResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/CFDI40/CFDI40_Traslado.json", "IssueJsonV1", false, false);
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
