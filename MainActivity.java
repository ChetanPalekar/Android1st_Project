package com.chetan.tranc_dice2;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    Integer randomval,x,score1=0,score2=0,r=0;
    Integer a[]={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    Boolean turn=true;
    Handler h=new Handler();


    public void roll_dice(View view)
    {
    if(turn==true) {

        random();
        display_image();
        update_score();
        if (randomval>=2)
        {
            turn=true;
        }
        else {
            turn = false;
            computerTurn();
        }
    }
    }

    public  void computerTurn()
    {
        if(turn==false)
        {
            random();
            display_image();
            update_score();
            if (randomval >= 2 )
            {
                turn = false;
                h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    computerTurn();
                }
            },2000);

            }
            else {
                turn=true;
            }
        }
    }

    public void random()
    {
        Random r=new Random();
        randomval=r.nextInt(6)+1;
    }
    public void  display_image()
    {
        x=randomval-1;
        final ImageView img=(ImageView) findViewById(R.id.imageView);
        img.setImageResource(a[x]);

    }
    public void update_score()
    {
        if(turn==true)
        {
            score1=score1+randomval;
            Log.d("MainActivity","In CUpdateScore");
            TextView ts=(TextView) findViewById(R.id.ys);
            ts.setText(Integer.toString(score1));
            TextView ls=(TextView) findViewById(R.id.lastYS1);
            ls.setText(Integer.toString(randomval));
        }
        else
        {
            score2=score2+randomval;
            Log.d("MainActivity","In CUpdateScore");
            TextView fs=(TextView) findViewById(R.id.cs);
            fs.setText(Integer.toString(score2));
            TextView lt=(TextView) findViewById(R.id.lastCS1);
            lt.setText(Integer.toString(randomval));

        }
    }
    public void reset_dice(View view)
    {
        TextView tt=(TextView) findViewById(R.id.cs);
        tt.setText(Integer.toString(r));
        TextView ff=(TextView) findViewById(R.id.ys);
        ff.setText(Integer.toString(r));
        TextView xc=(TextView) findViewById(R.id.lastYS1);
        xc.setText(Integer.toString(r));
        TextView xv=(TextView) findViewById(R.id.lastCS1);
        xv.setText(Integer.toString(r));
        ImageView i=(ImageView) findViewById(R.id.imageView);
        i.setImageResource(a[0]);
        score2=0;
        score1=0;
        turn=true;
    }

    public  void hold_dice(View view)
    {
        if (turn==false)
        {
            turn=true;
        }

    }

}
