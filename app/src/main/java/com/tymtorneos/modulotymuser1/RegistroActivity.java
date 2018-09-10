package com.tymtorneos.modulotymuser1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity {

    Button _loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        _loginLink = (Button)findViewById(R.id.btIniciarSesion);

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LogActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_out ,R.anim.push_left_in);
            }
        });
    }
}
