package com.jiahaozhang.mooddiary.common;

import android.app.Activity;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Toast;

import com.jiahaozhang.mooddiary.R;

/**
 * Created by acanprince on 2017/5/23.
 *
 * 双击退出-main中引入
 */

public class DoubleClickExitHelper {
    private Activity context;
    private boolean firstKeyDown = true;

    private Handler handler;

    private Toast exitToast;

    private Runnable onBackTimeRunnable = new Runnable() {
        @Override
        public void run() {
            if (exitToast != null) {
                exitToast.cancel();
            }
            firstKeyDown = true;
        }
    };

    public DoubleClickExitHelper(Activity context) {
        this.context = context;
        handler = new Handler();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return false;
        }
        if (firstKeyDown) {
            if (exitToast == null) {
                exitToast = Toast.makeText(context, context.getString(R.string.double_back_exit_hint), Toast.LENGTH_SHORT);
            }
            exitToast.show();
            handler.postDelayed(onBackTimeRunnable, 2000);
            firstKeyDown = false;
        } else {
            handler.removeCallbacks(onBackTimeRunnable);
            if (exitToast != null) {
                exitToast.cancel();
            }
            context.finish();
        }
        return true;
    }
}
