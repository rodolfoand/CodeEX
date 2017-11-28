package br.senai.sp.informatica.meusalbuns.lib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

import br.senai.sp.informatica.meusalbuns.R;

/**
 * Created by 34023325821 on 28/11/2017.
 */

public class Util {

    public static Bitmap circularBitmapAndText(int cor, int width, int height, String txt) {
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setColor(cor);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(width / 2, height / 2, width / 2, paint);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(100);
        canvas.drawText(txt, width / 2, height / 2 + 30, paint);
        return output;
    }

    public static byte[] bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encode(byteArray, Base64.DEFAULT);
    }

    public static Bitmap bitmapFromBase64(byte[] dados) {
        byte[] fotoArray = Base64.decode(dados, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(fotoArray, 0, fotoArray.length);
        return bitmap;
    }
}
