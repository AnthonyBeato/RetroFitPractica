package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retrofit.api.APIClient;
import com.example.retrofit.api.APIInterface;
import com.example.retrofit.dto.UserSingle;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        APIInterface api = APIClient.getClient().create(APIInterface.class);


        Intent intent = getIntent();




        int numero = intent.getIntExtra("id",1);
        api.find(numero).enqueue(new Callback<UserSingle>() {
            @Override
            public void onResponse(Call<UserSingle> call, Response<UserSingle> response) {
                Log.w("onResponse", response.body().data.toString());

                TextView nombre = findViewById(R.id.userName);
                TextView apellido = findViewById(R.id.userLastName);
                TextView correo = findViewById(R.id.userEmail);
                ImageView avatar = findViewById(R.id.userImage);

                nombre.setText(response.body().getData().getFirstName());
                apellido.setText(response.body().getData().getLastName());
                correo.setText(response.body().getData().getEmail());
                try {
                    URL url = new URL(response.body().getData().getAvatar());
                    // Bitmap mapa = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                  //  avatar.setImageBitmap(mapa);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }

            @Override
            public void onFailure(Call<UserSingle> call, Throwable t) {
                Log.w("onFailure", t.getLocalizedMessage());
                call.cancel();
            }
        });
    }
}