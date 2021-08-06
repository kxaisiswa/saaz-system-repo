package dao;

import java.sql.*;
import java.util.*;

import database.DatabaseConnection;
import model.ClassSubject;

public class ClassSubjectDAO {
	static Connection connect = null;
	static PreparedStatement ps = null;
	
	String classid, subjcode;
	
	public String addclasssubject(ClassSubject newclasssubject) {
		String addstatus = null;
		
		classid = newclasssubject.getClassid();
		subjcode = newclasssubject.getSubjcode();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("INSERT INTO SUBJECTCLASS(CLASSID, SUBJCODE) VALUES(?, ?)");
			ps.setString(1, classid);
			ps.setString(2, subjcode);
			
			ps.execute();
			addstatus = "Subject assigned to class";
			connect.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			addstatus = "Assign subject to class failed";
		}
		return addstatus;
	}
	
	public static List<ClassSubject> viewclasssubjectlist(String viewclassid) {
		List<ClassSubject> classubjlist = new ArrayList<ClassSubject>();
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM SUBJECTCLASS WHERE CLASSID = ?");
			ps.setString(1, viewclassid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ClassSubject classubjinfo = new ClassSubject();
				classubjinfo.setClassid(rs.getString("CLASSID"));
				classubjinfo.setSubjcode(rs.getString("SUBJCODE"));
				
				classubjlist.add(classubjinfo);
			}
			
			connect.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return classubjlist;
	}
	
	public static List<ClassSubject> viewclasssubjectnamelist(String viewclassid) {
		List<ClassSubject> classubjlist = new ArrayList<ClassSubject>();
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM SUBJECTCLASS SC JOIN SUBJECT S ON SC.SUBJCODE = S.SUBJCODE WHERE CLASSID = ?");
			ps.setString(1, viewclassid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ClassSubject classubjinfo = new ClassSubject();
				classubjinfo.setClassid(rs.getString("CLASSID"));
				classubjinfo.setSubject(SubjectDAO.viewsubjectbycode(rs.getString("SUBJCODE")));
				
				classubjlist.add(classubjinfo);
			}
			
			connect.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return classubjlist;
	}
	
	public static List<ClassSubject> viewclasssubjectlist() {
		List<ClassSubject> classubjlist = new ArrayList<ClassSubject>();
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM SUBJECTCLASS SC JOIN SUBJECT S ON SC.SUBJCODE = S.SUBJCODE");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ClassSubject classubjinfo = new ClassSubject();
				classubjinfo.setClassid(rs.getString("CLASSID"));
				classubjinfo.setSubject(SubjectDAO.viewsubjectbycode(rs.getString("SUBJCODE")));
				
				classubjlist.add(classubjinfo);
			}
			
			connect.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return classubjlist;
	}
	
	public void deleteclasssubject(String deleteclassid) {
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("DELETE FROM SUBJECTCLASS WHERE CLASSID = ?");
			ps.setString(1, deleteclassid);

			ps.execute();
			connect.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}