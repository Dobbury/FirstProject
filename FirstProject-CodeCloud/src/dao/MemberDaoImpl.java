package dao;

import java.util.List;

import dto.MemberDto;
import dto.ShareDto;

public interface MemberDaoImpl {

	public List<MemberDto> getbbsList();
	
	public boolean getId(String id);

	public boolean insert(MemberDto dto);
	
	public boolean update(MemberDto dto);
	
	public MemberDto login(MemberDto dto);

	public boolean getNick(String nick);

	public List<MemberDto> search(String id, String choice);

	public int delete(MemberDto dto);
}
