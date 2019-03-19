package com.airon.customkeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airon.customkeyboard.keyboard.DeleteCallback;
import com.airon.customkeyboard.keyboard.HideCallback;
import com.airon.customkeyboard.keyboard.KeyCallback;
import com.airon.customkeyboard.keyboard.KeyboardHandle;
import com.airon.customkeyboard.keyboard.ShowCallback;

public class MainActivity extends AppCompatActivity implements HideCallback, ShowCallback, DeleteCallback, KeyCallback, View.OnClickListener {

    private String[] pr1 = new String[]{"浙", "京", "粤", "津", "晋", "冀", "黑", "吉", "辽", "蒙", "苏",
            "沪", "皖", "赣", "鲁", "豫", "鄂", "湘", "闽", "桂", "渝", "琼", "川", "贵", "云", "藏", "陕",
            "甘", "青", "宁", "新"};
    private String[] pr2 = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
            "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z", "港", "奥", "学"};
    private Keyboard k1, k2;
    private KeyboardHandle mHandle = null;
    private EditText mEtCarNo[] = new EditText[8];
    private TextView KeySure, KeyCancel;
    private RelativeLayout ReTop;
    private KeyboardView mKeyboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initView();
    }

    private void initView() {
        KeySure = findViewById(R.id.key_sure);
        KeyCancel = findViewById(R.id.key_cancel);
        ReTop = findViewById(R.id.Re_top);
        KeySure.setOnClickListener(this);
        KeyCancel.setOnClickListener(this);
        mKeyboardView = findViewById(R.id.Key_board);
        mEtCarNo[0] = findViewById(R.id.et_one);
        mEtCarNo[1] = findViewById(R.id.et_two);
        mEtCarNo[2] = findViewById(R.id.et_three);
        mEtCarNo[3] = findViewById(R.id.et_four);
        mEtCarNo[4] = findViewById(R.id.et_five);
        mEtCarNo[5] = findViewById(R.id.et_six);
        mEtCarNo[6] = findViewById(R.id.et_seven);
        mEtCarNo[7] = findViewById(R.id.et_eight);
        k1 = new Keyboard(this, R.xml.province);
        k2 = new Keyboard(this, R.xml.symbols);
        mHandle = KeyboardHandle.creator(this, mEtCarNo, mKeyboardView, this);
        mHandle.SetHideCallback(this);
        mHandle.SetShowCallback(this);
        mHandle.SetDeleteCallback(this);
        mHandle.SetKeyBoard(k1, pr1).SetKeyBoard(k2, pr2).useKeyBoard(k1);
    }

    @Override
    public void keyHide() {
        ReTop.setVisibility(View.GONE);
    }

    @Override
    public void keyShow() {
        ReTop.setVisibility(View.VISIBLE);
    }

    @Override
    public void startEdit(int position) {
        if (position == 0) {
            mHandle.useKeyBoard(k1);
        } else {
            mHandle.useKeyBoard(k2);
        }
    }

    @Override
    public void delete(int position) {
        if (position == 7)
            mEtCarNo[7].setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.key_sure:
                for (EditText e : mEtCarNo) {
                    e.clearFocus();
                }
                mHandle.HideKeyboard();
                Log.d("TAG", "Content:" + mHandle.GetContent());
                break;
            case R.id.key_cancel:
                mHandle.HideKeyboard();
                break;
        }
    }
}
