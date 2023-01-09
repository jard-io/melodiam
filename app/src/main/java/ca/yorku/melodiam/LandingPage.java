package ca.yorku.melodiam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NavUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        //background animation
        ConstraintLayout constraintLayout = findViewById(R.id.mainBg);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        Button startButton = findViewById(R.id.start_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPage.this, Compose.class);
                startActivity(intent);
            }
        });
        ActionBar actionBar = getSupportActionBar();

        //action bar title
        if(actionBar !=null)

        {
            actionBar.setTitle("Instructions");
        }
        /* methods to display the icon in the ActionBar
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }
         */
    }

    //open options menu
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.menu_instructions, menu);
        return true;
    }

    // button action code to be completed
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.action_about:
                startActivity(new Intent(this, AboutPage.class));
                return true;

            case R.id.action_instructions:
                startActivity(new Intent(this, Instructions.class));

        }
        return super.onOptionsItemSelected(item);
    }

}
