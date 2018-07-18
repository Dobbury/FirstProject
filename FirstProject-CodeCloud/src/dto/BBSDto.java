package dto;

public class BBSDto {

	private int seq;
	private String title;
	private String content;
	private int share;
	private int liked;
	private int fork;
	private String language;
	
	public BBSDto() {
	}
	
	public BBSDto(int seq, String title, String content, int share, int liked, int fork, String language) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.share = share;
		this.liked = liked;
		this.fork = fork;
		this.language = language;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
