package com.dannicleo.cielo.desafio1.entity;

import com.dannicleo.cielo.desafio1.validators.PessoaFisica;
import com.dannicleo.cielo.desafio1.validators.PessoaJuridica;
import jakarta.validation.constraints.*;

import java.util.UUID;

public class SuperClient {

    private UUID uuid;

    @NotBlank(message = "O tipo não pode estar em branco")
    @Size(max = 1, message = "O tipo deve ter no máximo 1 caracter")
    @Pattern(regexp = "[FJ]", message = "O campo tipo deve ser 'F' para Pessoa Física ou 'J' para Pessoa Jurídica")
    private String tipo;

    @NotBlank(message = "O MCC não pode estar em branco")
    @Size(max = 4)
    private String mcc;

    @NotBlank(message = "O campo email não pode estar em branco")
    @Email(message = "O Email deve ser válido")
    private String email;

    @NotBlank(groups = PessoaJuridica.class, message = "O campo CNPJ não pode estar em branco")
    @Pattern(groups = PessoaJuridica.class, regexp = "0\\d{13}", message = "O CNPJ deve conter 14 dígitos formatados com zeros à esquerda")
    private String cnpj;
    @NotBlank(groups = PessoaJuridica.class, message = "O campo razão social não pode estar em branco")
    @Size(groups = PessoaJuridica.class,max = 50, message = "O campo razão social deve ter no máximo 50 caracteres")
    private String razaoSocial;

    @NotBlank(groups = PessoaJuridica.class, message = "O campo CPF do contato não pode estar em branco")
    @Pattern(groups = PessoaJuridica.class, regexp = "\\d{11}", message = "O CPF do contato deve conter 11 dígitos numéricos")
    private String cpfContato;
    @NotBlank(groups = PessoaJuridica.class, message = "O campo nome do contato não pode estar em branco")
    @Size(groups = PessoaJuridica.class,max = 50, message = "O campo nome do contato deve ter no máximo 50 caracteres")
    private String nomeContato;

    @NotBlank(groups = PessoaFisica.class, message = "O campo nome não pode estar em branco")
    @Size(groups = PessoaFisica.class, max = 50)
    private String nome;

    @NotEmpty(groups = PessoaFisica.class, message = "O campo CPF não pode estar em branco")
    @Pattern(groups = PessoaFisica.class, regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
    private String cpf;

    public SuperClient( String tipo, String mcc, String email, String cnpj, String razaoSocial, String cpfContato, String nomeContato, String nome, String cpf) {
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

    public SuperClient() {
    }

    public static SuperClient.Builder builder(){
        return new SuperClient.Builder();
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

    public static class Builder {
        private SuperClient superClient;

        public Builder() {
            superClient = new SuperClient();
        }

        public SuperClient.Builder uuid(UUID uuid) {
            superClient.uuid = uuid;
            return this;
        }

        public SuperClient.Builder tipo(String tipo) {
            superClient.tipo = tipo;
            return this;
        }

        public SuperClient.Builder mcc(String mcc) {
            superClient.mcc = mcc;
            return this;
        }

        public SuperClient.Builder email(String email) {
            superClient.email = email;
            return this;
        }

        public SuperClient.Builder cnpj(String cnpj) {
            superClient.cnpj = cnpj;
            return this;
        }

        public SuperClient.Builder razaoSocial(String razaoSocial) {
            superClient.razaoSocial = razaoSocial;
            return this;
        }

        public SuperClient.Builder cpfContato(String cpfContato) {
            superClient.cpfContato = cpfContato;
            return this;
        }

        public SuperClient.Builder nomeContato(String nomeContato) {
            superClient.nomeContato = nomeContato;
            return this;
        }

        public SuperClient.Builder nome(String nome) {
            superClient.nome = nome;
            return this;
        }

        public SuperClient.Builder cpf(String cpf) {
            superClient.cpf = cpf;
            return this;
        }

        public SuperClient build() {
            return superClient;
        }
    }


}
