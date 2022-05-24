package Utils.Enums;

public enum StampVersions {
    v1("v1"),
    v2("v2"),
    v3("v3"),
    v4("V4");

    public final String version;

    private StampVersions(String version){
        this.version = version;
    }
}
