package com.app.barber.ui.postauth.activities.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.BuildConfig;
import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.ImagePickerModel;
import com.app.barber.models.Model;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthMVPView;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthPresenter;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.ImageUtility;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.iface.OnBottomDialogItemListener;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by harish on 5/11/18.
 */

public class ProfileScreenActivity extends BaseActivity implements HomeAuthMVPView {
    @Inject
    PreferenceManager mPref;
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.portfolio_image)
    SimpleDraweeView portfolioImage;
    @BindView(R.id.image_upload)
    ImageView imageUpload;
    @BindView(R.id.name_edtxt)
    CustomEditText nameEdtxt;
    @BindView(R.id.user_mobile_edtxt)
    CustomEditText userMobileEdtxt;
    @BindView(R.id.user_email_edtxt)
    CustomEditText userEmailEdtxt;
    @BindView(R.id.profile_save_btn)
    CustomTextView profileSaveBtn;

    private HomeAuthPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.layout_profile_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new HomeAuthPresenter(this);
        presenter.attachView(this);
        txtTitleToolbar.setText(R.string.profile);
        setupUserData();

    }

    private void setupUserData() {
        nameEdtxt.setText(getUserData().getFullName());
        userMobileEdtxt.setText(getUserData().getPhoneNumber());
        if (getUserData().getProfileImage() != null && !getUserData().getProfileImage().equals("")) {
            portfolioImage.setImageURI(ImageUtility.getValidUrl(getUserData().getProfileImage()));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick({R.id.back_toolbar, R.id.img_edit, R.id.image_upload, R.id.profile_save_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.img_edit:
                break;
            case R.id.image_upload:
                if (Build.VERSION.SDK_INT < 23) {
                    //Do not need to check the permission
                    openPickAlert(1);
                } else {
                    if (checkAndRequestPermissions()) {
                        //If you have already permitted the permission
                        openPickAlert(1);
                    }
                }
                break;
            case R.id.profile_save_btn:
                saveProfile();
                break;
            /*case R.id.profile_portfolio_btn:
                activitySwitcher(ProfileScreenActivity.this, MyStyleActivity.class, null);
                break;*/

        }
    }

    private void saveProfile() {
        List<MultipartBody.Part> list = new ArrayList<MultipartBody.Part>(5);
        Map<String, RequestBody> params = new HashMap<>();
        // add_ Parameter
        params.put(NetworkConstatnts.Params.fullname, RequestBody.create(MediaType.parse("text/plain"), nameEdtxt.getText().toString()));
        params.put(NetworkConstatnts.Params.email, RequestBody.create(MediaType.parse("text/plain"), userEmailEdtxt.getText().toString()));
        params.put(NetworkConstatnts.Params.desc, RequestBody.create(MediaType.parse("text/plain"), ""));
        params.put(NetworkConstatnts.Params.shopName, RequestBody.create(MediaType.parse("text/plain"), ""));
        params.put(NetworkConstatnts.Params.twitter, RequestBody.create(MediaType.parse("text/plain"), "www.google.com"));
        params.put(NetworkConstatnts.Params.facebook, RequestBody.create(MediaType.parse("text/plain"), "www.google.com"));
        params.put(NetworkConstatnts.Params.insta, RequestBody.create(MediaType.parse("text/plain"), "www.google.com"));
        params.put(NetworkConstatnts.Params.other, RequestBody.create(MediaType.parse("text/plain"), "www.google.com"));
        params.put(NetworkConstatnts.Params.userType, RequestBody.create(MediaType.parse("text/plain"), String.valueOf(GlobalValues.UserTypes.CUSTOMER)));
        MultipartBody.Part partData = null;
        if (outputFile != null) {
            File file = new File(outputFile);
            partData = MultipartBody.Part.
                    createFormData("image", file.getName(),
                            RequestBody.create(MediaType.parse("file"), file));
        }
        presenter.updateProfileRequest(NetworkConstatnts.RequestCode.API_UPDATE_PROFILE, params, partData, true);
    }

    private boolean checkAndRequestPermissions() {
        int camPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int readstoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int writestoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (readstoragePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (writestoragePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (camPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), GlobalValues.RequestCodes.PERMISSIONS_REQUEST_CAMERA);
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case GlobalValues.RequestCodes.PERMISSIONS_REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    openPickAlert(1);
                    //Permission Granted Successfully. Write working code here.
                } else {
                    //You did not accept the request can not use the functionality.
                    CommonUtils.getInstance(this).ShowToast(getString(R.string.str_permission_denied));
                }
                break;
        }
    }

    /**
     * MULTI IMAGE PICKER CALL
     *
     * @param i
     */
    private void openPickAlert(int i) {
        String[] name = {"TAKE A PHOTO", "CHOOSE FROM GALLERY"};
        int[] icons = {R.drawable.ic_menu_camera, R.drawable.ic_menu_gallery};
        CommonUtils.getInstance(ProfileScreenActivity.this).openDialog(this, name, icons, new OnBottomDialogItemListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object t) {
                switch (type) {
                    case GlobalValues.ClickOperations.APAPTER_BOTTOM_DIALOG_CLICK:
                        switch (position) {
                            case 0:
                                openCamera();
                                break;
                            case 1:
                                openGallerypicker();
                                break;
                        }
                        break;
                }
            }
        });

    }

    private void openGallerypicker() {
        // Intent to gallery
        Intent in = new Intent(Intent.ACTION_PICK);
        in.setType("image/*");
        startActivityForResult(in, GlobalValues.RequestCodes.GALLERY_REQUEST);// start
    }

    private void openCamera() {
        File f = null;
        //            f = storage.setUpPhotoFile();
        File direct = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Barber");
        File wallpaperDirectory = direct;
        if (!direct.exists()) {
            wallpaperDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Barber");
            wallpaperDirectory.mkdirs();
        }
        String fileName = ImageUtility.getFileName();
        File file = new File(wallpaperDirectory, fileName + ImageUtility.JPEG_FILE_SUFFIX);
        if (file.exists()) {
            file.delete();
        }
        outputFile = file.getPath();
//      mCurrentCameraPhotoPath = f.getAbsolutePath();
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri photoURI = FileProvider.getUriForFile(ProfileScreenActivity.this, BuildConfig.APPLICATION_ID + ".provider", file);

            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        } else
            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        takePicture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(takePicture, GlobalValues.RequestCodes.REQUEST_TAKE_IMAGE);
    }

    private String outputFile;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GlobalValues.RequestCodes.REQUEST_TAKE_IMAGE && resultCode == RESULT_OK) {
            Log.e("onActivityResult ", " REQUEST_TAKE_IMAGE " + outputFile);
            cropRequest(outputFile);
        } else if (requestCode == GlobalValues.RequestCodes.GALLERY_REQUEST && resultCode == RESULT_OK) {
            outputFile = ImageUtility.getString(getApplicationContext(), data.getData());
            Log.e("onActivityResult ", " REQUEST_TAKE_IMAGE " + outputFile);
            cropRequest(outputFile);
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Log.e("onActivityResult ", " --Icon Image Replaced --> " + resultUri + "  " + resultUri.getPath());
                ImagePickerModel pickedImage = new ImagePickerModel();
                pickedImage.setImageCroped(true);
                pickedImage.setImagePath(resultUri.getPath());
                pickedImage.setType(Model.SELECTED_IMAGE);
                outputFile = resultUri.getPath();
                ArrayList<ImagePickerModel> imageList = new ArrayList<>();
                imageList.add(pickedImage);
                portfolioImage.setImageURI(resultUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void cropRequest(String outputFile) {
        CropImage.activity(Uri.fromFile(new File(outputFile))).setAspectRatio(1,
                1).setFixAspectRatio(true).start(ProfileScreenActivity.this);

    }

    @Override
    public void onSuccessResponse(int serviceMode, Object model) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_UPDATE_PROFILE:
                if (((LoginResponseModel) model).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    new CommonUtils().displayMessage(ProfileScreenActivity.this, ((LoginResponseModel) model).getMessage());
                    LoginResponseModel.UserBean userData = getUserData();
                    LoginResponseModel.UserBean updatedData = ((LoginResponseModel) model).getUser();
                    updatedData.setSessionId(userData.getSessionId());
                    presenter.saveUserData(updatedData);
                } else
                    new CommonUtils().displayMessage(ProfileScreenActivity.this, ((BaseResponse) model).getMessage());
                break;
        }
    }


    @Override
    public void onfaliurResponse(int serviceMode, Object o) {

    }

    @Override
    public void showProgres() {

    }

    @Override
    public void hidePreogress() {

    }

    @Override
    public void onSuccess(Object o, int type) {

    }

    @Override
    public void onError(String localizedMessage) {

    }

    @Override
    public void onException(Exception e) {

    }
}
