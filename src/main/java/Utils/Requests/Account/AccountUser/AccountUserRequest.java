package Utils.Requests.Account.AccountUser;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Responses.IResponse;
import Utils.Responses.Account.AccountUser.DataAccountUser;

/**
 * Clase AccountUserRequest para manejar solicitudes HTTP relacionadas con
 * usuarios de cuenta.
 */
public class AccountUserRequest {

    // Métodos estáticos para solicitudes específicas

    public static IResponse createCreateUserRequest(IRequest request)
            throws GeneralException, AuthException, IOException {
        return new AccountUserRequest().createUserRequest(request, DataAccountUser.class);
    }

    public static IResponse createUpdateUserRequest(IRequest request, UUID idUser)
            throws GeneralException, AuthException, IOException {
        return new AccountUserRequest().updateUserRequest(request, idUser, DataAccountUser.class);
    }

    public static IResponse createDeleteUserRequest(IRequest request, UUID idUser)
            throws GeneralException, AuthException, IOException {
        return new AccountUserRequest().deleteUserRequest(request, idUser, String.class);
    }

    public static IResponse createGetAllUsersRequest(IRequest request)
            throws GeneralException, AuthException, IOException {
        return new AccountUserRequest().getUserFiltersRequest(request, null, null, null, null, List.class);
    }

    public static IResponse createGetUserById(IRequest request, UUID idUser)
            throws GeneralException, AuthException, IOException {
        return new AccountUserRequest().getUserFiltersRequest(request, null, null, idUser, null, List.class);
    }

    public static IResponse createGetUserByEmail(IRequest request, String email)
            throws GeneralException, AuthException, IOException {
        return new AccountUserRequest().getUserFiltersRequest(request, email, null, null, null, List.class);
    }

    public static IResponse createGetUserByRfc(IRequest request, String rfc)
            throws GeneralException, AuthException, IOException {
        return new AccountUserRequest().getUserFiltersRequest(request, null, rfc, null, null, List.class);
    }

    public static IResponse createGetUserByActive(IRequest request, Boolean isActive)
            throws GeneralException, AuthException, IOException {
        return new AccountUserRequest().getUserFiltersRequest(request, null, null, null, isActive, List.class);
    }

    // Métodos internos con clases de respuesta específicas

    private <T> IResponse createUserRequest(IRequest request, Class<T> responseClass)
            throws GeneralException, AuthException, IOException {
        try {
            JSONObject requestJSON = AccountUserRequestHelper.buildUserCreateJson((AccountUserOptionsRequest) request);
            return executePostRequest(request, requestJSON, responseClass);
        } catch (JSONException e) {
            throw new GeneralException(500, "Error al construir JSON: " + e.getMessage());
        }
    }

    private <T> IResponse updateUserRequest(IRequest request, UUID idUser, Class<T> responseClass)
            throws GeneralException, AuthException, IOException {
        try {
            JSONObject requestJSON = AccountUserRequestHelper.buildUserUpdateJson((AccountUserOptionsRequest) request);
            return executePutRequest(request, requestJSON, responseClass);
        } catch (JSONException e) {
            throw new GeneralException(500, "Error al construir JSON: " + e.getMessage());
        }
    }

    private <T> IResponse deleteUserRequest(IRequest request, UUID idUser, Class<T> responseClass)
            throws GeneralException, AuthException, IOException {
        HttpDelete httpDelete = new HttpDelete(request.URI);
        AccountUserRequestHelper.configureHttpRequest(request, httpDelete, new JSONObject());
        return executeHttpRequest(httpDelete, responseClass);
    }

    private <T> IResponse getUserFiltersRequest(IRequest request, String filterEmail, String filterRFC,
            UUID filterIdUser, Boolean filterIsActive, Class<T> responseClass)
            throws GeneralException, AuthException, IOException {
        HttpGet httpGet = new HttpGet(
                filterEmail == null && filterRFC == null && filterIdUser == null && filterIsActive == null ? request.URI
                        : buildUriWithFilter(request.URI, filterEmail, filterRFC, filterIdUser, filterIsActive));
        AccountUserRequestHelper.configureHttpRequest(request, httpGet, new JSONObject());
        return executeHttpRequest(httpGet, responseClass);
    }

    // Métodos auxiliares

    private String buildUriWithFilter(String baseUri, String filterEmail, String filterRFC,
            UUID filterIdUser, Boolean filterIsActive) {

        StringBuilder uriBuilder = new StringBuilder(baseUri);
        boolean hasQueryParams = false;

        // Agregar filtros según su disponibilidad
        if (filterEmail != null) {
            uriBuilder.append(hasQueryParams ? "&" : "?").append("Email=").append(filterEmail);
            hasQueryParams = true;
        }
        if (filterRFC != null) {
            uriBuilder.append(hasQueryParams ? "&" : "?").append("TaxId=").append(filterRFC);
            hasQueryParams = true;
        }
        if (filterIdUser != null) {
            uriBuilder.append(hasQueryParams ? "&" : "?").append("IdUser=").append(filterIdUser);
            hasQueryParams = true;
        }
        if (filterIsActive != null) {
            uriBuilder.append(hasQueryParams ? "&" : "?").append("IsActive=").append(filterIsActive);
        }

        return uriBuilder.toString();
    }

    private <T> IResponse executeHttpRequest(HttpRequestBase requestBase, Class<T> responseClass)
            throws IOException, GeneralException {
        try (CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(requestBase)) {
            return AccountUserRequestHelper.handleResponse(response, responseClass);
        }
    }

    private <T> IResponse executePostRequest(IRequest request, JSONObject json, Class<T> responseClass)
            throws IOException, GeneralException {
        HttpPost httpPost = new HttpPost(request.URI);
        AccountUserRequestHelper.configureHttpRequest(request, httpPost, json);
        return executeHttpRequest(httpPost, responseClass);
    }

    private <T> IResponse executePutRequest(IRequest request, JSONObject json, Class<T> responseClass)
            throws IOException, GeneralException {
        HttpPut httpPut = new HttpPut(request.URI);
        AccountUserRequestHelper.configureHttpRequest(request, httpPut, json);
        return executeHttpRequest(httpPut, responseClass);
    }
}
