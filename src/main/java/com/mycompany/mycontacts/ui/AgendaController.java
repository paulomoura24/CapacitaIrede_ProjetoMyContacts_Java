package com.mycompany.mycontacts.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import com.mycompany.mycontacts.domain.Contato;
import com.mycompany.mycontacts.domain.ContatoComercial;
import com.mycompany.mycontacts.service.AgendaService;
import com.mycompany.mycontacts.validation.ValidadorEmail;

import java.util.List;

public class AgendaController {

    @FXML
    private TableView<Contato> tableContatos;

    @FXML
    private TableColumn<Contato, String> columnNome;

    @FXML
    private TableColumn<Contato, String> columnTelefone;

    @FXML
    private TableColumn<Contato, String> columnEmail;

    @FXML
    private TableColumn<Contato, String> columnEmpresa;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField telefoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField empresaField;

    @FXML
    private TextField buscaField;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Button salvarButton;

    @FXML
    private Button removerButton;

    @FXML
    private Button novoButton;

    @FXML
    private Label detalhesIdLabel;

    @FXML
    private Label detalhesTipoLabel;

    @FXML
    private Label detalhesNomeLabel;

    @FXML
    private Label detalhesTelefoneLabel;

    @FXML
    private Label detalhesEmailLabel;

    @FXML
    private Label detalhesEmpresaLabel;

    private final AgendaService agendaService = new AgendaService();
    private final ObservableList<Contato> contatos = FXCollections.observableArrayList();
    private Contato contatoSelecionado;

    @FXML
    private void initialize() {
        configurarTabela();
        carregarContatos();
        tableContatos.setItems(contatos);
        tableContatos.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> preencherFormulario(novo));
    }

    private void configurarTabela() {
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
    }

    private void carregarContatos() {
        List<Contato> resultados = agendaService.listarContatos();
        contatos.setAll(resultados);
        feedbackLabel.setText(resultados.isEmpty() ? "Nenhum contato cadastrado." : resultados.size() + " contato(s) carregado(s)." );
    }

    @FXML
    private void onSalvarAction(ActionEvent event) {
        limparCamposInvalidos();

        if (!validarFormulario()) {
            return;
        }

        try {
            String nome = nomeField.getText().trim();
            String telefone = telefoneField.getText().trim();
            String email = emailField.getText().trim();
            String empresa = empresaField.getText().trim();

            Contato contato;
            if (!empresa.isBlank()) {
                contato = new ContatoComercial(contatoSelecionado != null ? contatoSelecionado.getId() : null,
                        nome, telefone, email, empresa);
            } else {
                contato = new Contato(contatoSelecionado != null ? contatoSelecionado.getId() : null,
                        nome, telefone, email);
            }

            Contato salvo = agendaService.salvarContato(contato);
            contatoSelecionado = salvo;
            carregarContatos();
            selecionarContatoNaTabela(salvo);
            preencherDetalhes(salvo);
            feedbackLabel.setText("Contato salvo com sucesso.");
        } catch (Exception e) {
            feedbackLabel.setText("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void onBuscarAction(ActionEvent event) {
        String nome = buscaField.getText();
        List<Contato> resultado = agendaService.buscarPorNome(nome == null ? "" : nome);
        contatos.setAll(resultado);
        feedbackLabel.setText(resultado.isEmpty() ? "Nenhum contato encontrado." : resultado.size() + " resultado(s)." );
    }

    @FXML
    private void onMostrarTodosAction(ActionEvent event) {
        buscaField.clear();
        carregarContatos();
    }

    @FXML
    private void onRemoverAction(ActionEvent event) {
        Contato selecionado = tableContatos.getSelectionModel().getSelectedItem();
        if (selecionado == null || selecionado.getId() == null) {
            feedbackLabel.setText("Selecione um contato persistido para remover.");
            return;
        }

        agendaService.excluirContato(selecionado.getId());
        contatoSelecionado = null;
        carregarContatos();
        limparFormulario();
        limparDetalhes();
        feedbackLabel.setText("Contato removido com sucesso.");
    }

    @FXML
    private void onNovoAction(ActionEvent event) {
        contatoSelecionado = null;
        limparFormulario();
        limparDetalhes();
        feedbackLabel.setText("Pronto para novo contato.");
    }

    private void preencherFormulario(Contato contato) {
        contatoSelecionado = contato;
        if (contato == null) {
            limparFormulario();
            limparDetalhes();
            return;
        }

        nomeField.setText(contato.getNome());
        telefoneField.setText(contato.getTelefone());
        emailField.setText(contato.getEmail());
        empresaField.setText(contato instanceof ContatoComercial comercial ? comercial.getEmpresa() : "");
        preencherDetalhes(contato);
    }

    private void preencherDetalhes(Contato contato) {
        if (contato == null) {
            limparDetalhes();
            return;
        }

        detalhesIdLabel.setText(contato.getId() == null ? "-" : contato.getId().toString());
        detalhesTipoLabel.setText(contato instanceof ContatoComercial ? "Comercial" : "Pessoal");
        detalhesNomeLabel.setText(contato.getNome());
        detalhesTelefoneLabel.setText(contato.getTelefone());
        detalhesEmailLabel.setText(contato.getEmail());
        detalhesEmpresaLabel.setText(contato instanceof ContatoComercial comercial ? comercial.getEmpresa() : "-");
    }

    private void limparDetalhes() {
        detalhesIdLabel.setText("-");
        detalhesTipoLabel.setText("-");
        detalhesNomeLabel.setText("-");
        detalhesTelefoneLabel.setText("-");
        detalhesEmailLabel.setText("-");
        detalhesEmpresaLabel.setText("-");
    }

    private void selecionarContatoNaTabela(Contato contato) {
        if (contato == null || contato.getId() == null) {
            tableContatos.getSelectionModel().clearSelection();
            return;
        }

        for (Contato item : contatos) {
            if (item.getId() != null && item.getId().equals(contato.getId())) {
                tableContatos.getSelectionModel().select(item);
                tableContatos.scrollTo(item);
                return;
            }
        }
        tableContatos.getSelectionModel().clearSelection();
    }

    private boolean validarFormulario() {
        boolean valido = true;

        if (nomeField.getText() == null || nomeField.getText().isBlank()) {
            marcarCampoInvalido(nomeField);
            valido = false;
        }

        if (telefoneField.getText() == null || telefoneField.getText().isBlank() || !telefoneField.getText().matches("^[0-9\\s()+-]{8,20}$")) {
            marcarCampoInvalido(telefoneField);
            valido = false;
        }

        if (emailField.getText() == null || !ValidadorEmail.validar(emailField.getText().trim())) {
            marcarCampoInvalido(emailField);
            valido = false;
        }

        if (!valido) {
            feedbackLabel.setText("Corrija os campos destacados em vermelho.");
        }
        return valido;
    }

    private void marcarCampoInvalido(TextField campo) {
        if (!campo.getStyleClass().contains("error-field")) {
            campo.getStyleClass().add("error-field");
        }
    }

    private void limparCamposInvalidos() {
        nomeField.getStyleClass().remove("error-field");
        telefoneField.getStyleClass().remove("error-field");
        emailField.getStyleClass().remove("error-field");
        empresaField.getStyleClass().remove("error-field");
    }

    private void limparFormulario() {
        nomeField.clear();
        telefoneField.clear();
        emailField.clear();
        empresaField.clear();
        tableContatos.getSelectionModel().clearSelection();
    }
}


