/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycontacts.model;

import java.util.Objects;

public class Contato {

    private final String nome;
    private final String telefone;
    private final String email;

    public Contato(String nome, String telefone, String email) {
        this.nome = validarCampo(nome, "Nome");
        this.telefone = validarCampo(telefone, "Telefone");
        this.email = validarCampo(email, "Email");
    }

    private String validarCampo(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " não pode ser vazio.");
        }
        return valor.trim();
    }

    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format(
            "Nome: %s | Telefone: %s | Email: %s",
            nome, telefone, email
        );
    }
}
