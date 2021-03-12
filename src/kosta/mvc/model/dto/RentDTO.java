package kosta.mvc.model.dto;

public class RentDTO {
	private int rNum; // pk
	private String rDate;
	private String rExDate;
	private int rStatus;
	private int bISBN; // fk
	private String mID; // fk

	public RentDTO() {}
	public RentDTO(int rNum, String rDate, String rExDate, int rStatus, int bISBN, String mID) {
		super();
		this.rNum = rNum;
		this.rDate = rDate;
		this.rExDate = rExDate;
		this.rStatus = rStatus;
		this.bISBN = bISBN;
		this.mID = mID;
	}
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public String getrDate() {
		return rDate;
	}
	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	public String getrExDate() {
		return rExDate;
	}
	public void setrExDate(String rExDate) {
		this.rExDate = rExDate;
	}
	public int getrStatus() {
		return rStatus;
	}
	public void setrStatus(int rStatus) {
		this.rStatus = rStatus;
	}
	public int getbISBN() {
		return bISBN;
	}
	public void setbISBN(int bISBN) {
		this.bISBN = bISBN;
	}
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	@Override
	public String toString() {
		return rNum + " | " + rDate + " | " + rExDate + " | " + rStatus + " | "
				+ bISBN + " | " + mID;
	}
	
	
}
