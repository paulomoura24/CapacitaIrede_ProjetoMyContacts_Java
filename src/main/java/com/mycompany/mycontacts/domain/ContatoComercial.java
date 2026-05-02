/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mycontacts.domain;

public class ContatoComercial extends Contato {
    private final String empresa;

    public ContatoComercial(Integer id, String nome, String telefone, String email, String empresa) {
        super(id, nome, telefone, email);
        this.empresa = empresa == null ? "" : empresa.trim();
    }

    public ContatoComercial(String nome, String telefone, String email, String empresa) {
        this(null, nome, telefone, email, empresa);
    }

    public String getEmpresa() {
        return empresa;
    }

    @Override
    public String toString() {
        return super.toString() + ", Empresa: " + empresa;
    }
}
