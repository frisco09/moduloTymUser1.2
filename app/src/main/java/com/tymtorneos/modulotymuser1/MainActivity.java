package com.tymtorneos.modulotymuser1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tymtorneos.modulotymuser1.retrofit.RetrofitInstance;
import com.tymtorneos.modulotymuser1.retrofit.User;
import com.tymtorneos.modulotymuser1.retrofit.UserAdapter;
import com.tymtorneos.modulotymuser1.retrofit.UserList;
import com.tymtorneos.modulotymuser1.retrofit.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private UserAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /** Create handle for the RetrofitInstance interface*/
        UserService service = RetrofitInstance.getRetrofitInstance().create(UserService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call <List<User>> call = service.getUserData();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback <List<User>>() {
            @Override
            public void onResponse(Call <List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                generateUserList(users);
            }

            @Override
            public void onFailure(Call <List<User>> call, Throwable t) {
                createAvisoDialogo(t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: ", Toast.LENGTH_LONG).show();
            }
        });
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateUserList(List<User> userArrayList) {
        recyclerView = findViewById(R.id.recycler_view_user_list);
        adapter = new UserAdapter(userArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public AlertDialog createAvisoDialogo(String msj) {
        final AlertDialog b;
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        LayoutInflater inflater = MainActivity.this.getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_error, null);

        builder.setView(v);

        Button btnOk = (Button) v.findViewById(R.id.btnOk);
        TextView mensaje = (TextView) v.findViewById(R.id.txbAviso);
        mensaje.setText(msj);
        b = builder.create();
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.show();

        btnOk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // partido gratis
                        b.dismiss();
                    }
                }

        );

        return builder.create();
    }
}



/*
*     private void getUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vps-1542295-x.dattaweb.com/")//urlapi
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService postService = retrofit.create(UserService.class);
        Call<List<User>> call = postService.getPost();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                for(User user : response.body()) {
                    usuarios.add(user.getNombre());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }
* */
