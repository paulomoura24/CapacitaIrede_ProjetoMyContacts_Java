/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycontacts.model;

public class ContatoComercial extends Contato {
    private final String empresa;

    public ContatoComercial(String nome, String telefone, String email, String empresa) {
        super(nome, telefone, email);
        this.empresa = empresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    @Override
    public String toString() {
        return super.toString() + ", Empresa: " + empresa;
    }
}