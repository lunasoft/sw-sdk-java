package Utils.Helpers;

public class EnumAccountUser {

    public enum AccountUserProfiles {
        Distribuidor(2),
        Hijo(3);

        private final int value;

        AccountUserProfiles(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
