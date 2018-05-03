package com.example.matthew.fracground;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

/**
 * Created by Matthew on 12/5/2017.
 */

public class DragonCurve2 {

    public static void generate(Bitmap fractal, int iterations, int backColor, int color1, int color2) {
        Canvas canvas = new Canvas(fractal);
        canvas.drawARGB(Color.alpha(backColor), Color.red(backColor), Color.green(backColor), Color.blue(backColor));

        //TODO: make neater / generalize, see "Dimensions" on wikipedia page
        float scale = 3*fractal.getHeight() / 7;
        PointF origin = new PointF(fractal.getWidth() / 3, 1*fractal.getHeight() / 4);

        PointF p1 = new PointF(0,0);
        PointF p2 = new PointF(1,0);

        recurse(p1, canvas, color1, color2, origin, scale, 1, iterations);
        recurse(p2, canvas, color1, color2, origin, scale, 1, iterations);
    }

    private static void recurse(PointF p, Canvas canvas, int color1, int color2, PointF origin, float scale, int depth, int maxDepth){
        if(depth > maxDepth){
            //canvas.drawPoint(scale*p.x+origin.x, scale*p.y+origin.y, paint);
            //flip x and y
            //color1 = Color.RED;
            //color2 = Color.MAGENTA;

            //color changed as distance from either end spiral??????
            int dr = Color.red(color2) - Color.red(color1);
            int dg = Color.green(color2) - Color.green(color1);
            int db = Color.blue(color2) - Color.blue(color1);
            /*
            double maxDist = Math.sqrt(Math.pow((1+1/6)*scale,2)+Math.pow(0,2));
            float relDist = (float) (Math.sqrt(Math.pow(p.x-scale/3,2)+Math.pow(p.y,2)) / maxDist);
            */

            float relDist = (p.x+(1f/3)) / ((1f/3)+1+(1f/6));
            Log.d(":(", p.x + "," + relDist);
            //relDist = 0;
            int pointColor = Color.rgb((int) (Color.red(color1) + relDist * dr), (int) (Color.green(color1) + relDist * dg), (int) (Color.blue(color1) + relDist * db));
            Paint paint = new Paint();
            paint.setColor(pointColor);
            //paint.setColor(Color.rgb(Color.red(color1),Color.green(color1),Color.green(color1)));

            int size = 3;
            if(maxDepth == 10){
                size = 20;
            }

            canvas.drawCircle(scale*p.y+origin.x, scale*p.x+origin.y, size, paint);
            return;
        }




        recurse(f1(p), canvas, color1, color2, origin, scale, depth+1, maxDepth);
        recurse(f2(p), canvas, color1, color2, origin, scale, depth+1, maxDepth);
    }

    private static PointF f1(PointF p){
        PointF p2 = new PointF(0,0);
        p2.x = (float)((Math.cos(Math.PI / 4)*p.x - Math.sin(Math.PI / 4)*p.y) / Math.sqrt(2));
        p2.y = (float)((Math.sin(Math.PI / 4)*p.x + Math.cos(Math.PI / 4)*p.y) / Math.sqrt(2));
        return p2;
    }

    private static PointF f2(PointF p){
        PointF p2 = new PointF(0,0);
        p2.x = (float)((Math.cos(3*Math.PI / 4)*p.x - Math.sin(3*Math.PI / 4)*p.y) / Math.sqrt(2)) + 1;
        p2.y = (float)((Math.sin(3*Math.PI / 4)*p.x + Math.cos(3*Math.PI / 4)*p.y) / Math.sqrt(2));
        return p2;
    }
}
