package Utils.Requests.Account.AccountUser;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Responses.IResponse;
import Utils.Responses.Account.AccountUser.DataAccountUser;

public class AccountUserRequest {
    public IResponse createUserRequest(IRequest request) throws GeneralException, AuthException, IOException {
        try {
            JSONObject requestJSON = AccountUserRequestHelper.buildUserCreateJson((AccountUserOptionsRequest) request);
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(request.URI);
            AccountUserRequestHelper.configureHttpRequest(request, httpPost, requestJSON);

            try (CloseableHttpResponse responseB = client.execute(httpPost)) {
                return AccountUserRequestHelper.handleResponse(responseB,String.class);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, "Error en la construcción de la solicitud JSON: " + e.getMessage());
        }
    }

    public IResponse updateUser(IRequest request, UUID idUser) throws GeneralException, AuthException, IOException {
        try {
            JSONObject requestJSON = AccountUserRequestHelper.buildUserUpdateJson((AccountUserOptionsRequest) request);
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(request.URI);
            AccountUserRequestHelper.configureHttpRequest(request, httpPut, requestJSON);

            try (CloseableHttpResponse responseB = client.execute(httpPut)) {
                return AccountUserRequestHelper.handleResponse(responseB, String.class);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, "Error en la construcción de la solicitud JSON: " + e.getMessage());
        }
    }

    public IResponse deleteUserRequest(IRequest request, UUID IdUser)
            throws GeneralException, AuthException, IOException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpDelete httpDelete = new HttpDelete(request.URI);
            AccountUserRequestHelper.configureHttpRequest(request, httpDelete, new JSONObject());

            try (CloseableHttpResponse responseB = client.execute(httpDelete)) {
                return AccountUserRequestHelper.handleResponse(responseB, String.class);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, "Error en la construcción de la solicitud JSON: " + e.getMessage());
        }
    }

    public IResponse getAllUsersRequest(IRequest request, int page, int pageSize)
            throws GeneralException, AuthException, IOException {
        HttpGet httpGet = new HttpGet(request.URI);
        AccountUserRequestHelper.configureHttpRequest(request, httpGet, new JSONObject());

        try (CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(httpGet)) {
            return AccountUserRequestHelper.handleResponse(response, List.class);
        }
    }

    public IResponse getUserRequest(IRequest request) throws GeneralException, AuthException, IOException {
        HttpGet httpGet = new HttpGet(request.URI + "/info");
        AccountUserRequestHelper.configureHttpRequest(request, httpGet, new JSONObject());

        try (CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(httpGet)) {

            return AccountUserRequestHelper.handleResponse(response, DataAccountUser.class);
        }
    }

    public IResponse getUserIdRequest(IRequest request, UUID idUser)
            throws GeneralException, AuthException, IOException {
        HttpGet httpGet = new HttpGet(request.URI);
        AccountUserRequestHelper.configureHttpRequest(request, httpGet, new JSONObject());

        try (CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(httpGet)) {

            return AccountUserRequestHelper.handleResponse(response,DataAccountUser.class);
        }
    }
}
