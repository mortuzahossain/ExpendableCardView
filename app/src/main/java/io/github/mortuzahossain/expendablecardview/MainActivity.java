package io.github.mortuzahossain.expendablecardview;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.alespero.expandablecardview.ExpandableCardView;

public class MainActivity extends AppCompatActivity {

    private boolean isExpended = false;
    private int tvDefaultHeight = 0;
    private int initialHeight = 70;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableCardView card = findViewById(R.id.profile);
        card.setIcon(null);




        final TextView viewTv = findViewById(R.id.textView2);

        tvDefaultHeight = viewTv.getLayoutParams().height;
        Log.d("value default: ", String.valueOf(tvDefaultHeight));


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExpended) {
                    isExpended = false;
                    slideView(viewTv, tvDefaultHeight, initialHeight);
                } else {
                    isExpended = true;
                    slideView(viewTv, viewTv.getLayoutParams().height, tvDefaultHeight);
                }
            }
        });
    }

    public static void slideView(final View view,
                                 int currentHeight,
                                 int newHeight) {

        ValueAnimator slideAnimator = ValueAnimator
                .ofInt(currentHeight, newHeight)
                .setDuration(500);

        slideAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation1) {
                Integer value = (Integer) animation1.getAnimatedValue();
                Log.d("value: ", String.valueOf(value));
                view.getLayoutParams().height = value.intValue();
                view.requestLayout();
            }
        });

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.play(slideAnimator);
        animationSet.start();
    }
}
