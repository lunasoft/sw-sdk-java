package Utils.Helpers;

/**
 * EnumBalanceStamp contiene enumeraciones para el movimiento de saldo de
 * cuentas.
 */

public class EnumBalanceStamp {

    public enum AccountBalanceAction {
        /**
         * Agregar
         */
        Add("add"),
        /**
         * Remover
         */
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
