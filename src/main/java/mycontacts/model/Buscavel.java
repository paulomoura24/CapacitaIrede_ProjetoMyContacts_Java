/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mycontacts.model;

import mycontacts.exceptions.ContatoNaoEncontradoException;

public interface Buscavel {
    Contato buscarPorNome(String nome) throws ContatoNaoEncontradoException;
}