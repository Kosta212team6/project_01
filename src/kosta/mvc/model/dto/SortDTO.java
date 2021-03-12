package kosta.mvc.model.dto;

public class SortDTO {
	private int sCode; //pk
	private String sName;
	
	public SortDTO () {}
	public SortDTO (int sCode, String sName) {
		super();
		this.sCode = sCode;
		this.sName = sName;
	}
	public int getsCode() {
		return sCode;
	}
	public void setsCode(int sCode) {
		this.sCode = sCode;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	
	@Override
	public String toString() {
		return sCode + " | " + sName;
	}
	
}	
	
	
	