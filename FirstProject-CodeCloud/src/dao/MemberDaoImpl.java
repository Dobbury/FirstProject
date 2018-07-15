package dao;

import dto.MemberDto;

public interface MemberDaoImpl {

	public boolean getId(String id);
	public boolean insert(MemberDto dto);
	public MemberDto login(MemberDto dto);
	public boolean getNick(String nick);

}
