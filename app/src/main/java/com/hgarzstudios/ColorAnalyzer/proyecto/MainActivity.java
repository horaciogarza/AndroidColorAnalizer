package com.hgarzstudios.ColorAnalyzer.proyecto;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hernanlopez.proyecto.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmResults;



import android.hardware.Camera;
import android.Manifest;


public class MainActivity extends ActionBarActivity {

    private String APP_DIRECTORY = "myPictureApp/";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME;
    private String colorName;
    private String hexValue;
    private String rgbValue;
    Context context;
    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;

    private ImageView imageView;
    private Button favButton;

    Realm realm = null;// = Realm.getDefaultInstance();

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favButton = (Button) findViewById(R.id.pinColor);




        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    makeFav();

                    //realm.cancelTransaction();

                    //End - Realm

            }
        });
        //Check permissions
        verifyStoragePermissions(this);

        if (shouldAskPermission()){
            String[] perms = {"android.permission. WRITE_EXTERNAL_STORAGE"};

            int permsRequestCode = 200;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(perms, permsRequestCode);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)&& (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }


        realm.init(this);

        TEMPORAL_PICTURE_NAME = Environment.getExternalStorageDirectory() + "/NOMBRE_ARCHIVO.png";

        imageView = (ImageView) findViewById(R.id.setPicture);
        Button button = (Button) findViewById(R.id.buttonImage);

        context = this;
        favButton.setEnabled(false);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {getResources().getString(R.string.TakePhotoOptionString), /*"Elegir de galeria",*/ getResources().getString(R.string.CancelButtonString)};
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getResources().getString(R.string.SelectAnOptionString));
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int seleccion) {
                        if (options[seleccion] == getResources().getString(R.string.TakePhotoOptionString)) {
                            openCamera();
                        } /*else if (options[seleccion] == "Elegir de galeria") {
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);

                        } */else if (options[seleccion] == getResources().getString(R.string.CancelButtonString)) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

    }

    private void makeFav() {
        ProgressDialog progressdialog = new ProgressDialog(MainActivity.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.show();
        progressdialog.setCancelable(false);
        //REALM

        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        HistoryRealm user = realm.createObject(HistoryRealm.class); // Create a new object

        user.setColorName(colorName);
        user.setFav(false);
        user.setHexValue(hexValue);
        user.setRgbValue(rgbValue);
        realm.commitTransaction();

        progressdialog.dismiss();


        RealmResults<HistoryRealm> r = realm.where(HistoryRealm.class).findAll();

        for (HistoryRealm a : r) {
            Log.d("TEST", a.getColorName());
        }

        Toast.makeText(context, colorName + " was added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){

        switch(permsRequestCode){

            case 200:

                boolean writeAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;

                break;

        }

    }
    private boolean shouldAskPermission(){

        return(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1);

    }


    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();

        String path = Environment.getExternalStorageDirectory() + File.separator
                + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;

        File newFile = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
        startActivityForResult(intent, PHOTO_CODE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PHOTO_CODE:
                if (resultCode == RESULT_OK) {
                    String dir = Environment.getExternalStorageDirectory() + File.separator
                            + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
                    decodeBitmap(dir);
                }
                break;

            case SELECT_PICTURE:
                if (resultCode == RESULT_OK) {
                    Uri path = data.getData();
                    imageView.setImageURI(path);
                }


                break;

        }

    }


    private void decodeBitmap(String dir) {

        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);



        imageView.setImageBitmap(bitmap);
        imageView = (ImageView) findViewById(R.id.setPicture);
        final TextView txtRes = (TextView) findViewById(R.id.textView);
        final TextView txtRes2 = (TextView) findViewById(R.id.txt2);
        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                try{
                    final Bitmap bmpResult = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    // Obtener las coordenadas sobre el imageView
                    float eventX = event.getX();
                    float eventY = event.getY();
                    float[] eventXY = new float[] {eventX, eventY};

                    Matrix invertMatrix = new Matrix();
                    ((ImageView)view).getImageMatrix().invert(invertMatrix);

                    invertMatrix.mapPoints(eventXY);
                    int x = Integer.valueOf((int)eventXY[0]);
                    int y = Integer.valueOf((int)eventXY[1]);

                    //Limitar x, y dentro del bitmap
                    if(x < 0){
                        x = 0;
                    }else if(x > bmpResult.getWidth()-1){
                        x = bmpResult.getWidth()-1;
                    }
                    if(y < 0){
                        y = 0;
                    }else if(y > bmpResult.getHeight()-1){
                        y = bmpResult.getHeight()-1;
                    }
                    // Obtener el codigo decimal del pixel
                    int pixel = bmpResult.getPixel(x-50, y);
                    int rval = Color.red(pixel);
                    int gval = Color.green(pixel);
                    int bval = Color.blue(pixel);
                    int iColor = Color.argb(255, rval, gval, bval);


                    txtRes.setBackgroundColor(iColor);




                    // Calculo el CMYK Esto es de YAPA
                    double rr = rval/ 255.0000;
                    double gg = gval / 255.0000;
                    double bb = bval / 255.0000;
                    double black = 1.0000 - Math.max(rr,Math.max(gg,bb));
                    double  cyan = (1-rr-black) / (1-black);
                    double magenta = (1-gg-black) / (1-black);
                    double yellow = (1-bb-black) / (1-black);




                    hexValue = String.format("#%06X",  (0xFFFFFF & iColor), cyan, magenta, yellow, black);
                    ColorUtils colorts = new ColorUtils();
                    colorName = colorts.getColorNameFromHex(hexValue);

                    int[] rgb = colorts.hexToRGB(hexValue);
                    rgbValue = "RGB(" + rgb[0] + "," + rgb[1] +  ", " + rgb[2] + ")";


                    txtRes2.setText("Color: " + colorName + " \nHex:" + hexValue + " \n" + rgbValue);//(String.format("COLOR: #%06X",  (0xFFFFFF & iColor), cyan, magenta, yellow, black));
                    favButton.setEnabled(true);





                }catch(Exception e){
                    System.out.println("Error en pixel" + e.toString());


                }


                return true;
            }

        });

    }

}
