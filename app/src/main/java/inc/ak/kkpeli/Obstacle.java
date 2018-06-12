package inc.ak.kkpeli;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Amr on 05/06/2018.
 */

public class Obstacle implements GameObject{

    private Rect rectangle;
    private Rect rectangle2;
    private int color;

    public Rect getRectangle() {
        return rectangle;
    }

    public void incrementY(float Y) {
        rectangle.top += Y;
        rectangle.bottom += Y;
        rectangle2.top += Y;
        rectangle2.bottom += Y;

    }

    public Obstacle(int rectHeight, int color, int startX, int startY, int playerGap) {
        this.color = color;
        //left, top, bottom, right
        if (startX < Constants.LEFT_JUSTIFY) startX = Constants.LEFT_JUSTIFY;
        rectangle = new Rect(0, startY, startX,startY + rectHeight);
        rectangle2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
    }

    public boolean playerCollide(RectPlayer player) {
        return Rect.intersects(rectangle, player.getRectangle())
                || Rect.intersects(rectangle2, player.getRectangle());
    }
    @Override
    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
        canvas.drawRect(rectangle2, paint);

    }

    @Override
    public void update() {

    }
}
