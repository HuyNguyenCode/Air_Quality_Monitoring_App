package com.example.air_quality_monitoring_app.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

//import com.example.uitairindoor.R;
//import com.example.uitairindoor.SignUp;
//import com.example.uitairindoor.helper.DBHelper;
//import com.example.uitairindoor.helper.LocaleManager;

import java.util.Locale;


public class AppController {
    private static AppController instance;
    public boolean isSuccess = false;
    public boolean isNull = false;

    AppController() {}

    public static synchronized AppController getInstance() {
        if (instance == null) {
            instance = new AppController();
        }
        return instance;
    }

    ///Hide the keyboard when user tap out the input
//    public void hideKeyboard(Context context, View view) {
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (view != null) {
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }

    ///Check missing field
    public void checkRequiredFields(Context context, String... fields) {
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                Toast.makeText(context, "Please fill in the form!" + field, Toast.LENGTH_SHORT).show();
                isNull = true;
                return;
            }
            isNull = false;
        }
    }

    ///Check if user exist
//    public boolean checkUserAuthentication(Context context, String userName, String passWord) {
//        DBHelper dbHelper = new DBHelper(context);
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        String[] projection = {
//                DBHelper.COLUMN_USERNAME,
//                DBHelper.COLUMN_PASSWORD
//        };
//
//        String selection = DBHelper.COLUMN_USERNAME + " = ? AND " + DBHelper.COLUMN_PASSWORD + " = ?";
//        String[] selectionArgs = {userName, passWord};
//
//        Cursor cursor = db.query(
//                DBHelper.TABLE_USERS,
//                projection,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                null
//        );
//
//        int count = cursor.getCount();
//        cursor.close();
//
//        return count > 0;
//    }

    ///Register new user
//    public void registerSuccessAddToDB(Context context, String userName, String password1, String password2, String email){
//        if (!password1.equals(password2)) {
//            Toast.makeText(context, "Password must be the same! Please check again!", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            DBHelper dbHelper = new DBHelper(context);
//            SQLiteDatabase dbRead = dbHelper.getReadableDatabase();
//            Cursor cursor = dbRead.rawQuery("SELECT * FROM " + DBHelper.TABLE_USERS + " WHERE " + DBHelper.COLUMN_USERNAME + "=?", new String[]{userName});
//            int count = cursor.getCount();
//            cursor.close();
//            if (count > 0){
//                Toast.makeText(context, "Username already exists", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            else {
////                Đoạn code này dùng để lưu account vào DbHelper khi mà đã thoả tất cả các điều kiện trên
//                SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put(DBHelper.COLUMN_USERNAME, userName);
//                values.put(DBHelper.COLUMN_PASSWORD, password1);
//                values.put(DBHelper.COLUMN_EMAIL, email);
//
//                long newRowId = dbWrite.insert(DBHelper.TABLE_USERS, null, values);
//
//                if (newRowId != -1) {
//                    Toast.makeText(context, "Register Successfully!", Toast.LENGTH_SHORT).show();
//                    isSuccess = true;
//                } else {
//                    Toast.makeText(context, "Something when wrong, please try again later!", Toast.LENGTH_SHORT).show();
//                    isSuccess = false;
//                }
//            }
//        }
//    }

//    public void setLocale(Activity activity, Context context,String language, String country){
//        Resources resources = context.getResources();
//        Configuration configuration = resources.getConfiguration();
//        configuration.setLocale(new Locale(language, country));
//        Locale.setDefault(new Locale(language, country));
//        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
//        activity.recreate();
//    }

//    public void setLocaleActivity(Context context, String language, String country){
//        LocaleManager.setLocale(context, language, country);
//    }

}
