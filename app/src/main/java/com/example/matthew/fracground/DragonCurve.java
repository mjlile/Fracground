package com.example.matthew.fracground;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;

public class DragonCurve {

	private String fileName;


	private static final double length = 1;
	private static ArrayList<Boolean> path = new ArrayList<Boolean>();
	private static final double angle = Math.PI/2;


	public static void generate(Bitmap fractal, int iterations, int backColor, int color1, int color2) {
        Paint paint = new Paint(color1);
        Canvas canvas = new Canvas(fractal);

		//canvas.rotate(45);

		int dir = 0;
        final int W = fractal.getWidth()-1;
        final int H = fractal.getHeight()-1;
		PointF start = new PointF(W / 2, H - H / 12);
		
		//PointF start = new PointF((int) (W / 2), (int) (HT / 2));

		path.add(true);
        for (int i = 0; i < iterations; i++) {
            add(changedMiddle());
        }

		PointF p1 = new PointF(start.x, start.y);
		PointF p2 = new PointF(start.x, start.y);
		for (int depth = 0; depth + 1 < path.size(); depth++) {

			if (dir == 0) {
				if (path.get(depth)) {
					right(p2);
					dir = 1;
				} else {
					left(p2);
					dir = 3;
				}
			} else if (dir == 1) {
				if (path.get(depth)) {
					down(p2);
					dir = 2;
				} else {
					up(p2);
					dir = 0;
				}
			} else if (dir == 2) {
				if (path.get(depth)) {
					left(p2);

					dir = 3;
				} else {
					right(p2);
					dir = 1;
				}
			} else if (dir == 3) {
				if (path.get(depth)) {
					up(p2);
					dir = 0;
				} else {
					down(p2);
					dir = 2;
				}
			}

			canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
			p1.x = p2.x;
			p1.y = p2.y;
		}

		//canvas.rotate(-45);

	}

    private static ArrayList<Boolean> changedMiddle() {
        ArrayList<Boolean> list = (ArrayList<Boolean>) path.clone();
        list.set(list.size() / 2, !list.get(list.size() / 2));

        return list;
    }

    private static void add(ArrayList<Boolean> list) {
        path.add(true);

        for (int i = 0; i < list.size(); i++) {
            path.add(list.get(i));
        }
    }

	private static void right(PointF p) {
		p.x += Math.cos(angle) * length;
		p.y -= Math.sin(angle) * length;
	}

    private static void left(PointF p) {
		p.x += -Math.cos(angle) * length;
		p.y -= -Math.sin(angle) * length;
	}

    private static void up(PointF p) {
		p.x += Math.sin(angle) * length;
		p.y -= -Math.cos(angle) * length;
	}

    private static void down(PointF p) {
		p.x += -Math.sin(angle) * length;
		p.y -= Math.cos(angle) * length;
	}


}