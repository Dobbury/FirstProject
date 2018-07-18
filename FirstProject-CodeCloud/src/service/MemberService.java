package service;

import java.util.List;

import dao.MemberDao;
import dao.MemberDaoImpl;
import dto.MemberDto;

public class MemberService implements MemberServiceImpl {
	
	MemberDaoImpl dao = new MemberDao();
	public List<MemberDto> getbbsList(){
		return dao.getbbsList();
	}
	
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
	public boolean update(MemberDto dto) {
		return dao.update(dto);
	}
	public MemberDto search(String id) {
		return dao.search(id);
	}
}
