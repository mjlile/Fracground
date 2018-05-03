package com.example.matthew.fracground;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.*;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.app.WallpaperManager;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Spinner fractalSpinner, backColorSpinner, color1Spinner, color2Spinner, variantSpinner;
    private Button button;
    private ProgressBar progressBar;

    private HashMap<String, Integer> colorMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instantiate();

        //matthew is dumb
        //yessir
        //matthew is hella dumb
        //uknowitgirl
        //#rip
    }

    protected void instantiate(){
        fractalSpinner = (Spinner) findViewById(R.id.fractalSpinner);
        backColorSpinner = (Spinner) findViewById(R.id.backColorSpinner);
        color1Spinner = (Spinner) findViewById(R.id.color1Spinner);
        color2Spinner = (Spinner) findViewById(R.id.color2Spinner);
        variantSpinner = (Spinner) findViewById(R.id.variantSpinner);
        progressBar = (ProgressBar) findViewById((R.id.progressBar));
        progressBar.setVisibility(View.GONE);

        colorMap = Maps.getColorMap();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, new ArrayList<String>(colorMap.keySet()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        color1Spinner.setAdapter(adapter);
        color2Spinner.setAdapter(adapter);

        //set button
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                onButtonClick();
            }
        });
    }

    protected void onButtonClick(){
        final Context context = this;
        new AsyncTask<Void, Void, Bitmap>(){
            protected void onPreExecute(){
                super.onPreExecute();
                //display loading icon
                progressBar.setVisibility(View.VISIBLE);
            }

            protected Bitmap doInBackground(Void... params){
                //generate fractal in background (high-intensive)
                return generateBitmap();
            }

            protected void onPostExecute(Bitmap bmp){
                super.onPostExecute(bmp);
                //TODO: set wallpaper in async task

                final Bitmap fractalBitmap = bmp;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setPositiveButton("Set Wallpaper", new DialogInterface.OnClickListener() {
                    //TODO: set wallpaper in background?
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: save bitmap to storage.
                        try {
                            Log.d("Background", "Try");
                            //set background
                            WallpaperManager myWallpaperManager = WallpaperManager
                                    .getInstance(MainActivity.this);
                            myWallpaperManager.setBitmap(fractalBitmap);
                        } catch (Exception e) {
                            Log.d("Background", "Catch");
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                            builder2.setMessage(R.string.background_fail)
                                    .setTitle(R.string.error_title);
                            AlertDialog errorDialog = builder2.create();
                            errorDialog.show();
                            Log.e("Background", "Can't load background: " + e.toString());
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });

                AlertDialog bitmapDialog = builder.create();
                /*
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.fractal_display, null);
                bitmapDialog.setView(dialogLayout);
                */
                ImageView bitmapView = new ImageView(context);
                bitmapView.setImageBitmap(fractalBitmap);
                bitmapDialog.setView(bitmapView);
                bitmapDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                bitmapDialog.show();

                progressBar.setVisibility(View.GONE);
            }
        }.execute();
    }

    protected Bitmap generateBitmap(){
        //TODO: autoscaling and autosizing
        Bitmap fractal = Bitmap.createBitmap(256*8, 512*8, Bitmap.Config.ARGB_8888);
        int backColor = colorMap.get(backColorSpinner.getSelectedItem().toString());
        int color1 = colorMap.get(color1Spinner.getSelectedItem().toString());
        int color2 = colorMap.get(color2Spinner.getSelectedItem().toString());
        HashMap<String, Integer> detailMap = new HashMap<String, Integer>();
        //TODO: low med high different for diff fractals

        if(fractalSpinner.getSelectedItem().toString().equals("Fern")) {
            detailMap.put("Low", 1000000);
            detailMap.put("Medium", 5000000);
            detailMap.put("High", 10000000);

            Fern.generate(fractal, detailMap.get(variantSpinner.getSelectedItem().toString()), backColor, color1, color2);
        }else if(fractalSpinner.getSelectedItem().toString().equals("Dragon Curve")) {
            detailMap.put("Low", 10);
            detailMap.put("Medium", 16);
            detailMap.put("High", 18);


            DragonCurve2.generate(fractal, detailMap.get(variantSpinner.getSelectedItem().toString()), backColor, color1, color2);
            //18 is solid, 17 (17,3) has good texture. Low iter amd big dots is cool too (11,30)
        }else if(fractalSpinner.getSelectedItem().toString().equals("Julia 1")) {
            detailMap.put("Low", 127);
            detailMap.put("Medium", 254);
            detailMap.put("High", 1023);

            double[] z0 = {-.7, .27015};
            Julia.generate(fractal, detailMap.get(variantSpinner.getSelectedItem().toString()), backColor, color1, color2, "Julia 1");
        }else if(fractalSpinner.getSelectedItem().toString().equals("Julia 2")) {
            detailMap.put("Low", 127);
            detailMap.put("Medium", 254);
            detailMap.put("High", 1023);

            //mandelbrot?
            double[] z0 = {-.4, .6};


            Julia.generate(fractal, detailMap.get(variantSpinner.getSelectedItem().toString()), backColor, color1, color2, "Julia 2");
        }else if(fractalSpinner.getSelectedItem().toString().equals("Mandelbrot")) {
            detailMap.put("Low", 127);
            detailMap.put("Medium", 254);
            detailMap.put("High", 1023);

            double[] z0 = {-.79,.15};
            Julia.generate(fractal, detailMap.get(variantSpinner.getSelectedItem().toString()), backColor, color1, color2, "Mandelbrot");
        }

        return fractal;
    }
}
