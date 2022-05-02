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
* Nomina12Test
* Clase para UT de los servicio de timbrado disponibles utilizando diferentes ejemplos con complemento de nomina
* @author  Eduardo Mares
* @version 0.0.0.1
* @since   2022-05-01
*/
public class Nomina12Test {
    
    StampService stampService = new StampService(false);

    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina mediante el servicio de timbrado versión 1 de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 1
    */
    @Test
    public void testStampV1NominaResponseV1() {
        try {            
            SuccessV1Response response = stampService.StampResponseV1("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina.xml", "V1", true, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.tfd);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }

    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina extraordinaria mediante el servicio de timbrado versión 2 de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 2
    */
    @Test
    public void testStampV2NominaExtraordinariaResponseV2() {
        try {            
            SuccessV2Response response = stampService.StampResponseV2("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_Extraordinaria.xml", "V2", true, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.tfd);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }

    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina con horas extra mediante el servicio de timbrado versión 2 de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 3 en base64
    */
    @Test
    public void testStampV2NominaHorasExtraResponseV3B64() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_HorasExtra.xml", "V2", true, true);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }
    
    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina con incapacidades mediante el servicio de timbrado versión 1 sin sellar (Issue) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Test
    public void testIssueV1NominaIncapacidadesResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_Incapacidades.xml", "IssueV1", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }
    
    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina jubilación pensión retiro mediante el servicio de timbrado versión 2 sin sellar (Issue) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 3
    */
    @Test
    public void testIssueV2NominaJubilacionPensionRetiroResponseV3() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_JubilacionPensionRetiro.xml", "IssueV2", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }
    
    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina jubilación pensión retiro mediante el servicio de timbrado versión 2 sin sellar (Issue) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Test
    public void testIssueV2NominaJubilacionPensionRetiro2ResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_JubilacionPensionRetiro2.xml", "IssueV2", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }
        
    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina separación indemnización mediante el servicio de timbrado versión 1 de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 3
    */
    @Test
    public void testStampV1NominaSeparacionIndemnizacionResponseV3() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_SeparacionIndemnizacion.xml", "V1", true, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }

    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina sin deducciones mediante el servicio de timbrado versión 2 de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Test
    public void testStampV2NominaSinDeduccionesResponseV4() {
        try {            
            SuccessV2Response response = stampService.StampResponseV2("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_SinDeducciones.xml", "V2", true, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.tfd);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }

    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 1
    */
    @Test
    public void testIssueJsonV1NominaResponseV1() {
        try {            
            SuccessV1Response response = stampService.StampResponseV1("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina.json", "IssueJsonV1", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.tfd);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }

    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina extraordinaria mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 2
    */
    @Test
    public void testIssueJsonV1NominaExtraordinariaResponseV2() {
        try {            
            SuccessV2Response response = stampService.StampResponseV2("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_Extraordinaria.json", "IssueJsonV1", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.tfd);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }

    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina con horas extra mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 3
    */
    @Test
    public void testIssueJsonV1NominaHorasExtraResponseV3() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_HorasExtra.json", "IssueJsonV1", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }
    
    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina con incapacidades mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Test
    public void testIssueJsonV1NominaIncapacidadesResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_Incapacidades.json", "IssueJsonV1", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }
    
    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina jubilación pensión retiro mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 3
    */
    @Test
    public void testIssueJsonV1NominaJubilacionPensionRetiroResponseV3() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_JubilacionPensionRetiro.json", "IssueJsonV1", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }
    
    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina jubilación pensión retiro mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Test
    public void testIssueJsonV1NominaJubilacionPensionRetiro2ResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_JubilacionPensionRetiro2.json", "IssueJsonV1", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }
        
    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina separación indemnización mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 3
    */
    @Test
    public void testIssueJsonV1NominaSeparacionIndemnizacionResponseV3() {
        try {            
            SuccessV3Response response = stampService.StampResponseV3("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_SeparacionIndemnizacion.json", "IssueJsonV1", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }

    /**
    * Timbrado de CFDI versión 4.0 con complemento de nómina sin deducciones mediante el servicio de timbrado versión 1 (Json) de la librería sdk-java18 mediante usuario y contraseña con respuesta versión 4
    */
    @Test
    public void testIssueJsonV1NominaSinDeduccionesResponseV4() {
        try {            
            SuccessV4Response response = stampService.StampResponseV4("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina_SinDeducciones.json", "IssueJsonV1", false, false);
            Assert.assertNotNull(response);
            Assert.assertNotNull(response.Status);
            Assert.assertNotNull(response.cfdi);
            Assert.assertTrue("success".equalsIgnoreCase(response.Status));
        } catch (AuthException ex) {
            Assert.assertNotNull(ex);
        } catch (GeneralException ex) {
            Assert.assertNotNull(ex);
        } catch (IOException ex) {
            Assert.assertNotNull(ex);
        }
    }
}
