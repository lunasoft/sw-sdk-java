package Utils.Helpers;

public class Helpers {

    public static String NormalizeBaseUrl(String url){
        return !url.endsWith("/") ? url + "/" : url;
    }
}
