package fr.northborders.menucrossdrawable.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.view);
        final HamburgerMenuDrawable hamburgerMenuDrawable = new HamburgerMenuDrawable(getResources().getDimensionPixelSize(R.dimen.stroke_width), Color.YELLOW, Color.RED);

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(hamburgerMenuDrawable);
        } else {
            view.setBackground(hamburgerMenuDrawable);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hamburgerMenuDrawable.toggle();
            }
        });
    }
}
