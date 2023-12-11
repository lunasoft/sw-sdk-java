package Utils.Requests.Account.AccountUser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Responses.IResponse;
import Utils.Responses.Account.AccountUser.AccountUserResponse;
import Utils.Responses.Account.AccountUser.DataAccountUser;
import Utils.Responses.Account.AccountUser.DataAccountUserResponse;
import Utils.Responses.Account.AccountUser.ListDataAccountUserResponse;

public class AccountUserRequest {
    public IResponse createUserRequest(IRequest request) throws GeneralException, AuthException, IOException {
        try {
            JSONObject requestJSON = buildUserCreateJson((AccountUserOptionsRequest) request);
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(request.URI);
            configureHttpRequest(request, httpPost, requestJSON);

            try (CloseableHttpResponse responseB = client.execute(httpPost)) {
                return handleResponse(responseB);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }

    public IResponse updateUser(IRequest request, UUID idUser) throws GeneralException, AuthException, IOException {
        try {
            JSONObject requestJSON = buildUserUpdateJson((AccountUserOptionsRequest) request);
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(request.URI);
            configureHttpRequest(request, httpPut, requestJSON);

            try (CloseableHttpResponse responseB = client.execute(httpPut)) {
                return handleResponse(responseB);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }

    public IResponse deleteUserRequest(IRequest request, UUID IdUser)
            throws GeneralException, AuthException, IOException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpDelete httpDelete = new HttpDelete(request.URI);
            configureHttpRequest(request, httpDelete, new JSONObject());

            try (CloseableHttpResponse responseB = client.execute(httpDelete)) {
                return handleResponse(responseB);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }

    public IResponse getAllUsersRequest(IRequest request, int page, int pageSize)
            throws GeneralException, AuthException, IOException {
        HttpGet httpGet = new HttpGet(request.URI);
        configureHttpRequest(request, httpGet, new JSONObject());

        try (CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(httpGet)) {

            return handleListResponse(response);
        }
    }

    public IResponse getUserRequest(IRequest request) throws GeneralException, AuthException, IOException {
        HttpGet httpGet = new HttpGet(request.URI + "/info");
        configureHttpRequest(request, httpGet, new JSONObject());

        try (CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(httpGet)) {

            return handleUserResponse(response);
        }
    }

    public IResponse getUserIdRequest(IRequest request, UUID idUser)
            throws GeneralException, AuthException, IOException {
        HttpGet httpGet = new HttpGet(request.URI);
        configureHttpRequest(request, httpGet, new JSONObject());

        try (CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(httpGet)) {

            return handleUserResponse(response);
        }
    }

    private IResponse handleUserResponse(CloseableHttpResponse response) throws IOException, GeneralException {
        int status = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");

        if (!responseString.isEmpty() && status < 500) {
            JSONObject body = new JSONObject(responseString);

            if (status == 200) {
                JSONObject dataObject = body.getJSONObject("data");
                DataAccountUser user = UserData(dataObject);

                return new DataAccountUserResponse(status, body.getString("status"), user, "OK", "OK");
            } else {
                String messageDetail = body.optString("messageDetail", "");
                return new DataAccountUserResponse(status, body.getString("status"), null, body.getString("message"),
                        messageDetail);
            }
        } else {
            return new DataAccountUserResponse(status, "error", null, response.getStatusLine().getReasonPhrase(),
                    responseString);
        }
    }

    private IResponse handleListResponse(CloseableHttpResponse response) throws IOException, GeneralException {
        int status = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");

        if (!responseString.isEmpty() && status < 500) {
            JSONObject body = new JSONObject(responseString);

            if (status == 200) {
                JSONArray data = body.getJSONArray("data");
                List<DataAccountUser> userList = new LinkedList<>();

                for (int i = 0; i < data.length(); i++) {
                    JSONObject userData = data.getJSONObject(i);
                    DataAccountUser user = UserData(userData);
                    userList.add(user);
                }

                return new ListDataAccountUserResponse(status, body.getString("status"), userList, "OK", "OK");
            } else {
                String messageDetail = body.optString("messageDetail", "");
                return new ListDataAccountUserResponse(status, body.getString("status"), body.getString("message"),
                        messageDetail);
            }
        } else {
            return new ListDataAccountUserResponse(status, "error", response.getStatusLine().getReasonPhrase(),
                    responseString);
        }
    }

    private IResponse handleResponse(CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        int status = response.getStatusLine().getStatusCode();
        response.close();

        if (!responseString.isEmpty() && status < 500) {
            JSONObject body = new JSONObject(responseString);
            if (status == 200) {
                String data = body.getString("data");
                return new AccountUserResponse(status, body.getString("status"), data, "OK", "OK");

            } else {
                String messageDetail = "";
                if (!body.isNull("messageDetail")) {
                    messageDetail = body.getString("messageDetail");
                }
                return new AccountUserResponse(status, body.getString("status"), body.getString("message"),
                        messageDetail);
            }
        } else {
            return new AccountUserResponse(status, "error", response.getStatusLine().getReasonPhrase(),
                    responseString);
        }
    }

    private JSONObject buildUserCreateJson(AccountUserOptionsRequest accountUserOptionsRequest) {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("email", accountUserOptionsRequest.getEmail());
        requestJSON.put("password", accountUserOptionsRequest.getPassword());
        requestJSON.put("name", accountUserOptionsRequest.getName());
        requestJSON.put("rfc", accountUserOptionsRequest.getRfc());
        requestJSON.put("profile", accountUserOptionsRequest.getProfile().getValue());
        requestJSON.put("stamps", accountUserOptionsRequest.getStamps());
        requestJSON.put("unlimited", accountUserOptionsRequest.isUnlimited());
        requestJSON.put("active", accountUserOptionsRequest.isActive());
        return requestJSON;
    }

    private JSONObject buildUserUpdateJson(AccountUserOptionsRequest accountUserOptionsRequest) {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("name", accountUserOptionsRequest.getName());
        requestJSON.put("rfc", accountUserOptionsRequest.getRfc());
        requestJSON.put("unlimited", accountUserOptionsRequest.isUnlimited());
        requestJSON.put("active", accountUserOptionsRequest.isActive());
        return requestJSON;
    }

    private DataAccountUser UserData(JSONObject userData) {
        String email = userData.optString("email", null);
        String password = userData.optString("password", null);
        String nombre = userData.optString("nombre", null);
        String apellidoPaterno = userData.optString("apellidoPaterno", null);
        String apellidoMaterno = userData.optString("apellidoMaterno", null);
        String username = userData.optString("username", null);
        String fechaUltimoPassword = userData.optString("fechaUltimoPassword", null);
        String telefono = userData.optString("telefono", null);
        boolean administrador = userData.optBoolean("administrador");
        String profileValue = userData.optString("profileValue", null);
        String idUsuario = userData.optString("idUsuario", null);
        String idCliente = userData.optString("idCliente", null);
        String stamps = userData.optString("stamps", null);
        boolean unlimited = userData.optBoolean("unlimited");
        int profile = userData.optInt("profile");
        boolean activo = userData.optBoolean("activo");
        String registeredDate = userData.optString("registeredDate", null);
        boolean eliminado = userData.optBoolean("eliminado");
        String tokenAccess = userData.optString("tokenAccess", null);
        String tokenAccessHash = userData.optString("tokenAccessHash", null);

        return new DataAccountUser(
                email, password, nombre, apellidoPaterno, apellidoMaterno, username,
                fechaUltimoPassword, telefono, administrador, profileValue, idUsuario,
                idCliente, stamps, unlimited, profile, activo, registeredDate,
                eliminado, tokenAccess, tokenAccessHash);
    }

    private void configureHttpRequest(IRequest request, HttpRequestBase httpRequest, JSONObject requestJSON) {
        try {
            RequestHelper.setTimeOut(request.options, 5000);
            RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
            httpRequest.setConfig(request.options.build());
            httpRequest.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
            httpRequest.addHeader(new BasicHeader("Content-Type", "application/json"));

            if (httpRequest instanceof HttpEntityEnclosingRequestBase) {
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                Charset chars = Charset.forName("UTF-8");
                builder.setCharset(chars);
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                StringEntity sEntity = new StringEntity(requestJSON.toString(), StandardCharsets.UTF_8);
                ((HttpEntityEnclosingRequestBase) httpRequest).setEntity(builder.build());
                ((HttpEntityEnclosingRequestBase) httpRequest).setEntity(sEntity);
            }
        } catch (GeneralException e) {
            e.printStackTrace();
        }
    }
}
