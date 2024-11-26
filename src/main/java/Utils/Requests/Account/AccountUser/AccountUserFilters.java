package Utils.Requests.Account.AccountUser;

public enum AccountUserFilters {
    EMAIL("Email"),
    TAX_ID("TaxId"),
    ID_USER("IdUser"),
    IS_ACTIVE("IsActive");

    private final String queryKey;

    // Constructor correcto
    AccountUserFilters(String queryKey) {
        this.queryKey = queryKey;
    }

    public String getQueryKey() {
        return queryKey;
    }
}