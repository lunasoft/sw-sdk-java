package Utils.Requests;

import Utils.Requests.IRequest;
import Utils.Responses.IResponse;

/**
 * Created by asalvio on 08/02/2017.
 */
public interface IRequestor {
    public IResponse sendRequest(IRequest request);

}
