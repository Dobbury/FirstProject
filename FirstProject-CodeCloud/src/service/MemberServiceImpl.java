package service;

import dto.MemberDto;

public interface MemberServiceImpl {
	public boolean getId(String id);
	public boolean insert(MemberDto dto);
	public MemberDto login(MemberDto dto);
	public boolean getNick(String nick);
	public boolean update(MemberDto dto);
}
