package Utils.Helpers;

public class RespuestaTimbrado {
	public static ResponseStamp Stamped(char version) {
		switch(version) {
		case '1': return new BuildResponseV1();
		case '2': return new BuildResponseV2();
		case '3': return new BuildResponseV3();
		case '4': return new BuildResponseV4();
		default: return null;
		}
	}
}
