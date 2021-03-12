package kosta.mvc.model.dto;

public class ReturnDTO {
	private int rNum;
	private String rtDate;
	private int bISBN;
	
	public ReturnDTO() {}
	public ReturnDTO(int rNum, String rtDate, int bISBN) {
		super();
		this.rNum = rNum;
		this.rtDate = rtDate;
		this.bISBN = bISBN;
	}
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public String getRtDate() {
		return rtDate;
	}
	public void setRtDate(String rtDate) {
		this.rtDate = rtDate;
	}
	public int getbISBN() {
		return bISBN;
	}
	public void setbISBN(int bISBN) {
		this.bISBN = bISBN;
	}
	@Override
	public String toString() {
		return rNum + " | " + rtDate + " | " + bISBN;
	}
	
}
