package chatting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServerBackground {

	private ServerSocket serverSocket;
	private Socket socket;
 
	private String msg;

	private Map<String, DataOutputStream> clientMap = new HashMap<String, DataOutputStream>(); // 사용자의 정보를 저장하는 맵

	public void setting() {

		try {
			Collections.synchronizedMap(clientMap); // 교통정리
			serverSocket = new ServerSocket(7777);
			while (true) {
				// 서버가 할일 : 방문자를 계속 받기 위해 스레드를 계속 생성
				System.out.println("대기중..");
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + " 에서 접속하였습니다.");
				// 새로운 스레드 생성 소켓 정보 넘겨줌
				Receiver receiver = new Receiver(socket);
				receiver.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ServerBackground serverBackground = new ServerBackground();
		serverBackground.setting();
	}

	// 맵의 내용(클라이언트) 저장 삭제
	public void addClient(String nick, DataOutputStream out) {
		sendMessage(nick + "님이 접속 하셨습니다. \n");
		System.out.println(nick + "님이 접속 하셨습니다. \n");
		clientMap.put(nick, out);
	}

	public void removeClient(String nick) {
		sendMessage(nick + "님이 나가셨습니다. \n");
		System.out.println(nick + "님이 접속 나가셨습니다. \n");
		clientMap.remove(nick);
	}

	// 메세지 내용 전파

	public void sendMessage(String msg) {

		Iterator<String> it = clientMap.keySet().iterator();
		String key = "";

		while (it.hasNext()) {
			key = it.next();
			try {
				clientMap.get(key).writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

//---------------------------------------------------------------------------------------------------------------------------------------
	class Receiver extends Thread {
		// 리시버가 할일 : 네트워크 소켓을 받아서 계속 듣고, 요청하는 하는 일
		private DataInputStream in;
		private DataOutputStream out;
		private String nick;

		public Receiver(Socket socket) {

			try {
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());

				// 닉네임 설정 부분 처음 받아오는 글이 닉네
				nick = in.readUTF();
				addClient(nick, out);
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
					sendMessage(msg);
				}

			} catch (IOException e) {

				// 사용 접속 종료시 에러 발생 구간
				removeClient(nick);
			}

		}

	}

}
