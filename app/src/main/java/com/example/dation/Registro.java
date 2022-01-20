package com.example.dation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.IconCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
EditText t_name,t_email,t_pass;
Button b_insertar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_registro);

        t_name=findViewById(R.id.txtname);
        t_email=findViewById(R.id.txtemail);
        t_pass=findViewById(R.id.txtpassword);
        b_insertar=findViewById(R.id.btnregistro);

        b_insertar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                insertaDatos();
            }
        });

    }

    private void insertaDatos() {
        final String name=t_name.getText().toString().trim();
        final String email=t_email.getText().toString().trim();
        final String password=t_pass.getText().toString().trim();


        final ProgressDialog progressDialog=new ProgressDialog( this);
        progressDialog.setMessage("cargando");

        if (name.isEmpty()){
            t_name.setError("complete los campos");
            return;
        }else if (email.isEmpty()){
            t_email.setError("complete los campos");
            return;
        }

        else{
            progressDialog.show();
            StringRequest request =new StringRequest(Request.Method.POST, "https://sxrcotjo.lucusvirtual.es/insertar.php/", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("registro correctamente")) {
                        Toast.makeText(Registro.this, "datos insertados", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        Intent intent = new Intent(Registro.this, Login.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Registro.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Toast.makeText(Registro.this, "No se pudo insertar", Toast.LENGTH_SHORT).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Registro.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String>params= new HashMap<>();
                    params.put("name",name);
                    params.put("email",email);
                    params.put("password", password);
                    return params;
                }
            };

            RequestQueue requestQueue= Volley.newRequestQueue(Registro.this);
            requestQueue.add(request);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void OpenSignPage(View view) { startActivity(new Intent(Registro.this,Login.class));}
}