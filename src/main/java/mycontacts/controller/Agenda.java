/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycontacts.controller;

import mycontacts.model.Contato;
import mycontacts.model.Buscavel;
import mycontacts.exceptions.ContatoNaoEncontradoException;
import java.util.ArrayList;

public class Agenda implements Buscavel {
    private final ArrayList<Contato> contatos = new ArrayList<>();

    public void adicionarContato(Contato contato) {
        contatos.add(contato);
    }

    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
        } else {
            contatos.forEach(System.out::println);
        }
    }

    @Override
    public Contato buscarPorNome(String nome) throws ContatoNaoEncontradoException {
        for (Contato contato : contatos) {
            if (!contato.getNome().toLowerCase().contains(nome.toLowerCase())) {
            } else {
                return contato;
            }
        }
        throw new ContatoNaoEncontradoException("Contato não encontrado: " + nome);
    }

    public void removerContato(String nome) throws ContatoNaoEncontradoException {
        Contato contato = buscarPorNome(nome);
        contatos.remove(contato);
        System.out.println("Contato removido com sucesso!");
    }
}
