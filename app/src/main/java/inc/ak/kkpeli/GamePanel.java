package inc.ak.kkpeli;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import javax.net.ssl.ManagerFactoryParameters;

/**
 * Created by Amr on 04/06/2018.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public MainThread thread;
    MyApp mApp;

    private SceneManager manager;

    public GamePanel (Context context) {
        super(context);

        getHolder().addCallback(this);

        Constants.CURRENT_CONTEXT = context;
        thread = new MainThread(getHolder(), this);
        manager = new SceneManager(thread,context);

        setFocusable(true);

    }

    @Override
    public  void surfaceChanged (SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public  void surfaceCreated (SurfaceHolder holder) {

        thread = new MainThread (getHolder(),this);
        Constants.INIT_TIME = System.currentTimeMillis();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed (SurfaceHolder holder) {

        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch(Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {

        manager.recieveTouch(event);

        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {
        manager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        manager.draw(canvas);
    }
}
