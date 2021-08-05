package com.example.multiscreenswithlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    public static ArrayList<Client> clients=new ArrayList<>();
    public static String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillClients();
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.extUsername);
        password=findViewById(R.id.extPassword);
        login=findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(verify(user,pass))
                {
                    UserName=user;
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getBaseContext(),"Invalid username or password",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void fillClients()
    {
        clients.add(new Client("John","Brampton","user1","123456"));
        clients.add(new Client("Suzan","Toronto","user2","123456"));
    }
    //method to verify the user name and password
    public boolean verify(String user,String pass)
    {
        for(Client cln:clients)
            if(cln.getUsername().equalsIgnoreCase(user) && cln.getPassword().equals(pass))
                return true;
        return false;
    }
}