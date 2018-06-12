package inc.ak.kkpeli;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class GameActivity extends Activity {



    @Override
    protected void onCreate(Bundle values){
        super.onCreate(values);




        setContentView(new GamePanel(this));
    }

    public void finishActivity(){
        finish();
    }
}
