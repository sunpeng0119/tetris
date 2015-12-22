package com.example.sunpeng.tetris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button start = (Button)findViewById(R.id.gamestart);
        final EditText userName=(EditText)findViewById(R.id.username);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=userName.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("username",username);
                intent.setAction("gamestart");
                startActivity(intent);
            }
        });
    }
}
