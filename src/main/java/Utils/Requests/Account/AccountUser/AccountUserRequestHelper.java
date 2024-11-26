package Utils.Requests.Account.AccountUser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Responses.Account.AccountUser.AccountUserResponse;
import Utils.Responses.Account.AccountUser.DataAccountUser;

/**
 * Clase de utilidad que proporciona métodos auxiliares para manejar
 * olicitudes
 * y respuestas
 * relacionadas con usuarios de cuentas.
 */
public class AccountUserRequestHelper {
    /**
     * Maneja la respuesta HTTP y la convierte en un objeto AccountUserResponse con
     * el tipo especificado.
     *
     * @param response     La respuesta HTTP recibida.
     * @param responseType El tipo de datos esperado en la respuesta.
     * @param <T>          Tipo genérico para la respuesta.
     * @return Un objeto AccountUserResponse que contiene la información de la
     *         respuesta.
     * @throws IOException      Si hay un error de entrada/salida.
     * @throws GeneralException Si hay un error general.
     */
    protected static <T> AccountUserResponse<T> handleResponse(CloseableHttpResponse response, Class<T> responseType)
            throws IOException, GeneralException {
        int status = response.getStatusLine().getStatusCode();

        // Verifica si el código de estado es 204 (No Content) para la respuesta OK de Delete user
        if (status == 204) {
            return new AccountUserResponse<>(status, "success", null, "Usuario eliminado correctamente.", "");
        }

        String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

        // Verifica si la respuesta no está vacía y el estado es válido (< 500)
        if (!responseString.isEmpty() && status < 500) {
            JSONObject body = new JSONObject(responseString);

            if (status == 200) {
                Object dataObject = body.get("data");

                if (responseType == DataAccountUser.class && dataObject instanceof JSONObject) {
                    DataAccountUser user = UserData((JSONObject) dataObject);
                    return new AccountUserResponse<>(status, body.getString("status"), (T) user, "OK", "OK");
                } else if (responseType == String.class && dataObject instanceof String) {
                    String data = (String) dataObject;
                    return new AccountUserResponse<>(status, body.getString("status"), (T) data, "OK", "OK");
                } else if (responseType == List.class && dataObject instanceof JSONArray) {
                    JSONArray data = (JSONArray) dataObject;
                    List<DataAccountUser> userList = new LinkedList<>();

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject userData = data.getJSONObject(i);
                        DataAccountUser user = UserData(userData);
                        userList.add(user);
                    }

                    return new AccountUserResponse<>(status, body.getString("status"), (T) userList, "OK", "OK");
                }
            }
            if (status == 400) {// Se agrego para manejar las respuesras erroneas de Actualizar y Crear Usuarios
                                // en la version 2
                String messageDetail = body.optString("messageDetail", "");
                String message = body.optString("message", "Bad Request");
                return new AccountUserResponse<>(status, "error", null, message, messageDetail);
            } else {
                String messageDetail = body.optString("messageDetail", "");
                String message = body.optString("message", "");
                return new AccountUserResponse<>(status, body.getString("status"), null, message, messageDetail);
            }
        } else {
            // Maneja el caso donde el contenido no es válido o está vacío
            return new AccountUserResponse<>(status, "error", null, response.getStatusLine().getReasonPhrase(),
                    responseString);
        }
    }

    /**
     * Construye un objeto JSON para la creación de un usuario basado en la
     * información proporcionada.
     *
     * @param accountUserOptionsRequest Información del usuario para la creación.
     * @return Objeto JSON representando la solicitud de creación de usuario.
     */
    protected static JSONObject buildUserCreateJson(AccountUserOptionsRequest accountUserOptionsRequest) {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("email", accountUserOptionsRequest.getEmail());
        requestJSON.put("password", accountUserOptionsRequest.getPassword());
        requestJSON.put("name", accountUserOptionsRequest.getName());
        requestJSON.put("taxId", accountUserOptionsRequest.getTaxId());
        requestJSON.put("phone", accountUserOptionsRequest.getPhone());
        requestJSON.put("stamps", accountUserOptionsRequest.getStamps());
        requestJSON.put("isUnlimited", accountUserOptionsRequest.isUnlimited());
        requestJSON.put("notificationEmail", accountUserOptionsRequest.getNotificationEmail());
        return requestJSON;
    }

    /**
     * Construye un objeto JSON para la actualización de un usuario basado en la
     * información proporcionada.
     *
     * @param accountUserOptionsRequest Información del usuario para la
     *                                  actualización.
     * @return Objeto JSON representando la solicitud de actualización de usuario.
     */

    protected static JSONObject buildUserUpdateJson(AccountUserOptionsRequest accountUserOptionsRequest) {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("idUser", accountUserOptionsRequest.getIdUsuario());
        requestJSON.put("name", accountUserOptionsRequest.getName());
        requestJSON.put("taxId", accountUserOptionsRequest.getTaxId());
        requestJSON.put("unlimited", accountUserOptionsRequest.isUnlimited());
        requestJSON.put("phone", accountUserOptionsRequest.getPhone());
        requestJSON.put("notificationEmail", accountUserOptionsRequest.getNotificationEmail());
        return requestJSON;
    }

    /**
     * Construye un objeto DataAccountUser a partir de un objeto JSON.
     *
     * @param userData Objeto JSON que representa la información del usuario.
     * @return Objeto DataAccountUser creado a partir del JSON.
     */

    protected static DataAccountUser UserData(JSONObject userData) {
        String idUsuario = userData.optString("idUsuario", null);
        String idDealer = userData.optString("idDealer", null);
        String name = userData.optString("name", null);
        String taxId = userData.optString("taxId", null);
        String username = userData.optString("username", null);
        String lastPasswordChange = userData.optString("lastPasswordChange", null);
        String email = userData.optString("email", null);
        String notificationEmail = userData.optString("notificationEmail", null);
        boolean isAdmin = userData.optBoolean("isAdmin");
        int profile = userData.optInt("profile");
        boolean isActive = userData.optBoolean("isActive");
        String registeredDate = userData.optString("registeredDate", null);
        String accessToken = userData.optString("accessToken", null);
        String phone = userData.optString("phone", null);
        String stamps = userData.optString("stamps", null);
        boolean isUnlimited = userData.optBoolean("isUnlimited");

        return new DataAccountUser(
                idUsuario, idDealer, name, taxId, username, lastPasswordChange, email, notificationEmail,
                isAdmin, profile, isActive, registeredDate, accessToken, phone, stamps, isUnlimited);
    }

    /**
     * Configura la solicitud HTTP con la información proporcionada en la IRequest
     * 
     * el JSON de la solicitud.
     *
     * @param request     Información de la solicitud.
     * @param httpRequest Solicitud HTTP que se configurará.
     * @param requestJSON Objeto JSON que contiene la información de la solicitud.
     */

    protected static void configureHttpRequest(IRequest request, HttpRequestBase httpRequest, JSONObject requestJSON) {
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
