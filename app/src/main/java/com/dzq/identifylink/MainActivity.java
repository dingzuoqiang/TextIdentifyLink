package com.dzq.identifylink;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.dzq.identifylink.databinding.ActivityMainBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        String content = "这是一段包含网址的文字，点击可以进行相关操作：第一个网址 https://www.baidu.com/，第二个网址 https://www.jd.com/";
        SpannableString sps = new SpannableString(content);
        Pattern pattern = Pattern.compile("[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.]]*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        int startIndex = 0;
        while (matcher.find(startIndex)) {
            String value = matcher.group();
            int endIndex = matcher.end();
            ClickableSpan clickSpan = new MyClickSpan(this, value, Color.parseColor("#ff0000"));
            sps.setSpan(clickSpan, endIndex - value.length(), endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            startIndex = endIndex;
        }
        binding.tvContent.setText(sps);
        binding.tvContent.setMovementMethod(LinkMovementMethod.getInstance());
    }

    class MyClickSpan extends ClickableSpan {
        Context context;
        String text;
        int color;// 指定颜色值

        public MyClickSpan(Context context, String text, int color) {
            super();
            this.context = context;
            this.text = text;
            this.color = color;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(color);
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            //TODO 点击事件，自己定义跳转
            Log.e("11", text);
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }

}