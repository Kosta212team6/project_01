package kosta.mvc.model.dto;

public class SortDTO {
	private int scode;
	private String sname;
	
	public SortDTO () {}
	public SortDTO (int scode, String sname) {
		super();
		this.scode = scode;
		this.sname = sname;
	}
	public int getScode() {
		return scode;
	}
	public void setScode(int scode) {
		this.scode = scode;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	@Override
	public String toString() {
		return scode + " | " + sname;
	}
	
}
