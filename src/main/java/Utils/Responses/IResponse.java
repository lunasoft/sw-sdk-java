package Utils.Responses;

public abstract class IResponse {
    public int HttpStatusCode;
    public String Status;
    public String message = null;
    public String messageDetail = null;

    // Status error
    public IResponse(int httpStatusCode, String status, String message, String messageDetail) {
        HttpStatusCode = httpStatusCode;
        Status = status;
        this.message = message;
        this.messageDetail = messageDetail;
    }

    // Status success general
    public IResponse(int httpStatusCode, String status) {
        HttpStatusCode = httpStatusCode;
        Status = status;
    }

    /**
     * Obtiene el status de la solicitud.
     * Solo puede ser "success" o "error".
     * En caso de "success" puede consultar el campo "data".
     * En caso de "error", consultar "getMessage" y "getMessageDetail".
     * 
     * @return String
     */
    public String getStatus() {
        return this.Status;
    }

    public void setStatus(String value) {
        this.Status = value;
    }

    /**
     * En caso de requerirlo se puede modificar el Codigo de respuesta Http..
     * 
     * @parameter int
     */
    public void setHttpStatusCode(int value) {
        this.HttpStatusCode = value;
    }

    /**
     * Obtiene el mensaje de error obtenido.
     * <b>Nota:</b> Este valor generalmente solo existe cunado la solicitud
     * termino con status "error".
     * 
     * @return String
     */
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Obtiene los detalles acerca del mensaje de error.
     * Útil cuando el mensaje de error no es muy claro.
     * <b>Nota:</b> Este valor generalmente solo existe cunado la solicitud
     * termino con status "error".
     * <b>Nota:</b> Puede ser null.
     * 
     * @return String
     */
    public String getMessageDetail() {
        return this.messageDetail;
    }

    public void setMessageDetail(String value) {
        this.messageDetail = value;
    }
}
