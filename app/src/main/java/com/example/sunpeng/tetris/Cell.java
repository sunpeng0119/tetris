package com.example.sunpeng.tetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sunpeng on 15-12-22.
 */
public class Cell extends View {
    Bitmap bitmap;
    float[][] currentXY=new float[4][2];
    float x=10000;
    float y=0;
    float maxX=0;
    int speed;
    public Cell(Context context){
        super(context);
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.t);
        speed=bitmap.getHeight();
        setFocusable(true);
        setXY();
    }
    public Cell(Context context,int shape){
        super(context);
        switch (shape){
            case 1:
                bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.t);
                currentXY[0][0]=1080/2;
                currentXY[0][1]=-bitmap.getHeight();
                currentXY[1][0]=1080/2-bitmap.getWidth();
                currentXY[1][1]=-bitmap.getHeight();
                currentXY[2][0]=1080/2+bitmap.getWidth();
                currentXY[2][1]=-bitmap.getHeight();
                currentXY[3][0]=1080/2;
                currentXY[3][1]=-bitmap.getHeight()-bitmap.getHeight();
                break;
            case 2:
                bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.i);
                currentXY[0][0]=1080/2;
                currentXY[0][1]=-bitmap.getHeight();
                currentXY[1][0]=1080/2;
                currentXY[1][1]=-bitmap.getHeight()-bitmap.getHeight();
                currentXY[2][0]=1080/2;
                currentXY[2][1]=-bitmap.getHeight()-2*bitmap.getHeight();
                currentXY[3][0]=1080/2;
                currentXY[3][1]=-bitmap.getHeight()-3*bitmap.getHeight();
                break;
            case 3:
                bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.j);
                currentXY[0][0]=1080/2;
                currentXY[0][1]=-bitmap.getHeight();
                currentXY[1][0]=1080/2-bitmap.getWidth();
                currentXY[1][1]=-bitmap.getHeight();
                currentXY[2][0]=1080/2;
                currentXY[2][1]=-bitmap.getHeight()-bitmap.getHeight();
                currentXY[3][0]=1080/2;
                currentXY[3][1]=-bitmap.getHeight()-2*bitmap.getHeight();
                break;
            case 4:
                bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.o);
                currentXY[0][0]=1080/2;
                currentXY[0][1]=-bitmap.getHeight();
                currentXY[1][0]=1080/2+bitmap.getWidth();
                currentXY[1][1]=-bitmap.getHeight();
                currentXY[2][0]=1080/2;
                currentXY[2][1]=-bitmap.getHeight()-bitmap.getHeight();
                currentXY[3][0]=1080/2+bitmap.getWidth();
                currentXY[3][1]=-bitmap.getHeight()-bitmap.getHeight();
                break;
            case 5:
                bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.z);
                currentXY[0][0]=1080/2;
                currentXY[0][1]=-bitmap.getHeight();
                currentXY[1][0]=1080/2+bitmap.getWidth();
                currentXY[1][1]=-bitmap.getHeight();
                currentXY[2][0]=1080/2;
                currentXY[2][1]=-bitmap.getHeight()-bitmap.getHeight();
                currentXY[3][0]=1080/2-bitmap.getWidth();
                currentXY[3][1]=-bitmap.getHeight()-bitmap.getHeight();
                break;
            case 6:
                bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.s);
                currentXY[0][0]=1080/2;
                currentXY[0][1]=-bitmap.getHeight();
                currentXY[1][0]=1080/2-bitmap.getWidth();
                currentXY[1][1]=-bitmap.getHeight();
                currentXY[2][0]=1080/2;
                currentXY[2][1]=-bitmap.getHeight()-bitmap.getHeight();
                currentXY[3][0]=1080/2+bitmap.getWidth();
                currentXY[3][1]=-bitmap.getHeight()-bitmap.getHeight();
                break;
            case 0:
                bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.l);
                currentXY[0][0]=1080/2;
                currentXY[0][1]=-bitmap.getHeight();
                currentXY[1][0]=1080/2+bitmap.getWidth();
                currentXY[1][1]=-bitmap.getHeight();
                currentXY[2][0]=1080/2;
                currentXY[2][1]=-bitmap.getHeight()-bitmap.getHeight();
                currentXY[3][0]=1080/2;
                currentXY[3][1]=-bitmap.getHeight()-2*bitmap.getHeight();
                break;
            default:
                bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.l);
                currentXY[0][0]=1080/2;
                currentXY[0][1]=-bitmap.getHeight();
                currentXY[1][0]=1080/2+bitmap.getWidth();
                currentXY[1][1]=-bitmap.getHeight();
                currentXY[2][0]=1080/2;
                currentXY[2][1]=-bitmap.getHeight()+bitmap.getHeight();
                currentXY[3][0]=1080/2;
                currentXY[3][1]=-bitmap.getHeight()+2*bitmap.getHeight();
                break;
        }
        setXY();
        speed=bitmap.getHeight();
    }
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        for(int i=0;i<4;i++){
        canvas.drawBitmap(bitmap, currentXY[i][0], currentXY[i][1], p);
        }
    }
    public void move(){
        for(int i=0;i<4;i++){
            currentXY[i][1]+=speed;
        }
        setXY();
    }
    public List<OneCell> splitCell(Context context){
        List<OneCell> cells=new LinkedList<OneCell>();
        for(int i=0;i<4;i++){
            OneCell temp=new OneCell(context);
            temp.currentX=this.currentXY[i][0];
            temp.currentY=this.currentXY[i][1];
            temp.bitmap=this.bitmap;
            cells.add(temp);
        }

        return cells;
    }
    public boolean toEnd(Cell cell,List<OneCell> cells,RelativeLayout root){
        boolean flag=false;
        if(cell.y>=root.getHeight()-300){
            flag=true;
        }else flag=false;
        for(int i=0;i<4;i++){
            for(int j=0;j<cells.size();j++){
                if(cell.currentXY[i][0]==cells.get(j).currentX
                        &&cell.currentXY[i][1]==cells.get(j).currentY){
                    Log.v("test","撞上了");
                    flag=true;
                }
            }
        }
        return flag;

    }
    public void movelift(){
        for(int i=0;i<4;i++){
            currentXY[i][0]-=speed;
        }
        setXY();
    }
    public void moveright(){
        for(int i=0;i<4;i++){
            currentXY[i][0]+=speed;
        }
        setXY();
    }
    public void remove(){
        for(int i=0;i<4;i++){
            currentXY[i][1]-=speed;
        }
        setXY();
    }
    public void spin(){
        for(int i=1;i<4;i++){
            float xtemp=currentXY[i][0];
            float ytemp=currentXY[i][1];
            currentXY[i][0]=currentXY[0][0]+ytemp-currentXY[0][1];
            currentXY[i][1]=currentXY[0][1]-xtemp+currentXY[0][0];
        }
        setXY();
    }
    public void setXY(){
        for(int i=0;i<4;i++){
            if(this.y<currentXY[i][1]){this.y=currentXY[i][1];}
            if (this.x>currentXY[i][0]){this.x=currentXY[i][0];}
            if(this.maxX<currentXY[i][0]){this.maxX=currentXY[i][0];}
        }
    }

}
