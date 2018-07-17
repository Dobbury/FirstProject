package dao;

import java.util.LinkedList;

import dto.BBSDto;

public interface BBSDaoImpl {
	public LinkedList<BBSDto> getSelfBbsList();

	public boolean insert(String title, String lang, String content);

	public BBSDto select(int seq);

	public boolean update(BBSDto dto);

	public boolean delete(int seq);

	public int share(BBSDto dto);

	public int unshare(int seq);

}
