package inc.ak.kkpeli;

import android.app.Activity;
import android.content.Intent;
import android.database.Observable;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {


    private ToggleButton easyButton;
    private ToggleButton mediumButton;
    private ToggleButton hardButton;
    private Button playButton;
    Thread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       //setContentView(new GamePanel(this));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.LEFT_JUSTIFY = (int) dm.widthPixels/10;

        //setContentView(new GamePanel(this));
        mainMenuView();

        final Handler h = new Handler();

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!GameplayScene.gameOver){
                    if (GameplayScene.gameOver)
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                startMainMenu();
                            }

                        });
                }
            }
        });
    }

    public void mainMenuView() {
        setContentView(R.layout.activity_main);

        easyButton = (ToggleButton) findViewById(R.id.easy_button);
        mediumButton = (ToggleButton) findViewById(R.id.medium_button);
        hardButton = (ToggleButton) findViewById(R.id.hard_button);

        playButton = (Button) findViewById(R.id.play_button);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonSelected(v);
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonSelected(v);
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonSelected(v);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent playIntent = new Intent(MainActivity.this, GamePanel.class);
                //startActivity(playIntent);

                if (easyButton.isChecked() || mediumButton.isChecked() || hardButton.isChecked())
                startGame();
                else
                    showToast("Please Choose Difficulty");
            }
        });
    }

    public void setButtonSelected(View v){
        switch (v.getId()){
            case R.id.easy_button:
                if (easyButton.isChecked()) {
                   // Toast.makeText(this, "easy button checked", Toast.LENGTH_SHORT).show();
                    //v.setBackgroundColor(Color.rgb(0, 100, 0));
                    easyButton.setBackgroundColor(Color.rgb(0, 100, 0));

                    mediumButton.setChecked(false);
                    mediumButton.setBackgroundColor(Color.parseColor("#ffff8800"));
                    Constants.GAME_SPEED = 13000.0f;
                    hardButton.setChecked(false);
                    hardButton.setBackgroundColor(Color.parseColor("#ffff4444"));
                } else
                    easyButton.setBackgroundColor(Color.parseColor("#ff99cc00"));
                   // Toast.makeText(this, "easy button unchecked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.medium_button:
                if (mediumButton.isChecked()) {
                    v.setBackgroundColor(Color.rgb(204, 102, 0));
                    mediumButton.setBackgroundColor(Color.rgb(204, 102, 0));

                    easyButton.setChecked(false);
                    easyButton.setBackgroundColor(Color.parseColor("#ff99cc00"));

                    Constants.GAME_SPEED = 9000.0f;

                    hardButton.setChecked(false);
                    hardButton.setBackgroundColor(Color.parseColor("#ffff4444"));


                }else
                    mediumButton.setBackgroundColor(Color.parseColor("#ffff8800"));
                break;
            case R.id.hard_button:
                if (hardButton.isChecked()) {
                    v.setBackgroundColor(Color.rgb( 204, 0, 0));
                    hardButton.setBackgroundColor(Color.rgb(204, 0, 0));

                    easyButton.setChecked(false);
                    easyButton.setBackgroundColor(Color.parseColor("#ff99cc00"));

                    Constants.GAME_SPEED = 4000.0f;

                    mediumButton.setChecked(false);
                    mediumButton.setBackgroundColor(Color.parseColor("#ffff8800"));

                }else
                    hardButton.setBackgroundColor(Color.parseColor("#ffff4444"));
                break;
        }
    }
    private void showToast (String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void startGame() {
        t.start();
        setContentView(new GamePanel(this));
    }

    public void startMainMenu() {
        if (GameplayScene.gameOver) {
            GameplayScene.gameOver=false;
            mainMenuView();
        }
    }
}
