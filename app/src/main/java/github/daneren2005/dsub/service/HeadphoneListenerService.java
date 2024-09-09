/*
  This file is part of Subsonic.
	Subsonic is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.
	Subsonic is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
	GNU General Public License for more details.
	You should have received a copy of the GNU General Public License
	along with Subsonic. If not, see <http://www.gnu.org/licenses/>.
	Copyright 2015 (C) Scott Jackson
*/

package github.daneren2005.dsub.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;

import github.daneren2005.dsub.receiver.HeadphonePlugReceiver;
import github.daneren2005.dsub.util.Util;

/**
 * Created by Scott on 4/6/2015.
 */
public class HeadphoneListenerService extends Service {
	private HeadphonePlugReceiver receiver;

	@SuppressLint("WrongConstant")
	@Override
	public void onCreate() {
		super.onCreate();

		receiver = new HeadphonePlugReceiver();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			registerReceiver(receiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG), Context.RECEIVER_EXPORTED);
		} else {
			registerReceiver(receiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(!Util.shouldStartOnHeadphones(this)) {
			stopSelf();
		}

		return Service.START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		try {
			if(receiver != null) {
				unregisterReceiver(receiver);
			}
		} catch(Exception e) {
			// Don't care
		}
	}
}
