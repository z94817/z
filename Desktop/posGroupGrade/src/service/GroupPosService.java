package service;

import java.util.List;

import pojo.GroupPosGrade;
import pojo.GroupPosUser;
import pojo.SubCompany;
import dao.GroupPosDao;
import dao.impl.GroupPosDaoImpl;

public class GroupPosService {

	public void addGrade(List<GroupPosGrade> list){
		GroupPosDao pgd = new GroupPosDaoImpl();
		pgd.addGrade(list);
	}
	
	public List<GroupPosUser> findUser(){
		GroupPosDao gpd = new GroupPosDaoImpl();
		return gpd.findUser();
	}
	
	public GroupPosUser findUserByUID(int uId){
		GroupPosDao gpd = new GroupPosDaoImpl();
		return gpd.findUserByUID(uId);
	}
	
	public List<GroupPosUser> findUserByCID(int cid){
		GroupPosDao gpd = new GroupPosDaoImpl();
		return gpd.findUserByCID(cid);
	}
	
	public List<SubCompany> findSubCompany(){
		GroupPosDao gpd = new GroupPosDaoImpl();
		return gpd.findSubCompany();
	}
	
	public void saveIp(String ipAddr,int companyId){
		GroupPosDao gpd = new GroupPosDaoImpl();
		gpd.saveIp(ipAddr, companyId);
	}
	
	public String findeIp(String ipAddr){
		GroupPosDao gpd = new GroupPosDaoImpl();
		return gpd.findeIp(ipAddr);
	}
	
	public void addSubCompany(String cName){
		GroupPosDao gpd = new GroupPosDaoImpl();
		gpd.addSubCompany(cName);
	}
	
	public void updateSubCompany(int cId,String cName){
		GroupPosDao gpd = new GroupPosDaoImpl();
		gpd.updateSubCompany(cId,cName);
	}
	
	public void deleteSubCompany(int cId){
		GroupPosDao gpd = new GroupPosDaoImpl();
		gpd.deleteSubCompany(cId);
	}
	
	public void addUsers(String fileName, String uName, String uWork) {
		GroupPosDao gpd = new GroupPosDaoImpl();
		gpd.addUsers(fileName, uName, uWork);
	}
	
	public void updateUsers(int uId,String fileName, String uName, String uWork) {
		GroupPosDao gpd = new GroupPosDaoImpl();
		gpd.updateUsers(uId, fileName, uName, uWork);
	}
	
	public void deleteUsers(int uId){
		GroupPosDao gpd = new GroupPosDaoImpl();
		gpd.deleteUsers(uId);
	}
	
	public void saveSubCompanyAndUser(int cId, int uId) {
		GroupPosDao gpd = new GroupPosDaoImpl();
		gpd.saveSubCompanyAndUser(cId, uId);
	}
	
	public void deleteSubCompanyAndUser(int cId, int uId) {
		GroupPosDao gpd = new GroupPosDaoImpl();
		gpd.deleteSubCompanyAndUser(cId, uId);
	}
	
	public List<GroupPosGrade> findGradeByUID(int uId,int month) {
		GroupPosDao gpd = new GroupPosDaoImpl();
		return gpd.findGradeByUID(uId,month);
	}
	
	public List<GroupPosGrade> findAllGrade(int uId) {
		GroupPosDao gpd = new GroupPosDaoImpl();
		return gpd.findAllGrade(uId);
	}
}
