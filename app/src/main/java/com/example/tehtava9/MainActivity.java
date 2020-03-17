package com.example.tehtava9;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private GestureDetector detector;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detector = new GestureDetector(MainActivity.this, new Kuuntelija());
        LinearLayout layout = findViewById(R.id.main);

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        });
    }

    class Kuuntelija extends GestureDetector.SimpleOnGestureListener {

        private static final long VELOCITY_THRESHOLD = 1;

        @Override
        public boolean onDoubleTap(MotionEvent e) {

            LinearLayout layout = findViewById(R.id.main);
            ColorDrawable colorDrawable = (ColorDrawable) layout.getBackground();
            //try koska antaa välillä(ekalla kerralla?) NULL pointerii .getColor()
            try {
                int color = colorDrawable.getColor();

                if (color == Color.WHITE)
                    layout.setBackgroundColor(Color.BLACK);
                else
                    layout.setBackgroundColor(Color.WHITE);
            } catch (Exception ex) {
                ex.printStackTrace();

            }
            return false;
        }


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            {
                double colour = Math.abs((double) velocityX);
                colour = Math.pow(colour, 2);
                colour = Math.sqrt(colour);
                colour = colour / 16000 * 255 + 50;
                LinearLayout layout = findViewById(R.id.main);

                if (Math.abs(velocityX) < VELOCITY_THRESHOLD && Math.abs(velocityY) < VELOCITY_THRESHOLD) {
                    return false;
                } else if (Math.abs(velocityX) > Math.abs(velocityY)) {
                    layout.setBackgroundColor(Color.rgb((int) (colour), 0, 0));
                    return true;
                } else {
                    layout.setBackgroundColor(Color.rgb(0, 0, (int) (colour)));
                    return true;
                }
            }
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }
    }

}