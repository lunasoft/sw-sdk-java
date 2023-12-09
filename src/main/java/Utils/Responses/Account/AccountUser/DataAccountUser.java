package Utils.Responses.Account.AccountUser;


public class DataAccountUser {
    public String email;
	public String password;
	public String name;
	public String rfc;
	public String profile;
	public String stamps;
	public String unlimited;
    public String active;
	
	public DataAccountUser(String email, String password, String name, String rfc, String profile, String stamps, String unlimited, String active) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.rfc = rfc;
		this.profile = profile;
		this.stamps = stamps;
		this.unlimited = unlimited;
        this.active = active;
	}
}
