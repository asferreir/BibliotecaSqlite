package br.edu.unp.biblioteca.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import br.edu.unp.biblioteca.R;
import br.edu.unp.biblioteca.config.ConfiguracaoFirebase;


public class MainActivity extends AppCompatActivity {

    private ImageView botaoPesquisar;
    private ImageView botaoAdicionar;
    private ImageView botaoEmprestar;
    private ImageView botaoDevolver;
    private FirebaseAuth usuarioAutenticacao;
    private Toolbar toolbar;
    //private Button botaoSair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        botaoPesquisar = (ImageView) findViewById(R.id.btnPesquisar);
        botaoPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SearchBookActivity.class));
            }
        });

        botaoAdicionar = (ImageView) findViewById(R.id.btnAdicionar);
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddBookActivity.class));
            }
        });

        botaoEmprestar = (ImageView) findViewById(R.id.btnEmprestar);
        botaoEmprestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GiveBackBookActivity.class));
            }
        });

        botaoDevolver = (ImageView) findViewById(R.id.btnDevolver);
        botaoDevolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GiveOutBookActivity.class));
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar( toolbar );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_sair){
            deslogarUsuario();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deslogarUsuario(){
        usuarioAutenticacao.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
