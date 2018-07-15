package dao;

import java.util.LinkedList;

import dto.BBSDto;

public interface BBSDaoImpl {
	public LinkedList<BBSDto> list();
	
	public int insert(String title, String lang, String content);
	
	public void select();
	
	public int update(int seq, String title, String lang, String content, boolean share);
	
	public int delete(int seq, boolean share);
	
	public int share(BBSDto dto);

	public int unshare(int seq);
	
}
