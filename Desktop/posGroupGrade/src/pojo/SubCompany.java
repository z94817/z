package pojo;

import java.io.Serializable;
import java.util.List;


public class SubCompany implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cId;
	private String cName;
	private int cStatus;
	private List<GroupPosUser> users;
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public int getcStatus() {
		return cStatus;
	}
	public void setcStatus(int cStatus) {
		this.cStatus = cStatus;
	}
	public List<GroupPosUser> getUsers() {
		return users;
	}
	public void setUsers(List<GroupPosUser> users) {
		this.users = users;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cId;
		result = prime * result + ((cName == null) ? 0 : cName.hashCode());
		result = prime * result + cStatus;
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		SubCompany other = (SubCompany) obj;
		if (cId != other.cId)
			return false;
		if (cName == null) {
			if (other.cName != null)
				return false;
		} else if (!cName.equals(other.cName))
			return false;
		if (cStatus != other.cStatus)
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SubCompany [cId=" + cId + ", cName=" + cName + ", cStatus="
				+ cStatus + ", users=" + users + "]";
	}

}
