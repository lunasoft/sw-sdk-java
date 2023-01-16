package Utils.Responses.Storage;

import Utils.Responses.IResponse;

public class StorageResponse extends IResponse {

    private StorageData dataXml;

    /**
     * Constructor de la clase.
     * 
     * @param httpStatusCode codigo de respuesta.
     * @param status         status de llamada a API.
     * @param msg            mensaje devuelto por API.
     * @param msgDetail      detalles mensaje de la API.
     * @param dataXml        datos del xml 
     */
    public StorageResponse(int httpStatusCode, String status, String msg, String msgDetail,
            StorageData dataXml) {
        super(httpStatusCode, status, msg, msgDetail);
        this.dataXml = dataXml;

    }

    public StorageResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }

    // Metodo para acceder a los datos de la respuesta
    public StorageData getData() {
        return this.dataXml;
    }
}