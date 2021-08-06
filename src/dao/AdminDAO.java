package dao;

import java.sql.*;
import java.util.*;

import database.DatabaseConnection;
import model.Admin;

public class AdminDAO {
	static Connection connect = null;
	static PreparedStatement ps = null;
	
	int adminid, adminphonenum, superviseid;
	String adminpassword, adminname, adminemail;
	
	public String loginadmin(Admin admininfo) {
		String status = null;
		adminid = admininfo.getAdminid();
		adminpassword = admininfo.getAdminpassword();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM ADMIN WHERE ADMINID = ?");
			ps.setInt(1, adminid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ps = connect.prepareStatement("SELECT * FROM ADMIN WHERE ADMINID = ? AND ADMINPASSWORD = ?");
				ps.setInt(1, adminid);
				ps.setString(2, adminpassword);
				
				ResultSet secrs = ps.executeQuery();
				
				if(secrs.next()) {
					status = "success";
				}
				else {
					status = "Wrong password";
				}
			}
			else {
				status = "ID number not exist";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public String addadmin(Admin newadmin) {
		String addstatus = null;
		
		adminid = newadmin.getAdminid();
		adminname = newadmin.getAdminname();
		adminpassword = newadmin.getAdminpassword();
		superviseid = newadmin.getSuperviseid();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM ADMIN WHERE ADMINID = ?");
			ps.setInt(1, adminid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				addstatus = "Admin ID already existed";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO ADMIN (ADMINID, ADMINNAME, ADMINPASSWORD, SUPERVISEID) VALUES(?, ?, ?, ?)");
				ps.setInt(1, adminid);
				ps.setString(2, adminname);
				ps.setString(3, adminpassword);
				ps.setInt(4, superviseid);
	
				ps.execute();
				addstatus = "New admin information added";
			}
			
			connect.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			addstatus = "Add admin failed";
		}
		return addstatus;
	}
	
	public static Admin viewadmin(int viewadminid) {
		Admin admininfo = new Admin();
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM ADMIN WHERE ADMINID = ?");
			ps.setInt(1, viewadminid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				admininfo.setAdminid(rs.getInt("ADMINID"));
				admininfo.setAdminpassword(rs.getString("ADMINPASSWORD"));
				admininfo.setAdminname(rs.getString("ADMINNAME"));
				admininfo.setAdminphonenum(rs.getInt("ADMINPHONENUM"));
				admininfo.setAdminemail(rs.getString("ADMINEMAIL"));
				admininfo.setSuperviseid(rs.getInt("SUPERVISEID"));
			}
			
			connect.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return admininfo;
	}
	
	public static List<Admin> viewadminlist() {
		List<Admin> listadmin = new ArrayList<Admin>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM ADMIN");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Admin admininfo = new Admin();
				admininfo.setAdminid(rs.getInt("ADMINID"));
				admininfo.setAdminpassword(rs.getString("ADMINPASSWORD"));
				admininfo.setAdminname(rs.getString("ADMINNAME"));
				admininfo.setAdminphonenum(rs.getInt("ADMINPHONENUM"));
				admininfo.setAdminemail(rs.getString("ADMINEMAIL"));
				admininfo.setSuperviseid(rs.getInt("SUPERVISEID"));
				
				listadmin.add(admininfo);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return listadmin;
	}
	
	public String updateadmin(Admin updtadmin) {
		String updtstatus = null;
		
		adminid = updtadmin.getAdminid();
		adminpassword = updtadmin.getAdminpassword();
		adminname = updtadmin.getAdminname();
		adminphonenum = updtadmin.getAdminphonenum();
		adminemail = updtadmin.getAdminemail();
		superviseid = updtadmin.getSuperviseid();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("UPDATE ADMIN SET ADMINPASSWORD = ?, ADMINNAME = ?, ADMINPHONENUM = ?, ADMINEMAIL = ? WHERE ADMINID = ?");
			ps.setString(1, adminpassword);
			ps.setString(2, adminname);
			ps.setInt(3, adminphonenum);
			ps.setString(4, adminemail);
			ps.setInt(5, adminid);
			
			ps.execute();
			if(superviseid != 0) {
				connect = DatabaseConnection.getConnection();
				ps = connect.prepareStatement("UPDATE ADMIN SET SUPERVISEID = ? WHERE ADMINID = ?");
				ps.setInt(1, superviseid);
				ps.setInt(2, adminid);
				
				ps.execute();
			}
			updtstatus = "Admin information updated";
		}
		catch(Exception e) {
			e.printStackTrace();
			updtstatus = "Update admin information failed";
		}
		return updtstatus;
	}
}