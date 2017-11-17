package com.ruaho.studyapp.goodcode.SocketConnection.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ruaho.studyapp.R;
import com.ruaho.studyapp.activity.BaseActivity;
import com.ruaho.studyapp.bean.Bean;
import com.ruaho.studyapp.bean.Constant;
import com.ruaho.studyapp.bean.JsonUtils;
import com.ruaho.studyapp.bean.Lang;
import com.ruaho.studyapp.goodcode.SocketConnection.adapter.Message;
import com.ruaho.studyapp.goodcode.SocketConnection.adapter.MessageAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by ruaho on 2017/11/17.
 * 众信登录消息服务器的例子
 */
public class CochatSocketActivity extends BaseActivity {

    private RecyclerView.Adapter mAdapter;
    private static final String TAG = "CochatSocketActivity";

    private static final int TYPING_TIMER_LENGTH = 600;
    private RecyclerView mMessagesView;
    private EditText mInputMessageView;
    private List<Message> mMessages = new ArrayList<Message>();
    private boolean mTyping = false;
    private Handler mTypingHandler = new Handler();
    private String mUsername;
    private Socket mSocket;
    private Boolean isConnected = true;
    private ImageButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_socket);

        initView();
        initData();
        initEvent();

    }

    private void initView() {
        mMessagesView = (RecyclerView) findViewById(R.id.messages);
        mInputMessageView = (EditText) findViewById(R.id.message_input);
        sendButton = (ImageButton) findViewById(R.id.send_button);

        mAdapter = new MessageAdapter(this, mMessages);
        mMessagesView.setLayoutManager(new LinearLayoutManager(CochatSocketActivity.this));
        mMessagesView.setAdapter(mAdapter);
    }

    private void initData() {
        try {
            mSocket = IO.socket(Constant.CHAT_SERVER_URL);
            loginInfo();
            loginInit();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void initEvent() {

        mInputMessageView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int id, KeyEvent event) {
                if (id == R.id.send || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });
        mInputMessageView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (null == mUsername) return;
                if (!mSocket.connected()) return;

                if (!mTyping) {
                    mTyping = true;
                    mSocket.emit("typing");
                }

                mTypingHandler.removeCallbacks(onTypingTimeout);
                mTypingHandler.postDelayed(onTypingTimeout, TYPING_TIMER_LENGTH);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg();
            }
        });

    }

    /**
     * 登录人信息
     */
    private void loginInfo() {
        JSONObject loginReq = new JSONObject();//登陆的请求(存储很多参数数据)，传递给后台
        try {
            loginReq.putOpt("userName", "1vlCZ9oRhfNqTxeB40Tx3wqZ");
            loginReq.putOpt("token", "07f44593e8a27928c4b820966efd77c6");
            loginReq.putOpt("displayName", "喻林");
            loginReq.putOpt("version", "5300");

            loginReq.putOpt("startTimeMillis", "1510819752023");
            loginReq.putOpt("uuid", "779b57c23423a149"); //设备手机的UUID
            loginReq.putOpt("deviceName", "HUAWEI H60-L01"); //设备手机名称和信息

            loginReq.putOpt("deviceToken", "0866568024913468200000200200CN01");
            loginReq.putOpt("deviceTokenType", "huawei.android");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit("loginv17", loginReq,
                new Ack() {
                    @Override
                    public void call(Object... args) {
                        try{
                            Object arg = args[0];
                            JSONObject ackJson = (JSONObject) arg;//将将JSONObject转为Bean
                            Bean ackData = JsonUtils.toBean(ackJson);
                            //处理消息
                        } catch (Exception e) {
                            Log.i("loginv17", "解析json失败");
                        }
                    }
                });
    }

    /**
     * 初始化
     */
    private void loginInit() {
        mSocket.on(Socket.EVENT_CONNECT, onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT, onDisconnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.on("message", onNewMessage);
        mSocket.on("event", onUserJoined);

        mSocket.on("user left", onUserLeft);
        mSocket.on("typing", onTyping);
        mSocket.on("stop typing", onStopTyping);
        mSocket.connect();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        mSocket.off(Socket.EVENT_CONNECT, onConnect);
        mSocket.off(Socket.EVENT_DISCONNECT, onDisconnect);
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
//        mSocket.off("new message", onNewMessage);
        mSocket.off("message", onNewMessage);

//        mSocket.off("user joined", onUserJoined);
        mSocket.off("event", onUserJoined);
        mSocket.off("user left", onUserLeft);
        mSocket.off("typing", onTyping);
        mSocket.off("stop typing", onStopTyping);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK != resultCode) {
            finish();
            return;
        }

        mUsername = data.getStringExtra("username");
        int numUsers = data.getIntExtra("numUsers", 1);

        addLog("欢迎!!!");
        addParticipantsLog(numUsers);
    }

    protected final Object lock = new Object();

    private void sendMsg() {
        final String message = mInputMessageView.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            mInputMessageView.requestFocus();
            return;
        }
        JSONObject msgJson = new JSONObject();
        try {
            //to
            JSONObject toObj = new JSONObject();
            toObj.putOpt("fullId", "u__0FfNubxf91cqEntAhWQedl");//发送给谁
            toObj.putOpt("nickName", "李冬冬");//给谁的名字
            msgJson.putOpt("to", toObj);
            JSONObject fromObj = new JSONObject();
            //from
            fromObj.putOpt("fullId", "u__1vlCZ9oRhfNqTxeB40Tx3wqZ");//发送者
            fromObj.putOpt("nickName", "喻林");
            msgJson.putOpt("from", fromObj);
            msgJson.put("body", message);//编辑者发送的消息
            msgJson.putOpt("at", null);//@某人
            msgJson.putOpt("clientId", Lang.getUUID());
            msgJson.putOpt("bodyType", "text");
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        Log.i(TAG, "onSuccess: emit" + System.currentTimeMillis());
        Log.i(TAG, "开始发送");
        //发送消息 ;
        mSocket.emit("message", msgJson, new Ack() {
            @Override
            public void call(Object... args) {
                synchronized (lock) {//加锁，避免次子线程同时被触发代码，导致后续bug

                    Log.i(TAG, "onSuccess: emit_success" + System.currentTimeMillis());
                    if (null == args || 0 == args.length) {
                        return;
                    }
                    Object arg = args[0];
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addMessage("喻林", message);
                            mInputMessageView.setText("");
                        }
                    });

                    Log.i(TAG, "发送成功");
                }
            }
        });
    }

    private void addLog(String message) {
        mMessages.add(new Message.Builder(Message.TYPE_LOG)
                .message(message).build());
        mAdapter.notifyItemInserted(mMessages.size() - 1);
        scrollToBottom();
    }

    private void addParticipantsLog(int numUsers) {
        addLog(getResources().getQuantityString(R.plurals.message_participants, numUsers, numUsers));
    }

    private void addMessage(String username, String message) {
        mMessages.add(new Message.Builder(Message.TYPE_MESSAGE)
                .username(username).message(message).build());
        mAdapter.notifyItemInserted(mMessages.size() - 1);
        scrollToBottom();
    }

    private void addTyping(String username) {
        mMessages.add(new Message.Builder(Message.TYPE_ACTION)
                .username(username).build());
        mAdapter.notifyItemInserted(mMessages.size() - 1);
        scrollToBottom();
    }

    private void removeTyping(String username) {
        for (int i = mMessages.size() - 1; i >= 0; i--) {
            Message message = mMessages.get(i);
            if (message.getType() == Message.TYPE_ACTION && message.getUsername().equals(username)) {
                mMessages.remove(i);
                mAdapter.notifyItemRemoved(i);
            }
        }
    }



    private void leave() {
        mUsername = null;
        mSocket.disconnect();
        mSocket.connect();
    }

    private void scrollToBottom() {
        mMessagesView.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!isConnected) {
                        if (null != mUsername)
                            mSocket.emit("add user", mUsername);
                        Toast.makeText(getApplicationContext(),
                                R.string.connect, Toast.LENGTH_LONG).show();
                        isConnected = true;
                    }
                }
            });
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "diconnected");
                    isConnected = false;
                    Toast.makeText(getApplicationContext(),
                            R.string.disconnect, Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "Error connecting");
                    Toast.makeText(getApplicationContext(),
                            R.string.error_connect, Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = JsonUtils.toBean((JsonUtils.toBean(data)).getStr("from")).getStr("nickName");
                        message = (JsonUtils.toBean(data)).getStr("body");

                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        return;
                    }

                    removeTyping(username);
                    addMessage(username, message);
                }
            });
        }
    };

    private Emitter.Listener onUserJoined = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
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

                    addLog(getResources().getString(R.string.message_user_joined, username));
                    addParticipantsLog(numUsers);
                }
            });
        }
    };

    private Emitter.Listener onUserLeft = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
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

                    addLog(getResources().getString(R.string.message_user_left, username));
                    addParticipantsLog(numUsers);
                    removeTyping(username);
                }
            });
        }
    };

    private Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    try {
                        username = data.getString("username");
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                        return;
                    }
                    addTyping(username);
                }
            });
        }
    };

    private Emitter.Listener onStopTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
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
            });
        }
    };

    private Runnable onTypingTimeout = new Runnable() {
        @Override
        public void run() {
            if (!mTyping) return;

            mTyping = false;
            mSocket.emit("stop typing");
        }
    };
}
