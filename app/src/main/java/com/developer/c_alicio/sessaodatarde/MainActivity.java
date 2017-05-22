package com.developer.c_alicio.sessaodatarde;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btnEntrar, btRegistrar;
    EditText etLogin;
    EditText etSenha;

    Usuario usuario;

    Filmes filmes;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();


        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        btRegistrar = (Button)findViewById(R.id.btRegistrar);
        etLogin = (EditText)findViewById(R.id.etLogin);
        etSenha = (EditText)findViewById(R.id.etPassword);

        filmes = new Filmes();
    }

    public void registrar(View view){
        Intent mIntent = new Intent(MainActivity.this, RegistrarActivity.class);
        startActivity(mIntent);
    }


    // transitando entre activity
    public void entrar(View view){

        usuario = new Usuario(etLogin.getText().toString(),etSenha.getText().toString());

        firebaseAuth.signInWithEmailAndPassword(usuario.getLogin(), usuario.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Os Campos Login e Senha São Obrigatórios!!", Toast.LENGTH_LONG).show();
                        }
                        else{


                                Intent mIntent = new Intent(MainActivity.this, DefaultActivity.class);

                                //passando parâmetos
                                //mIntent.putExtra("login", etLogin.getText().toString());
                                //mIntent.putExtra("senha", etSenha.getText().toString());

                                //passando parâmetos (objetos)
                                Bundle obj = new Bundle();
                                obj.putSerializable("usuario", usuario);
                                obj.putSerializable("filmes", filmes);
                                mIntent.putExtra("obj",obj);

                                startActivity(mIntent);

                        }

                    }
                });


    }
}
