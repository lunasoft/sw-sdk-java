package Utils.Responses.Validate;

import java.util.LinkedList;

public class DetailNode{
	public String section;
	public LinkedList<DetailData> detail;
	public DetailNode(String section, LinkedList<DetailData> detail) {
		this.section = section;
		this.detail = detail;
	}
}