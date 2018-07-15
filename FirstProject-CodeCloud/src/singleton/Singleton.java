package singleton;

import java.util.LinkedList;

import controller.MemberController;
import dao.QAbbsDao;
import dto.BBSDto;
import dto.MemberDto;
import dto.QAbbsDto;
import view.memberpanel.QAbbsList;

public class Singleton {
	public static Singleton s = null;
	public String hostDB_IP= null;	// 호스트 디비 IP 값 받는 변수
	
	public MemberController MemCtrl = new MemberController();
	public MemberDto nowMember = null;
	
	public QAbbsDto qaDto;
	public QAbbsDao qaDao = new QAbbsDao();
	//public QAbbsDao qaDao ;
	public QAbbsList blf = null;

	public LinkedList<BBSDto> selfcodelist = new LinkedList<>();

	private Singleton() {
		
	}
	public static Singleton getInstance() {
		if(s==null){
			s = new Singleton();
		}
		
		return s;
	}
}
