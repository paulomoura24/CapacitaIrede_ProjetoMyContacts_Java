package com.mycompany.mycontacts.repository;

import com.mycompany.mycontacts.config.DatabaseConnection;
import com.mycompany.mycontacts.domain.Contato;
import com.mycompany.mycontacts.domain.ContatoComercial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContatoDAO implements GenericRepository<Contato, Integer> {

    public ContatoDAO() {
        criarTabela();
    }

    private void criarTabela() {
        String sql;
        if (DatabaseConnection.isMySql()) {
            sql = "CREATE TABLE IF NOT EXISTS contatos ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nome VARCHAR(255) NOT NULL, "
                    + "telefone VARCHAR(50) NOT NULL, "
                    + "email VARCHAR(255) NOT NULL, "
                    + "empresa VARCHAR(255), "
                    + "tipo VARCHAR(50) NOT NULL"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        } else {
            sql = "CREATE TABLE IF NOT EXISTS contatos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nome TEXT NOT NULL, "
                    + "telefone TEXT NOT NULL, "
                    + "email TEXT NOT NULL, "
                    + "empresa TEXT, "
                    + "tipo TEXT NOT NULL"
                    + ");";
        }

        try (Connection connection = DatabaseConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao criar tabela de contatos.", e);
        }
    }

    @Override
    public Contato salvar(Contato contato) {
        if (contato.getId() == null) {
            return inserir(contato);
        }
        atualizar(contato);
        return contato;
    }

    private Contato inserir(Contato contato) {
        String sql = "INSERT INTO contatos(nome, telefone, email, empresa, tipo) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, contato.getNome());
            statement.setString(2, contato.getTelefone());
            statement.setString(3, contato.getEmail());
            statement.setString(4, getEmpresa(contato));
            statement.setString(5, getTipo(contato));
            statement.executeUpdate();

            int id = -1;
            // Se for SQLite, pega o id com SELECT last_insert_rowid()
            if (!DatabaseConnection.isMySql()) {
                try (PreparedStatement stmtId = connection.prepareStatement("SELECT last_insert_rowid()")) {
                    try (ResultSet rs = stmtId.executeQuery()) {
                        if (rs.next()) {
                            id = rs.getInt(1);
                        }
                    }
                }
            } else {
                // MySQL
                try (PreparedStatement stmtId = connection.prepareStatement("SELECT LAST_INSERT_ID()")) {
                    try (ResultSet rs = stmtId.executeQuery()) {
                        if (rs.next()) {
                            id = rs.getInt(1);
                        }
                    }
                }
            }
            if (id != -1) {
                return recriarContato(contato, id);
            }
            throw new SQLException("Falha ao obter chave gerada para contato.");
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao salvar contato.", e);
        }
    }

    private void atualizar(Contato contato) {
        String sql = "UPDATE contatos SET nome = ?, telefone = ?, email = ?, empresa = ?, tipo = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, contato.getNome());
            statement.setString(2, contato.getTelefone());
            statement.setString(3, contato.getEmail());
            statement.setString(4, getEmpresa(contato));
            statement.setString(5, getTipo(contato));
            statement.setInt(6, contato.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao atualizar contato.", e);
        }
    }

    @Override
    public Optional<Contato> buscarPorId(Integer id) {
        String sql = "SELECT * FROM contatos WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(criarContato(resultSet));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao buscar contato por ID.", e);
        }
    }

    @Override
    public List<Contato> buscarTodos() {
        String sql = "SELECT * FROM contatos ORDER BY nome";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Contato> contatos = new ArrayList<>();
            while (resultSet.next()) {
                contatos.add(criarContato(resultSet));
            }
            return contatos;
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao buscar todos os contatos.", e);
        }
    }

    @Override
    public List<Contato> buscarPorNome(String nome) {
        String sql = "SELECT * FROM contatos WHERE lower(nome) LIKE ? ORDER BY nome";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + nome.trim().toLowerCase() + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Contato> contatos = new ArrayList<>();
                while (resultSet.next()) {
                    contatos.add(criarContato(resultSet));
                }
                return contatos;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao buscar contatos por nome.", e);
        }
    }

    @Override
    public void excluir(Integer id) {
        String sql = "DELETE FROM contatos WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao excluir contato.", e);
        }
    }

    private Contato criarContato(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        String telefone = resultSet.getString("telefone");
        String email = resultSet.getString("email");
        String empresa = resultSet.getString("empresa");
        String tipo = resultSet.getString("tipo");

        if ("comercial".equalsIgnoreCase(tipo)) {
            return new ContatoComercial(id, nome, telefone, email, empresa);
        }
        return new Contato(id, nome, telefone, email);
    }

    private Contato recriarContato(Contato contato, int id) {
        if (contato instanceof ContatoComercial comercial) {
            return new ContatoComercial(id, comercial.getNome(), comercial.getTelefone(), comercial.getEmail(), comercial.getEmpresa());
        }
        return new Contato(id, contato.getNome(), contato.getTelefone(), contato.getEmail());
    }

    private String getTipo(Contato contato) {
        return contato instanceof ContatoComercial ? "comercial" : "pessoal";
    }

    private String getEmpresa(Contato contato) {
        return contato instanceof ContatoComercial comercial ? comercial.getEmpresa() : "";
    }
}


