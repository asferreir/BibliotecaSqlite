package br.edu.unp.biblioteca.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import br.edu.unp.biblioteca.R;
import br.edu.unp.biblioteca.config.ConfiguracaoFirebase;
import br.edu.unp.biblioteca.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button botaoCadastrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = (EditText) findViewById(R.id.edit_cadastro_nome);
        email = (EditText) findViewById(R.id.edit_cadastro_email);
        senha = (EditText) findViewById(R.id.edit_cadastro_senha);
        botaoCadastrar = (Button) findViewById(R.id.btnCadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());

                cadastrarUsuario();
            }
        });
    }

    public void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CadastroUsuarioActivity.this,
                            "Usuario Cadastrado com Sucesso", Toast.LENGTH_LONG).show();
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId(usuarioFirebase.getUid());
                    usuario.salvar();
                    autenticacao.signOut();;
                    finish();

                }else {
                    String erroExcecao = "";

                    try {
                        throw Objects.requireNonNull(task.getException());

                    }catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao  = "Digite uma senha mais forte, contendo mais caracteres e com letras e numeros";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao  = "O email digitado é invalido, digite um novo email";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao  = "O email ja esta em uso.";
                    } catch (Exception e) {
                        erroExcecao = "Erro ao Cadastrado com Usuario";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroUsuarioActivity.this,
                            "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
