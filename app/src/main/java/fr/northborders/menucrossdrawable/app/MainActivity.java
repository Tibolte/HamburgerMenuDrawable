package fr.northborders.menucrossdrawable.app;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.view);
        final MenuCrossDrawable menuCrossDrawable = new MenuCrossDrawable(getResources().getDimensionPixelSize(R.dimen.stroke_width), Color.YELLOW, Color.RED);

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(menuCrossDrawable);
        } else {
            view.setBackground(menuCrossDrawable);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuCrossDrawable.toggle();
            }
        });
    }
}
