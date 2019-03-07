/*
package com.app.barber.ui.postauth.activities.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

*/
/**
 * Created by harish on 2/11/18.
 *//*


public class PaymentTypeActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.text_type_card)
    CustomTextView textTypeCard;
    @BindView(R.id.type_card_layout)
    LinearLayout typeCardLayout;
    @BindView(R.id.text_type_cash)
    CustomTextView textTypeCash;
    @BindView(R.id.img_check_type_two)
    ImageView imgCheckTypeTwo;
    @BindView(R.id.type_cash_layout)
    LinearLayout typeCashLayout;
    @BindView(R.id.text_type_both)
    CustomTextView textTypeBoth;
    @BindView(R.id.img_check_type_three)
    ImageView imgCheckTypeThree;
    @BindView(R.id.type_both_layout)
    LinearLayout typeBothLayout;
    @BindView(R.id.continue_btn)
    ImageView continueBtn;
    boolean isCard, isCash, isBoth;

    @Override
    public int getLayoutId() {
        return R.layout.layout_payment_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.back_toolbar, R.id.type_card_layout, R.id.type_cash_layout, R.id.type_both_layout, R.id.continue_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.type_card_layout:
                setSelection(R.id.type_card_layout);
                break;
            case R.id.type_cash_layout:
                setSelection(R.id.type_cash_layout);
                break;
            case R.id.type_both_layout:
                setSelection(R.id.type_both_layout);
                break;
            case R.id.continue_btn:
                activitySwitcher(PaymentTypeActivity.this, AddWorkspacePhotos.class, null);
                break;
        }
    }

    private void setSelection(int intType) {
        switch (intType) {
            case R.id.type_card_layout:
                typeCardLayout.setBackgroundResource(R.drawable.rectangle_selected_type_drawable);
                typeCashLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
                typeBothLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
                textTypeCard.setTextColor(getResources().getColor(R.color.colorPrimary));
                textTypeCash.setTextColor(getResources().getColor(R.color.color_white));
                textTypeBoth.setTextColor(getResources().getColor(R.color.color_white));
                break;
            case R.id.type_cash_layout:

                typeCardLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
                typeCashLayout.setBackgroundResource(R.drawable.rectangle_selected_type_drawable);
                typeBothLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
                textTypeCard.setTextColor(getResources().getColor(R.color.color_white));
                textTypeCash.setTextColor(getResources().getColor(R.color.colorPrimary));
                textTypeBoth.setTextColor(getResources().getColor(R.color.color_white));

                break;
            case R.id.type_both_layout:
                typeCardLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
                typeCashLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
                typeBothLayout.setBackgroundResource(R.drawable.rectangle_selected_type_drawable);
                textTypeCard.setTextColor(getResources().getColor(R.color.color_white));
                textTypeCash.setTextColor(getResources().getColor(R.color.color_white));
                textTypeBoth.setTextColor(getResources().getColor(R.color.colorPrimary));

                break;
        }
    }
}
*/
