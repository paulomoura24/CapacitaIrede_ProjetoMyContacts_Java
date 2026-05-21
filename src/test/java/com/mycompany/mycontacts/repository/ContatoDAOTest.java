package com.mycompany.mycontacts.repository;

import com.mycompany.mycontacts.config.DatabaseConnection;
import com.mycompany.mycontacts.domain.Contato;
import com.mycompany.mycontacts.domain.ContatoComercial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContatoDAOTest {
    @Test
    void deveInserirContato() {
        Contato contato = new Contato("Carlos", "11911112222", "carlos@teste.com");
        Contato salvo = contatoDAO.salvar(contato);
        assertNotNull(salvo.getId());
        assertEquals("Carlos", salvo.getNome());
    }

    @Test
    void deveRemoverContato() {
        Contato contato = new Contato("Marina", "21933334444", "marina@teste.com");
        Contato salvo = contatoDAO.salvar(contato);
        assertNotNull(salvo.getId());
        contatoDAO.excluir(salvo.getId());
        List<Contato> resultados = contatoDAO.buscarPorNome("Marina");
        assertEquals(0, resultados.size());
    }

    @Test
    void deveBuscarContatoPorNome() {
        contatoDAO.salvar(new Contato("Lucas", "31955556666", "lucas@teste.com"));
        contatoDAO.salvar(new Contato("Luciana", "31977778888", "luciana@teste.com"));
        List<Contato> resultados = contatoDAO.buscarPorNome("Luc");
        assertEquals(2, resultados.size());
        assertTrue(resultados.stream().anyMatch(c -> c.getNome().equals("Lucas")));
        assertTrue(resultados.stream().anyMatch(c -> c.getNome().equals("Luciana")));
    }

    private ContatoDAO contatoDAO;

    @BeforeEach
    void setUp() throws Exception {
        // Usando arquivo temporário SQLite para garantir a mesma base durante o teste
        String tempDb = java.nio.file.Files.createTempFile("contatos-test", ".db").toAbsolutePath().toString();
        DatabaseConnection.setDatabaseUrl("jdbc:sqlite:" + tempDb);
        contatoDAO = new ContatoDAO();
    }

    @AfterEach
    void tearDown() {
        // Nada a fazer, o arquivo temporário será removido pelo sistema operacional
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


