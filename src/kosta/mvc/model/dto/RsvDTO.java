package kosta.mvc.model.dto;

public class RsvDTO {
	private int rsvNum; //pk
	private String rsvDate;
	private String mID; //fk
	private int bIsbn; //fk
	
	public RsvDTO () {}

	public RsvDTO(int rsvNum, String rsvDate, String mID, int bIsbn) {
		super();
		this.rsvNum = rsvNum;
		this.rsvDate = rsvDate;
		this.mID = mID;
		this.bIsbn = bIsbn;
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

	public void setMID(String mID) {
		this.mID = mID;
	}

	public int getbIsbn() {
		return bIsbn;
	}

	public void setbIsbn(int bIsbn) {
		this.bIsbn = bIsbn;
	}
	
	@Override
	public String toString() {
		return rsvNum + " | " + rsvDate + " | " + mID + " | " + bIsbn;
	}
	
}
