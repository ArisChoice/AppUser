/*
package com.app.barber.ui.postauth.activities.chat.chat_adapters;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.barber.R;
import com.app.barber.models.ChatMessageModel;
import com.app.barber.util.CustomDate;
import com.app.barber.util.ImageUtility;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.util.quickblox.utils.chat.ChatHelper;
import com.app.barber.util.quickblox.utils.qb.PaginationHistoryListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.model.QBAttachment;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.core.helper.CollectionsUtil;
import com.quickblox.users.model.QBUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


*/
/**
 * Created by Sunny on 9/14/2018.
 *//*


public class ConversationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> */
/*implements
        StickyHeaderAdapter<ConversationAdapter.HeaderViewHolder> *//*
 {

    private static final String TAG = ConversationAdapter.class.getSimpleName();
    final int VIEW_TYPE_MY = 0;
    final int VIEW_TYPE_FRIEND = 1;

    Activity context;
    private LayoutInflater mInflater;
    private boolean isDeleteSelected;
    List<QBChatMessage> chatList;
    OnItemClickListener onItemClickListener;
    QBChatDialog chatDialog;
    private PaginationHistoryListener paginationListener;
    private String otherImage;

    public ConversationAdapter(Activity context, QBChatDialog chatDialog, List<QBChatMessage> chatList, OnItemClickListener onItemClickListener) {
        this.chatDialog = chatDialog;
        this.context = context;
        this.chatList = chatList;
        this.onItemClickListener = onItemClickListener;
        mInflater = LayoutInflater.from(context);
    }

    public void addList(List<QBChatMessage> items) {
        this.chatList.clear();
        this.chatList.addAll(items);
        notifyDataSetChanged();
    }

    public List<QBChatMessage> getList() {
        return this.chatList;
    }

    public void addToList(List<QBChatMessage> items) {
        chatList.addAll(0, items);
        notifyItemRangeInserted(0, items.size());
    }

    public void add(QBChatMessage item) {
        this.chatList.add(item);
        notifyItemInserted(chatList.size() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (isIncoming(chatList.get(position))) {
            return VIEW_TYPE_FRIEND;
        } else {
            return VIEW_TYPE_MY;
        }
    }
   */
/* @Override
    public int getItemViewType(int position) {
        QBChatMessage chatMessage = chatList.get(position);
        if (this.hasAttachments(chatMessage)) {
            QBAttachment attachment = this.getQBAttach(position);
            return !"photo".equalsIgnoreCase(attachment.getType()) &&
                    !"image".equalsIgnoreCase(attachment.getType()) ?
                    ("location".equalsIgnoreCase(attachment.getType()) ?
                            this.getLocationView(chatMessage) : ("audio".equalsIgnoreCase(attachment.getType()) ?
                            (this.isIncoming(chatMessage) ? 6 : 5) : ("video".equalsIgnoreCase(attachment.getType()) ?
                            (this.isIncoming(chatMessage) ? 8 : 7) : this.customViewType(position)))) : (this.isIncoming(chatMessage) ? 4 : 3);
        } else {
            return this.isIncoming(chatMessage) ? 2 : 1;
        }
    }*//*


    protected int customViewType(int position) {
        return -1;
    }

    protected boolean isIncoming(QBChatMessage chatMessage) {
        QBUser currentUser = QBChatService.getInstance().getUser();
        return chatMessage.getSenderId() != null && !chatMessage.getSenderId().equals(currentUser.getId());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_FRIEND) {
            View view = mInflater.inflate(R.layout.view_layout_recivier, parent, false);
            return new FriendViewHolder(view);
        } else {
            View view = mInflater.inflate(R.layout.view_layout_sender, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public int getItemCount() {
        return chatList == null || chatList.size() == 0 ? 0 : chatList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int post) {
        Log.e(" onBindViewHolder ", "::    " + chatList.get(post).getBody());
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).txtMessage.setText(chatList.get(post).getBody());
            ((MyViewHolder) holder).txtMessageDate.setText(DateUtils.
                    getRelativeTimeSpanString(CustomDate.getValidTimestamp(chatList.get(post).getDateSent()), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS));
        } else {
            ((FriendViewHolder) holder).txtMessage.setText(chatList.get(post).getBody());
            ((FriendViewHolder) holder).txtMessageDate.setText(DateUtils.
                    getRelativeTimeSpanString(CustomDate.getValidTimestamp(chatList.get(post).getDateSent()), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS));
            ((FriendViewHolder) holder).imgUserImage.setImageURI(ImageUtility.getValidUrl(otherImage));
        }
    }

    private boolean isRead(QBChatMessage chatMessage) {
        Integer currentUserId = ChatHelper.getCurrentUser().getId();
        return !CollectionsUtil.isEmpty(chatMessage.getReadIds()) && chatMessage.getReadIds().contains(currentUserId);
    }

    public void setPaginationHistoryListener(PaginationHistoryListener paginationListener) {
        this.paginationListener = paginationListener;
    }

    public void setFriendImage(String otherImage) {
        this.otherImage = otherImage;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.imgUserImage)
        SimpleDraweeView imgUserImage;
        @BindView(R.id.txtMessage)
        TextView txtMessage;
        @BindView(R.id.txtMessageDate)
        TextView txtMessageDate;
        @BindView(R.id.linearChat)
        LinearLayout linearChat;
        @Nullable
        @BindView(R.id.senderLayout)
        RelativeLayout mSenderLayout;
        @BindView(R.id.receiverLayout)
        @Nullable
        RelativeLayout mReceiverLayout;
        @Nullable
        @BindView(R.id.imageView_msgReadStatus)
        ImageView readStatus;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.imgUserImage)
        SimpleDraweeView imgUserImage;
        @BindView(R.id.txtMessage)
        TextView txtMessage;
        @BindView(R.id.txtMessageDate)
        TextView txtMessageDate;
        @BindView(R.id.linearChat)
        LinearLayout linearChat;
        @Nullable
        @BindView(R.id.senderLayout)
        RelativeLayout mSenderLayout;
        @Nullable
        @BindView(R.id.receiverLayout)
        RelativeLayout mReceiverLayout;
        @Nullable
        @BindView(R.id.imageView_msgReadStatus)
        ImageView readStatus;

        public FriendViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

}
*/
