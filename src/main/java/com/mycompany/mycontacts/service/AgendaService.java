package com.mycompany.mycontacts.service;

import com.mycompany.mycontacts.domain.Contato;
import com.mycompany.mycontacts.repository.ContatoDAO;
import com.mycompany.mycontacts.validation.ValidadorEmail;

import java.util.List;

public class AgendaService {

    private final ContatoDAO contatoDAO;

    public AgendaService() {
        this(new ContatoDAO());
    }

    public AgendaService(ContatoDAO contatoDAO) {
        this.contatoDAO = contatoDAO;
    }

    public Contato salvarContato(Contato contato) {
        if (!ValidadorEmail.validar(contato.getEmail())) {
            throw new IllegalArgumentException("Email inválido.");
        }
        return contatoDAO.salvar(contato);
    }

    public List<Contato> listarContatos() {
        return contatoDAO.buscarTodos();
    }

    public List<Contato> buscarPorNome(String nome) {
        return contatoDAO.buscarPorNome(nome == null ? "" : nome);
    }

    public void excluirContato(Integer id) {
        contatoDAO.excluir(id);
    }
}


