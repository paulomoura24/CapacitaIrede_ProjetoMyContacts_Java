package com.mycompany.mycontacts.repository;

import com.mycompany.mycontacts.domain.Contato;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class RepositorioGenerico<T extends Contato> {

    private final List<T> itens = new ArrayList<>();

    public void adicionar(T item) {
        itens.add(item);
    }

    public void adicionarTodos(Collection<? extends T> contatos) {
        itens.addAll(contatos);
    }

    public List<T> listar() {
        return Collections.unmodifiableList(itens);
    }

    public List<T> buscarPorNome(String nome) {
        String chave = nome == null ? "" : nome.trim().toLowerCase();
        return itens.stream()
                .filter(containsNome(chave))
                .toList();
    }

    public void remover(T item) {
        itens.remove(item);
    }

    public void copiarPara(Collection<? super T> destino) {
        destino.addAll(itens);
    }

    private Predicate<T> containsNome(String chave) {
        return contato -> contato.getNome().toLowerCase().contains(chave);
    }
}

