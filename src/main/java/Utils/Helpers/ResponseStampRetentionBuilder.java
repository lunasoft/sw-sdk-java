package Utils.Helpers;

public class ResponseStampRetentionBuilder {
    public static ResponseStamp StampedRetention(char version){
        switch(version){
            case '3': return new BuildRetentionResponseV3();
            default: return null;
        }
    }
}
