package com.dannicleo.cielo.desafio2.validators;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class ClientPessoaJuridicaValidator {

    private UUID uuid;

    @NotBlank(message = "O tipo não pode estar em branco")
    @Size(max = 1, message = "O tipo deve ter no máximo 1 caracter")
    @Pattern(regexp = "[FJ]", message = "O campo tipo deve ser 'F' para Pessoa Física ou 'J' para Pessoa Jurídica")
    private String tipo;

    @NotBlank(message = "O MCC não pode estar em branco")
    @Size(min = 4, max = 4, message = "O MCC deve ter 4 digitos")
    private String mcc;

    @NotBlank(message = "O campo email não pode estar em branco")
    @Email(message = "O Email deve ser válido")
    private String email;

    @NotBlank(message = "O campo CNPJ não pode estar em branco")
    @Pattern(regexp = "0\\d{13}", message = "O CNPJ deve conter 14 dígitos formatados com zeros à esquerda")
    private String cnpj;

    @NotBlank(message = "O campo razão social não pode estar em branco")
    @Size(max = 50, message = "O campo razão social deve ter no máximo 50 caracteres")
    private String razaoSocial;

    @NotBlank(message = "O campo CPF do contato não pode estar em branco")
    @Pattern(regexp = "\\d{11}", message = "O CPF do contato deve conter 11 dígitos numéricos")
    private String cpfContato;

    @NotBlank(message = "O campo nome do contato não pode estar em branco")
    @Size(max = 50, message = "O campo nome do contato deve ter no máximo 50 caracteres")
    private String nomeContato;


    public ClientPessoaJuridicaValidator(String tipo, String mcc, String email,
                                         String cnpj, String razaoSocial, String
                                                 cpfContato, String nomeContato) {
        this.tipo = tipo;
        this.mcc = mcc;
        this.email = email;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.cpfContato = cpfContato;
        this.nomeContato = nomeContato;
    }

    public ClientPessoaJuridicaValidator() {
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
}
