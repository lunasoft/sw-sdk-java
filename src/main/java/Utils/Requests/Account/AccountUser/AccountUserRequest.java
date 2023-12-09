package Utils.Requests.Account.AccountUser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
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
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.Account.AccountUser.AccountUserResponse;
import Utils.Responses.Account.AccountUser.DataAccountUser;
import Utils.Responses.Account.AccountUser.DataAccountUserResponse;
import Utils.Responses.Account.AccountUser.ListDataAccountUserResponse;


public class AccountUserRequest implements IRequestor {
//crear usuario
    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, IOException {
        try {
            JSONObject requestJSON = new JSONObject();
            requestJSON.put("email", ((AccountUserOptionsRequest) request).getEmail());
            requestJSON.put("password", ((AccountUserOptionsRequest) request).getPassword());
            requestJSON.put("name", ((AccountUserOptionsRequest) request).getName());
            requestJSON.put("rfc", ((AccountUserOptionsRequest) request).getRfc());
            requestJSON.put("profile", ((AccountUserOptionsRequest) request).getProfile().getValue());
            requestJSON.put("stamps", ((AccountUserOptionsRequest) request).getStamps());
            requestJSON.put("unlimited", ((AccountUserOptionsRequest) request).isUnlimited());
            requestJSON.put("active", ((AccountUserOptionsRequest) request).isActive());
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(request.URI);
            RequestHelper.setTimeOut(request.options, requestJSON.toString().length());
            RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
            httppost.setConfig(request.options.build());
            httppost.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
            httppost.addHeader(new BasicHeader("Content-Type", "application/json"));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            Charset chars = Charset.forName("UTF-8");
            builder.setCharset(chars);
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            StringEntity sEntity = new StringEntity(requestJSON.toString());
            httppost.setEntity(builder.build());
            httppost.setEntity(sEntity);
            CloseableHttpResponse responseB = client.execute(httppost);
            HttpEntity entity = responseB.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            int status = responseB.getStatusLine().getStatusCode();
            client.close();
            responseB.close();
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
                    return new AccountUserResponse(status, body.getString("status"), body.getString("message"), messageDetail);
                }
            } else {
                return new AccountUserResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
                        responseString);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }

    public IResponse UpdateUser(IRequest request, UUID idUser) throws GeneralException, AuthException, IOException {
        try {
            JSONObject requestJSON = new JSONObject();
            requestJSON.put("name", ((AccountUserOptionsRequest) request).getName());
            requestJSON.put("rfc", ((AccountUserOptionsRequest) request).getRfc());
            requestJSON.put("unlimited", ((AccountUserOptionsRequest) request).isUnlimited());
            requestJSON.put("active", ((AccountUserOptionsRequest) request).isActive());
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(request.URI);
            RequestHelper.setTimeOut(request.options, requestJSON.toString().length());
            RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
            httpPut.setConfig(request.options.build());
            httpPut.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
            httpPut.addHeader(new BasicHeader("Content-Type", "application/json"));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            Charset chars = Charset.forName("UTF-8");
            builder.setCharset(chars);
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            StringEntity sEntity = new StringEntity(requestJSON.toString());
            httpPut.setEntity(builder.build());
            httpPut.setEntity(sEntity);
            CloseableHttpResponse responseB = client.execute(httpPut);
            HttpEntity entity = responseB.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            int status = responseB.getStatusLine().getStatusCode();
            client.close();
            responseB.close();
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
                    return new AccountUserResponse(status, body.getString("status"), body.getString("message"), messageDetail);
                }
            } else {
                return new AccountUserResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
                        responseString);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }
    

    public IResponse DeleteUserRequest(IRequest request, UUID IdUser ) throws GeneralException, AuthException, IOException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpDelete http = new HttpDelete(request.URI);
            RequestHelper.setTimeOut(request.options, 3000);
            RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
            http.setConfig(request.options.build());
            http.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
            http.addHeader(new BasicHeader("Content-Type", "application/json"));
            CloseableHttpResponse responseB = client.execute(http);
            HttpEntity entity = responseB.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            int status = responseB.getStatusLine().getStatusCode();
            client.close();
            responseB.close();
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
                    return new AccountUserResponse(status, body.getString("status"), body.getString("message"), messageDetail);
                }
            } else {
                return new AccountUserResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
                        responseString);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }
/*
    public IResponse GetAllUsersRequest(IRequest request) throws GeneralException, AuthException, IOException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet http = new HttpGet(request.URI);
            RequestHelper.setTimeOut(request.options, 3000);
            RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
            http.setConfig(request.options.build());
            http.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
            http.addHeader(new BasicHeader("Content-Type", "application/json"));
            CloseableHttpResponse responseB = client.execute(http);
            HttpEntity entity = responseB.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            int status = responseB.getStatusLine().getStatusCode();
            client.close();
            responseB.close();
            if (!responseString.isEmpty() && status < 500) {
                JSONObject body = new JSONObject(responseString);
                if (status == 200) {
                    JSONArray data = body.getJSONArray("data");
                    List<DataAccountUser> lista = new LinkedList<DataAccountUser>();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject dato = new JSONObject(data.get(i).toString());
                        DataAccountUser datos = new DataAccountUser(dato.getString("email"), dato.getString("password"),
                                dato.getString("name"), dato.getString("rfc"),
                                dato.getString("profile"), dato.getString("stamps"),dato.getString("unlimited"),
                                dato.getString("active"));
                        lista.add(datos);
                    }
                    return new ListDataAccountUserResponse(status, body.getString("status"), lista, "OK", "OK");

                } else {
                    String messageDetail = "";
                    if (!body.isNull("messageDetail")) {
                        messageDetail = body.getString("messageDetail");
                    }
                    return new ListDataAccountUserResponse(status, body.getString("status"), body.getString("message"),
                            messageDetail);
                }
            } else {
                return new ListDataAccountUserResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
                        responseString);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }

    public IResponse GetUserRequest(IRequest request) throws GeneralException, AuthException, IOException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet http = new HttpGet(request.URI);
            RequestHelper.setTimeOut(request.options, 3000);
            RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
            http.setConfig(request.options.build());
            http.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
            http.addHeader(new BasicHeader("Content-Type", "application/json"));
            CloseableHttpResponse responseB = client.execute(http);
            HttpEntity entity = responseB.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            int status = responseB.getStatusLine().getStatusCode();
            client.close();
            responseB.close();
            if (!responseString.isEmpty() && status < 500) {
                JSONObject body = new JSONObject(responseString);
                if (status == 200) {
                    JSONObject data = body.getJSONObject("data");
                    DataAccountUser datos = new DataAccountUser(data.getString("email"), data.getString("password"),
                            data.getString("name"), data.getString("rfc"),
                            data.getString("profile"), data.getString("stamps"),
                            data.getString("unlimited"),data.getString("active"));
                    return new DataAccountUserResponse(status, body.getString("status"), datos, "OK", "OK");

                } else {
                    String messageDetail = "";
                    if (!body.isNull("messageDetail")) {
                        messageDetail = body.getString("messageDetail");
                    }
                    return new DataAccountUserResponse(status, body.getString("status"), body.getString("message"),
                            messageDetail);
                }
            } else {
                return new DataAccountUserResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
                        responseString);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }
 */
}
