package Utils.Requests.Cancelation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.http.client.ClientProtocolException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.StatusCancelationResponse;

public class StatusCancelationRequest implements IRequestor{
	public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, GeneralException, UnsupportedEncodingException, ClientProtocolException, IOException, SOAPException {
		String soapEndpointUrl = request.URI;
        String soapAction = ((StatusCancelationOptionsRequest) request).getAction();
        String rfcEmisor = ((StatusCancelationOptionsRequest) request).getRfcEmisor();
        String rfcReceptor = ((StatusCancelationOptionsRequest) request).getRfcReceptor();
        String total = ((StatusCancelationOptionsRequest) request).getTotal();
        String uuid = ((StatusCancelationOptionsRequest) request).getUuid();
        
        SOAPMessage Response = callSoapWebService(soapEndpointUrl, soapAction, rfcEmisor, rfcReceptor, total, uuid);
        SOAPBody body = Response.getSOAPBody();
        SOAPFault error = body.getFault();
		if (error != null) {
			return new StatusCancelationResponse(500, "error", error.getFaultCode(), error.getFaultString());
		}
		else {
			String codigoEstatus = null;
			String estado = null;
		    String esCancelable = null;
		    String estatusCancelacion = null; 
			NodeList ConsultaResult = body.getElementsByTagName("ConsultaResponse").item(0).getChildNodes();
			for (int i = 0; i < ConsultaResult.getLength(); i++) {
				NodeList datos = ConsultaResult.item(i).getChildNodes();
				for (int d = 0; d < datos.getLength(); d++) {
					Node dato = datos.item(d);
					if(dato.getNodeName().equalsIgnoreCase("a:CodigoEstatus")) {
						codigoEstatus = dato.getTextContent();
					}
					if(dato.getNodeName().equalsIgnoreCase("a:Estado")) {
						estado = dato.getTextContent();
					}
					if(dato.getNodeName().equalsIgnoreCase("a:EsCancelable")) {
						esCancelable = dato.getTextContent();
					}
					if(dato.getNodeName().equalsIgnoreCase("a:EstatusCancelacion")) {
						estatusCancelacion = dato.getTextContent();
					}
				}
			}
			if(codigoEstatus.startsWith("N -")) {
				return new StatusCancelationResponse(400, "error", codigoEstatus, estado);
			}
			else {
				return new StatusCancelationResponse(200, "success", codigoEstatus, estado, esCancelable, estatusCancelacion, "OK", "OK");				
			}
		}
	}
	
	private static void createSoapEnvelope(SOAPMessage soapMessage, String rfcEmisor, String rfcReceptor, String total, String uuid) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();
        String myNamespace = "tem";
        String myNamespaceURI = "http://tempuri.org/";
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("Consulta", myNamespace);
        SOAPElement soapExpresionImpresa = soapBodyElem.addChildElement("expresionImpresa", myNamespace);
        soapExpresionImpresa.addTextNode("<![CDATA[?re="+rfcEmisor+"&rr="+rfcReceptor+"&tt="+total+"&id="+uuid+"]]>");
    }
	private SOAPMessage callSoapWebService(String soapEndpointUrl, String soapAction, String rfcEmisor, String rfcReceptor, String total, String uuid) {
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapRequest = createSOAPRequest(soapAction, rfcEmisor, rfcReceptor, total, uuid);
            SOAPMessage soapResponse = soapConnection.call(soapRequest, soapEndpointUrl);
            return soapResponse;
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\n");
            e.printStackTrace();
        }
		return null;
    }

    private static SOAPMessage createSOAPRequest(String soapAction, String rfcEmisor, String rfcReceptor, String total, String uuid) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        createSoapEnvelope(soapMessage, rfcEmisor, rfcReceptor, total, uuid);
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);
        soapMessage.saveChanges();
        return soapMessage;
    }
	

}
