package com.example.hernanlopez.proyecto;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.io.File;
import java.sql.SQLOutput;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends ActionBarActivity {

    private String APP_DIRECTORY = "myPictureApp/";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME;
    private String colorName;
    private String hexValue;
    private String rgbValue;


    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;

    private ImageView imageView;
    private Button favButton;

    Realm realm = null;// = Realm.getDefaultInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm.init(this);
        realm  = Realm.getDefaultInstance();
        realm.beginTransaction();


        TEMPORAL_PICTURE_NAME = Environment.getExternalStorageDirectory() + "/NOMBRE_ARCHIVO.png";

        imageView = (ImageView) findViewById(R.id.setPicture);
        Button button = (Button) findViewById(R.id.buttonImage);
        favButton = (Button) findViewById(R.id.pinnedButton);

        favButton.setEnabled(false);

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressdialog = new ProgressDialog(MainActivity.this);

                progressdialog.setMessage("Please Wait....");
                progressdialog.show();
                progressdialog.setCancelable(false);
                //REALM

                realm.commitTransaction();

                realm.beginTransaction();
                HistoryRealm user = realm.createObject(HistoryRealm.class); // Create a new object

                user.setColorName(colorName);
                user.setFav(false);
                user.setHexValue(hexValue);
                user.setRgbValue(rgbValue);
                realm.commitTransaction();

                progressdialog.dismiss();
                
                RealmResults<HistoryRealm> r = realm.where(HistoryRealm.class).findAll();

                for (HistoryRealm a: r){
                    Log.d("TEST", a.getColorName());
                }


                //End - Realm
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Selecciona una Opcion");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int seleccion) {
                        if (options[seleccion] == "Tomar foto") {
                            openCamera();
                        } else if (options[seleccion] == "Elegir de galeria") {
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                        } else if (options[seleccion] == "Cancelar") {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

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
