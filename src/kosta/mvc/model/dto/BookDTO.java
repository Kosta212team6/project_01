package kosta.mvc.model.dto;

public class BookDTO {
	private int bIsbn; //pk
	private String bName;
	private String bWrite;
	private String bPub;
	private String bDate;
	private int bStatus;
	private int sCode; //fk
	
	public BookDTO() {}
	
	public BookDTO(int bIsbn, String bName, String bWrite, String bPub, String bDate, int bStatus, int sCode) {
		super();
		this.bIsbn = bIsbn;
		this.bName = bName;
		this.bWrite = bWrite;
		this.bPub = bPub;
		this.bDate = bDate;
		this.bStatus = bStatus;
		this.sCode = sCode;
	}

	public int getbIsbn() {
		return bIsbn;
	}

	public void setbIsbn(int bIsbn) {
		this.bIsbn = bIsbn;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbWrite() {
		return bWrite;
	}

	public void setbWrite(String bWrite) {
		this.bWrite = bWrite;
	}

	public String getbPub() {
		return bPub;
	}

	public void setbPub(String bPub) {
		this.bPub = bPub;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public int getbStatus() {
		return bStatus;
	}

	public void setbStatus(int bStatus) {
		this.bStatus = bStatus;
	}

	public int getsCode() {
		return sCode;
	}

	public void setsCode(int sCode) {
		this.sCode = sCode;
	}
	
	@Override
	public String toString() {
		return bIsbn + " | " + bName + " | " +  bWrite + " | " +  bPub + " | " +  bDate + " | " +  bStatus + " | " +  sCode;
	}
	
}
