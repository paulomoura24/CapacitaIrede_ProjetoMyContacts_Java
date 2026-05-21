# 📇 MyContacts

Sistema de gerenciamento de contatos desenvolvido em **Java com JavaFX**, utilizando **MySQL para persistência de dados**, com foco em boas práticas de arquitetura, organização em camadas e experiência de usuário.

---

## 📑 Sumário
<ol>
  <li>Sobre o Projeto</li>
  <li>Interface Gráfica (JavaFX)</li>
  <liPersistência de Dados (MySQL)></li>
  <li>Arquitetura do Projeto</li>
  <li>Tecnologias Utilizadas</li>
  <li>Funcionalidades</li>
  <li>Como Executar o Projeto</li>
  <li>Testes</li>
  <li>Melhorias Futuras</li>
  <li>Tratamento de Exceções</li>
  <li>Boas Práticas Aplicadas</li>
  <li>Contribuição</li>
  <li>Licença</li>
  <liAutor></li>
  <li>Considerações Finais</li>   
</ol>

---

## 📌 Sobre o Projeto

O **MyContacts** é uma aplicação desktop que permite o cadastro, busca, atualização e remoção de contatos pessoais e comerciais. A aplicação possui uma **interface gráfica construída com JavaFX** e utiliza **banco de dados MySQL** para armazenamento persistente das informações.

O projeto foi estruturado seguindo princípios de separação de responsabilidades, facilitando manutenção, escalabilidade e evolução.

---

## 🖥️ Interface Gráfica (JavaFX)

A aplicação conta com uma interface gráfica desenvolvida com **JavaFX**, utilizando arquivos FXML para definição das telas e controllers para controle da lógica.

### Principais características da UI:

* 🎨 Interface amigável e responsiva
* 🧩 Separação entre layout (FXML) e lógica (Controller)
* 🔄 Atualização dinâmica de dados
* 📋 Listagem de contatos em componentes visuais
* 🖱️ Interação via eventos e formulários

---

## 🗄️ Persistência de Dados (MySQL)

O sistema utiliza o **MySQL** como banco de dados relacional, garantindo persistência, integridade e eficiência no armazenamento das informações.

### Características da persistência:

* 💾 Armazenamento em banco relacional (MySQL)
* 🔗 Conexão via JDBC
* 📂 Camada Repository/DAO para acesso a dados
* 🔄 Operações CRUD completas
* ⚙️ Configuração externa via arquivo `.properties`

---

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas:

```text id="cpgm6z"
src/main/java/com/mycompany/mycontacts/
│
├── app/            # Classe principal (JavaFX Application)
├── config/         # Configuração do banco (MySQL/JDBC)
├── domain/         # Entidades do sistema
├── exception/      # Exceções customizadas
├── repository/     # Acesso a dados (DAO)
├── service/        # Regras de negócio
├── ui/             # Controllers JavaFX
└── validation/     # Validações

src/main/resources/
│
├── fxml/           # Telas (FXML)
├── styles/         # CSS
└── db.properties   # Configuração do banco
```

---

## 🧩 Tecnologias Utilizadas

* Java 21+
* JavaFX
* Maven
* MySQL
* JDBC
* FXML
* CSS
* Padrão DAO
* Arquitetura em camadas

---

## ⚙️ Funcionalidades

* 📌 Cadastro de contatos
* 🔍 Busca de contatos
* ✏️ Atualização de dados
* ❌ Remoção de contatos
* 📧 Validação de e-mail
* 🧾 Contatos comerciais
* 🖥️ Interface gráfica interativa
* 💾 Persistência em banco MySQL

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

* Java JDK 21 ou superior
* Maven
* MySQL instalado e em execução

---

### 🛠️ Configuração do Banco de Dados

1. Crie o banco no MySQL:

```sql id="x61x7t"
CREATE DATABASE mycontacts;
```

2. Configure o arquivo:

```text id="z6vclb"
src/main/resources/db.properties
```

3. Exemplo de configuração:

```properties id="3eh3yf"
db.url=jdbc:mysql://localhost:3306/mycontacts
db.user=root
db.password=senha
```

---

### ▶️ Execução

```bash id="rzapz6"
mvn clean install
mvn javafx:run
```
Se ocorrer erro de conexão, verifique as configurações do arquivo `db.properties` e se o MySql está ativo.

---

## 🧪 Testes
O projeto inclui testes automatizados com JUnit. Para execultá-los:
```bash
mvn test
```
Os relatórios de teste são gerados em `surefire-reports`.

---

## 🧪 Melhorias Futuras

* Testes automatizados (JUnit)
* Migração para JPA/Hibernate
* API REST com Spring Boot
* Deploy em nuvem
* Sistema de autenticação

---

## 📄 Tratamento de Exceções

* `ContatoNaoEncontradoException`

---

## 📐 Boas Práticas Aplicadas

* Separação de responsabilidades (SRP)
* Arquitetura em camadas
* DAO para persistência
* Uso de JDBC com configuração externa
* Interface desacoplada da lógica
* Código modular e reutilizável

---

## 🤝 Contribuição

1. Fork do projeto
2. Crie uma branch (`feature/minha-feature`)
3. Commit suas alterações
4. Push
5. Abra um Pull Request

---

## 📜 Licença

Licença MIT.

---

## 👨‍💻 Autor

**Paulo Sérgio Queiroz Moura**

---

## ⭐ Considerações Finais

O **MyContacts** demonstra a construção de uma aplicação desktop completa, integrando:

* Interface gráfica com JavaFX
* Arquitetura organizada em camadas
* Persistência com MySQL via JDBC

Servindo como uma excelente base para projetos profissionais em Java e evolução para sistemas mais robustos.
