package br.edu.unp.biblioteca.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import br.edu.unp.biblioteca.R;
import br.edu.unp.biblioteca.model.Livro;

public class AddBookActivity extends AppCompatActivity {

    private EditText titulo;
    private EditText autor;
    private EditText editora;
    private Button botaoCadastrarLivro;
    private Livro livro;
    private DatabaseReference referenciaFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        titulo = (EditText) findViewById(R.id.editTitulo);
        autor = (EditText) findViewById(R.id.editAutor);
        editora = (EditText) findViewById(R.id.editEditora);
        botaoCadastrarLivro = (Button) findViewById(R.id.btnCadastrarLivro);

        botaoCadastrarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                livro = new Livro();
                livro.setTitulo(titulo.getText().toString());
                livro.setAutor(autor.getText().toString());
                livro.setEditora(editora.getText().toString());
                livro.salvar();
                Toast.makeText(AddBookActivity.this, "Livro Cadastrado com Sucesso!", Toast.LENGTH_LONG).show();
                finish();

            }
        });
    }
}
