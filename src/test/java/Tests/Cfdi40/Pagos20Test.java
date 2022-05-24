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
* Pagos20Test
* Clase para UT de los servicio de timbrado disponibles utilizando diferentes ejemplos con complemento de pagos
* @author  Eduardo Mares
* @version 0.0.0.1
* @since   2022-05-01
*/
public class Pagos20Test {
    
    StampService stampService = new StampService(false);

    /**
    * Timbrado de CFDI versión 4.0 de tipo pago mediante el servicio de timbrado versión 1 de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 1
    */
    @Test
    public void testStampV1PagoResponseV1() {
        try {            
            SuccessV1Response response = stampService.StampResponseV1("src/test/resources/CFDI40/Pagos20/CFDI40_Pago.xml", "V1", true, false);
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
    * Timbrado de CFDI versión 4.0 de tipo pago con moneda de pago en USD y monda del documento relacionado en MXN mediante el servicio de timbrado versión 2 de la libreria sdk-java18 mediante usuario y contraseña con respuesta versión 2
    */
    @Test
    public void testStampV2PagoDolarResponseV2() {
        try {            
            SuccessV2Response response = stampService.StampResponseV2("src/test/resources/CFDI40/Pagos20/CFDI40_Pago_Dolar.xml", "V2", true, false);
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
    * Timbrado de CFDI versión 4.0 de tipo pago con moneda de pago en MXN y monda del documento relacionado en USD mediante el servicio de timbrado versión 2 de la libreria sdk-java18 mediante usuario y contraseña con respuesta versión 3 en base64
    */
    @Test
    public void testStampV2PagoDoctoRelacionadoEnDolarResponseV3B64() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/Pagos20/CFDI40_Pago_DoctoRelacionadoEnDolar.xml", "V2", true, true);
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
    * Timbrado de CFDI versión 4.0 de tipo pago con moneda de pago en EUR y monda del documento relacionado en USD mediante el servicio de timbrado versión 1 sin sellar (Issue) de la libreria sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Test
    public void testIssueV1PagoEURyDRenUSDResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/Pagos20/CFDI40_Pago_EURyDRenUSD.xml", "IssueV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo pago con dos monedas de pago distintas y monda del documento relacionado en USD mediante el servicio de timbrado versión 2 sin sellar (Issue) de la libreria sdk-java18 mediante usuario y contraseña con respuesta versión 3
    */
    @Test
    public void testIssueV2PagoMonedasDistintasyDRenUSDResponseV3() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/Pagos20/CFDI40_Pago_MonedasDistintasyDRenUSD.xml", "IssueV2", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo pago con con Factoraje mediante el servicio de timbrado versión 2 sin sellar (Issue) de la libreria sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Test
    public void testIssueV2PagoFactorajeResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/Pagos20/CFDI40_Pago_Factoraje.xml", "IssueV2", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo pago mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 1
    */
    @Test
    public void testIssueJsonV1PagoResponseV1() {
        try {            
            SuccessV1Response response = stampService.StampResponseV1("src/test/resources/CFDI40/Pagos20/CFDI40_Pago.json", "IssueJsonV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo pago con moneda de pago en USD y monda del documento relacionado en MXN mediante el servicio de timbrado versión 1 (Json) de la libreria sdk-java18 mediante usuario y contraseña con respuesta versión 2
    */
    @Test
    public void testIssueJsonV1PagoDolarResponseV2() {
        try {            
            SuccessV2Response response = stampService.StampResponseV2("src/test/resources/CFDI40/Pagos20/CFDI40_Pago_Dolar.json", "IssueJsonV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo pago con moneda de pago en EUR y monda del documento relacionado en USD mediante el servicio de timbrado versión 1 (Json) de la libreria sdk-java18 mediante usuario y contraseña con respuesta versión 3
    */
    @Test
    public void testIssueJsonV1PagoEURyDRenUSDResponseV3() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/Pagos20/CFDI40_Pago_EURyDRenUSD.json", "IssueJsonV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo pago con dos monedas de pago distintas y monda del documento relacionado en USD mediante el servicio de timbrado versión 1 (Json) de la libreria sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Test
    public void testIssueV4PagoMonedasDistintasyDRenUSDResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/Pagos20/CFDI40_Pago_MonedasDistintasyDRenUSD.json", "IssueJsonV1", false, false);
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
