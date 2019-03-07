package com.app.barber.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.models.request.RequestBarberModel;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.adapters.OpenBottonDialogAdapter;
import com.app.barber.ui.preauth.activities.SplashActivity;
import com.app.barber.util.iface.OnBottomDialogItemListener;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;
import com.app.barber.views.TouchImageView;
import com.app.barber.views.WheelView;
import com.facebook.drawee.view.SimpleDraweeView;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by harish on 23/8/18.
 */

public class CommonUtils {
    private static final String TAG = CommonUtils.class.getSimpleName();
    private static Context context = BarberApplication.getInstance();
    private static CommonUtils ourInstance;


    public static CommonUtils getInstance(Context context) {
        CommonUtils.context = context;
        if (ourInstance == null) {
            ourInstance = new CommonUtils();
        }
        return ourInstance;
    }


    public static void hideView(View v) {
        v.setVisibility(View.GONE);
    }

    public static void showView(View v) {
        v.setVisibility(View.VISIBLE);
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getTimestamp() {
        return new SimpleDateFormat(GlobalValues.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }

    public static long getTimestampOther() {
        return System.currentTimeMillis() / 10000;
    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isEmpty(EditText editText) {
        if (editText.getText().toString().trim().contentEquals("") || editText.getText().toString().trim() == null) {
            editText.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Description : Toast with Message String as input
     */
    Toast toast;

    public void ShowToast(String msg) {
        try {
            if (toast != null)
                toast.cancel();
            if (msg != null && !msg.trim().equalsIgnoreCase("")) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
                toast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Description : Validates the email
     *
     * @param editText
     * @return true : if email is valid
     */
    public boolean isValidEmail(EditText editText) {
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(editText.getText().toString());
        if (matcher.matches()) {
            return true;
        } else {
            ShowToast(context.getString(R.string.error_toast_invalid_email));
            return false;
        }
    }

    /**
     * @param context
     * @desc to check weather app is in
     * background or not.
     * *
     */
    public static boolean isAppInBackground(Context context) {

        boolean isInBackground = true;
        try {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
                for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                    if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        for (String activeProcess : processInfo.pkgList) {
                            if (activeProcess.equals(context.getPackageName())) {
                                isInBackground = false;
                            }
                        }
                    }
                }
            } else {
                List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
                ComponentName componentInfo = taskInfo.get(0).topActivity;
                if (componentInfo.getPackageName().equals(context.getPackageName())) {
                    isInBackground = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isInBackground;
    }

    public static String getText(EditText editText) {
        return editText.getText().toString();
    }

    /**
     * get unique device id
     */

    @SuppressLint("MissingPermission")
    public static String getUniqueDeviceId(Context context) {

        String deviceId;
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            final String tmDevice, tmSerial, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            deviceId = deviceUuid.toString();
        } catch (Exception e) {
            deviceId = tm.getDeviceId();
        }
        return deviceId;
    }

    public void openDialog(Activity activity, final String[] name, int[] icons, final OnBottomDialogItemListener listener) {
        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_image_pick_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
        RecyclerView list_view = child.findViewById(R.id.list_view);
        list_view.setLayoutManager(new LinearLayoutManager(activity));
        list_view.setAdapter(new OpenBottonDialogAdapter(name, icons, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                switch (type) {
                    case GlobalValues.ClickOperations.APAPTER_BOTTOM_DIALOG_CLICK:
                        String s = (String) o;
                        Log.e("Data ", s);
                        listener.onItemClick(view, position, type, o);
                        mBottomSheetDialog.dismiss();
                        break;
                }
            }
        }));
    }

    private String[] TIME;

    public void openDialogTimePicker(Activity activity, String icons, final OnBottomDialogItemListener listener) {
        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_time_pick_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
//        TIME = getList();
        final WheelView wheelOne = child.findViewById(R.id.wheel_view_one);
        final WheelView wheelTwo = child.findViewById(R.id.wheel_view_two);
        final TextView saveBtn = child.findViewById(R.id.save_Btn);
        final TextView cancelBtn = child.findViewById(R.id.cancel_Btn);
        wheelOne.setOffset(1);
        wheelOne.setItems(getList());
        wheelOne.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.d(TAG, "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
        wheelTwo.setOffset(1);
        wheelTwo.setItems(getList());
        wheelTwo.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.d(TAG, "selectedIndex: " + selectedIndex + ", item: " + item);

            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeSlotsModel timeModel = new TimeSlotsModel();
                timeModel.setOpeningHours(wheelOne.getSeletedItem());
                timeModel.setClosingHours(wheelTwo.getSeletedItem());
                mBottomSheetDialog.dismiss();
                listener.onItemClick(wheelTwo, 0, 1, timeModel);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
                listener.onItemClick(wheelTwo, 0, 2, null);
            }
        });
    }

    public void openDialogBarberType(Activity activity, Object o, final OnBottomDialogItemListener listener) {
        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_barber_type_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
//        TIME = getList();
        final TextView confirmBtn = child.findViewById(R.id.confirm_btn);
        final LinearLayout regularBarber = child.findViewById(R.id.regular_holder);
        final LinearLayout CalloutBarber = child.findViewById(R.id.callout_holder);

        final ImageView regularImg = child.findViewById(R.id.select_Regular_barber_img);
        final ImageView calloutImg = child.findViewById(R.id.select_callout_barber_img);
        RequestBarberModel rModl = ((RequestBarberModel) o);
        if (rModl.getFilter() == null) {
            RequestBarberModel rModel = new RequestBarberModel();
            RequestBarberModel.Filter fModel = rModel.new Filter();
            fModel.setDate("");
            fModel.setDayAvailability("");
//            fModel.setDate(new CustomDate().getCurrentMonth(activity, GlobalValues.DateFormats.DEFAULT_FORMAT_DATE));//default
//            fModel.setDayAvailability(activity.getString(R.string.str_any_time));//default
            rModl.setFilter(fModel);
        } else {
            if (rModl.getFilter().getBarberType() != null && !rModl.getFilter().getBarberType().equals("")) {
                if (rModl.getFilter().getBarberType().contains("1")) {
                    new Handler().postDelayed(() -> regularBarber.performClick(), GlobalValues.TIME_DURATIONS.SMALL);
                } else if (rModl.getFilter().getBarberType().contains("2")) {
                    new Handler().postDelayed(() -> CalloutBarber.performClick(), GlobalValues.TIME_DURATIONS.SMALL);
                }
            }
        }
        regularBarber.setOnClickListener(v -> {

            if (regularImg.getVisibility() == View.VISIBLE) {
                regularImg.setVisibility(View.GONE);
            } else {
                regularImg.setVisibility(View.VISIBLE);
                calloutImg.setVisibility(View.GONE);
                rModl.getFilter().setBarberType(String.valueOf(GlobalValues.BARBER_TYPES.BARBER));
                rModl.getFilter().setDistricts(null);
            }

        });
        CalloutBarber.setOnClickListener(v -> {
            if (calloutImg.getVisibility() == View.VISIBLE) {
                calloutImg.setVisibility(View.GONE);
            } else {
                rModl.getFilter().setBarberType(String.valueOf(GlobalValues.BARBER_TYPES.CALLOUT_BARBER));
                calloutImg.setVisibility(View.VISIBLE);
                regularImg.setVisibility(View.GONE);
            }
        });
        confirmBtn.setOnClickListener(v -> {
            mBottomSheetDialog.dismiss();
            if (calloutImg.getVisibility() == View.GONE && regularImg.getVisibility() == View.GONE) {
                rModl.getFilter().setBarberType("");
            }
            listener.onItemClick(child, 0, 2, rModl);
        });
    }

    public void openDialogBookingCalender(Activity activity, String icons, final OnBottomDialogItemListener listener) {
        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_booking_calender_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
//        TIME = getList();
        final TextView confirmBtn = child.findViewById(R.id.confirm_btn);


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
//                listener.onItemClick(wheelTwo, 0, 2, null);
            }
        });
    }

    private ArrayList<String> getList() {
        ArrayList<String> time = new ArrayList<>();
        String timeValue = "2018-10-28T00:37:04.899+05:30";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        try {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(sdf.parse(timeValue));

            if (startCalendar.get(Calendar.MINUTE) < 30) {
                startCalendar.set(Calendar.MINUTE, 30);
            } else {
                startCalendar.add(Calendar.MINUTE, 30); // overstep hour and clear minutes
                startCalendar.clear(Calendar.MINUTE);
            }

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(startCalendar.getTime());

            // if you want dates for whole next day, uncomment next line
            //endCalendar.add(Calendar.DAY_OF_YEAR, 1);
            endCalendar.add(Calendar.HOUR_OF_DAY, 24 - startCalendar.get(Calendar.HOUR_OF_DAY));

            endCalendar.clear(Calendar.MINUTE);
            endCalendar.clear(Calendar.SECOND);
            endCalendar.clear(Calendar.MILLISECOND);

            SimpleDateFormat slotTime = new SimpleDateFormat("hh:mm a");
//            SimpleDateFormat slotDate = new SimpleDateFormat(", dd/MM/yy");
            while (endCalendar.after(startCalendar)) {
                String slotStartTime = slotTime.format(startCalendar.getTime());
//                String slotStartDate = slotDate.format(startCalendar.getTime());

                startCalendar.add(Calendar.MINUTE, 5);
                String slotEndTime = slotTime.format(startCalendar.getTime());
                time.add(slotStartTime);
                Log.d("DATE", slotStartTime + " - " + slotEndTime);
            }

        } catch (ParseException e) {
            // date in wrong format
        }

        return time;
    }


    public void getKeyHash() {//3pWvky8jAayZrBrP7iZm/US+hck=(debug)
        // Add code to print out the key hash
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    "com.app.trimcheck.customer",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    public void LogoutUser() {
        CommonUtils.getInstance(context).ShowToast(context.getString(R.string.str_session_expired));
  /*      Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        QBChatService.getInstance().logout();
                        new PreferenceManager().removeQbUser();
                    } catch (SmackException.NotConnectedException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();*/

        new PreferenceManager().removeQbUser();
        new PreferenceManager().setPrefrencesBoolean(GlobalValues.KEYS.isLogedIn, false);
        new PreferenceManager().clearUserData();
        Intent intent = new Intent(context, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static String getValidUrl(String imageURL) {
        return NetworkConstatnts.URL.BASE_URL + imageURL;
    }

    public void navigateUsertoMap(Activity activity, String startlat, String startlong, String endlat, String endlong) {
        Intent navigation = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + startlat + "," + startlong + "&daddr=+" + endlat + "," + endlong));
        activity.startActivity(navigation);
    }

    public static Point getDisplaySize(WindowManager windowManager) {
        try {
            if (Build.VERSION.SDK_INT > 16) {
                Display display = windowManager.getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                display.getMetrics(displayMetrics);
                return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
            } else {
                return new Point(0, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Point(0, 0);
        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


    public String getDevideGCMid() {
        String mypreferenceNew = "mypref_Token";
        //GET THIS TOKEN FROM PREF
        SharedPreferences sharedpreferences = context.getSharedPreferences(mypreferenceNew, Context.MODE_PRIVATE);
        String deviceId = sharedpreferences.getString("pushToken", "");
        Log.e(TAG, " Refreshed token  ::: sendRegistrationToServer  =====> " + sharedpreferences.getString("pushToken", ""));
        return deviceId;
    }

    //  Used in Message screen activity & Chat screen Activity.
    public static boolean IsChatActive = false;

    public static boolean getIsChatActive() {
        return IsChatActive;
    }

    public boolean setIsChatActive(boolean IsChatActive) {
        CommonUtils.IsChatActive = IsChatActive;
        return IsChatActive;
    }

    public void displayMessage(Activity aCtivty, String toastString) {
        Log.e("displayMessage", "" + toastString);
        /*Snackbar.make(getCurrentFocus(), toastString, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();*/
        try {
            if (toastString == null || toastString.equals("")) {
                toastString = "Some error occured";
            }
        } catch (Exception e) {
            toastString = "Some error occured";
        }
        try {
            Snackbar snackbar = Snackbar.make(aCtivty.getCurrentFocus(), toastString, Snackbar.LENGTH_SHORT);
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            TextView textView = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(context.getResources().getColor(R.color.color_white));
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // slide the view from below itself to the current position
    public void slideUp(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view) {
        view.setVisibility(View.GONE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    public String getCurrentDate() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }

    public String getNewDate(int position) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");// HH:mm:ss");
        String reg_date = df.format(c.getTime());
//        showtoast("Currrent Date Time : "+reg_date);
        Log.d("getNewDate", "C " + reg_date + " " + position);
        c.add(Calendar.DATE, position);  // number of days to add
        String end_date = df.format(c.getTime());
        Log.d("getNewDate", "E " + end_date);
//        showtoast("end Time : "+end_date);
        return end_date;
    }

    public static String formatDecimalPoint(String avgRating, int i) {
        String valueA = avgRating;
//        Log.d("formatDecimalPoint", "  " + Float.valueOf(avgRating));
        try {
            switch (i) {
                case 1:
                    valueA = String.format("%.1f", Float.valueOf(avgRating));
                    break;
                case 2:
                    valueA = String.format("%.2f", Float.valueOf(avgRating));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0.0";
        }

        return valueA;
    }

    public static double getValidCordinates(String lat) {
        double value = 0.0;
        try {
            if (lat != null && !lat.equals(""))
                value = Double.parseDouble(lat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public Dialog FullImageScreem(Activity activity, String imageUrl) {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Holo_NoActionBar_Fullscreen);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme; //style id
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.full_image_view_layout);
        dialog.setCancelable(true);
        SimpleDraweeView fullImage = (SimpleDraweeView) dialog.findViewById(R.id._full_image);
        try {
//            new ImageUtility(activity).LoadImage(CommonUtils.getValidUrl(imageUrl), fullImage);
            fullImage.setImageURI(Uri.parse(imageUrl));
        } catch (Exception e) {
//            e.printStackTrace();
        }
        ImageView backImage = dialog.findViewById(R.id.back_toolbar);
        backImage.bringToFront();
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;
    }

    public static void hideSoftKeyboard(FragmentActivity activity, View vw) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (vw != null)
            inputMethodManager.hideSoftInputFromWindow(vw.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        else
            inputMethodManager.hideSoftInputFromWindow(new View(activity).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public String getSupportChatDialog(LoginResponseModel.UserBean userData) {
        return userData.getUserID() + "" + GlobalValues.SUPPORT_ADMIN_ID;
    }
}
