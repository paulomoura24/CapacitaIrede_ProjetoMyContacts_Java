/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mycontacts.service;

import com.mycompany.mycontacts.exception.ContatoNaoEncontradoException;
import com.mycompany.mycontacts.domain.Buscavel;
import com.mycompany.mycontacts.domain.Contato;
import com.mycompany.mycontacts.repository.ContatoMemoryRepository;
import java.util.List;

public class Agenda implements Buscavel {
    private final ContatoMemoryRepository<Contato> repositorio = new ContatoMemoryRepository<>();

    public void adicionarContato(Contato contato) {
        repositorio.adicionar(contato);
    }

    public List<Contato> listarContatos() {
        return repositorio.listar();
    }

    @Override
    public Contato buscarPorNome(String nome) throws ContatoNaoEncontradoException {
        return repositorio.buscarPorNome(nome)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ContatoNaoEncontradoException("Contato não encontrado: " + nome));
    }

    public void removerContato(Contato contato) {
        repositorio.remover(contato);
    }
}

