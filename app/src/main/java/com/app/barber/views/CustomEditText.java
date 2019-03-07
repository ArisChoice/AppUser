package com.app.barber.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.app.barber.util.FontCache;
import com.app.barber.util.GlobalValues;

/**
 * Created by Harish on 23/2/18.
 */

public class CustomEditText extends AppCompatEditText {


    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context,attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context,attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(GlobalValues.CONSTANTS.ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        Typeface customFont = selectTypeface(context, textStyle);
        setTypeface(customFont);
    }


    private Typeface selectTypeface(Context context, int textStyle) {

        switch (textStyle) {
            case Typeface.BOLD:
                return FontCache.getTypeface(GlobalValues.Font.COMFORTAA_BOLD, context);

            case Typeface.ITALIC:
                return FontCache.getTypeface(GlobalValues.Font.COMFORTAA_LIGHT, context);

            case Typeface.NORMAL:
            default:
                return FontCache.getTypeface(GlobalValues.Font.COMFORTAA_REGULAR, context);
        }
    }

}
