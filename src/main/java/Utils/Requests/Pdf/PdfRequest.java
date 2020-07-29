package Utils.Requests.Pdf;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Requests.Pdf.PdfOptionsRequest;
import Utils.Responses.IResponse;
import Utils.Responses.Pdf.PdfResponse;

public class PdfRequest implements IRequestor{

	public IResponse sendRequest(IRequest request) throws GeneralException, AuthException {
		try {
			String boundary = UUID.randomUUID().toString();
			String xmlStr = ((PdfOptionsRequest) request).getXml();
			String raw = "--"+boundary+"\r\nContent-Disposition: form-data; name=file; filename=file\r\nContent-Type: application/xml\r\n\r\n"+xmlStr+"\r\n--"+boundary+"--";
			CloseableHttpClient client = HttpClients.createDefault();
            HttpPost http = new HttpPost(request.URI);
            RequestHelper.setTimeOut(request.options, xmlStr.length());
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			http.setConfig(request.options.build());
            http.setHeader("Authorization", "Bearer " + request.Token);
            http.addHeader("Content-Type", "multipart/form-data; boundary="+boundary);
            if(((PdfOptionsRequest) request).getTemplateId() != null && !((PdfOptionsRequest) request).getTemplateId().isEmpty()) {
            	http.setHeader("TemplateId",((PdfOptionsRequest) request).getTemplateId());
            }
            else {
            	http.setHeader("TemplateId","6c757687-7979-4a5b-b4e0-fe5d35d1f970");
            }
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            Charset chars = Charset.forName("UTF-8");
        	builder.setCharset(chars);
        	builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        	builder.addTextBody("file", raw, ContentType.DEFAULT_BINARY);
        	if(((PdfOptionsRequest) request).getTemplateId() != null && !((PdfOptionsRequest) request).getTemplateId().isEmpty()) {
        		builder.addTextBody("extras", ((PdfOptionsRequest) request).getExtras(), ContentType.APPLICATION_JSON);        		
        	}
            http.setEntity(builder.build());
            
            CloseableHttpResponse responseB = client.execute(http);
            
            HttpEntity entity = responseB.getEntity();
            
            String responseString = EntityUtils.toString(entity, "UTF-8");
            int statusE = responseB.getStatusLine().getStatusCode();
            client.close();
            responseB.close();
            System.out.println(responseString);
            if(!responseString.isEmpty() && statusE < 500) {
            	JSONObject body = new JSONObject(responseString);
            	if(statusE==200){
            		JSONObject data = new JSONObject(body.get("data").toString());
            		return new PdfResponse(statusE,body.get("message").toString(),data.get("contentB64").toString(),data.getInt("contentSizeBytes"),data.get("uuid").toString(),data.get("serie").toString(),data.get("folio").toString(),data.get("stampDate").toString(),data.get("issuedDate").toString(),data.get("rfcIssuer").toString(),data.get("rfcReceptor").toString(),data.get("total").toString(),"OK","OK");
            	}
            	else {
            		String messageDetail = "";
            		if (!body.isNull("messageDetail")) {
                        messageDetail = body.getString("messageDetail");
                    }
                    return new PdfResponse(statusE, body.getString("message"), body.getString("message"), messageDetail);
            	}
            }
            else {
            	return new PdfResponse(statusE,"error",responseB.getStatusLine().getReasonPhrase(), responseString);
            }
		}
		catch (JSONException e){
            throw  new GeneralException(500,e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new GeneralException(500,e.getMessage());
        }
	}

}
