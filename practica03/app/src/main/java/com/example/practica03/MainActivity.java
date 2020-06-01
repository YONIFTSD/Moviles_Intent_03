package com.example.practica03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.net.Uri;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
public class MainActivity extends AppCompatActivity {

    private int CAMERA_PIC_REQUEST = 1;
    private  EditText edtnombre, edtcelular, edtCodigo;
    public String strLatitude,strLongitude;
    private Uri uriImage;
    private static final String TAG = ":";
    Bitmap ImageCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_camera = (Button) findViewById(R.id.btn_camera);

        //componentes
        edtCodigo = findViewById(R.id.txt_codigo);
        edtnombre = findViewById(R.id.txt_nombres);
        edtcelular = findViewById(R.id.txt_nombres);



        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent CameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(CameraIntent,CAMERA_PIC_REQUEST);
            }
        });


        ((Button)findViewById(R.id.btn_envio)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///Intent enviarIntent = new Intent();
                //enviarIntent.setAction(Intent.ACTION_SEND);
                //enviarIntent.putExtra(Intent.EXTRA_TEXT, ((EditText)findViewById(R.id.txt_nombres)).getText().toString());


                sendToReceptorApp(uriImage);
                //enviarIntent.setType("*/*");
                //if (enviarIntent.resolveActivity(getPackageManager()) != null){
                  //  startActivity(Intent.createChooser(enviarIntent,getResources().getText(R.string.chooser_msj)));
                //}
            }
        });

    }

    private void sendToReceptorApp(Uri uriToImage){
        Intent enviarIntent = new Intent();
        enviarIntent.setAction(Intent.ACTION_SEND);
        enviarIntent.putExtra("codigo",((EditText)findViewById(R.id.txt_codigo)).getText().toString());
        enviarIntent.putExtra("nombres",((EditText)findViewById(R.id.txt_nombres)).getText().toString());
        enviarIntent.putExtra("celular",((EditText)findViewById(R.id.txt_celular)).getText().toString());
        enviarIntent.putExtra("Imagen", ImageCamera);
        enviarIntent.setType("*/*");
        if (enviarIntent.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(enviarIntent,getResources().getText(R.string.chooser_msj)));
        }else{
            Log.d(TAG,"no se resolvio el envio de datos ");
        }



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC_REQUEST){
            if (resultCode == RESULT_OK){
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                ImageView img_camera = (ImageView)findViewById(R.id.img_camera);
                img_camera.setImageBitmap(bitmap);
                ImageCamera = bitmap;
                uriImage = data.getData();
                //Log.d(TAG,uriImage.toString());


            }
        }
    }


}
