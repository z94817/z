package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pojo.GroupPosGrade;
import pojo.GroupPosUser;
import pojo.SubCompany;
import utils.DBUtils;
import dao.GroupPosDao;

public class GroupPosDaoImpl implements GroupPosDao {

	@Override
	public void addGrade(List<GroupPosGrade> list) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			for (int i = 0; i < list.size(); i++) {
				GroupPosGrade pgg = list.get(i);
				String sql ="INSERT into group_pos_grade VALUES(NULL,?,?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, pgg.getpIndexAttitude());
				ps.setInt(2, pgg.getpIndexCorrect());
				ps.setInt(3, pgg.getpIndexTime());
				ps.setString(4, pgg.getcName());
				ps.setString(5,new java.util.Date().toLocaleString());
				ps.setString(6, pgg.getpNote());
				ps.setInt(7, pgg.getpUserId());
				ps.execute();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.closeConnection(conn);
		}
		
	}

	@Override
	public List<GroupPosUser> findUser() {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from group_pos_user where u_status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			List<GroupPosUser> list = new ArrayList<GroupPosUser>();
			while(rs.next()){
				GroupPosUser gpu = new GroupPosUser();
				gpu.setuId(rs.getInt("u_id"));
				gpu.setuName(rs.getString("u_name"));
				gpu.setuImg(rs.getString("u_img"));
				gpu.setuWork(rs.getString("u_Work"));
				list.add(gpu);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	@Override
	public GroupPosUser findUserByUID(int uId) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from group_pos_user where u_id = ? AND u_status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uId);
			ps.setInt(2, 1);
			ResultSet rs = ps.executeQuery();
			GroupPosUser gpu = new GroupPosUser();
			if(rs.next()){
				gpu.setuId(rs.getInt("u_id"));
				gpu.setuName(rs.getString("u_name"));
				gpu.setuImg(rs.getString("u_img"));
				gpu.setuWork(rs.getString("u_Work"));
			}
			return gpu;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public List<SubCompany> findSubCompany() {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from sub_company where c_status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			List<SubCompany> list = new ArrayList<SubCompany>();
			while(rs.next()){
				SubCompany sc = new SubCompany();
				sc.setcId(rs.getInt("c_id"));
				sc.setcName(rs.getString("c_name"));
				list.add(sc);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public List<GroupPosUser> findUserByCID(int cId) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from group_pos_user where u_status = ? AND u_id in(SELECT u_id FROM subcompany_pos_user WHERE c_id = ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, cId);
			ResultSet rs = ps.executeQuery();
			List<GroupPosUser> list = new ArrayList<GroupPosUser>();
			while(rs.next()){
				GroupPosUser gpu = new GroupPosUser();
				gpu.setuId(rs.getInt("u_id"));
				gpu.setuName(rs.getString("u_name"));
				gpu.setuImg(rs.getString("u_img"));
				gpu.setuWork(rs.getString("u_Work"));
				list.add(gpu);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	public void saveIp(String ipAddr,int companyId){
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "INSERT into group_pos_ip values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ipAddr);
			ps.setString(2,new java.util.Date().toLocaleString());
			ps.setInt(3, companyId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public String findeIp(String ipAddr) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "select p_date from group_pos_ip where p_ip = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ipAddr);
			ResultSet rs = ps.executeQuery();
			String date=null;
			if(rs.next()){
				date = rs.getString("p_date");
			}
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public void addSubCompany(String cName) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "INSERT into sub_company values(null,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cName);
			ps.setInt(2, 1);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeConnection(conn);
		}
		
	}

	@Override
	public void updateSubCompany(int cId, String cName) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "UPDATE sub_company SET c_name = ? WHERE c_id = ? AND c_status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cName);
			ps.setInt(2,cId);
			ps.setInt(3,1);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeConnection(conn);
		}
		
	}

	@Override
	public void deleteSubCompany(int cId) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "UPDATE sub_company SET c_status = ? WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,2);
			ps.setInt(2,cId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeConnection(conn);
		}
		
	}

	@Override
	public void addUsers(String fileName, String uName, String uWork) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "INSERT INTO group_pos_user VALUES(null,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uName);
			ps.setString(2, fileName);
			ps.setString(3, uWork);
			ps.setInt(4, 1);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public void updateUsers(int uId, String fileName, String uName, String uWork) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "UPDATE group_pos_user SET u_name = ?,u_img= ?,u_work= ? WHERE u_id = ? AND u_status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uName);
			ps.setString(2, fileName);
			ps.setString(3, uWork);
			ps.setInt(4, uId);
			ps.setInt(5, 1);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public void deleteUsers(int uId) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "UPDATE group_pos_user SET u_status = ? WHERE u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,2);
			ps.setInt(2,uId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public void saveSubCompanyAndUser(int cId, int uId) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "INSERT INTO subcompany_pos_user VALUES(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cId);
			ps.setInt(2, uId);
			ps.setString(3, new Date().toLocaleString());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public void deleteSubCompanyAndUser(int cId, int uId) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "DELETE FROM subcompany_pos_user WHERE c_id = ? and u_id= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cId);
			ps.setInt(2, uId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public List<GroupPosGrade> findGradeByUID(int uId,int month) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "SELECT * FROM group_pos_grade WHERE p_user_id = ? AND MONTH(p_date)= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uId);
			ps.setInt(2, month);
			ResultSet rs = ps.executeQuery();
			List<GroupPosGrade> list = new ArrayList<GroupPosGrade>();
			while(rs.next()){
				GroupPosGrade gpg = new GroupPosGrade();
				gpg.setpId(rs.getInt("p_id"));
				gpg.setpIndexAttitude(rs.getInt("p_index_attitude"));
				gpg.setpIndexTime(rs.getInt("p_index_time"));
				gpg.setpIndexCorrect(rs.getInt("p_index_correct"));
				gpg.setcName(rs.getString("c_name"));
				gpg.setpDate(rs.getDate("p_date"));
				gpg.setpNote(rs.getString("p_note"));
				gpg.setpUserId(rs.getInt("p_user_id"));
				list.add(gpg);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public List<GroupPosGrade> findAllGrade(int uId) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "SELECT * FROM group_pos_grade WHERE p_user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uId);
			ResultSet rs = ps.executeQuery();
			List<GroupPosGrade> list = new ArrayList<GroupPosGrade>();
			while(rs.next()){
				GroupPosGrade gpg = new GroupPosGrade();
				gpg.setpId(rs.getInt("p_id"));
				gpg.setpIndexAttitude(rs.getInt("p_index_attitude"));
				gpg.setpIndexTime(rs.getInt("p_index_time"));
				gpg.setpIndexCorrect(rs.getInt("p_index_correct"));
				gpg.setcName(rs.getString("c_name"));
				gpg.setpDate(rs.getDate("p_date"));
				gpg.setpNote(rs.getString("p_note"));
				gpg.setpUserId(rs.getInt("p_user_id"));
				list.add(gpg);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			DBUtils.closeConnection(conn);
		}
	}

	

}
