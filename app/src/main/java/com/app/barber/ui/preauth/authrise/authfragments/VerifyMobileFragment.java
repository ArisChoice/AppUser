package com.app.barber.ui.preauth.authrise.authfragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.barber.R;
import com.app.barber.core.BaseFragment;
import com.app.barber.ui.preauth.authrise.AuthanticationActivity;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by harish on 14/12/18.
 */

public class VerifyMobileFragment extends BaseFragment {

    @BindView(R.id.close_btn)
    ImageView closeBtn;
    @BindView(R.id.otp_edtxt)
    CustomEditText otpEdtxt;
    @BindView(R.id.resend_otp)
    CustomTextView resendOtp;
    @BindView(R.id.btn_verify)
    CustomTextView btnVerify;
    Unbinder unbinder;
    private String from;
    private String countryCode, enteredNumber;

    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_verify_mobile;
    }

    @Override
    public void UpdateData(int position, Bundle bundle) {
        from = bundle.getString(GlobalValues.Extras.FROM);
        enteredNumber = bundle.getString(GlobalValues.Extras.ADD_MOBILE);
        countryCode = bundle.getString(GlobalValues.Extras.COUNTRY_CODE);
//        atNumber.setText("at " + countryCode + enteredNumber);
        otprequest();
    }

    private void otprequest() {
        mPref.setPrefrencesBoolean(GlobalValues.Extras.VERIFIED, false);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+" + countryCode + enteredNumber,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
        Toast.makeText(getActivity(), R.string.error_please_wait, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    private String mVerificationId;
    private String codeReceived;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code 
            if (code != null) {
//                editTextCode.setText(code);
                //verifying the code
                codeReceived = code;
//                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
            mResendToken = forceResendingToken;
        }
    };

    private void verifyVerificationCode(String otp) {
        try {
            //creating the credential
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
            //signing the user
            signInWithPhoneAuthCredential(credential);
        } catch (Exception e) {
            goNext();
            new CommonUtils().displayMessage(getActivity(), "Error");
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity
//                            Intent intent = new Intent(getActivity(), ProfileActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
                            new CommonUtils().displayMessage(getActivity(), "Verified successfully");
                            goNext();
                        } else {
                            goNext();
                            //verification unsuccessful.. display an error message
                            String message = "Somthing is wrong, we will fix it soon...";
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered";
                                new CommonUtils().ShowToast(message);
//                                new CommonUtils().displayMessage(getActivity(), message);
                            }
                            /*new CommonUtils().displayMessage(getActivity(), "Verified successfully");
                            Intent intent = new Intent();
                            intent.putExtra(GlobalValues.Extras.ADD_MOBILE, enteredNumber);git add -A
                            mPref.setPrefrencesBoolean(GlobalValues.Extras.VERIFIED, true);
                            setResult(RESULT_OK, intent);
                            finish();*/
                        }
                    }
                });
    }

    private void goNext() {
        Bundle intent = new Bundle();
        intent.putString(GlobalValues.Extras.ADD_MOBILE, enteredNumber);
        intent.putBoolean(GlobalValues.Extras.VERIFIED, true);
        mPref.setPrefrencesBoolean(GlobalValues.Extras.VERIFIED, true);
        if (from.equals(LoginSignUpFragment.class.getName())) {
            AuthanticationActivity.getInstance().changeFragment(0, intent);
//            setResult(RESULT_OK, intent);
        } else {
//            activitySwitcher(getActivity(), ChangePasswordActivity.class, null);
            AuthanticationActivity.getInstance().changeFragment(3, intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.close_btn, R.id.resend_otp, R.id.btn_verify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_btn:
                break;
            case R.id.resend_otp:
                otprequest();
                break;
            case R.id.btn_verify:
                verifyOtp();
                break;
        }
    }

    private void verifyOtp() {
        if (validatefields()) {
            String code = otpEdtxt.getText().toString();
            verifyVerificationCode(code);
        }
    }

    private boolean validatefields() {
        if (CommonUtils.isEmpty(otpEdtxt)) {
            CommonUtils.getInstance(getActivity()).
                    displayMessage(getActivity(), getActivity().getString(R.string.error_otp));
            return false;
        } else
            return true;
    }
}
