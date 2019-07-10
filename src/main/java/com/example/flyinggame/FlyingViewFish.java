package com.example.flyinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class FlyingViewFish extends View {

    private Bitmap fish []= new Bitmap[2];
    private Bitmap backGroundImage;
    private Paint scorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];
    private int score;
    private int fishX =10 , fishY,fishSpeed;
    private int canvasHiget , canvasWeigth;
    boolean touch = false;

    private int yellowX,yellowY,yellowSpeed=16;
    private Paint yellowPaint = new Paint();

    private int greenX,greenY, greenSpeed = 20;
    private Paint greenPaint = new Paint();



    public FlyingViewFish(Context context)
    {
        super(context);
        fish [0] = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish [1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        backGroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);
        life[0]= BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]= BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
        fishY = 550;
        score = 0;
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasHiget = canvas.getHeight();
        canvasWeigth = canvas.getWidth();

        canvas.drawBitmap(backGroundImage,0,0,null);
        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHiget - fish[0].getHeight()*3;
        fishY = fishY + fishSpeed;
        if(fishY<minFishY)
        {
            fishY = minFishY;
        }
        if(fishY>maxFishY)
        {
            fishY = maxFishY;
        }
        fishSpeed +=2;
        if(touch)
        {
            canvas.drawBitmap(fish[1],fishX,fishX,null);
            touch =false;
        }
        else
        {
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }



        yellowX -= yellowSpeed;
        if(hitFishChecker(yellowX,yellowY)){
            score+=10;
            yellowX = -100;
        }


        if(yellowX <0)
        {
            yellowX = canvasWeigth + 21;
            yellowY = (int) Math.floor(Math.random() *(maxFishY-minFishY)) + minFishY;
        }
        canvas.drawCircle(yellowX,yellowY,25,yellowPaint);



        canvas.drawText("Score : " + score,20,60,scorePaint);
        canvas.drawBitmap(life[0],580,20,null);
        canvas.drawBitmap(life[0],680,20,null);
        canvas.drawBitmap(life[0],780,20,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;
            fishSpeed = -22;
        }
        return true;
    }

    public boolean hitFishChecker(int x, int y)
    {
        if(fishX < x && x < (fishX + fish[0].getWidth()) && fishY<y && y < (fishY +fish[0].getHeight()) )
        {
            return true;
        }
        return false;
    }


}
