package br.edu.unp.biblioteca.model;

import com.google.firebase.database.DatabaseReference;

import br.edu.unp.biblioteca.config.ConfiguracaoFirebase;

public class Livro {
    private String id;
    private String titulo;
    private String autor;
    private String editora;

    public Livro() {
    }

    public void salvar(){
        DatabaseReference referenciaFireabase = ConfiguracaoFirebase.getFirebase();
        referenciaFireabase.child("livros").push().setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
}
