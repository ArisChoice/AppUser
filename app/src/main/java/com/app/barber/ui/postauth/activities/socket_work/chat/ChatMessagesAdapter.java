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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.barber.R;
import com.app.barber.models.chat_model.ChatMessageModel;
import com.app.barber.util.ImageUtility;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ChatMessages adapter.
 */
public class ChatMessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatMessageModel> mItems;
    private Context mContext;
    private String otherImage;

    /**
     * Constructor to create a new ChatMessagesAdapter
     *
     * @param items
     * @param context
     */
    public ChatMessagesAdapter(List<ChatMessageModel> items, Context context) {
        mItems = items;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view;

        if (viewType == ChatMessageModel.TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.view_layout_recivier, parent, false);
            viewHolder = new ReceivedMessageViewHolder(view);
        } else {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.view_layout_sender, parent, false);
            viewHolder = new SentMessageViewHolder(view);
        }

        return viewHolder;
    }

    public void setFriendImage(String otherImage) {
        this.otherImage = otherImage;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessageModel chatMessage = mItems.get(position);

        if (chatMessage.getType() == ChatMessageModel.TYPE_MESSAGE_RECEIVED) {
//            ((ReceivedMessageViewHolder) holder).tvUsername.setText(chatMessage.getSender_id());
            ((ReceivedMessageViewHolder) holder).txtMessage.setText(chatMessage.getMessage());
            ((ReceivedMessageViewHolder) holder).imgUserImage.setImageURI(ImageUtility.getValidUrl(otherImage));
        } else {
//            ((SentMessageViewHolder) holder).tvUsername.setText(chatMessage.getSender_id());
            ((SentMessageViewHolder) holder).txtMessage.setText(chatMessage.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    /**
     * Use this method to add new chat message to to the RecyclerView.
     *
     * @param chatMessage
     */
    public void addNewMessage(@NonNull ChatMessageModel chatMessage) {
        mItems.add(chatMessage);
        notifyItemInserted(mItems.size() - 1);
    }

    public void updateAll(List<com.app.barber.models.chat_model.ChatMessageModel> cList) {
        this.mItems.clear();
        this.mItems.addAll(cList);
        notifyDataSetChanged();
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
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

        public ReceivedMessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            txtMessageDate.setVisibility(View.GONE);

        }
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder {
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

        public SentMessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            txtMessageDate.setVisibility(View.GONE);
//            tvUsername = itemView.findViewById(R.id.tvUsername);
//            tvMessage = itemView.findViewById(R.id.tvMessage);
        }
    }
}
