package Utils.Requests.Authentication;

import Exceptions.AuthException;
import Exceptions.GenaralException;
import Utils.Constants;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.JSendFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class AuthSoapRequest implements IRequestor {
    @Override
    public IResponse sendRequest(IRequest request) throws GenaralException, AuthException {
        try {
            String body = Constants.auth_soap_envelope(request.User, request.Password);
            HttpResponse<String> response = Unirest.post("http://pruebascfdi.smartweb.com.mx/Autenticacion/wsAutenticacion.asmx?WSDL=")
                    .header("content-type", "text/xml")
                    .header("cache-control", "no-cache")
                    .header("postman-token", "99a233c9-5d0a-cb6f-f0d8-b8380fcdfe14")
                    .body(body)
                    .asString();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(response.getBody()));
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();


            if (response.getStatus()==200){
                String data = doc.getFirstChild().getFirstChild().getFirstChild().getFirstChild().getTextContent();
                return (IResponse) JSendFactory.response(
                        "success",
                       data,
                        response.getStatus()
                );
            }
            String data = doc.getFirstChild().getTextContent();
            System.out.println(data);
            return (IResponse) JSendFactory.response(
                    "error",
                   data,
                    response.getStatus()
            );
        } catch (UnirestException e) {

            throw new GenaralException(500,e.getMessage());
        } catch (ParserConfigurationException e) {
            throw new GenaralException(500,e.getMessage());
        } catch (SAXException e) {
            throw new GenaralException(500,e.getMessage());
        } catch (IOException e) {
            throw new GenaralException(500,e.getMessage());
        }

    }
}
