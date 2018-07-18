package singleton;

import java.util.LinkedList;

import controller.MemberController;
import dao.BBSDao;
import dao.QAbbsDao;
import dao.ShareDao;
import dto.BBSDto;
import dto.MemberDto;
import dto.QAbbsDto;
import view.membermainview.QAbbsList;
import dto.ShareDto;

public class Singleton {
	public static Singleton s = null;
	public String hostDB_IP= null;	// 호스트 디비 IP 값 받는 변수
	
	public MemberController MemCtrl = new MemberController();
	public MemberDto nowMember = null;
	public QAbbsDto qaDto;
	public QAbbsDao qaDao = new QAbbsDao();
	public QAbbsList blf = null;
	public ShareDao sharDao = new ShareDao();
	
	public BBSDao selfDao = new BBSDao();
	

	private Singleton() {
		
	}
	public static Singleton getInstance() {
		if(s==null){
			s = new Singleton();
		}
		return s;
	}
}
