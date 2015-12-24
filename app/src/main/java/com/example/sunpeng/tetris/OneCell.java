package com.example.sunpeng.tetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by sunpeng on 15-12-22.
 */
public class OneCell extends View{
    Bitmap bitmap;
    float currentX;
    float currentY;

    public OneCell(Context context){
        super(context);
        setFocusable(true);
    }
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint p=new Paint();
        canvas.drawBitmap(bitmap, currentX, currentY, p);
    }
    public static void clear(List<OneCell> cells,RelativeLayout root,Cell cell){
        int num=12;
        int[] lo=new int[20];
        float buchang=cell.bitmap.getHeight();
        float fristlo=1392;
        //遍历cells，对满行的进行消除
        for(int i=0;i<cells.size();i++){
            lo[(int)((fristlo-cells.get(i).currentY)/buchang)]++;
        }
        for(int j=0;j<lo.length;j++){
            if(lo[j]==12){
                for(int i=0;i<cells.size();i++){
                    if(cells.get(i).currentY==fristlo-j*buchang){
                        root.removeView(cells.get(i));
                        cells.remove(i--);
                    }else if(cells.get(i).currentY<fristlo-j*buchang){
                        cells.get(i).currentY+=cell.speed;
                        cells.get(i).invalidate();
                    }
                }

            }
        }
    }
}
