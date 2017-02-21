package Utils.Requests;

import Exceptions.AuthException;
import Exceptions.GenaralException;
import Utils.Requests.IRequest;
import Utils.Responses.IResponse;

public interface IRequestor {
    public IResponse sendRequest(IRequest request) throws GenaralException, AuthException;

}
