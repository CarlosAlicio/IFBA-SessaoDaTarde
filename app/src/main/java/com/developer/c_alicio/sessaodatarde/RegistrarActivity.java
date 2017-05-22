package com.developer.c_alicio.sessaodatarde;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener{

    Button btRegistrar;
    EditText etEmail;
    EditText etPassword;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        btRegistrar = (Button)findViewById(R.id.btRegistrar);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);

        btRegistrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btRegistrar){
            registrarUsuario();
        }
    }

    private void registrarUsuario() {

        String email = etEmail.getText().toString();
        String senha = etPassword.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)){
            Toast.makeText(getApplicationContext(), "Os Campos E-mail e Senha São Obrigatórios!!", Toast.LENGTH_LONG).show();
        }

        progressDialog.setMessage("Registrando, Aguarde...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "RegistroEfetuado com Suceso!!", Toast.LENGTH_LONG).show();
                            progressDialog.cancel();
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Falha ao tentar Efetuara o Registro. Por Favor Tete Novamente!!", Toast.LENGTH_LONG).show();
                    }
                });

    }

}
