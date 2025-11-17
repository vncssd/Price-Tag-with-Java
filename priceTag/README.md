## 🏷️ Price Tag CLI Generator

Este é um projeto de uma aplicação **CLI (Command Line Interface)** simples desenvolvida em **Java** para gerar etiquetas de preço formatadas para diferentes tipos de produtos: **comum**, **usado**, ou **importado**.

---

## ✨ Funcionalidades

O CLI aceita comandos para gerar etiquetas com formatação e cálculos específicos, dependendo do tipo de produto.

* **Produto Comum:** Exibe apenas o **Nome** e o **Preço** do produto.
* **Produto Usado:** Exibe o **Nome**, o **Preço** e a **Data de Fabricação** do produto (geralmente para fins de depreciação ou informação).
* **Produto Importado:** Exibe o **Nome**, o **Preço** (após o cálculo da taxa de alfândega), e a **Taxa de Alfândega** utilizada.

---

## 🛠️ Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando puramente a linguagem:

* **Java** (versão 8 ou superior)

---

## 🚀 Como Executar

### Pré-requisitos

Certifique-se de ter o **Java Development Kit (JDK)** instalado em sua máquina.

### Compilação e Execução

1.  **Clone o repositório:**
    ```bash
    git clone [LINK_DO_SEU_REPOSITORIO]
    cd [pasta-do-projeto]
    ```

2.  **Compile os arquivos Java:**
    Se o seu arquivo principal for `PriceTagCLI.java` e estiver no diretório `src/`:
    ```bash
    javac src/*.java
    ```

3.  **Execute o programa:**
    Assumindo que sua classe principal se chama `PriceTagCLI`:
    ```bash
    java -cp src PriceTagCLI
    ```
    *Obs: Os comandos exatos podem variar dependendo da estrutura de pastas e do nome da sua classe principal.*

---

## 📝 Comandos de Exemplo (Uso)

O CLI deverá interagir com o usuário solicitando os dados necessários. Embora a implementação exata do comando possa variar (seja por *flags* ou entrada interativa), a lógica de uso será a seguinte:

| Tipo de Produto | Dados Necessários | Exemplo de Saída (Lógica) |
| :--- | :--- | :--- |
| **Comum** | Nome, Preço | Nome: TV, Preço: R$ 1500.00 |
| **Usado** | Nome, Preço, Data de Fabricação | Nome: Cadeira Usada, Preço: R$ 80.00 (Data de Fabricação: 01/01/2022) |
| **Importado** | Nome, Preço Original, Taxa de Alfândega (%) | Nome: Smartphone Importado, Preço (c/Taxa): R$ 3250.00 (Taxa: 15%) |

---

## 🤝 Contribuições

Sinta-se à vontade para enviar *pull requests* ou abrir *issues* para melhorias e correções de bugs.
