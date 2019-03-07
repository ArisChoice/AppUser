/*
package com.app.barber.ui.postauth.activities.chat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.ChatMessageModel;
import com.app.barber.models.ChatPushModel;
import com.app.barber.models.request.UpdateChatDialogRequest;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.chat.chat_adapters.ConversationAdapter;
import com.app.barber.ui.postauth.activities.chat.chatmvp.ChatMVPView;
import com.app.barber.ui.postauth.activities.chat.chatmvp.ChatPresenter;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.CustomDate;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.util.quickblox.utils.ErrorUtils;
import com.app.barber.util.quickblox.utils.Toaster;
import com.app.barber.util.quickblox.utils.chat.ChatHelper;
import com.app.barber.util.quickblox.utils.qb.PaginationHistoryListener;
import com.app.barber.util.quickblox.utils.qb.QbChatDialogMessageListenerImp;
import com.app.barber.util.quickblox.utils.qb.VerboseQbChatConnectionListener;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;
import com.google.gson.Gson;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBRestChatService;
import com.quickblox.chat.model.QBAttachment;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.chat.model.QBDialogType;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.helper.StringifyArrayList;
import com.quickblox.messages.QBPushNotifications;
import com.quickblox.messages.model.QBEnvironment;
import com.quickblox.messages.model.QBEvent;
import com.quickblox.messages.model.QBNotificationType;
import com.quickblox.messages.model.QBPushType;
import com.quickblox.users.model.QBUser;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.app.barber.util.GlobalValues.KEYS.EXTRA_DIALOG_ID;

*/
/**
 * Created by harish on 16/11/18.
 *//*


public class ChatWorkActivity extends BaseActivity implements ChatMVPView {
    String TAG = ChatWorkActivity.class.getSimpleName();
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    @BindView(R.id.chat_field)
    CustomEditText chatField;
    @BindView(R.id.send_btn)
    ImageView sendBtn;
    private QBChatDialog qbChatDialog;
    protected List<QBChatMessage> messagesList;
    private ChatMessageListener chatMessageListener;
    private int skipPagination = 0;
    private boolean checkAdapterInit;
    private ConnectionListener chatConnectionListener;
    Snackbar snackbar;
    private ArrayList<QBChatMessage> unShownMessages;
    private ConversationAdapter chatAdapter;
    private String qbChatDialogId;
    private ChatPresenter presenter;
    private String otherImage;

    @Override
    public int getLayoutId() {
        return R.layout.layout_chat_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(ChatWorkActivity.this).inject(this);
        presenter = new ChatPresenter(ChatWorkActivity.this);
        presenter.attachView(this);
        txtTitleToolbar.setText(R.string.str_chat);
        initQbuser();
//        restoreChatSession();
        qbChatDialogId = getIntent().getStringExtra(GlobalValues.KEYS.EXTRA_DIALOG_ID);
        otherImage = getIntent().getStringExtra(GlobalValues.KEYS.OTHER_IMAGE);
        initRecyclar();
        showLoading();
        joinChatDialog();
    }

    private void initQbuser() {
        Thread thread = new Thread(() -> {
            try {
                try {
                    QBChatService.getInstance().login(PreferenceManager.getInstance(ChatWorkActivity.this).getQbUser());
                } catch (XMPPException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SmackException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void joinChatDialog() {
        QBRestChatService.getChatDialogById(qbChatDialogId).performAsync(new QBEntityCallback<QBChatDialog>() {
            @Override
            public void onSuccess(QBChatDialog dialog, Bundle bundle) {
                Log.d(TAG, "joinChatDialog " + dialog.getName() + " QBChatDialog TYPE= " + dialog.getType());
                qbChatDialog = dialog;
//                deleteActionManage(false);
//                if (qbChatDialog == null) {
//                    return;
//                }

                try {
                    qbChatDialog.initForChat(QBChatService.getInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                    finish();
                }
                //setUpAdpter();
                initChat();
                initChatConnectionListener();
                setUpListeners();
            }

            @Override
            public void onError(QBResponseException e) {
                hideLoading();
                Log.d(TAG, "joinChatDialog   QBResponseException onError " + e.getMessage());
//                hideCustomDialog();
            }
        });
    }

    private void setUpListeners() {
        chatMessageListener = new ChatMessageListener();
        qbChatDialog.addMessageListener(chatMessageListener);
//        initMessageStatusManagerAndListener();
    }

    private void initChat() {
        switch (qbChatDialog.getType()) {
            case GROUP:
            case PUBLIC_GROUP:
                joinGroupChat();
                break;
            case PRIVATE:
                loadDialogUsers();
                break;
            default:
                Toaster.shortToast(String.format("%s %s", getString(R.string.chat_unsupported_type), qbChatDialog.getType().name()));
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //addChatMessagesAdapterListeners();
        ChatHelper.getInstance().addConnectionListener(chatConnectionListener);
        new CommonUtils().setIsChatActive(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //removeChatMessagesAdapterListeners();
        ChatHelper.getInstance().removeConnectionListener(chatConnectionListener);
        new CommonUtils().setIsChatActive(false);
    }

    @Override
    public void onBackPressed() {
        releaseChat();
//        sendDialogId();
        super.onBackPressed();
    }
*/
/*
    private void sendDialogId() {
        Intent result = new Intent();
        result.putExtra(EXTRA_DIALOG_ID, qbChatDialog.getDialogId());
        setResult(RESULT_OK, result);
    }*//*


    private void joinGroupChat() {
        Log.e("Chat", "Group");
//        progressBar.setVisibility(View.VISIBLE);
        ChatHelper.getInstance().join(qbChatDialog, new QBEntityCallback<Void>() {
            @Override
            public void onSuccess(Void result, Bundle b) {
                if (snackbar != null) {
                    snackbar.dismiss();
                }
                loadDialogUsers();
            }

            @Override
            public void onError(QBResponseException e) {
//                progressBar.setVisibility(View.GONE);
//                snackbar = showErrorSnackbar(R.string.connection_error, e, null);
            }
        });
    }

    private void loadDialogUsers() {
        ChatHelper.getInstance().getUsersFromDialog(qbChatDialog, new QBEntityCallback<ArrayList<QBUser>>() {
            @Override
            public void onSuccess(ArrayList<QBUser> users, Bundle bundle) {
//                setChatNameToActionBar();
                loadChatHistory();
            }

            @Override
            public void onError(QBResponseException e) {
                hideLoading();
                showErrorSnackbar(R.string.chat_load_users_error, e,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadDialogUsers();
                            }
                        });
            }
        });
    }

    protected Snackbar showErrorSnackbar(@StringRes int resId, Exception e,
                                         View.OnClickListener clickListener) {
        return ErrorUtils.showSnackbar(getSnackbarAnchorView(), resId, e,
                R.string.dlg_retry, clickListener);
    }

    protected View getSnackbarAnchorView() {
        return findViewById(R.id.recyclar_view_lst);
    }

    private void leaveGroupDialog() {
        try {
            ChatHelper.getInstance().leaveChatDialog(qbChatDialog);
        } catch (XMPPException | SmackException.NotConnectedException e) {
            Log.w(TAG, e);
        }
    }

    private void releaseChat() {
        try {
            qbChatDialog.removeMessageListrener(chatMessageListener);
            if (!QBDialogType.PRIVATE.equals(qbChatDialog.getType())) {
                leaveGroupDialog();
            }
        } catch (Exception e) {

        }
    }

    private void initRecyclar() {
        messagesList = new ArrayList<>();
        recyclarViewLst.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        chatAdapter = new ConversationAdapter(ChatWorkActivity.this, qbChatDialog, messagesList, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {

            }
        });
        chatAdapter.setPaginationHistoryListener(new PaginationListener());
//        recyclarViewLst.addItemDecoration(new StickyRecyclerHeadersDecoration(chatAdapter));
        recyclarViewLst.setAdapter(chatAdapter);
        chatAdapter.setFriendImage(otherImage);
    }

    private void initChatConnectionListener() {
        chatConnectionListener = new VerboseQbChatConnectionListener(getSnackbarAnchorView()) {
            @Override
            public void reconnectionSuccessful() {
                super.reconnectionSuccessful();
                skipPagination = 0;
                switch (qbChatDialog.getType()) {
                    case GROUP:
                        checkAdapterInit = false;
                        // Join active room if we're in Group Chat
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                joinGroupChat();
                            }
                        });
                        break;
                }
            }

            @Override
            public void reconnectionFailed(Exception error) {
                super.reconnectionFailed(error);
                Log.e(TAG, "connectionFailed");
            }
        };
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {

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

    private class PaginationListener implements PaginationHistoryListener {

        @Override
        public void downloadMore() {
            Log.w(TAG, "downloadMore");
            loadChatHistory();
        }
    }

    private void loadChatHistory() {
        ChatHelper.getInstance().loadChatHistory(qbChatDialog, skipPagination, new QBEntityCallback<ArrayList<QBChatMessage>>() {
            @Override
            public void onSuccess(ArrayList<QBChatMessage> messages, Bundle args) {
                // The newest messages should be in the end of list,
                // so we need to reverse list to show messages in the right order
                Log.d("loadChatHistory", "load");
                hideLoading();
                Collections.reverse(messages);
                if (!checkAdapterInit) {
                    checkAdapterInit = true;
                    chatAdapter.addList(messages);
                    addDelayedMessagesToAdapter();
                } else {
                    chatAdapter.addToList(messages);
                }
                if (chatAdapter.getItemCount() != 0 && chatAdapter.getItemCount() > 5)
                    recyclarViewLst.scrollToPosition(chatAdapter.getItemCount() - 1);
                markedmessageasRead();
            }

            @Override
            public void onError(QBResponseException e) {
                hideLoading();
//                progressBar.setVisibility(View.GONE);
                skipPagination -= ChatHelper.CHAT_HISTORY_ITEMS_PER_PAGE;
                snackbar = showErrorSnackbar(R.string.connection_error, e, null);
            }
        });
        skipPagination += ChatHelper.CHAT_HISTORY_ITEMS_PER_PAGE;
    }

    public void markedmessageasRead() {
        QBRestChatService.markMessagesAsRead(qbChatDialog.getDialogId(), null).performAsync(new QBEntityCallback<Void>() {
            @Override
            public void onSuccess(Void result, Bundle bundle) {
                Log.i(TAG, "read OK");
            }

            @Override
            public void onError(QBResponseException errors) {
                Log.i(TAG, "read error");
            }
        });

    }

    private void addDelayedMessagesToAdapter() {
        if (unShownMessages != null && !unShownMessages.isEmpty()) {
            List<QBChatMessage> chatList = chatAdapter.getList();
            for (QBChatMessage message : unShownMessages) {
                if (!chatList.contains(message)) {
                    chatAdapter.add(message);
                }
            }
        }
    }

    private List<ChatMessageModel> getTemList() {
        ArrayList<ChatMessageModel> listTemp = new ArrayList<>();
        ChatMessageModel mod = new ChatMessageModel();
        mod.setMyMessage(true);
        listTemp.add(mod);
        mod = new ChatMessageModel();
        mod.setMyMessage(false);
        listTemp.add(mod);
        return listTemp;
    }

    @OnClick({R.id.back_toolbar, R.id.send_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.send_btn:
                String text = chatField.getText().toString().trim();
                if (!TextUtils.isEmpty(text)) {
                    sendChatMessage(text, null);
                }
                break;
        }
    }

    private void sendChatMessage(String text, QBAttachment attachment) {
        try {
            QBChatMessage chatMessage = new QBChatMessage();
            if (attachment != null) {
                chatMessage.addAttachment(attachment);
            } else {
                chatMessage.setBody(text);
            }
            chatMessage.setSaveToHistory(true);
            chatMessage.setDateSent(System.currentTimeMillis() / 1000);
            chatMessage.setMarkable(true);
            if (qbChatDialog != null) {
                if (!QBDialogType.PRIVATE.equals(qbChatDialog.getType()) && !qbChatDialog.isJoined()) {
                    Toaster.shortToast("You're still joining a group chat, please wait a bit");
                    return;
                }

            } else onBackPressed();


            qbChatDialog.sendMessage(chatMessage);

            if (QBDialogType.PRIVATE.equals(qbChatDialog.getType())) {
                showMessage(chatMessage);
//                sendIosPushNotification(text);
//                sendAndroidPushNotification(text);
                new Handler().postDelayed(() -> saveLastTimeStamp(text), GlobalValues.TIME_DURATIONS.SMALL);
            }

            if (attachment != null) {
//                attachmentPreviewAdapter.remove(attachment);
            } else {
                chatField.setText("");
            }

        } catch (Exception e) {
            Log.w(TAG, e);
            Toaster.shortToast("Can't send a message, You are not connected to chat");
            onBackPressed();
        }
    }

    private void saveLastTimeStamp(String text) {
        UpdateChatDialogRequest cRequest = new UpdateChatDialogRequest();
        cRequest.setDialogId(qbChatDialogId);
        cRequest.setMesssage(text);
        cRequest.setTime(CustomDate.getCurrentTimeUTC(ChatWorkActivity.this, GlobalValues.DateFormats.DEFAULT_FORMAT_DATE_TIME));
        presenter.updateChatDilaog(NetworkConstatnts.RequestCode.API_UPDTAE_CHAT_DIALOG, cRequest, false);
    }

    private void sendIosPushNotification(String message) {

        StringifyArrayList<Integer> userIds = new StringifyArrayList<Integer>();
        userIds.add(Integer.parseInt(getUserData().getQBId()));

        QBEvent event = new QBEvent();
        event.setUserIds(userIds);
        event.setEnvironment(QBEnvironment.DEVELOPMENT);
        event.setNotificationType(QBNotificationType.PUSH);
        event.setPushType(QBPushType.APNS);
        event.setMessage(message);

        QBPushNotifications.createEvent(event).performAsync(new QBEntityCallback<QBEvent>() {
            @Override
            public void onSuccess(QBEvent qbEvent, Bundle args) {
                // sent
            }

            @Override
            public void onError(QBResponseException errors) {

            }
        });
    }

    private void sendAndroidPushNotification(String message) {
        ChatPushModel chatPushModel = new ChatPushModel();
        chatPushModel.setDialogId(qbChatDialogId);
        chatPushModel.setSenderName(getUserData().getFullName());
        chatPushModel.setMessage(message);
        StringifyArrayList<Integer> userIds = new StringifyArrayList<Integer>();
        userIds.add(Integer.parseInt(getUserData().getQBId()));

        QBEvent event = new QBEvent();
        event.setUserIds(userIds);
        event.setEnvironment(QBEnvironment.DEVELOPMENT);
        event.setNotificationType(QBNotificationType.PUSH);
        event.setPushType(QBPushType.GCM);
        event.setMessage(new Gson().toJson(chatPushModel));

        QBPushNotifications.createEvent(event).performAsync(new QBEntityCallback<QBEvent>() {
            @Override
            public void onSuccess(QBEvent qbEvent, Bundle args) {
                // sent
            }

            @Override
            public void onError(QBResponseException errors) {

            }
        });
    }

    private class ChatMessageListener extends QbChatDialogMessageListenerImp {
        @Override
        public void processMessage(String s, QBChatMessage qbChatMessage, Integer integer) {
            Log.e(TAG, "processMessage");
            showMessage(qbChatMessage);
        }
    }

    public void showMessage(QBChatMessage message) {
        if (isAdapterConnected()) {
            chatAdapter.add(message);
            scrollMessageListDown();
        } else {
            delayShowMessage(message);
        }
    }

    private boolean isAdapterConnected() {
        return checkAdapterInit;
    }

    private void scrollMessageListDown() {
        recyclarViewLst.scrollToPosition(messagesList.size() - 1);
    }

    private void delayShowMessage(QBChatMessage message) {
        if (unShownMessages == null) {
            unShownMessages = new ArrayList<>();
        }
        unShownMessages.add(message);
    }
}
*/
