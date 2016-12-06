package vn.com.arilliance.amazingwallpapers.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import vn.com.arilliance.amazingwallpapers.R;

/**
 * Created by Hoang Tran on 12/2/2016.
 */

public class Utils {
    private static Utils singleton = new Utils();
    private String backStateName;
    private ProgressDialog mProgressDialog;

    public static Utils getInstance() {
        return singleton;
    }

    public void replaceFragment(Fragment fragment, Activity activity) {
        FragmentManager manager = ((FragmentActivity) activity).getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
        backStateName = fragment.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) {
            ft.replace(R.id.fragment_container, fragment, fragment.getClass().toString());
        }
        ft.addToBackStack(fragment.getClass().toString());
        ft.commit();
    }

    public Drawable getDrawble(Context context, int id) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getResources().getDrawable(id, context.getTheme());
        } else {
            return context.getResources().getDrawable(id);
        }
    }

    public int getColor(Context context, int id) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getResources().getColor(id, context.getTheme());
        } else {
            return context.getResources().getColor(id);
        }
    }
    public void showProgressDialog(Activity context, String msg) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(msg);
        try {
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void setupUI(final Activity activity, View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity, v);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(activity, innerView);
            }
        }
    }

//    public void call(String number, Activity activity){
//        String data = "tel:" + number;
//        Intent callIntent = new Intent(Intent.ACTION_CALL);
//        callIntent.setData(Uri.parse(data));
//        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            Log.i("Phone call", "Permission not granted!");
//            return;
//        }
//        activity.startActivity(callIntent);
//    }

    public void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    view.getWindowToken(), 0);
        }
    }
}
