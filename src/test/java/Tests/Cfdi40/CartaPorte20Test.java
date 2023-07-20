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
* CartaPorte20Test
* Clase para UT de los servicio de timbrado disponibles utilizando diferentes ejemplos con complemento de carta porte
* @author  Eduardo Mares
* @version 0.0.0.1
* @since   2022-05-01
*/
public class CartaPorte20Test {
    
    StampService stampService = new StampService(false);

    /**
    * Timbrado de CFDI versión 4.0 de tipo ingreso con complemento carta porte de tipo autotransporte mediante el servicio de timbrado versión 1 de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 1
    */
    @Ignore
    public void testStampV1IngresoCartaPorteAutotransporteResponseV1() {
        try {            
            SuccessV1Response response = stampService.StampResponseV1("src/test/resources/CFDI40/CartaPorte20/CFDI40_Ingreso_CartaPorte_Autotransporte.xml", "V1", true, false);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso con complemento carta porte de tipo transporte ferroviario mediante el servicio de timbrado versión 2 de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 2
    */
    @Ignore
    public void testStampV2IngresoCartaPorteTransporteFerroviarioResponseV2() {
        try {            
            SuccessV2Response response = stampService.StampResponseV2("src/test/resources/CFDI40/CartaPorte20/CFDI40_Ingreso_CartaPorte_TransporteFerroviario.xml", "V2", true, false);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso con complemento carta porte de tipo transporte aéreo mediante el servicio de timbrado versión 2 de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 3 en base64
    */
    @Ignore
    public void testStampV2IngresoCartaPorteTransporteAereoResponseV3B64() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/CartaPorte20/CFDI40_Ingreso_CartaPorte_TransporteAereo.xml", "V2", true, true);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso con complemento carta porte de tipo transporte marítimo mediante el servicio de timbrado versión 2 de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Ignore
    public void testStampV2IngresoCartaPorteTransporteMaritimoResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/CartaPorte20/CFDI40_Ingreso_CartaPorte_TransporteMaritimo.xml", "V2", true, false);
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
    * Timbrado de CFDI versión 4.0 de tipo traslado con complemento carta porte de tipo autotransporte marítimo mediante el servicio de timbrado versión 1 sin sellar (Issue) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 1
    */
    @Ignore
    public void testIssueV1TrasladoCartaPorteAutotransporteResponseV1() {
        try {            
            SuccessV1Response response = stampService.StampResponseV1("src/test/resources/CFDI40/CartaPorte20/CFDI40_Traslado_CartaPorte_Autotransporte.xml", "IssueV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo traslado con complemento carta porte de tipo transporte aéreo mediante el servicio de timbrado versión 2 sin sellar (Issue) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 2
    */
    @Ignore
    public void testIssueV2TrasladoCartaPorteTransporteAereoResponseV2() {
        try {            
            SuccessV2Response response = stampService.StampResponseV2("src/test/resources/CFDI40/CartaPorte20/CFDI40_Traslado_CartaPorte_TransporteAereo.xml", "IssueV2", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo traslado con complemento carta porte de tipo transporte ferroviario mediante el servicio de timbrado versión 2 sin sellar (Issue) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 3
    */
    @Ignore
    public void testIssueV2TrasladoCartaPorteTransporteFerroviarioResponseV3() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/CartaPorte20/CFDI40_Traslado_CartaPorte_TransporteFerroviario.xml", "IssueV2", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo traslado con complemento carta porte de tipo transporte marítimo mediante el servicio de timbrado versión 2 sin sellar (Issue) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Ignore
    public void testIssueV2TrasladoCartaPorteTransporteMaritimoResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/CartaPorte20/CFDI40_Traslado_CartaPorte_TransporteMaritimo.xml", "IssueV2", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso con complemento carta porte de tipo autotransporte mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 1
    */
    @Ignore
    public void testIssueJsonV1IngresoCartaPorteAutotransporteResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/CartaPorte20/CFDI40_Ingreso_CartaPorte_Autotransporte.json", "IssueJsonV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo ingreso con complemento carta porte de tipo transporte ferroviario mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Ignore
    public void testIssueJsonV1IngresoCartaPorteTransporteFerroviarioResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/CartaPorte20/CFDI40_Ingreso_CartaPorte_TransporteFerroviario.json", "IssueJsonV1", false, false);
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
    * Timbrado de CFDI versión 4.0 de tipo traslado con complemento carta porte de tipo autotransporte mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Ignore
    public void testIssueJsonV1TrasladoCartaPorteAutotransporteResponseV4B64() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/CartaPorte20/CFDI40_Traslado_CartaPorte_Autotransporte.json", "IssueJsonV1", false, false);
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
