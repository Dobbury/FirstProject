package dto;

public class QAbbsDto {

	private int seq = 0;// 번호
	private String nick = "";// 닉네임
	private String title = "";// 제목
	private String content = "";// 글작성부분
	private String wdate = "";// 날짜
	private int del;
	private int ref;
	private int step;
	private int dept;
	private int visible;

	public QAbbsDto() {
	}

	public QAbbsDto(int seq, String nick, String title, String content, String wdate, int del, int ref, int step,
			int dept, int visible) {
		super();
		this.seq = seq;
		this.nick = nick;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.ref = ref;
		this.step = step;
		this.dept = dept;
		this.visible = visible;
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

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDept() {
		return dept;
	}

	public void setDept(int dept) {
		this.dept = dept;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		return "QAbbsDto [seq=" + seq + ", nick=" + nick + ", title=" + title + ", content=" + content + ", wdate="
				+ wdate + ", del=" + del + ", ref=" + ref + ", step=" + step + ", dept=" + dept + ", visible=" + visible
				+ "]";
	}
}