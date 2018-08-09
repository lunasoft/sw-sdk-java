package Utils.Requests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Responses.IResponse;

public interface IRequestor {
    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, GeneralException, UnsupportedEncodingException, ClientProtocolException, IOException;

}
