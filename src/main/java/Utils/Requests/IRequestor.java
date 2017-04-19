package Utils.Requests;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Responses.IResponse;

public interface IRequestor {
    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, GeneralException;

}
