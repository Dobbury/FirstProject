package dto;

public class ShareDto {

	
	private int seq=0; 
	private String nick = "";
	private String title ="";
	private String content = "";// 글작성부분
	private int liked=0;
	private int fork=0;
	private String lang = ""; 
	
	public ShareDto() {}

	public ShareDto(int seq, String nick,  String lang, String title, String content, int liked, int fork) {
		super();
		this.seq = seq;
		this.nick = nick;
		this.lang=lang;
		this.title = title;
		this.content = content;
		this.liked = liked;
		this.fork = fork;
		
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	public int getFork() {
		return fork;
	}

	public void setFork(int fork) {
		this.fork = fork;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public String toString() {
		return "ShareDto [seq=" + seq + ", nick=" + nick + ", title=" + title + ", content=" + content + ", liked="
				+ liked + ", fork=" + fork + ", lang=" + lang + "]";
	}

 
	 
	
	
	
	
	
}
