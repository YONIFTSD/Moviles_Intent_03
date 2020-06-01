package com.example.a03intent_receptor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import android.widget.ImageView;
import android.graphics.Bitmap;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = ":";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent  receptorIntent = getIntent();
        String action = receptorIntent.getAction();
        String tipo = receptorIntent.getType();
        if (Intent.ACTION_SEND.equals(action) && tipo != null ){
            //if ("text/plain".equals(tipo)){
                //ManipularTexto(receptorIntent);
            ((TextView)findViewById(R.id.edt_codigo)).setText(receptorIntent.getStringExtra("codigo"));
            ((TextView)findViewById(R.id.edt_nombre)).setText(receptorIntent.getStringExtra("nombres"));
            ((TextView)findViewById(R.id.edt_celular)).setText(receptorIntent.getStringExtra("celular"));

            Bitmap bitmap = (Bitmap) receptorIntent.getParcelableExtra("Imagen");
            ImageView icono = (ImageView) findViewById(R.id.img_photo);
            icono.setImageBitmap(bitmap);

            if(receptorIntent.getExtras().get(Intent.EXTRA_STREAM) != null){
                Uri img = receptorIntent.getParcelableExtra(Intent.EXTRA_STREAM);
                ImageView imageView =  findViewById(R.id.img_photo);
                imageView.setImageURI(img);
            }
            //}
        }
    }
    private void ManipularTexto(Intent intent){

        String texto = intent.getStringExtra(Intent.EXTRA_TEXT);
        if(texto != null){
            ((TextView)findViewById(R.id.edt_codigo)).setText(texto);
            Toast.makeText(getBaseContext(),texto,Toast.LENGTH_LONG).show();
        }
    }

}
