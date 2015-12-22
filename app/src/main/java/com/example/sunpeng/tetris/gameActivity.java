package com.example.sunpeng.tetris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class gameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent =getIntent();
        Bundle bundle=intent.getExtras();
        String username=bundle.getString("username");
        final TextView test=(TextView)findViewById(R.id.text);
        test.setText(username);
    }

}
