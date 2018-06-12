package inc.ak.kkpeli;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
    public Context getAppContext(){
        return mContext;
    }
}
