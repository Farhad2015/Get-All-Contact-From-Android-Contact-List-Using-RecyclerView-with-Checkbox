package com.ezzetech.getallcontactsrnd;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Development on 9/3/2016.
 */
public class DatabaseAdapter {

    Cursor getPhoneNumber;
    ContentResolver resolver ;

    Context context;

    public DatabaseAdapter(Context context) {
        this.context = context;
    }

    public List<SelectUser> getData() {
        List<SelectUser> data = new ArrayList<>();

        getPhoneNumber = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if (getPhoneNumber != null) {
            Log.e("count", "" + getPhoneNumber.getCount());
            if (getPhoneNumber.getCount() == 0) {
                Toast.makeText(context, "No contacts in your contact list.", Toast.LENGTH_LONG).show();
            }

            while (getPhoneNumber.moveToNext()) {
                String id = getPhoneNumber.getString(getPhoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                String name = getPhoneNumber.getString(getPhoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = getPhoneNumber.getString(getPhoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //String EmailAddr = getPhoneNumber.getString(getPhoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA2));
                String image_thumb = getPhoneNumber.getString(getPhoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI));

                SelectUser selectUser = new SelectUser();
                selectUser.setImagepath(image_thumb);
                selectUser.setName(name);
                selectUser.setPhone(phoneNumber);
                selectUser.setCheckedBox(false);
                selectUser.setEmail(id);
                data.add(selectUser);
            }
        } else {
            Log.e("Cursor close 1", "----");
        }

        getPhoneNumber.close();

        return data;
    }
}
