/*
 * Copyright 2018 Mayur Rokade
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package com.app.barber.ui.postauth.activities.socket_work.chat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.chat_model.ChatMessageModel;
import com.app.barber.models.request.UpdateChatDialogRequest;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.models.response.ConversationResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.chat.chatmvp.ChatMVPView;
import com.app.barber.ui.postauth.activities.socket_work.eventservice.EventServiceImpl;
import com.app.barber.ui.postauth.activities.socket_work.util.Injection;
import com.app.barber.ui.postauth.activities.socket_work.util.TextUtils;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.CustomDate;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements ChatContract.View, ChatMVPView {

    private static final String TAG = ChatActivity.class.getSimpleName();
    private static final long TYPING_TIMER_LENGTH = 3000;
    private static final long ALERT_LENGTH = 2000;
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.tvAlert)
    TextView tvAlert;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    @BindView(R.id.chat_field)
    CustomEditText chatField;
    @BindView(R.id.send_btn)
    ImageView sendBtn;
    private RecyclerView.LayoutManager mLayoutManager;
    private ChatMessagesAdapter mChatMessagesAdapter;
    //    private LinearLayout llTyping;
//    private TextView tvTyping, tvAlert;
    private ChatContract.Presenter mPresenter;
    private boolean mTyping = false;
    private Handler mTypingHandler = new Handler();
    private int mAlerterHeight;
    private String chatDialogId, otherImage, otherUserId;
    private com.app.barber.ui.postauth.activities.chat.chatmvp.ChatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat_new);
        ButterKnife.bind(this);
        ((BarberApplication) getApplication()).getMyComponent(ChatActivity.this).inject(this);
        presenter = new com.app.barber.ui.postauth.activities.chat.chatmvp.ChatPresenter(ChatActivity.this);
        presenter.attachView(this);
        txtTitleToolbar.setText(R.string.str_chat);
        chatDialogId = getIntent().getStringExtra(GlobalValues.KEYS.EXTRA_DIALOG_ID);
//        chatDialogId = "16917";
        otherImage = getIntent().getStringExtra(GlobalValues.KEYS.OTHER_IMAGE);
        otherUserId = getIntent().getStringExtra(GlobalValues.KEYS.USER_ID);
//        otherUserId = "17";
        EventServiceImpl.getInstce().mSocket.emit("join", chatDialogId);

    }

    /**
     * @return layout resource id
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_chat_new;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void getConversationList() {
        presenter.getConversationList(NetworkConstatnts.RequestCode.API_CONVERSATION_LIST, chatDialogId, "" + getUserData().getUserID(), otherUserId, true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new ChatPresenter(this, this,
                Injection.provideSchedulerProvider(),
                Injection.providesRepository(this));
        getConversationList();
        new CommonUtils().setIsChatActive(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        new CommonUtils().setIsChatActive(false);
        mPresenter.unsubscribe();

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_name:
                askUsername();
                break;
            case R.id.info:
                showInfo();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void initView() {
        // Init UI elements
      /*  rvChatMessages = findViewById(R.id.rvChatMessages);
        etSendMessage = findViewById(R.id.etSendMessage);
        ivSendMessage = findViewById(R.id.btnSendMessage);
        tvTyping = findViewById(R.id.tvTyping);
        llTyping = findViewById(R.id.llTyping);
        tvAlert = findViewById(R.id.tvAlert);
        tvAlert.setTranslationY(-100);

        getSupportActionBar().setTitle("Realtime MVP Chat");

        // Ask the user to set a username,
        // when the app opens up.
        if (!User.isUsernameUpdated()) {
            askUsername();
        }*/
        tvAlert.setTranslationY(-100);
        setupChatMessages();
        setupSendButton();
        setupTextWatcher();
    }

    @Override
    public void setPresenter(ChatContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showAlert(final String message, final boolean isError) {
        Log.i(TAG, "showAlert: " + message);
        final int successColor = ContextCompat.getColor(this, R.color.color_green);
        final int errorColor = ContextCompat.getColor(this, R.color.color_red);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvAlert.setText(message);

                if (isError) {
                    tvAlert.setBackgroundColor(errorColor);
                } else {
                    tvAlert.setBackgroundColor(successColor);
                }

                mAlerterHeight = tvAlert.getHeight();
                tvAlert.setTranslationY(-1 * mAlerterHeight);

                tvAlert.animate()
                        .translationY(0)
                        .setDuration(500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        hideAlert();
                                    }
                                }, ALERT_LENGTH);
                            }
                        });
            }
        });
    }

    @Override
    public void hideAlert() {
        tvAlert.animate()
                .translationY(-1 * mAlerterHeight)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        tvAlert.setText("");
                    }
                });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void setupChatMessages() {
        mChatMessagesAdapter = new ChatMessagesAdapter(new ArrayList<ChatMessageModel>(), this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclarViewLst.setAdapter(mChatMessagesAdapter);
        recyclarViewLst.setLayoutManager(mLayoutManager);
        mChatMessagesAdapter.setFriendImage(otherImage);
    }

    private void setupSendButton() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {

        String message = chatField.getText().toString().trim();

        if (TextUtils.isValidString(message)) {
//            ChatMessage chatMessage = new ChatMessage(
//                    User.getUsername(), message, ChatMessage.TYPE_MESSAGE_SENT);
            ChatMessageModel cModel = new ChatMessageModel();
            cModel.setChat_dialog(chatDialogId);
            cModel.setReceiver_id(otherUserId);
            cModel.setSender_id("" + getUserData().getUserID());
            cModel.setMessage(message);
            cModel.setCreated_at(CustomDate.getCurrentTimeUTC(ChatActivity.this, "yyyy-MM-dd HH:mm:ss"));
            cModel.setType("chat");
            Log.e(" sendMessage ", " " + new Gson().toJson(cModel));
            mPresenter.sendMessage(cModel);
            addMessage(cModel);
            new Handler().postDelayed(() -> saveLastTimeStamp(message), GlobalValues.TIME_DURATIONS.SMALL);
            chatField.setText("");
        }
    }

    private void saveLastTimeStamp(String text) {
        UpdateChatDialogRequest cRequest = new UpdateChatDialogRequest();
        cRequest.setDialogId(chatDialogId);
        cRequest.setMesssage(text);
        cRequest.setTime(CustomDate.getCurrentTimeUTC(ChatActivity.this, "yyyy-MM-dd HH:mm:ss"));
        presenter.updateChatDilaog(NetworkConstatnts.RequestCode.API_UPDTAE_CHAT_DIALOG, cRequest, false);
    }

    private void addMessage(ChatMessageModel chatMessage) {
        mChatMessagesAdapter.addNewMessage(chatMessage);
        recyclarViewLst.scrollToPosition(mChatMessagesAdapter.getItemCount() - 1);
    }

    /*private void askUsername() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(
                R.layout.dialog_set_username, null);
        final AlertDialog dialog = builder.setView(view).setCancelable(false).create();

        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnClose = view.findViewById(R.id.btnClose);
        final EditText etUsername = view.findViewById(R.id.etUsername);
        etUsername.setText(User.getUsername());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                if (TextUtils.isValidString(username)) {
                    mPresenter.changeUsername(username);
                    dialog.dismiss();
                }
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }*/

    /*private void showInfo() {
        startActivity(new Intent(this, AboutActivity.class));
    }*/

    private void setupTextWatcher() {
        chatField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!mTyping) {
                    mTyping = true;
                    mPresenter.onTyping();
                }

                mTypingHandler.removeCallbacks(onTypingTimeout);
                mTypingHandler.postDelayed(onTypingTimeout, TYPING_TIMER_LENGTH);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onConnect(final Object... args) {
        showAlert("Connected", false);
    }

    @Override
    public void onDisconnect(final Object... args) {
        showAlert("Disconnected", false);
    }

    @Override
    public void onConnectError(final Object... args) {
        showAlert("No internet connection", true);
    }

    @Override
    public void onConnectTimeout(final Object... args) {
        showAlert("Connection Timeout", false);

    }

    @Override
    public void onNewMessage(final Object... args) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject data = (JSONObject) args[0];
                String username;
                String message;
                try {
                    Log.e(" onNewMessage ", "------------ " + new Gson().toJson(data));
                    MessageData messageData = new Gson().fromJson(new Gson().toJson(data), MessageData.class);
//                    Log.e(" onNewMessage ", "-=============== " + new Gson().toJson(messageData));
//                    username = data.getString("username");
//                    message = data.getString("message");
                    ChatMessageModel cModel = new ChatMessageModel();
                    cModel.setSender_id(data.getString("sender_id"));
                    cModel.setReceiver_id(data.getString("receiver_id"));
                    cModel.setChat_dialog(data.getString("chat_dialog"));
                    cModel.setMessage(data.getString("message"));
                    cModel.setType(1);
                    cModel.setType("chat");
                    Log.e(" onNewMessage ", " " + new Gson().toJson(cModel));
                    addMessage(cModel);
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                    return;
                }
            }
        });
    }

    @Override
    public void onUserJoined(Object... args) {
        JSONObject data = (JSONObject) args[0];
        String username;
        int numUsers;
        try {
            username = data.getString("username");
            numUsers = data.getInt("numUsers");
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            return;
        }
        ChatMessageModel cModel = new ChatMessageModel();
        cModel.setChat_dialog("16917");
        cModel.setReceiver_id("17");
        cModel.setSender_id("169");
        cModel.setMessage(new Gson().toJson(cModel));
        showAlert(username + " has joined", false);
    }

    @Override
    public void onUserLeft(Object... args) {
        JSONObject data = (JSONObject) args[0];
        String username;
        int numUsers;
        try {
            username = data.getString("username");
            numUsers = data.getInt("numUsers");
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            return;
        }

        showAlert(username + " has left", false);
    }

    @Override
    public void onTyping(Object... args) {
        JSONObject data = (JSONObject) args[0];
        String username;
        try {
            username = data.getString("username");
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            return;
        }

        addTyping(username);
        Log.i(TAG, "onTyping: " + username);
    }

    @Override
    public void onStopTyping(Object... args) {
        JSONObject data = (JSONObject) args[0];
        String username;
        try {
            username = data.getString("username");
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            return;
        }
        removeTyping(username);
    }

    @Override
    public void onMessageDelivered(ChatMessageModel chatMessage) {
        // Update UI to show the message has been delivered
    }


    @Override
    public void updateUsername(String username) {
//        User.setUsername(username);
    }

    private Runnable onTypingTimeout = new Runnable() {
        @Override
        public void run() {
            if (!mTyping) return;

            mTyping = false;
            mPresenter.onStopTyping();
        }
    };

    private void addTyping(final String username) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                tvTyping.setText(username + " is typing");
//                llTyping.setVisibility(View.VISIBLE);
            }
        });
    }

    private void removeTyping(String username) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                tvTyping.setText("");
//                llTyping.setVisibility(View.GONE);
            }
        });
    }

    @OnClick({R.id.back_toolbar, R.id.img_edit, R.id.send_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.img_edit:
                break;
            case R.id.send_btn:
                sendMessage();
                break;
        }
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_CONVERSATION_LIST:
                ConversationResponseModel responseData = (ConversationResponseModel) o;
                if (responseData != null && responseData.getObject() != null && responseData.getObject().getChatList() != null && responseData.getObject().getChatList().size() > 0) {
                    mChatMessagesAdapter.updateAll(presenter.getValidateList(responseData.getObject(), getUserData()));
                }
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