package com.example.retrofit;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.retrofit.api.APIClient;
import com.example.retrofit.api.APIInterface;
import com.example.retrofit.databinding.ActivityMainBinding;
import com.example.retrofit.dto.UserSingle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        APIInterface api = APIClient.getClient().create(APIInterface.class);

        api.find(2).enqueue(new Callback<UserSingle>() {
            @Override
            public void onResponse(Call<UserSingle> call, Response<UserSingle> response) {
                Log.w("onResponse", response.body().data.toString());
            }

            @Override
            public void onFailure(Call<UserSingle> call, Throwable t) {
                Log.w("onFailure", t.getLocalizedMessage());
                call.cancel();
            }
        });
    }
}