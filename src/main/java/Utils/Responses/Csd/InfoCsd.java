package Utils.Responses.Csd;

public class InfoCsd {
	public String issuerRfc;
	public String certificateNumber;
	public boolean isActive;
	public String issuerBussinesName;
	public String validFrom;
	public String validTo;
	public String certificateType;
	
	public InfoCsd(String issuerRfc, String certificateNumber, boolean isActive, String issuerBussinesName, String validFrom, String validTo, String certificateType) {
		this.issuerRfc = issuerRfc;
		this.certificateNumber = certificateNumber;
		this.isActive = isActive;
		this.issuerBussinesName = issuerBussinesName;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.certificateType = certificateType;
	}
}
