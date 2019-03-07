package com.app.barber.util.iface;

import android.view.View;

import org.json.JSONException;

/**
 * Created by Harish on 29/8/17.
 */

public interface OnItemClickListener<T> {

    public void onItemClick(View view, int position, int type, T t);

}
