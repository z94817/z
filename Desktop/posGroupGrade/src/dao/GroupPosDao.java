package dao;

import java.util.List;

import pojo.GroupPosGrade;
import pojo.GroupPosUser;
import pojo.SubCompany;

public interface GroupPosDao {

	public void addGrade(List<GroupPosGrade> list);
	
	public List<GroupPosUser> findUser();
	
	public GroupPosUser findUserByUID(int uId);
	
	public List<GroupPosUser> findUserByCID(int cId);
	
	public List<SubCompany> findSubCompany();
	
	public List<GroupPosGrade> findGradeByUID(int uId,int month);
	
	public void saveIp(String ipAddr,int companyId);
	
	public String findeIp(String ipAddr);
	
	public void addSubCompany(String cName);
	
	public void updateSubCompany(int cId,String cName);
	
	public void deleteSubCompany(int cId);
	
	public void addUsers(String fileName,String uName,String uWork);
	
	public void updateUsers(int uId,String fileName,String uName,String uWork);
	
	public void deleteUsers(int uId);
	
	public void saveSubCompanyAndUser(int cId,int uId);
	
	public void deleteSubCompanyAndUser(int cId,int uId);
	
	public List<GroupPosGrade> findAllGrade(int uId);
	
}
