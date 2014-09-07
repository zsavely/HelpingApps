package ru.zsavely.settings.playbackvolume;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setVolume();
		finish();
	}

	private void setVolume() {
		AudioManager mgr = null;

		mgr = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		if (mgr.getStreamVolume(AudioManager.STREAM_MUSIC) > 0) {
			mgr.setStreamVolume(AudioManager.STREAM_MUSIC, 0,
					AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);

			Toast.makeText(this, "Set volume to 0.", Toast.LENGTH_SHORT).show();
		} else {
			mgr.setStreamVolume(AudioManager.STREAM_MUSIC, 70,
					AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);

			Toast.makeText(this, "Set volume to 70.", Toast.LENGTH_SHORT).show();
		}

		// mgr.setStreamVolume(AudioManager.STREAM_RING, 0,
		// AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		// mgr.setStreamVolume(AudioManager.STREAM_ALARM, 0,
		// AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		// mgr.setStreamVolume(AudioManager.STREAM_SYSTEM, 0,
		// AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		// mgr.setStreamVolume(AudioManager.STREAM_NOTIFICATION, 0,
		// AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
	}

}
