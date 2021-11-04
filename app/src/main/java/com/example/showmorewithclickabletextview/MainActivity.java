package com.example.showmorewithclickabletextview;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView descText;
    TextView show, hide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        descText = (TextView) findViewById(R.id.description_text);
        show = findViewById(R.id.show);
        updateColorSymbols(descText);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                expandTextView(descText);
            }
        });
        hide = findViewById(R.id.hide);
        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                collapseTextView(descText,4);

            }
        });
    }
    private void expandTextView(TextView tv){
        ObjectAnimator animation = ObjectAnimator.ofInt(tv, "maxLines", tv.getLineCount());
        animation.setDuration(350).start();
    }

    private void collapseTextView(TextView tv, int numLines){
        ObjectAnimator animation = ObjectAnimator.ofInt(tv, "maxLines", numLines);
        animation.setDuration(350).start();
    }

    public  void updateColorSymbols(TextView textView){
        new PatternEditableBuilder().
                addPattern(Pattern.compile("\\#(\\w+)"), Color.RED,
                        new PatternEditableBuilder.SpannableClickedListener() {
                            @Override
                            public void onSpanClicked(String text) {
                                Toast.makeText(textView.getContext(), "Clicked username: " + text,
                                        Toast.LENGTH_SHORT).show();
                            }

                        }).into(textView);
        new PatternEditableBuilder().
                addPattern(Pattern.compile("\\@(\\w+)"), Color.BLUE,
                        new PatternEditableBuilder.SpannableClickedListener() {
                            @Override
                            public void onSpanClicked(String text) {
                                Toast.makeText(textView.getContext(), "Clicked username: " + text,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).into(textView);


    }
}