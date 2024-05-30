package Utils.Responses.Account.AccountUser;

import Utils.Responses.IResponse;

/**
 * Clase que representa una respuesta específica para operaciones relacionadas con usuarios de cuentas.
 *
 * @param <T> Tipo genérico para almacenar datos específicos de la respuesta.
 */
public class AccountUserResponse<T> extends IResponse {
    /**
     * Datos específicos de la respuesta, de tipo genérico.
     */
    public T data;

    /**
     * Constructor que inicializa una instancia de AccountUserResponse.
     *
     * @param httpStatusCode Código de estado HTTP de la respuesta.
     * @param status         Estado de la respuesta (éxito, error, etc.).
     * @param data           Datos específicos de la respuesta.
     * @param msg            Mensaje general asociado con la respuesta.
     * @param msgDetail      Detalles adicionales del mensaje asociado con la respuesta.
     */
    public AccountUserResponse(int httpStatusCode, String status, T data, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.data = data;
    }
}
