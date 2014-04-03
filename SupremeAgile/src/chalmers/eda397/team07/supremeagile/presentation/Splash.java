package chalmers.eda397.team07.supremeagile.presentation;

import chalmers.eda397.team07.supremeagile.R;
import chalmers.eda397.team07.supremeagile.common.SAContext;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;

public class Splash extends Activity {
	MediaPlayer startSong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash);

		startSong = MediaPlayer.create(this, R.raw.start);
		startSong.start();
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(5000);
					
					// TODO load some stuff here
					SAContext.username = "DanielDHuynh";
					SAContext.password = "hanhduy0104";
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openStart = new Intent(
							"android.intent.action.REPOSITORY");
					startActivity(openStart);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		startSong.stop();
		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		startSong.release();
	}
}
