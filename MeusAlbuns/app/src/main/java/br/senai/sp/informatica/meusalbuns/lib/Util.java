package br.senai.sp.informatica.meusalbuns.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public static Bitmap setPic(int width, int height, Uri fotoUri, Context context) throws IOException {
        return setFilePic(width,height,fotoUri,context);
    }

    private static Bitmap setFilePic(int width, int height, Object foto, Context context) throws IOException {
        // Get the dimensions of the View
        int targetW = width;
        int targetH = height;

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        Rect rect = new Rect(-1, -1, -1, -1);

        InputStream is;
        if(foto instanceof File) {
            is = context.getContentResolver().openInputStream(Uri.fromFile((File)foto));
        } else if(foto instanceof Uri) {
            is = context.getContentResolver().openInputStream((Uri)foto);
        } else {
            throw new IOException("Invalid URI");
        }

        BitmapFactory.decodeStream(is, rect, bmOptions);
        is.close();

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = 0;
        if (targetH != 0 && targetW != 0)
            scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        //bmOptions.inPurgeable = true;

        Bitmap bitmap;
        if(foto instanceof File) {
            bitmap = BitmapFactory.decodeFile(foto.toString(), bmOptions);
        } else {
            ParcelFileDescriptor parcelFileDescriptor =
                    context.getContentResolver().openFileDescriptor((Uri) foto, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, bmOptions);
            parcelFileDescriptor.close();
        }

        return bitmap;
    }
    /*
     * Cria um arquivo temporário para armazenar a Foto
     */
    @SuppressLint("SimpleDateFormat")
    public static File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        if (!storageDir.exists())
            storageDir.mkdir();
        try {
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            return image;
        } catch (IOException ex) {
            Log.e(imageFileName, storageDir.getPath(), ex);
            throw new IOException(ex);
        }
    }
}
