/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycontacts.app;

import mycontacts.controller.Agenda;
import mycontacts.model.Contato;
import mycontacts.model.ContatoComercial;
import mycontacts.exceptions.ContatoNaoEncontradoException;
import mycontacts.utils.ValidadorEmail;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Agenda agenda = new Agenda();
            int opcao = 0;
            
            do {
                System.out.println("===== AGENDA DE CONTATOS =====");
                System.out.println("1. Adicionar novo contato");
                System.out.println("2. Listar contatos");
                System.out.println("3. Buscar por nome");
                System.out.println("4. Remover contato");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                
                try {
                    opcao = Integer.parseInt(scanner.nextLine()); 
                } catch (NumberFormatException e) {
                    System.out.println("Digite um número válido.");
                    continue;
                }
                
                try {
                    switch (opcao) {
                        case 1 -> {
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Telefone: ");
                            String telefone = scanner.nextLine();
                            System.out.print("Email: ");
                            String email = scanner.nextLine();
                            
                            // Validação de email
                            if (!ValidadorEmail.validar(email)) {
                                System.out.println("Email inválido! Contato não adicionado.");
                                break;
                            }
                            
                            System.out.print("É contato comercial? (s/n): ");
                            String resp = scanner.nextLine();
                            
                            if (resp.equalsIgnoreCase("s")) {
                                System.out.print("Empresa: ");
                                String empresa = scanner.nextLine();
                                agenda.adicionarContato(new ContatoComercial(nome, telefone, email, empresa));
                            } else {
                                agenda.adicionarContato(new Contato(nome, telefone, email));
                            }
                            System.out.println("Contato adicionado com sucesso!");
                        }
                        
                        case 2 -> agenda.listarContatos();
                        
                        case 3 -> {
                            System.out.print("Digite o nome: ");
                            String busca = scanner.nextLine();
                            System.out.println(agenda.buscarPorNome(busca));
                        }

                        case 4 -> {
                            System.out.print("Digite o nome: ");
                            String remover = scanner.nextLine();
                            agenda.removerContato(remover);
                        }
                        
                        case 5 -> System.out.println("Encerrando...");
                        
                        default -> System.out.println("Opção inválida!");
                    }
                } catch (ContatoNaoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
            } while (opcao != 5);
        }
    }
}