# Análise Comparativa de Árvores AVL e BST com Dados de Imigrantes (CRAI)

> **Sobre:** Projeto acadêmico de Estruturas de Dados que analisa e compara a performance de árvores AVL (auto-balanceada) e BST (Busca Binária) aplicadas a um conjunto de dados reais de atendimento a imigrantes.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Eclipse IDE](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)

---

## 🎯 Objetivo do Projeto

Este projeto tem como principal objetivo demonstrar e comparar a eficiência de duas estruturas de dados fundamentais:

1.  **Árvore de Busca Binária (BST):** Uma estrutura de nó simples e rápida para inserção em cenários ideais.
2.  **Árvore AVL:** Uma BST auto-balanceada que garante que as operações de inserção, busca e remoção mantenham uma complexidade de tempo de O(log n), evitando o pior caso de uma árvore degenerada O(n) que pode ocorrer em uma BST comum.

A comparação é realizada utilizando um conjunto de dados do mundo real para analisar o desempenho prático de cada árvore em operações de inserção e busca.

## 📊 Sobre o Dataset

O conjunto de dados utilizado é o `bancocrai2014a2019-sistematizacao-geoinfo-atualizada.csv`.

Este arquivo contém registros de atendimento do **CRAI (Centro de Referência e Atendimento para Imigrantes)**, cobrindo o período de 2014 a 2019. A análise escrita e detalhada sobre esta base de dados pode ser encontrada no documento `Análise da Base de Dados do Centro de Referência e Atendimento para Imigrantes.docx`.

## 📂 Estrutura do Projeto

O código-fonte está organizado da seguinte forma (assumindo o pacote `gov` como visto nos seus arquivos):

* `src/gov/Main.java`: Classe principal do programa. É responsável por:
    * Ler o arquivo `.csv`.
    * Instanciar as árvores AVL e BST.
    * Processar os dados e inseri-los em ambas as árvores.
    * Medir o tempo e a performance das operações para fins de comparação.
* `src/gov/AVL.java`: Implementação da estrutura da Árvore AVL, incluindo as lógicas de rotação (simples e dupla) para manter o fator de balanceamento.
* `src/gov/BST.java`: Implementação da estrutura da Árvore de Busca Binária padrão.
* `src/gov/Node.java`: Classe que representa o nó básico utilizado por ambas as árvores, contendo os dados e referências aos filhos (e, possivelmente, a altura, para a AVL).

## 🚀 Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/SEU-USUARIO/analise-avl-bst-imigrantes.git](https://github.com/SEU-USUARIO/analise-avl-bst-imigrantes.git)
    ```
2.  **Abra na sua IDE:**
    * Importe o projeto em uma IDE Java (esse projeto foi desenvolvido utilizando ECLIPSE).
3.  **Verifique o Caminho do CSV:**
    * Abra o arquivo `src/gov/Main.java`.
    * Localize a linha que lê o arquivo `bancocrai2014a2019...csv`.
    * Certifique-se de que o caminho para o arquivo está correto em relação à raiz do projeto. O ideal é usar um caminho relativo.
4.  **Execute:**
    * Rode a classe `Main.java` para iniciar a análise. A saída (tempos de execução, altura das árvores, etc.) será exibida no console.

## 📈 Análise e Resultados

A análise completa dos resultados está documentada em:
`Análise da Base de Dados do Centro de Referência e Atendimento para Imigrantes.pdf`.

Em resumo, espera-se que a **Árvore AVL**, apesar de possuir um tempo de *inserção* ligeiramente maior (devido à sobrecarga de verificações de balanceamento e rotações), mantenha uma altura significativamente menor e, consequentemente, um tempo de *busca* muito mais rápido e consistente O(log n) em comparação com a **BST**, que pode degenerar e se aproximar de um desempenho de O(n) dependendo da ordem de entrada dos dados.

## 📄 Licença

Este projeto está licenciado sob a **Licença MIT**. Veja o arquivo `LICENSE` para mais detalhes.
