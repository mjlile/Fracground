package com.example.matthew.fracground;

/**
 * Created by Matthew on 12/2/2017.
 */

import android.graphics.*;

public class Fern {

    public static void generate(Bitmap fractal, int iterations, int backColor, int color1, int color2) {
        final int W = fractal.getWidth()-1;
        final int H = fractal.getHeight()-1;

        Canvas canvas = new Canvas(fractal);
        canvas.drawARGB(Color.alpha(backColor), Color.red(backColor), Color.green(backColor), Color.blue(backColor));
        /*
        for(int i = 0; i <= H; i++){
            for(int j = 0; j <= W; j++){
                fractal.setPixel(i,j,backColor);
            }
        }*/

        float scale = 320f;

        //calculate color difference
        int dr = Color.red(color2) - Color.red(color1);
        int dg = Color.green(color2) - Color.green(color1);
        int db = Color.blue(color2) - Color.blue(color1);

        //farthest possible point
        double maxDist = Math.sqrt(Math.pow(2.1820, 2) + Math.pow(9.9983, 2));

        //Draw origin
        PointF origin = new PointF(W / 2f, H / 12);
        Paint paint = new Paint();

        //iterate
        PointF pt = new PointF(0, 0);
        for (int i = 0; i < iterations; i++) {
            double rand = Math.random();

            if (rand < 0.01) {
                //pt = f1(pt);
            } else if (rand < 0.08) {
                pt = f4(pt);
            } else if (rand < 0.15) {
                pt = f3(pt);
            } else {
                pt = f2(pt);
            }

            //calculate color gradient
            double relDist = Math.sqrt(pt.x * pt.x + pt.y * pt.y) / maxDist;
            if (relDist > 1) {
                relDist = 1;
            }

            int pointColor = Color.rgb((int) (Color.red(color1) + relDist * dr), (int) (Color.green(color1) + relDist * dg), (int) (Color.blue(color1) + relDist * db));

            //c = new Color((int)(255),(int)(val),(int)(255 - (val))); //pink to yellow
            //c = new Color((int)(2.55*val),(int)(255-val),(int)(155+val)); //pastel blue to pink ish

            //draw dot
            paint.setColor(pointColor);
            canvas.drawPoint((scale * pt.x + origin.x), H - (scale * pt.y + origin.y), paint);
            //fractal.setPixel((int) (scale * pt.x + origin.x), H - (int) (scale * pt.y + origin.y), pointColor);
        }
    }

    private static PointF f1(PointF pt) {
        pt.x = 0;
        pt.y = 0.16f * pt.y;
        return pt;
    }

    // play with 0.04 for curly fun :)))
    private static PointF f2(PointF pt) {
        PointF pt2 = new PointF(0, 0);
        pt2.x = 0.85f * pt.x + 0.04f * pt.y;
        pt2.y = -0.04f * pt.x + 0.85f * pt.y + 1.6f;
        return pt2;
    }

    private static PointF f3(PointF pt) {
        PointF pt2 = new PointF(0, 0);
        pt2.x = 0.2f * pt.x - 0.26f * pt.y;
        pt2.y = 0.23f * pt.x + 0.22f * pt.y + 1.6f;
        return pt2;
    }

    private static PointF f4(PointF pt) {
        PointF pt2 = new PointF(0, 0);
        pt2.x = -0.15f * pt.x + 0.28f * pt.y;
        pt2.y = 0.26f * pt.x + 0.24f * pt.y + 0.44f;
        return pt2;
    }

}
