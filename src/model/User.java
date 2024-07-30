package model;

public class User {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String senhaHash;

    public User(String nome, String telefone, String email, String cpf, String senhaHash) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }

        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.senhaHash = senhaHash;
    }

    // Getters e setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }
        this.cpf = cpf;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }
}
