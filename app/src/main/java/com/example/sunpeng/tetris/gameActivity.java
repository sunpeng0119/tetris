package com.example.sunpeng.tetris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class gameActivity extends Activity {
    RelativeLayout root;
    Cell cell;
    List<OneCell> cells;
    public static final int NEWONE=1;
    public static final int RESET=0;
    Button right;
    Button left;
    Button spin;
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        root=(RelativeLayout)findViewById(R.id.actionroot);
        right=(Button)findViewById(R.id.right);
        left=(Button)findViewById(R.id.left);
        spin=(Button)findViewById(R.id.spin);
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        String username=bundle.getString("username");
        cells=new LinkedList<OneCell>();
        final Random random=new Random();
        final TextView textView=(TextView)findViewById(R.id.text);
        cell=new Cell(this,random.nextInt(6));
        root.addView(cell);
        num=0;
        final Handler handler=new Handler(){
              @Override
              public void handleMessage(Message msg) {
                  Cell temp=cell;
                  temp.move();
                  if(msg.what==NEWONE){
                      if(num%1==0){
                          if(!temp.toEnd(temp,cells,root))
                          {
                            cell.move();
                              Log.v("y",""+cell.maxX);
                            cell.invalidate();}
                      }
                      num++;

                      if(temp.toEnd(temp,cells,root)) {
                          Log.v("size", cells.size() + "test");
                          cell.remove();
                          cells.addAll(cell.splitCell(gameActivity.this));
                          Log.v("size", cells.get(0).currentY + "test");
                          Log.v("size", root.getHeight()-300 + "test");
                          root.removeView(cell);
                          cell = new Cell(gameActivity.this, random.nextInt(6));
                          root.addView(cell);
                          textView.setText("x" + cells.size());
                          if (cells.size() > 0) {
                              for (int i = 0; i < cells.size(); i++) {
                                  root.removeView(cells.get(i));
                                  root.addView(cells.get(i));
                              }
                          }
                          OneCell.clear(cells,root,cell);
                      }

                  }
          }
        };
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cell.maxX<=975-cell.bitmap.getWidth()){
                cell.moveright();
                cell.invalidate();}
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cell.x>=18+cell.bitmap.getHeight()){
                cell.movelift();
                cell.invalidate();}
            }
        });
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cell.spin();
                cell.invalidate();}

        });
        Timer timer = new Timer();
            timer.schedule(new

            TimerTask() {
                @Override
                public void run () {
                    Message message = new Message();
                    message.what = NEWONE;
                    handler.sendMessage(message);
            }
        },10,1000);

    }

}
