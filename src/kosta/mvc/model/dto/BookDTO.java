package kosta.mvc.model.dto;

public class BookDTO {
	private int bISBN; //pk
	private String bName;
	private String bWrite;
	private String bPub;
	private String bDate;
	private int bStatus;
	private int sCode; //fk
	
	public BookDTO() {}
	
	public BookDTO(int bISBN) {
		super();
		this.bISBN = bISBN;
	}
	
	public BookDTO(int bISBN, String bName, String bWrite, String bPub, String bDate, int bStatus, int sCode) {
		this(bISBN);
		this.bName = bName;
		this.bWrite = bWrite;
		this.bPub = bPub;
		this.bDate = bDate;
		this.bStatus = bStatus;
		this.sCode = sCode;
	}

	public int getbIsbn() {
		return bISBN;
	}

	public void setbIsbn(int bISBN) {
		this.bISBN = bISBN;
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
		return bISBN + " | " + bName + " | " +  bWrite + " | " +  bPub + " | " +  bDate + " | " +  bStatus + " | " +  sCode;
	}
	
}
