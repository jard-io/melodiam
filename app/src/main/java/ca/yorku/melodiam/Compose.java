package ca.yorku.melodiam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NavUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class Compose extends AppCompatActivity {
    ImageView gif_view;
    ArrayList<Integer> animationOptions;
    Random randInt;
    int selectorNum;
    EditText input_text;
    Button play;
    TextView results_view;
    private SoundPool soundPool;
    private int a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
    private int andsign, asteriskmark, atsign, bracket, colon, comma, dash, dollarsign, doublequotationmark,
    exclamationpoint, numbersign, percentsign, period, plussign, questionmark, semicolon, singlequotationmark,
    space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compose);

        //making animation visible
        gif_view = findViewById(R.id.gifview); //initialize gif_view
        animationOptions = new ArrayList<>(); //initialize animationOptions and fill
        animationOptions.add(0);
        animationOptions.add(1);
        animationOptions.add(2);
        animationOptions.add(3);
        randInt = new Random();
        int minRange = 0, maxRange = 3;
        selectorNum = randInt.nextInt(maxRange - minRange) + minRange;
        switch (selectorNum){
            case 0: //makes animation be purple goop
                Glide.with(this).load(R.drawable.goop_purple).into(gif_view);
                break;
            case 1: //makes animation green goop
                Glide.with(this).load(R.drawable.goop_green).into(gif_view);
                break;
            case 2: //makes animation teal bubbles
                Glide.with(this).load(R.drawable.buubles_teal).into(gif_view);
                break;
            case 3: //makes animation pink bubbles
                Glide.with(this).load(R.drawable.bubbles_pink).into(gif_view);
                break;
        }


        // initialize scene elements
        input_text = findViewById(R.id.input); //EditText takes input
        play = findViewById(R.id.play); //Button sends signal for input
        results_view = findViewById(R.id.results_view); //TextView returns input

        /* Assume minimum API is 21 (Lollipop), otherwise set IF statement
        for deprecated soundPool instantiation
         */
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION) //Ctrl + B for documentation; SONIFICATION is for short clips
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION) //again, SONIFICATION suits our purposes
                .build(); //initializes audio

        soundPool = new SoundPool.Builder() //init SoundPool
                .setMaxStreams(44)
                .setAudioAttributes(audioAttributes)
                .build();

        //--- import clips PER CHARACTER
        a = soundPool.load(this, R.raw.a, 1); b = soundPool.load(this, R.raw.b, 1); c = soundPool.load(this, R.raw.c, 1);
        d = soundPool.load(this, R.raw.d, 1); e = soundPool.load(this, R.raw.e, 1); f = soundPool.load(this, R.raw.f, 1);
        g = soundPool.load(this, R.raw.g, 1); h = soundPool.load(this, R.raw.h, 1); i = soundPool.load(this, R.raw.i, 1);
        j = soundPool.load(this, R.raw.j, 1); k = soundPool.load(this, R.raw.k, 1); l = soundPool.load(this, R.raw.l, 1);
        m = soundPool.load(this, R.raw.m, 1); n = soundPool.load(this, R.raw.n, 1); o = soundPool.load(this, R.raw.o, 1);
        p = soundPool.load(this, R.raw.p, 1); q = soundPool.load(this, R.raw.q, 1); r = soundPool.load(this, R.raw.r, 1);
        s = soundPool.load(this, R.raw.s, 1); t = soundPool.load(this, R.raw.t, 1); u = soundPool.load(this, R.raw.u, 1);
        v = soundPool.load(this, R.raw.v, 1); w = soundPool.load(this, R.raw.w, 1); x = soundPool.load(this, R.raw.x, 1);
        y = soundPool.load(this, R.raw.y, 1); z = soundPool.load(this, R.raw.z, 1);

        //--- import clips PER SPECIAL CHARATACTER
        andsign = soundPool.load(this, R.raw.andsign, 1); asteriskmark = soundPool.load(this, R.raw.asteriskmark, 1);
        atsign = soundPool.load(this, R.raw.atsign, 1); bracket = soundPool.load(this, R.raw.bracket, 1);
        colon = soundPool.load(this, R.raw.colon, 1); comma = soundPool.load(this, R.raw.comma, 1);
        dash = soundPool.load(this, R.raw.dash, 1); dollarsign = soundPool.load(this, R.raw.dollarsign, 1);
        doublequotationmark = soundPool.load(this, R.raw.doublequotationmark, 1);
        exclamationpoint = soundPool.load(this, R.raw.exclamationpoint, 1); numbersign = soundPool.load(this, R.raw.numbersign, 1);
        percentsign = soundPool.load(this, R.raw.percentsign, 1); period = soundPool.load(this, R.raw.period, 1);
        plussign = soundPool.load(this, R.raw.plussign, 1); questionmark = soundPool.load(this, R.raw.questionmark, 1);
        semicolon = soundPool.load(this, R.raw.semicolon, 1); singlequotationmark = soundPool.load(this, R.raw.singlequotationmark, 1);
        space = soundPool.load(this, R.raw.space, 1);

        //When PLAY button is clicked
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String input = input_text.getText().toString();  //get input from EditText
                input = input.toLowerCase(); //make all characters lower case
                char[] inputChars = input.toCharArray();  //initialize array of chars

                if (input.length() != 0){ //if there's an input
                    results_view.setText(input); //display the input on the screen (will need to stylize this later)

                    // process string per character
                    for (char c: inputChars){
                        playSound(c); //play each character

                        //delay for loop by 1 second to ensure that each sound finishes playing (may not be the safest solution, but hey, it works)
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else
                    results_view.setText(R.string.no_text_error); //return this message if EditText is empty

            }
        });

        //Action bar setup
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Instructions");
        }
    }

    //play each sound
    public void playSound(char letter){
        switch (letter){
            case 'a':
                soundPool.play(a, 1, 1, 0, 0, 1);
                break;
            case 'b':
                soundPool.play(b, 1, 1, 0, 0, 1);
                break;
            case 'c':
                soundPool.play(c, 1, 1, 0, 0, 1);
                break;
            case 'd':
                soundPool.play(d, 1, 1, 0, 0, 1);
                break;
            case 'e':
                soundPool.play(e, 1, 1, 0, 0, 1);
                break;
            case 'f':
                soundPool.play(f, 1, 1, 0, 0, 1);
                break;
            case 'g':
                soundPool.play(g, 1, 1, 0, 0, 1);
                break;
            case 'h':
                soundPool.play(h, 1, 1, 0, 0, 1);
                break;
            case 'i':
                soundPool.play(i, 1, 1, 0, 0, 1);
                break;
            case 'j':
                soundPool.play(j, 1, 1, 0, 0, 1);
                break;
            case 'k':
                soundPool.play(k, 1, 1, 0, 0, 1);
                break;
            case 'l':
                soundPool.play(l, 1, 1, 0, 0, 1);
                break;
            case 'm':
                soundPool.play(m, 1, 1, 0, 0, 1);
                break;
            case 'n':
                soundPool.play(n, 1, 1, 0, 0, 1);
                break;
            case 'o':
                soundPool.play(o, 1, 1, 0, 0, 1);
                break;
            case 'p':
                soundPool.play(p, 1, 1, 0, 0, 1);
                break;
            case 'q':
                soundPool.play(q, 1, 1, 0, 0, 1);
                break;
            case 'r':
                soundPool.play(r, 1, 1, 0, 0, 1);
                break;
            case 's':
                soundPool.play(s, 1, 1, 0, 0, 1);
                break;
            case 't':
                soundPool.play(t, 1, 1, 0, 0, 1);
                break;
            case 'u':
                soundPool.play(u, 1, 1, 0, 0, 1);
                break;
            case 'v':
                soundPool.play(v, 1, 1, 0, 0, 1);
                break;
            case 'w':
                soundPool.play(w, 1, 1, 0, 0, 1);
                break;
            case 'x':
                soundPool.play(x, 1, 1, 0, 0, 1);
                break;
            case 'y':
                soundPool.play(y, 1, 1, 0, 0, 1);
                break;
            case 'z':
                soundPool.play(z, 1, 1, 0, 0, 1);
                break;
            case '&':
                soundPool.play(andsign, 1, 1, 0, 0, 1);
                break;
            case '*':
                soundPool.play(asteriskmark, 1, 1, 0, 0, 1);
                break;
            case '@':
                soundPool.play(atsign, 1, 1, 0, 0, 1);
                break;
            case '[':
                soundPool.play(bracket, 1, 1, 0, 0, 1);
                break;
            case ']':
                soundPool.play(bracket, 1, 1, 0, 0, 1);
                break;
            case '(':
                soundPool.play(bracket, 1, 1, 0, 0, 1);
                break;
            case ')':
                soundPool.play(bracket, 1, 1, 0, 0, 1);
                break;
            case '}':
                soundPool.play(bracket, 1, 1, 0, 0, 1);
                break;
            case '{':
                soundPool.play(bracket, 1, 1, 0, 0, 1);
                break;
            case ':':
                soundPool.play(colon, 1, 1, 0, 0, 1);
                break;
            case ',':
                soundPool.play(comma, 1, 1, 0, 0, 1);
                break;
            case '-':
                soundPool.play(dash, 1, 1, 0, 0, 1);
                break;
            case '$':
                soundPool.play(dollarsign, 1, 1, 0, 0, 1);
                break;
            case '"':
                soundPool.play(doublequotationmark, 1, 1, 0, 0, 1);
                break;
            case '!':
                soundPool.play(exclamationpoint, 1, 1, 0, 0, 1);
                break;
            case '1':
                soundPool.play(numbersign, 1, 1, 0, 0, 1);
                break;
            case '2':
                soundPool.play(numbersign, 1, 1, 0, 0, 1);
                break;
            case '3':
                soundPool.play(numbersign, 1, 1, 0, 0, 1);
                break;
            case '4':
                soundPool.play(numbersign, 1, 1, 0, 0, 1);
                break;
            case '5':
                soundPool.play(numbersign, 1, 1, 0, 0, 1);
                break;
            case '6':
                soundPool.play(numbersign, 1, 1, 0, 0, 1);
                break;
            case '7':
                soundPool.play(numbersign, 1, 1, 0, 0, 1);
                break;
            case '8':
                soundPool.play(numbersign, 1, 1, 0, 0, 1);
                break;
            case '9':
                soundPool.play(numbersign, 1, 1, 0, 0, 1);
                break;
            case '0':
                soundPool.play(numbersign, 1, 1, 0, 0, 1);
                break;
            case '%':
                soundPool.play(percentsign, 1, 1, 0, 0, 1);
                break;
            case '.':
                soundPool.play(period, 1, 1, 0, 0, 1);
                break;
            case '+':
                soundPool.play(plussign, 1, 1, 0, 0, 1);
                break;
            case '?':
                soundPool.play(questionmark, 1, 1, 0, 0, 1);
                break;
            case ';':
                soundPool.play(semicolon, 1, 1, 0, 0, 1);
                break;
            case '\'':
                soundPool.play(period, 1, 1, 0, 0, 1);
                break;
            default:
                soundPool.play(space, 1, 1, 0, 0, 1);
                break;
        }
    }

    //Because we have limited resources, soundPool needs to be released after use
    @Override
    protected void onDestroy(){
        super.onDestroy(); //clears memory, i think
        soundPool.release(); //releases sound files
        soundPool = null; //removes soundPool
    }

    //open options menu from action bar
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.menu_instructions, menu); //
        return true;
    }

    // action bar button action code to be completed
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.action_about:
                startActivity(new Intent(this, AboutPage.class));
                return true;

            case R.id.action_instructions:
                startActivity(new Intent(this, Instructions.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}