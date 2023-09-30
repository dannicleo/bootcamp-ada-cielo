package com.dannicleo.cielo.desafio1.validators;

import jakarta.validation.constraints.*;

import java.util.UUID;

public class ClientPessoaFisicaValidator {
    private UUID uuid;

    @NotBlank(message = "O tipo não pode estar em branco")
    @Size(max = 1, message = "O tipo deve ter no máximo 1 caracter")
    @Pattern(regexp = "[FJ]", message = "O campo tipo deve ser 'F' para Pessoa Física ou 'J' para Pessoa Jurídica")
    private String tipo;

    @NotBlank(message = "O MCC não pode estar em branco")
    @Size(min = 4, max = 4)
    private String mcc;

    @NotBlank(message = "O campo email não pode estar em branco")
    @Email(message = "O Email deve ser válido")
    private String email;

    @NotBlank(message = "O campo nome não pode estar em branco")
    @Size(max = 50)
    private String nome;

    @NotEmpty( message = "O campo CPF não pode estar em branco")
    @Pattern( regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
    private String cpf;

    public ClientPessoaFisicaValidator(String tipo, String mcc, String email, String nome, String cpf) {
        this.tipo = tipo;
        this.mcc = mcc;
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
    }

    public ClientPessoaFisicaValidator() {
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

}















