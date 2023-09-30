package com.dannicleo.cielo.desafio1.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "CLIENTES")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID uuid;
    private String tipo;
    private String mcc;
    private String email;

    private String cnpj;
    private String razaoSocial;
    private String cpfContato;
    private String nomeContato;

    private String nome;
    private String cpf;

    private Client(String tipo, String mcc, String email,
                  String cnpj, String razaoSocial, String cpfContato,
                  String nomeContato, String nome, String cpf) {
        this.tipo = tipo;
        this.mcc = mcc;
        this.email = email;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.cpfContato = cpfContato;
        this.nomeContato = nomeContato;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Client() {
    }

    public static Client.Builder builder(){
        return new Builder();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCpfContato() {
        return cpfContato;
    }

    public void setCpfContato(String cpfContato) {
        this.cpfContato = cpfContato;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "uuid=" + uuid +
                ", tipo='" + tipo + '\'' +
                ", mcc='" + mcc + '\'' +
                ", email='" + email + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", cpfContato='" + cpfContato + '\'' +
                ", nomeContato='" + nomeContato + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    public static class Builder {
        private Client client;

        public Builder() {
            client = new Client();
        }

        public Builder uuid(UUID uuid) {
            client.uuid = uuid;
            return this;
        }

        public Builder tipo(String tipo) {
            client.tipo = tipo;
            return this;
        }

        public Builder mcc(String mcc) {
            client.mcc = mcc;
            return this;
        }

        public Builder email(String email) {
            client.email = email;
            return this;
        }

        public Builder cnpj(String cnpj) {
            client.cnpj = cnpj;
            return this;
        }

        public Builder razaoSocial(String razaoSocial) {
            client.razaoSocial = razaoSocial;
            return this;
        }

        public Builder cpfContato(String cpfContato) {
            client.cpfContato = cpfContato;
            return this;
        }

        public Builder nomeContato(String nomeContato) {
            client.nomeContato = nomeContato;
            return this;
        }

        public Builder nome(String nome) {
            client.nome = nome;
            return this;
        }

        public Builder cpf(String cpf) {
            client.cpf = cpf;
            return this;
        }

        public Client build() {
            return client;
        }
    }

}
