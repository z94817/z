package pojo;

import java.io.Serializable;
import java.sql.Date;

public class GroupPosGrade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pId;
	private int pIndexAttitude;
	private int pIndexTime;
	private int pIndexCorrect;
	private int subCompanyId;
	private String cName;
	private Date pDate;
	private String pNote;
	private int pUserId;
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getpIndexAttitude() {
		return pIndexAttitude;
	}
	public void setpIndexAttitude(int pIndexAttitude) {
		this.pIndexAttitude = pIndexAttitude;
	}
	public int getpIndexTime() {
		return pIndexTime;
	}
	public void setpIndexTime(int pIndexTime) {
		this.pIndexTime = pIndexTime;
	}
	public int getpIndexCorrect() {
		return pIndexCorrect;
	}
	public void setpIndexCorrect(int pIndexCorrect) {
		this.pIndexCorrect = pIndexCorrect;
	}
	public int getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(int subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public Date getpDate() {
		return pDate;
	}
	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
	public String getpNote() {
		return pNote;
	}
	public void setpNote(String pNote) {
		this.pNote = pNote;
	}
	public int getpUserId() {
		return pUserId;
	}
	public void setpUserId(int pUserId) {
		this.pUserId = pUserId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cName == null) ? 0 : cName.hashCode());
		result = prime * result + ((pDate == null) ? 0 : pDate.hashCode());
		result = prime * result + pId;
		result = prime * result + pIndexAttitude;
		result = prime * result + pIndexCorrect;
		result = prime * result + pIndexTime;
		result = prime * result + ((pNote == null) ? 0 : pNote.hashCode());
		result = prime * result + pUserId;
		result = prime * result + subCompanyId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupPosGrade other = (GroupPosGrade) obj;
		if (cName == null) {
			if (other.cName != null)
				return false;
		} else if (!cName.equals(other.cName))
			return false;
		if (pDate == null) {
			if (other.pDate != null)
				return false;
		} else if (!pDate.equals(other.pDate))
			return false;
		if (pId != other.pId)
			return false;
		if (pIndexAttitude != other.pIndexAttitude)
			return false;
		if (pIndexCorrect != other.pIndexCorrect)
			return false;
		if (pIndexTime != other.pIndexTime)
			return false;
		if (pNote == null) {
			if (other.pNote != null)
				return false;
		} else if (!pNote.equals(other.pNote))
			return false;
		if (pUserId != other.pUserId)
			return false;
		if (subCompanyId != other.subCompanyId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GroupPosGrade [pId=" + pId + ", pIndexAttitude="
				+ pIndexAttitude + ", pIndexTime=" + pIndexTime
				+ ", pIndexCorrect=" + pIndexCorrect + ", subCompanyId="
				+ subCompanyId + ", cName=" + cName + ", pDate=" + pDate
				+ ", pNote=" + pNote + ", pUserId=" + pUserId + "]";
	}
	
}
