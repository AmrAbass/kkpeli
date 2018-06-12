package inc.ak.kkpeli;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Amr on 07/06/2018.
 */

public interface Scene {
    public void update ();
    public void draw (Canvas canvas);
    public void terminate ();
    public void recieveTouch (MotionEvent event);
}
