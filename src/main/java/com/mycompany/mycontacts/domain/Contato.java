/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mycontacts.domain;

public class Contato {

    private final Integer id;
    private final String nome;
    private final String telefone;
    private final String email;

    public Contato(Integer id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = validarCampo(nome, "Nome");
        this.telefone = validarCampo(telefone, "Telefone");
        this.email = validarCampo(email, "Email");
    }

    public Contato(String nome, String telefone, String email) {
        this(null, nome, telefone, email);
    }

    private String validarCampo(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " não pode ser vazio.");
        }
        return valor.trim();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEmpresa() {
        return "";
    }

    @Override
    public String toString() {
        return String.format(
            "Nome: %s | Telefone: %s | Email: %s",
            nome, telefone, email
        );
    }
}

