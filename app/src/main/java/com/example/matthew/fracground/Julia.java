package com.example.matthew.fracground;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Matthew on 12/8/2017.
 */

public class Julia {
    //private static final int SCALE = 80;
    //private static final int W = 16 * SCALE;
    //private static final int HT = 9 * SCALE;


    private static int mand(double[] z0, int limit, String version) {
        double[] z = new double[]{z0[0], z0[1]};
        // Julia  <--Sets the c value (use (0,0) for Mandelbrot
        //See invertedJulia for other aesthetically pleasing starting values
        if (version.equals("Julia 1")){
            z0[0] = -.4;
            z0[1] = .6;
        }else if(version.equals("Julia 2")) {
            z0[0] = -.7;
            z0[1] = .27015;
        }else{
            //z0[0] = 0;
            //z0[0] = 0;
            //Mandelbrot
        }
        for (int i = 0; i < limit; i++) {

            double modulus = Math.sqrt(z[0] * z[0] + z[1] * z[1]);
            if (modulus > 2.0) {
                return i;
            }
            double temp = z[0];
            z[0] = z[0] * z[0] - z[1] * z[1] + z0[0];
            z[1] = 2 * temp * z[1] + z0[1];
        }
        return limit;
    }

    public static void generate(Bitmap fractal, int iterations, int backColor, int color2, int color1, String version) {
        Canvas canvas = new Canvas(fractal);

        double[] z0 = {0,0};

        int H = fractal.getWidth();
        int W = fractal.getHeight();

        int limit = iterations;
        double[] center = new double[]{0, 0};    //Sets the center of the image to point (0,0) - Use (-.5,0) for mandelbrot <--Lets you pan
if(version.equals("Mandelbrot")){
    center = new double[]{-.5, 0};
}
        double yScale = 2;                        //Sets height of mandelbrot set (Use 2 for Mandelbrot) <--Lets you zoom up to a certain point (doubles can only go so far)
        double xScale = 16 * yScale / 9;            //Scaled according to height so each pixel is a square
        double[] xRange = new double[2];
        xRange[0] = center[0] - xScale / 2;
        xRange[1] = center[0] + xScale / 2;
        double[] yRange = new double[2];
        yRange[0] = center[1] - yScale / 2;
        yRange[1] = center[1] + yScale / 2;

        double width = (Math.abs(xRange[0]) + Math.abs(xRange[1]));
        double height = (Math.abs(yRange[0]) + Math.abs(yRange[1]));

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {

                z0[0] = xRange[1] - width + width * i / W;
                z0[1] = yRange[1] - height + height * j / H;
                int escapeTime = mand(z0, limit, version);
                Paint paint = new Paint();
                if (escapeTime == limit) {
                    paint.setColor(backColor);
                } else {
                    double relDist = (double) Math.log(escapeTime + 1) / Math.log(256);
                    int dr = Color.red(color2) - Color.red(color1);
                    int dg = Color.green(color2) - Color.green(color1);
                    int db = Color.blue(color2) - Color.blue(color1);

                    paint.setColor(Color.rgb((int) (Color.red(color1) + relDist * dr), (int) (Color.green(color1) + relDist * dg), (int) (Color.blue(color1) + relDist * db)));
                }
                /*
                if(version.equals("Mandelbrot")){
                    canvas.drawPoint(j, (int)(i - .5 * yScale), paint);
                }else{
                    canvas.drawPoint(j, i, paint);
                }
                */
                canvas.drawPoint(j, i, paint);
            }
        }

    }

}

