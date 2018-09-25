package pojo;

import java.io.Serializable;
import java.util.List;

public class GroupPosUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uId;
	private String uName;
	private String uImg;
	private String uWork;
	private int uStatus;
	private List<GroupPosGrade> grades;
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuImg() {
		return uImg;
	}
	public void setuImg(String uImg) {
		this.uImg = uImg;
	}
	public String getuWork() {
		return uWork;
	}
	public void setuWork(String uWork) {
		this.uWork = uWork;
	}
	public int getuStatus() {
		return uStatus;
	}
	public void setuStatus(int uStatus) {
		this.uStatus = uStatus;
	}
	public List<GroupPosGrade> getGrades() {
		return grades;
	}
	public void setGrades(List<GroupPosGrade> grades) {
		this.grades = grades;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grades == null) ? 0 : grades.hashCode());
		result = prime * result + uId;
		result = prime * result + ((uImg == null) ? 0 : uImg.hashCode());
		result = prime * result + ((uName == null) ? 0 : uName.hashCode());
		result = prime * result + uStatus;
		result = prime * result + ((uWork == null) ? 0 : uWork.hashCode());
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
		GroupPosUser other = (GroupPosUser) obj;
		if (grades == null) {
			if (other.grades != null)
				return false;
		} else if (!grades.equals(other.grades))
			return false;
		if (uId != other.uId)
			return false;
		if (uImg == null) {
			if (other.uImg != null)
				return false;
		} else if (!uImg.equals(other.uImg))
			return false;
		if (uName == null) {
			if (other.uName != null)
				return false;
		} else if (!uName.equals(other.uName))
			return false;
		if (uStatus != other.uStatus)
			return false;
		if (uWork == null) {
			if (other.uWork != null)
				return false;
		} else if (!uWork.equals(other.uWork))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GroupPosUser [uId=" + uId + ", uName=" + uName + ", uImg="
				+ uImg + ", uWork=" + uWork + ", uStatus=" + uStatus
				+ ", grades=" + grades + "]";
	}
	
}
