package ca.yorku.melodiam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);

        ActionBar actionBar = getSupportActionBar();

        //action bar title
        if (actionBar != null) {
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
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.action_instructions:
                startActivity(new Intent(this, Instructions.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
