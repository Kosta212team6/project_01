package kosta.mvc.model.dto;

public class MemberDTO {
	private String mID; // pk
	private String mName;
	private String mPhone;
	private String mPwd;
	private String mAble;
	private int mStatus;
	private int nCode; // fk
	
	public MemberDTO() {}
	public MemberDTO(String mID, String mPwd, int mStatus) {
		this.mID = mID;
		this.mPwd = mPwd;
		this.mStatus = mStatus;
	}
	public MemberDTO(String mID, String mName, String mPhone, String mPwd, String mAble, int mStatus, int nCode) {
		this(mID, mPwd, mStatus);
		this.mName = mName;
		this.mPhone = mPhone;
		this.mAble = mAble;
		this.nCode = nCode;
	}
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public String getmPwd() {
		return mPwd;
	}
	public void setmPwd(String mPwd) {
		this.mPwd = mPwd;
	}
	public String getmAble() {
		return mAble;
	}
	public void setmAble(String mAble) {
		this.mAble = mAble;
	}
	public int getmStatus() {
		return mStatus;
	}
	public void setmStatus(int mStatus) {
		this.mStatus = mStatus;
	}
	public int getnCode() {
		return nCode;
	}
	public void setnCode(int nCode) {
		this.nCode = nCode;
	}
	@Override
	public String toString() {
		return mID + " | " + mName + " | " + mPhone + " | " + mAble + " | ";
	}

}
