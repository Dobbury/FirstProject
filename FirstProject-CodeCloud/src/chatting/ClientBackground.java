package chatting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBackground extends Thread {

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private chatPanel gui;
	private String msg;
	private String nickName;
	
	public void setGui(chatPanel gui) {
		this.gui = gui;
	}

	public void connet() {
		try {
			socket = new Socket("127.0.0.1", 7777);
			System.out.println("클라이언트 : 서버 연결됨");

			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());

			out.writeUTF(nickName); // 접속 하자마자 닉네임을 전송하면, 서버가 이걸 닉네임으로 인식해서 맵에 저장
			System.out.println("메세지 전송완료");

			if(Thread.State.NEW == this.getState())	//new만되고 실행을 안했을때
				this.start();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {

		try {
			// 계속 듣기
			while (in != null) {
				msg = in.readUTF();
				gui.appendMsg(msg);
			}

		} catch (IOException e) {

		}
	}
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendMessage(String msg2) {
		try {
			out.writeUTF(msg2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;

	}

}
