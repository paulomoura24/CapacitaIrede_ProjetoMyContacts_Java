package com.mycompany.mycontacts.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, ID> {

    T salvar(T entity);

    Optional<T> buscarPorId(ID id);

    List<T> buscarTodos();

    List<T> buscarPorNome(String nome);

    void excluir(ID id);
}

