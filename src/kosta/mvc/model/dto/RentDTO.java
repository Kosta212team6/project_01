package kosta.mvc.model.dto;

public class RentDTO {
	private int rNum; // pk
	private String rDate;
	private String rExDate;
	private int rStatus;
	private int bISBN; // fk
	private String mID; // fk
	private String bName;

	public RentDTO() {}
	public RentDTO(int rNum, int bISBN) {
		this.rNum = rNum;
		this.bISBN = bISBN;
	}
	public RentDTO(int rNum, String rDate, String rExDate, int rStatus, int bISBN, String mID) {
		this(rNum, bISBN);
		this.rDate = rDate;
		this.rExDate = rExDate;
		this.rStatus = rStatus;
		this.mID = mID;
	}
	
	public RentDTO(int rNum, String rDate, String rExDate, int rStatus, int bISBN, String mID, String bName) {
		this.rNum = rNum;
		this.rDate = rDate;
		this.rExDate = rExDate;
		this.rStatus = rStatus;
		this.bISBN = bISBN;
		this.mID = mID;
		this.bName = bName;
	}
	



	public String getbName() {
		return bName;
	}
	public void setBname(String bName) {
		this.bName = bName;
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
		return rNum + " | " + bName + " | " + bISBN +" | " +rDate + " | " + rExDate + " | " + rStatus + " | " + mID ;
			
		
		
		
		
	}
	
	
}
