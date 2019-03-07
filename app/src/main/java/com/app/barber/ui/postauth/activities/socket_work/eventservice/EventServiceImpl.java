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

package com.app.barber.ui.postauth.activities.socket_work.eventservice;

import android.support.annotation.NonNull;
import android.util.Log;

import com.app.barber.models.chat_model.ChatMessageModel;
import com.google.gson.Gson;

import java.net.URISyntaxException;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Implementation of {@link EventService} which connects and disconnects to the server.
 * It also sends and receives events from the server.
 */
public class EventServiceImpl implements EventService {

    private static final String TAG = EventServiceImpl.class.getSimpleName();
    private static final String SOCKET_URL = "http://111.93.127.5:3020" /*"https://socket-io-chat.now.sh"*/;
    private static final String EVENT_CONNECT = Socket.EVENT_CONNECT;
    private static final String EVENT_DISCONNECT = Socket.EVENT_DISCONNECT;
    private static final String EVENT_CONNECT_ERROR = Socket.EVENT_CONNECT_ERROR;
    private static final String EVENT_CONNECT_TIMEOUT = Socket.EVENT_CONNECT_TIMEOUT;
    private static final String EVENT_NEW_MESSAGE = "new-message";
    private static final String EVENT_USER_JOINED = "join";
    private static final String EVENT_USER_LEFT = "user left";
    private static final String EVENT_TYPING = "typing";
    private static final String EVENT_STOP_TYPING = "stop typing";
    private static final String JOIN_ = "join";
    private static final String EVENT_SEND_MESSAGE = "send-message";
    private static EventService INSTANCE;
    private static EventListener mEventListener;
    public static Socket mSocket;
    private static EventServiceImpl inst;
    private String mUsername;

    // Prevent direct instantiation
    private EventServiceImpl() {
    }

    /**
     * Returns single instance of this class, creating it if necessary.
     *
     * @return
     */
    public static EventService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventServiceImpl();
        }

        return INSTANCE;
    }

    public static EventServiceImpl getInstce() {
        if (inst == null) {
            inst = new EventServiceImpl();
        }
        return inst;
    }

    /**
     * Connect to the server.
     *
     * @param username
     * @throws URISyntaxException
     */
    @Override
    public void connect(String username) throws URISyntaxException {
        mUsername = username;
        mSocket = IO.socket(SOCKET_URL);

        // Register the incoming events and their listeners
        // on the socket.
        mSocket.on(EVENT_CONNECT, onConnect);
        mSocket.on(EVENT_DISCONNECT, onDisconnect);
        mSocket.on(EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.on(EVENT_SEND_MESSAGE, onSendMessage);
        mSocket.on(EVENT_NEW_MESSAGE, onNewMessage);
        mSocket.on(EVENT_USER_JOINED, onUserJoined);
        mSocket.on(EVENT_USER_LEFT, onUserLeft);
        mSocket.on(EVENT_TYPING, onTyping);
        mSocket.on(EVENT_STOP_TYPING, onStopTyping);

        mSocket.connect();
    }

    /**
     * Disconnect from the server.
     */
    @Override
    public void disconnect() {
        if (mSocket != null) mSocket.disconnect();
    }

    /**
     * Send chat message to the server.
     *
     * @param chatMessage
     * @return
     */
    @Override
    public Flowable<ChatMessageModel> sendMessage(@NonNull final ChatMessageModel chatMessage) {
        return Flowable.create(new FlowableOnSubscribe<ChatMessageModel>() {
            @Override
            public void subscribe(FlowableEmitter<ChatMessageModel> emitter) throws Exception {
                /*
                 * Socket.io supports acking messages.
                 * This feature can be used as
                 * mSocket.emit("EVENT_NEW_MESSAGE", chatMessage.getMessage(), new Ack() {
                 *   @Override
                 *   public void call(Object... args) {
                 *       // Do something with args
                 *
                 *       // On success
                 *       emitter.onNext(chatMessage);
                 *
                 *       // On error
                 *       emitter.onError(new Exception("Sending message failed."));
                 *    }
                 * });
                 *
                 * */
                if (chatMessage.getMessage() == null) {
                    mSocket.emit(JOIN_, chatMessage.getChat_dialog());
                } else
                    mSocket.emit(EVENT_SEND_MESSAGE, new Gson().toJson(chatMessage));
                emitter.onNext(chatMessage);
            }
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * Send typing event to the server.
     */
    @Override
    public void onTyping() {
        mSocket.emit(EVENT_TYPING);
    }

    /**
     * Send stop typing event to the server.
     */
    @Override
    public void onStopTyping() {
        mSocket.emit(EVENT_STOP_TYPING);
    }

    /**
     * Set eventListener.
     * <p>
     * When server sends events to the socket, those events are passed to the
     * RemoteDataSource -> Repository -> Presenter -> View using EventListener.
     *
     * @param eventListener
     */
    @Override
    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.i(TAG, "call: onConnect  " + args.toString());
            mSocket.emit("add user", mUsername);
            if (mEventListener != null) mEventListener.onConnect(args);
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.i(TAG, "call: onDisconnect ");
            if (mEventListener != null) mEventListener.onDisconnect(args);
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.i(TAG, "call: onConnectError  " + args.toString());
            if (mEventListener != null) mEventListener.onConnectError(args);
        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i(TAG, "call: onNewMessage");
            if (mEventListener != null) mEventListener.onNewMessage(args);
        }
    };
    private Emitter.Listener onSendMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i(TAG, "call: onSendMessage");
            if (mEventListener != null) mEventListener.onNewMessage(args);
        }
    };

    private Emitter.Listener onUserJoined = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i(TAG, "call: onNewMessage");
            if (mEventListener != null) mEventListener.onUserJoined(args);
        }
    };

    private Emitter.Listener onUserLeft = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i(TAG, "call: onNewMessage");
            if (mEventListener != null) mEventListener.onUserLeft(args);
        }
    };

    private Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i(TAG, "call: onNewMessage");
            if (mEventListener != null) mEventListener.onTyping(args);
        }
    };

    private Emitter.Listener onStopTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i(TAG, "call: onNewMessage");
            if (mEventListener != null) mEventListener.onStopTyping(args);
        }
    };
}
