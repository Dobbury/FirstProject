package service;

import java.util.List;

import dto.MemberDto;

public interface MemberServiceImpl {
	public boolean getId(String id);
	public boolean insert(MemberDto dto);
	public MemberDto login(MemberDto dto);
	public boolean getNick(String nick);
	public boolean update(MemberDto dto);
	public List<MemberDto> getbbsList();
	public List<MemberDto> search(String txt, String choice);
}
