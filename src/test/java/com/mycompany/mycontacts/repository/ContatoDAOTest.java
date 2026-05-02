package com.mycompany.mycontacts.repository;

import com.mycompany.mycontacts.config.DatabaseConnection;
import com.mycompany.mycontacts.domain.Contato;
import com.mycompany.mycontacts.domain.ContatoComercial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContatoDAOTest {

    private ContatoDAO contatoDAO;

    @BeforeEach
    void setUp() {
        DatabaseConnection.setDatabaseUrl("jdbc:mysql://localhost:3306/mycontacts");
        contatoDAO = new ContatoDAO();
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS contatos");
        }
    }

    @Test
    void deveSalvarEBuscarContato() {
        Contato contato = new Contato("Paulo", "11999999999", "paulo@teste.com");
        Contato salvo = contatoDAO.salvar(contato);

        assertNotNull(salvo.getId());

        List<Contato> resultados = contatoDAO.buscarPorNome("Paulo");
        assertEquals(1, resultados.size());
        assertEquals("Paulo", resultados.get(0).getNome());
    }

    @Test
    void deveSalvarEExcluirContatoComercial() {
        Contato contato = new ContatoComercial("Ana", "21988888888", "ana@empresa.com", "Empresa X");
        Contato salvo = contatoDAO.salvar(contato);

        assertNotNull(salvo.getId());
        assertTrue(salvo instanceof ContatoComercial);

        contatoDAO.excluir(salvo.getId());
        List<Contato> resultados = contatoDAO.buscarPorNome("Ana");
        assertEquals(0, resultados.size());
    }

    @Test
    void deveAtualizarContatoExistente() {
        Contato contato = new Contato("Bruno", "21977777777", "bruno@teste.com");
        Contato salvo = contatoDAO.salvar(contato);

        Contato atualizado = new Contato(salvo.getId(), "Bruno Silva", "21977777777", "bruno@teste.com");
        contatoDAO.salvar(atualizado);

        List<Contato> resultados = contatoDAO.buscarPorNome("Silva");
        assertEquals(1, resultados.size());
        assertEquals("Bruno Silva", resultados.get(0).getNome());
    }
}


