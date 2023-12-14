package Utils.Helpers;

public class EnumBalanceStamp {

    public enum AccountBalanceAction {
        Add("add"),
        Remove("remove");
    
        private final String value;
    
        AccountBalanceAction(String value) {
            this.value = value;
        }
    
        public String getValue() {
            return value;
        }
    }
    
}
