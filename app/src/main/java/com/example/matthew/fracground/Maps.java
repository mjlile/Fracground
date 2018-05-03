package com.example.matthew.fracground;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * Created by Matthew on 12/2/2017.
 */

public class Maps {

    public static HashMap<String, Integer> getColorMap(){
        HashMap colors = new HashMap<String, Integer>();

        colors.put("Red", Color.RED);
        colors.put("Blue", Color.BLUE);
        colors.put("Green", Color.GREEN);

        colors.put("Cyan", Color.CYAN);
        colors.put("Magenta", Color.MAGENTA);
        colors.put("Yellow", Color.YELLOW);
        colors.put("Black", Color.BLACK);

        colors.put("White", Color.WHITE);
        colors.put("Gray", Color.GRAY);


        //TODO: make seperate option for michigan colors
        colors.put("Maize", Color.parseColor("#ffcb05"));
        colors.put("Go Blue", Color.parseColor("#00274c"));

        /*
        //TODO: Hex values
        colors.put("Umma Tan", Color.parseColor("#dbc88f"));
        colors.put("Burton Tower Beige", Color.parseColor("#8c9651"));
        colors.put("The Rock Gray", Color.parseColor("#7b8487"));
        colors.put("Angell Hall Ash", Color.parseColor("#7b8487"));
        colors.put("Law Quad Stone", Color.parseColor("#00274c"));
        colors.put("Puma Black", Color.parseColor("#7b8487"));
        colors.put("Hill Brown", Color.parseColor("#00274c"));
        colors.put("LSA Orange", Color.parseColor("#7b8487"));
        colors.put("Archway Ivy", Color.parseColor("#00274c"));
        colors.put("Rackham Roof Green", Color.parseColor("#7b8487"));
        colors.put("Canham Pool Blue", Color.parseColor("#00274c"));
        colors.put("Matthaei Violet", Color.parseColor("#7b8487"));

        colors.put("Diag M Metallic", Color.parseColor("#7b8487"));

        colors.put("Tappan Red", Color.parseColor("#7b8487"));
        colors.put("Ross School Orange", Color.parseColor("#7b8487"));
        colors.put("Wave Field Green", Color.parseColor("#7b8487"));
        colors.put("Taubman Teal", Color.parseColor("#7b8487"));
        colors.put("Arboretum Blue", Color.parseColor("#7b8487"));
        colors.put("Ann Arbor Amethyst", Color.parseColor("#7b8487"));
*/
        return colors;
    }

    /*
    public static HashMap<String, Callable<Bitmap>> getFractalMap(){
        //// TODO: 12/3/2017 hashmap of executable fractal functions




        return colors;
    }*/
}
