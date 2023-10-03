package Utils.Requests.Storage;

import java.util.UUID;
import Utils.Constants;
import Utils.Requests.IRequest;

public class StorageOptionsRequest extends IRequest {
    private UUID uuid;

    public StorageOptionsRequest(String token, UUID uuid, String URIAPI, String proxyHost, int proxyPort) {
        super(token, URIAPI + Constants.STORAGE_PATH, proxyHost, proxyPort);
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
