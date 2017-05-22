package com.developer.c_alicio.sessaodatarde;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DefaultActivity extends AppCompatActivity {

    Usuario usuario;

    Filmes filmes;

    Button btEnviarFilme;
    EditText etFilme;
    ListView mFilmesList;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defaut);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://testesessaodatarde.firebaseio.com/filmes");


        btEnviarFilme = (Button)findViewById(R.id.btEnviarFilme);
        etFilme = (EditText)findViewById(R.id.etFilme);
        mFilmesList = (ListView) findViewById(R.id.lvFilmes);


        //recebendo Parâmetros
//        Intent mIntent = getIntent();

//        String login = mIntent.getStringExtra("login");
//        String senha = mIntent.getStringExtra("senha");
//        Toast.makeText(getApplicationContext(), "Login: " + login + " / " + "Senha: " + senha, Toast.LENGTH_LONG).show();


        //Recebendo Objetos
        Bundle obj = getIntent().getBundleExtra("obj");
        if(obj != null){
            usuario = (Usuario)obj.getSerializable("usuario");
            filmes = (Filmes)obj.getSerializable("filmes");
        }

        // Adapter normal
        // ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
        // getApplicationContext(),
        // android.R.layout.simple_list_item_1,
        // android.R.id.text1,
        // filmes.getFilmes()
        // );
        // mFilmesList.setAdapter(mAdapter);


        mFilmesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });


        //Adapter Personalizado
        //MeuAdapter meuAdapter = new MeuAdapter();
        //mFilmesList.setAdapter(meuAdapter);

        //Adapter FireBase
        FirebaseListAdapter<String> adapter = new FirebaseListAdapter<String>(
                this, String.class, android.R.layout.simple_list_item_1, myRef) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView txtFilme = (TextView)v.findViewById(android.R.id.text1);
                txtFilme.setText(model);
            }
        };

        mFilmesList.setAdapter(adapter);
    }

    public void enviarFilmeDataBase(View view){

        myRef.push().setValue(etFilme.getText().toString());

        // Criar chave e valor
        // DatabaseReference ref = myRef.child("aladim");
        // ref.setValue(etFilme.getText().toString());

        etFilme.setText("");
    }

    public class MeuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return filmes.getCapas().length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.fragment,null);

            ImageView capa = (ImageView)convertView.findViewById(R.id.imgCapa);
            TextView titulo = (TextView)convertView.findViewById(R.id.tvTitulo);
            TextView descricao = (TextView)convertView.findViewById(R.id.tvDescricao);

            capa.setImageResource(filmes.getCapas()[position]);
            titulo.setText(filmes.getTitulo()[position]);
            descricao.setText(filmes.getDescricao()[position]);

            return convertView;

        }
    }


}
