package service;

import dao.MemberDao;
import dao.MemberDaoImpl;
import dto.MemberDto;

public class MemberService implements MemberServiceImpl {
	
	MemberDaoImpl dao = new MemberDao();
	
	public boolean getId(String id) {
		return dao.getId(id);
	}
	public boolean insert(MemberDto dto) {
		return dao.insert(dto);
	}
	public MemberDto login(MemberDto dto) {
		return dao.login(dto);
	}
	public boolean getNick(String nick) {
		return dao.getNick(nick);
	}
}
