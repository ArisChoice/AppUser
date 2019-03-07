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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.app.barber.models.response.MyImagesResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.adapters.MyStyleAdapter;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthMVPView;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthPresenter;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.ImageUtility;
import com.app.barber.util.iface.OnBottomDialogItemListener;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by harish on 6/11/18.
 */

public class MyStyleActivity extends BaseActivity implements HomeAuthMVPView {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.img_edit)
    ImageView imgAdd;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    private MyStyleAdapter myStyleAdapter;
    private HomeAuthPresenter presenter;


    @Override
    public int getLayoutId() {
        return R.layout.layout_mystyle_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new HomeAuthPresenter(this);
        presenter.attachView(this);
        txtTitleToolbar.setText(R.string.str_mystyle);
        imgAdd.setVisibility(View.INVISIBLE);
        imgAdd.setImageResource(R.drawable.add_copy);
        initRecyclar();
        getMyStyleImages();
    }

    private void initRecyclar() {
        ArrayList list = new ArrayList<MyImagesResponseModel.ImagesBean>();
        list.add(null);
        myStyleAdapter = new MyStyleAdapter(MyStyleActivity.this, list,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, int type, Object t) {
                        switch (type) {
                            case GlobalValues.ClickOperations.ADD_IMAGE:
//                                imageType = 3;//Portfolio image
                                imagePickerCall();
                                break;
                            case GlobalValues.ClickOperations.DETAILS:
                                new CommonUtils().FullImageScreem(MyStyleActivity.this, ImageUtility.getValidUrl(((MyImagesResponseModel.ImagesBean) t).getMItem1()));
                                break;
                            case GlobalValues.ClickOperations.REMOVE:
                                presenter.deleteImage(NetworkConstatnts.RequestCode.API_DELETE_IMAGE, String.valueOf(((MyImagesResponseModel.ImagesBean) t).getMItem2()), true);

                                break;
                        }
                    }
                });
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyStyleActivity.this);
        recyclarViewLst.setLayoutManager(new GridLayoutManager(MyStyleActivity.this, 2));
        recyclarViewLst.setAdapter(myStyleAdapter);
    }

    private void imagePickerCall() {
        if (Build.VERSION.SDK_INT < 23) {
            //Do not need to check the permission
            openPickAlert(1);
        } else {
            if (checkAndRequestPermissions()) {
                //If you have already permitted the permission
                openPickAlert(1);
            }
        }
    }

    @OnClick({R.id.back_toolbar, R.id.img_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.img_edit:
                imagePickerCall();
                break;

        }
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
        CommonUtils.getInstance(MyStyleActivity.this).openDialog(this, name, icons, new OnBottomDialogItemListener() {
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
            Uri photoURI = FileProvider.getUriForFile(MyStyleActivity.this, BuildConfig.APPLICATION_ID + ".provider", file);

            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        } else
            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        takePicture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(takePicture, GlobalValues.RequestCodes.REQUEST_TAKE_IMAGE);
    }

    private void cropRequest(String outputFile) {
        CropImage.activity(Uri.fromFile(new File(outputFile))).setAspectRatio(1,
                1).setFixAspectRatio(true).start(MyStyleActivity.this);

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
                ArrayList<ImagePickerModel> imageList = new ArrayList<>();
                imageList.add(pickedImage);
                saveImages(resultUri.getPath());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void saveImages(String path) {
        // Add Images
        List<MultipartBody.Part> list = new ArrayList<MultipartBody.Part>(2);
        Map<String, RequestBody> params = new HashMap<>();
        // add_ Parameter
        params.put(NetworkConstatnts.Params.value, RequestBody.create(MediaType.parse("text/plain"), "portfolio"));
        File file = new File(path);
        list.add(MultipartBody.Part.
                createFormData("image", file.getName(),
                        RequestBody.create(MediaType.parse("file"), file)));
        presenter.postImages(NetworkConstatnts.RequestCode.API_POST_WORKSPACE_IMAGES, params, list, true);


    }

    @Override
    public void onSuccessResponse(int serviceMode, Object model) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_POST_WORKSPACE_IMAGES:
                if (((BaseResponse) model).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    new CommonUtils().displayMessage(MyStyleActivity.this, ((BaseResponse) model).getMessage());
                    getMyStyleImages();
                } else
                    new CommonUtils().displayMessage(MyStyleActivity.this, ((BaseResponse) model).getMessage());
                break;
            case NetworkConstatnts.RequestCode.API_GET_IMAGES:
                if (((MyImagesResponseModel) model).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    if (((MyImagesResponseModel) model).getImages() != null && ((MyImagesResponseModel) model).getImages().size() > 0) {
                        myStyleAdapter.updateAll(((MyImagesResponseModel) model).getImages());
                        noListDataText.setVisibility(View.GONE);
                        recyclarViewLst.setVisibility(View.VISIBLE);
                    }
                } else
                    new CommonUtils().displayMessage(MyStyleActivity.this, ((BaseResponse) model).getMessage());
                break;
            case NetworkConstatnts.RequestCode.API_DELETE_IMAGE:
                getMyStyleImages();
                break;
        }
    }

    private void getMyStyleImages() {
        presenter.getMyStyleList(NetworkConstatnts.RequestCode.API_GET_IMAGES, null, true);
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
