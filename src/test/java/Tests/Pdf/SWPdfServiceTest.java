package Tests.Pdf;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Issue.SWIssueService;
import Services.Pdf.SWPdfService;
import Tests.Utils;
import Utils.Helpers.PdfTemplates;
import Utils.Responses.IResponse;
import Utils.Responses.Pdf.PdfResponse;
import Utils.Responses.Stamp.SuccessV3Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class SWPdfServiceTest {
    Utils settings = new Utils();
    String baseDir = "src/test/resources/CFDI40/";
    String logoB64 = settings.getResource("logoSw.png");
    SWIssueService issue = new SWIssueService(Utils.tokenSW, Utils.urlSW);

    @Rule
    public TestName testName = new TestName();

    @Ignore
    public void GeneratePdf_ByUser_Success() throws AuthException, GeneralException, IOException {
        SuccessV3Response stamp = (SuccessV3Response) issue
                .IssueXml(settings.getCFDI(baseDir + "CFDI40/CFDI40_Ingreso.xml",
                        false, "v3", false), "v3");
        if (stamp.Status.equals("success")) {
            SWPdfService app = new SWPdfService(Utils.userSW, Utils.passwordSW, Utils.urlApiSW, Utils.urlSW);
            PdfResponse response = null;
            response = (PdfResponse) app.GeneratePdf(stamp.cfdi, logoB64);
            Utils.showTestLog(testName, response.Status);
            Assert.assertEquals(response.Status, "success");
            Assert.assertTrue(!response.contentB64.isEmpty());
            Assert.assertTrue(response.contentSizeBytes > 0);
            Assert.assertTrue(!response.uuid.isEmpty());
            Assert.assertTrue(!response.serie.isEmpty());
            Assert.assertTrue(!response.folio.isEmpty());
            Assert.assertTrue(!response.stampDate.isEmpty());
            Assert.assertTrue(!response.issuedDate.isEmpty());
            Assert.assertTrue(!response.rfcIssuer.isEmpty());
            Assert.assertTrue(!response.rfcReceptor.isEmpty());
            Assert.assertTrue(!response.total.isEmpty());
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test(expected = AuthException.class)
    public void GeneratePdf_ByUser_InvalidPass_Error() throws AuthException, GeneralException, IOException {
        try {
            new SWPdfService(Utils.userSW, "InvalidPassword", Utils.urlApiSW, Utils.urlSW);
        } catch (AuthException ex) {
            Utils.showTestLog(testName, ex.getMessage());
            throw ex;
        }
    }

    @Ignore
    public void GeneratePdf_ByToken_Success() throws AuthException, GeneralException, IOException {
        SuccessV3Response stamp = (SuccessV3Response) issue
                .IssueXml(settings.getCFDI(baseDir + "CFDI40/CFDI40_Ingreso.xml",
                        false, "v3", false), "v3");
        if (stamp.Status.equals("success")) {
            SWPdfService app = new SWPdfService(Utils.tokenSW, Utils.urlApiSW);
            PdfResponse response = null;
            response = (PdfResponse) app.GeneratePdf(stamp.cfdi, this.logoB64);
            Utils.showTestLog(testName, response.Status);
            Assert.assertEquals(response.Status, "success");
            Assert.assertTrue(!response.contentB64.isEmpty());
            Assert.assertTrue(response.contentSizeBytes > 0);
            Assert.assertTrue(!response.uuid.isEmpty());
            Assert.assertTrue(!response.serie.isEmpty());
            Assert.assertTrue(!response.folio.isEmpty());
            Assert.assertTrue(!response.stampDate.isEmpty());
            Assert.assertTrue(!response.issuedDate.isEmpty());
            Assert.assertTrue(!response.rfcIssuer.isEmpty());
            Assert.assertTrue(!response.rfcReceptor.isEmpty());
            Assert.assertTrue(!response.total.isEmpty());
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void GeneratePdf_ByToken_InvalidToken_Error() throws AuthException, GeneralException, IOException {

        SWPdfService app = new SWPdfService("wrong this", Utils.urlApiSW);
        PdfResponse response = null;
        response = (PdfResponse) app.GeneratePdf("", this.logoB64);
        Utils.showTestLog(testName, response.Status);
        Assert.assertEquals(response.Status, "error");
        Assert.assertTrue(!response.message.isEmpty());
        Assert.assertEquals(response.message, "El token debe contener 3 partes");
    }

    @Ignore
    public void GeneratePdf_TemplateAsEnum_Success() throws AuthException, GeneralException, IOException {
        HashMap<String, String> extras = new HashMap<String,String>();
        extras.put("Observaciones", "Observaciones ejemplo");
        extras.put("DatosExtras", "Tel. 0000000000, Empresas SW");
        SuccessV3Response stamp = (SuccessV3Response) issue
                .IssueXml(settings.getCFDI(baseDir + "CFDI40/CFDI40_Ingreso.xml",
                        false, "v3", false), "v3");
        if (stamp.Status.equals("success")) {
            SWPdfService app = new SWPdfService(Utils.tokenSW, Utils.urlApiSW);
            PdfResponse response = null;
            response = (PdfResponse) app.GeneratePdf(stamp.cfdi, PdfTemplates.cfdi40, this.logoB64, extras);
            Utils.showTestLog(testName, response.Status);
            Assert.assertEquals(response.Status, "success");
            Assert.assertTrue(!response.contentB64.isEmpty());
            Assert.assertTrue(response.contentSizeBytes > 0);
            Assert.assertTrue(!response.uuid.isEmpty());
            Assert.assertTrue(!response.serie.isEmpty());
            Assert.assertTrue(!response.folio.isEmpty());
            Assert.assertTrue(!response.stampDate.isEmpty());
            Assert.assertTrue(!response.issuedDate.isEmpty());
            Assert.assertTrue(!response.rfcIssuer.isEmpty());
            Assert.assertTrue(!response.rfcReceptor.isEmpty());
            Assert.assertTrue(!response.total.isEmpty());
        } else {
            Assert.assertTrue(false);
        }
    }

    @Ignore
    public void GeneratePdf_TemplateAsString__Success() throws AuthException, GeneralException, IOException {
        SuccessV3Response stamp = (SuccessV3Response) issue
                .IssueXml(settings.getCFDI(baseDir + "CFDI40/CFDI40_Ingreso.xml",
                        false, "v3", false), "v3");
        if (stamp.Status.equals("success")) {
            SWPdfService app = new SWPdfService(Utils.tokenSW, Utils.urlApiSW);
            PdfResponse response = null;
            response = (PdfResponse) app.GeneratePdf(stamp.cfdi, "cfdi40", this.logoB64, null);
            Utils.showTestLog(testName, response.Status);
            Assert.assertEquals(response.Status, "success");
            Assert.assertTrue(!response.contentB64.isEmpty());
            Assert.assertTrue(response.contentSizeBytes > 0);
            Assert.assertTrue(!response.uuid.isEmpty());
            Assert.assertTrue(!response.serie.isEmpty());
            Assert.assertTrue(!response.folio.isEmpty());
            Assert.assertTrue(!response.stampDate.isEmpty());
            Assert.assertTrue(!response.issuedDate.isEmpty());
            Assert.assertTrue(!response.rfcIssuer.isEmpty());
            Assert.assertTrue(!response.rfcReceptor.isEmpty());
            Assert.assertTrue(!response.total.isEmpty());
        } else {
            Assert.assertTrue(false);
        }
    }

    @Ignore
    public void GeneratePdf_AllParams__Success() throws AuthException, GeneralException, IOException {
        HashMap<String, String> extras = new HashMap<String,String>();
        extras.put("Observaciones", "Observaciones ejemplo");
        extras.put("DatosExtras", "Tel. 0000000000, Empresas SW");
        SuccessV3Response stamp = (SuccessV3Response) issue
                .IssueXml(settings.getCFDI(baseDir + "CFDI40/CFDI40_Ingreso.xml",
                        false, "v3", false), "v3");
        if (stamp.Status.equals("success")) {
            SWPdfService app = new SWPdfService(Utils.tokenSW, Utils.urlApiSW);
            PdfResponse response = null;
            response = (PdfResponse) app.GeneratePdf(stamp.cfdi, PdfTemplates.cfdi40, this.logoB64, extras);
            Utils.showTestLog(testName, response.Status);
            Assert.assertEquals(response.Status, "success");
            Assert.assertTrue(!response.contentB64.isEmpty());
            Assert.assertTrue(response.contentSizeBytes > 0);
            Assert.assertTrue(!response.uuid.isEmpty());
            Assert.assertTrue(!response.serie.isEmpty());
            Assert.assertTrue(!response.folio.isEmpty());
            Assert.assertTrue(!response.stampDate.isEmpty());
            Assert.assertTrue(!response.issuedDate.isEmpty());
            Assert.assertTrue(!response.rfcIssuer.isEmpty());
            Assert.assertTrue(!response.rfcReceptor.isEmpty());
            Assert.assertTrue(!response.total.isEmpty());
        } else {
            Assert.assertTrue(false);
        }
    }
    @Ignore
    public void GeneratePdf_Pagos20__Success() throws AuthException, GeneralException, IOException {
        HashMap<String, String> extras = new HashMap<String,String>();
        extras.put("Observaciones", "Observaciones ejemplo");
        extras.put("DatosExtras", "Tel. 0000000000, Empresas SW");
        SuccessV3Response stamp = (SuccessV3Response) issue
                .IssueXml(settings.getCFDI(baseDir + "Pagos20/CFDI40_Pago.xml",
                        false, "v3", false), "v3");
        if (stamp.Status.equals("success")) {
            SWPdfService app = new SWPdfService(Utils.tokenSW, Utils.urlApiSW);
            PdfResponse response = null;
            response = (PdfResponse) app.GeneratePdf(stamp.cfdi, PdfTemplates.payment20, this.logoB64, extras);
            Utils.showTestLog(testName, response.Status);
            Assert.assertEquals(response.Status, "success");
            Assert.assertTrue(!response.contentB64.isEmpty());
            Assert.assertTrue(response.contentSizeBytes > 0);
            Assert.assertTrue(!response.uuid.isEmpty());
            Assert.assertTrue(!response.serie.isEmpty());
            Assert.assertTrue(!response.folio.isEmpty());
            Assert.assertTrue(!response.stampDate.isEmpty());
            Assert.assertTrue(!response.issuedDate.isEmpty());
            Assert.assertTrue(!response.rfcIssuer.isEmpty());
            Assert.assertTrue(!response.rfcReceptor.isEmpty());
            Assert.assertTrue(!response.total.isEmpty());
        } else {
            Assert.assertTrue(false);
        }
    }
    @Ignore
    public void GeneratePdf_Nomina12__Success() throws AuthException, GeneralException, IOException {
        HashMap<String, String> extras = new HashMap<String,String>();
        extras.put("Observaciones", "Observaciones ejemplo");
        extras.put("DatosExtras", "Tel. 0000000000, Empresas SW");
        SuccessV3Response stamp = (SuccessV3Response) issue
                .IssueXml(settings.getCFDI(baseDir + "Nomina12/CFDI40_Nomina.xml",
                        false, "v3", false), "v3");
        if (stamp.Status.equals("success")) {
            SWPdfService app = new SWPdfService(Utils.tokenSW, Utils.urlApiSW);
            PdfResponse response = null;
            response = (PdfResponse) app.GeneratePdf(stamp.cfdi, PdfTemplates.payroll40, this.logoB64, extras);
            Utils.showTestLog(testName, response.Status);
            Assert.assertEquals(response.Status, "success");
            Assert.assertTrue(!response.contentB64.isEmpty());
            Assert.assertTrue(response.contentSizeBytes > 0);
            Assert.assertTrue(!response.uuid.isEmpty());
            Assert.assertTrue(!response.serie.isEmpty());
            Assert.assertTrue(!response.folio.isEmpty());
            Assert.assertTrue(!response.stampDate.isEmpty());
            Assert.assertTrue(!response.issuedDate.isEmpty());
            Assert.assertTrue(!response.rfcIssuer.isEmpty());
            Assert.assertTrue(!response.rfcReceptor.isEmpty());
            Assert.assertTrue(!response.total.isEmpty());
        } else {
            Assert.assertTrue(false);
        }
    }
    @Ignore
    public void GeneratePdf_CP20__Success() throws AuthException, GeneralException, IOException {
        HashMap<String, String> extras = new HashMap<String,String>();
        extras.put("Observaciones", "Observaciones ejemplo");
        extras.put("DatosExtras", "Tel. 0000000000, Empresas SW");
        SuccessV3Response stamp = (SuccessV3Response) issue
                .IssueXml(settings.getCFDI(baseDir + "CartaPorte20/CFDI40_Traslado_CartaPorte_Autotransporte.xml",
                        false, "v3", false), "v3");
        if (stamp.Status.equals("success")) {
            SWPdfService app = new SWPdfService(Utils.tokenSW, Utils.urlApiSW);
            PdfResponse response = null;
            response = (PdfResponse) app.GeneratePdf(stamp.cfdi, PdfTemplates.billoflading40, this.logoB64, extras);
            Utils.showTestLog(testName, response.Status);
            Assert.assertEquals(response.Status, "success");
            Assert.assertTrue(!response.contentB64.isEmpty());
            Assert.assertTrue(response.contentSizeBytes > 0);
            Assert.assertTrue(!response.uuid.isEmpty());
            Assert.assertTrue(!response.serie.isEmpty());
            Assert.assertTrue(!response.folio.isEmpty());
            Assert.assertTrue(!response.stampDate.isEmpty());
            Assert.assertTrue(!response.issuedDate.isEmpty());
            Assert.assertTrue(!response.rfcIssuer.isEmpty());
            Assert.assertTrue(!response.rfcReceptor.isEmpty());
            Assert.assertTrue(!response.total.isEmpty());
        } else {
            Assert.assertTrue(false);
        }
    }
}
