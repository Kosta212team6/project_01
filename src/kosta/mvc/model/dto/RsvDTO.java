package kosta.mvc.model.dto;

public class RsvDTO {
	private int rsvNum; //  pk
	private String rsvDate;
	private String mID; //  fk
	private int bISBN; //  fk
	private String bName;
	private int bStatus;
	
	public RsvDTO () {}
	public RsvDTO(int rsvNum, String rsvDate, String mID, int bISBN, String bName, int bStatus) {
		super();
		this.rsvNum = rsvNum;
		this.rsvDate = rsvDate;
		this.mID = mID;
		this.bISBN = bISBN;
		this.bName = bName;
		this.bStatus = bStatus;
	}

	public int getRsvNum() {
		return rsvNum;
	}

	public void setRsvNum(int rsvNum) {
		this.rsvNum = rsvNum;
	}

	public String getRsvDate() {
		return rsvDate;
	}

	public void setRsvDate(String rsvDate) {
		this.rsvDate = rsvDate;
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public int getbISBN() {
		return bISBN;
	}

	public void setbISBN(int bISBN) {
		this.bISBN = bISBN;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public int getbStatus() {
		return bStatus;
	}

	public void setbStatus(int bStatus) {
		this.bStatus = bStatus;
	}
	
	
	@Override
	public String toString() {
		return rsvNum + " | " + rsvDate + " | " + mID + " | " + bISBN + " | "
				+ bName + " | " + bStatus;
	}
	
}
