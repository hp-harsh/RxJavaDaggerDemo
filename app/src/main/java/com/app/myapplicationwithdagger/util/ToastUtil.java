package com.app.myapplicationwithdagger.util;

import android.content.Context;
import android.graphics.Typeface;

import com.app.myapplicationwithdagger.R;
import com.github.johnpersano.supertoasts.SuperToast;


/**
 * ToastUtil.java : This class is used for displaying Toast
 *
 * @author : Harsh Patel
 * @version : 1.0.0
 * @Date : 02/06/2017
 * @Change History :
 * {Change ID:#} :
 */


public class ToastUtil {

    public static final boolean isToast = true;
    private static Typeface mTypeFace;

    public static void show(Context context, String message) {

        if (isToast) {
            try {
                final SuperToast superToast = new SuperToast(context);
                superToast.cancelAllSuperToasts();//added to cancel all previous toast messages.
                if (!superToast.isShowing()) {
                    superToast.setText(message);
                    //superToast.setIcon(R.mipmap.ic_launcher, SuperToast.IconPosition.LEFT);
                    superToast.setTextSize(SuperToast.TextSize.MEDIUM);
                    superToast.setTextColor(context.getResources().getColor(R.color.black));
                    superToast.setBackground(SuperToast.Background.WHITE);
                    superToast.setDuration(SuperToast.Duration.SHORT);
                    superToast.setAnimations(SuperToast.Animations.SCALE);
                    superToast.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void show(Context context, String message, int timeInMilliSecond) {

        if (timeInMilliSecond <= 0) {
            return;
        }

        if (isToast) {
            try {
                final SuperToast superToast = new SuperToast(context);
                if (!superToast.isShowing()) {
                    superToast.setText(message);
                    //superToast.setIcon(R.mipmap.ic_launcher, SuperToast.IconPosition.LEFT);
                    superToast.setTextSize(SuperToast.TextSize.MEDIUM);
                    superToast.setTextColor(context.getResources().getColor(R.color.black));
                    superToast.setBackground(SuperToast.Background.WHITE);
                    superToast.setDuration(timeInMilliSecond);
                    superToast.setAnimations(SuperToast.Animations.SCALE);
                    superToast.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
