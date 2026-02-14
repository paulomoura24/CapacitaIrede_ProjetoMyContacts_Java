# 📇 MyContacts

Sistema de gerenciamento de contatos desenvolvido em **Java**, com foco em organização modular, boas práticas de programação orientada a objetos e separação de responsabilidades.

---

## 📌 Sobre o Projeto

O **MyContacts** é uma aplicação de console que permite:

* ➕ Cadastrar contatos
* 🔍 Buscar contatos por nome
* 📋 Listar todos os contatos
* ❌ Tratar exceções personalizadas
* ✔ Validar e-mails

O projeto foi estruturado utilizando conceitos fundamentais de **POO**, como:

* Encapsulamento
* Herança
* Polimorfismo
* Tratamento de exceções
* Organização por pacotes

---

## 🏗 Estrutura do Projeto

```
mycontacts/
├── app/
│   └── Main.java
├── controller/
│   └── Agenda.java
├── model/
│   ├── Contato.java
│   └── ContatoComercial.java
├── exceptions/
│   └── ContatoNaoEncontradoException.java
└── utils/
    └── ValidadorEmail.java
```

### 📂 Descrição dos Pacotes

* **app** → Classe principal responsável pela execução do sistema.
* **controller** → Gerencia as regras de negócio da agenda.
* **model** → Representação das entidades do sistema.
* **exceptions** → Exceções personalizadas.
* **utils** → Classes utilitárias (validação de e-mail).

---

## 🚀 Tecnologias Utilizadas

* Java 8+
* Programação Orientada a Objetos (POO)
* Regex para validação
* Estrutura modular por pacotes
* IDE recomendada: NetBeans ou VS Code

---

## ▶ Como Executar o Projeto

### 1️⃣ Clonar o repositório

```
git clone https://github.com/seu-usuario/mycontacts.git
```

### 2️⃣ Abrir na IDE

* Abra o projeto no **NetBeans** ou **VS Code**
* Certifique-se de que o JDK está configurado

### 3️⃣ Executar

Execute a classe:

```
Main.java
```

---

## 💡 Funcionalidades Implementadas

* Cadastro de contatos comuns e comerciais
* Busca por nome (case insensitive)
* Listagem formatada
* Validação básica de e-mail
* Exceção personalizada para contato não encontrado
* Tratamento de entradas inválidas

---

## 🔒 Boas Práticas Aplicadas

* Programação orientada a interfaces
* Uso de `List` ao invés de implementação concreta
* Encapsulamento de atributos
* Tratamento adequado de exceções
* Separação entre regras de negócio e exibição

---

## 🔮 Melhorias Futuras

* Persistência em arquivo (CSV ou JSON)
* Integração com banco de dados
* Transformação em API REST com Spring Boot
* Interface gráfica (JavaFX ou Web)
* Implementação de testes unitários com JUnit

---

## 👨‍💻 Autor

**Paulo Sérgio Queiroz Moura**
Graduando em Análise e Desenvolvimento de Sistemas

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos e de estudo.

