package com.example.retrofit;

import android.content.res.Configuration;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.adapters.UserAdapterReyclerView;
import com.example.retrofit.adapters.UserListAdapter;
import com.example.retrofit.api.APIClient;
import com.example.retrofit.api.APIInterface;
import com.example.retrofit.databinding.ActivityMainBinding;
import com.example.retrofit.dto.User;
import com.example.retrofit.dto.UserList;
import com.example.retrofit.dto.UserSingle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    private List<User> listUsers = new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        APIInterface api = APIClient.getClient().create(APIInterface.class);


        CardView card = findViewById(R.id.card);
      /*  api.find(2).enqueue(new Callback<UserSingle>() {
            @Override
            public void onResponse(Call<UserSingle> call, Response<UserSingle> response) {
                Log.w("onResponse", response.body().data.toString());
            }

            @Override
            public void onFailure(Call<UserSingle> call, Throwable t) {
                Log.w("onFailure", t.getLocalizedMessage());
                call.cancel();
            }
        }); */

       api.findAll().enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
               System.out.println(response.body().data);



                listUsers = response.body().getData();
                System.out.println(listUsers.get(0).getFirstName());


                RecyclerView recyclerView = binding.recycler;

                int spanCount = 2;

                if (MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    spanCount = 4;
                }
                System.out.println(listUsers);

                recyclerView.setHasFixedSize(true);
                //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), spanCount));

                 UserListAdapter adapter = new UserListAdapter(listUsers);
                //Obtener el listado de usuarios de la API\

                System.out.println(adapter.getItemCount());
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
             //   call.cancel();
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
             //   Log.w("onFailure", t.getLocalizedMessage());
                System.out.println("Error");
                call.cancel();
            }
        });


        //Recycle


    }




}