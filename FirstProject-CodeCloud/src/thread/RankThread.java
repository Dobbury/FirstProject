package thread;

import singleton.Singleton;
import view.membermainview.QAbbsMain;
import view.membermainview.Sharebbs;

public class RankThread extends Thread {

	@Override
	public void run() {
		
		while (true) {
			try {
				Singleton s = Singleton.getInstance();

				Sharebbs.setRankList(s.sharDao.getLikeList(), s.sharDao.getForkList());
				QAbbsMain.setRankList(s.sharDao.getLikeList(), s.sharDao.getForkList());
				
				sleep(2000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
