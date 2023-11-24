package github.daneren2005.dsub;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;

public class AstigaApplication extends Application {
    private static final String TAG = AstigaApplication.class.getSimpleName();

    public static final class AstigaApplicationLifecycleObserver implements DefaultLifecycleObserver {

        public boolean isBackground;

        @Override
        public void onStart(@NonNull LifecycleOwner owner) {
            //Log.d(TAG, "onStart");
            isBackground=false;
        }

        @Override
        public void onStop(@NonNull LifecycleOwner owner) {
            //Log.d(TAG, "onStop");
            isBackground=true;
        }
    };

    public static final AstigaApplicationLifecycleObserver BACKGROUND_LISTENER = new AstigaApplicationLifecycleObserver();

    @Override
    public void onCreate() {
        super.onCreate();

        ProcessLifecycleOwner.get().getLifecycle()
                .addObserver(BACKGROUND_LISTENER);

    }
}
