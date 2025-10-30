package gov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        AVL avl = new AVL();
        String caminhoCSV = "C:\\Users\\davi-\\OneDrive\\Documentos\\Faculdade\\Matérias\\6ºSemestre\\ED2\\bancocrai2014a2019-sistematizacao-geoinfo-atualizada.csv";
        String separador = ";";
        int totalInseridos = 0;

        // faz a leitura e inserção dos dados do CSV
        try {
            BufferedReader br = new BufferedReader(new FileReader(caminhoCSV));
            String linha;

            long inicio = System.nanoTime();
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("ID")) continue; // pula o cabeçalho

                String[] partes = linha.split(separador);
                if (partes.length < 11) continue; // ignora linhas incompletas

                int id = Integer.parseInt(partes[0].trim());
                String anoEntrada = partes[1].trim().equalsIgnoreCase("Não Informado") ? "0000" : partes[1].trim();
                String sexo = partes[4].trim();
                String pais = partes[6].trim();
                String raca = partes[10].trim();

                Node novo = new Node(id, anoEntrada, sexo, pais, raca);
                bst.insert(novo);
                avl.insert(new Node(id, anoEntrada, sexo, pais, raca));
                totalInseridos++;
            }
            long fim = System.nanoTime();
            long tempo = (fim - inicio) / 1_000_000;

            // exibe métricas após inserção inicial
            System.out.println("Dados inseridos: " + totalInseridos);
            System.out.println("Tempo de inserção (ms): " + tempo);
            System.out.println("Comparacoes BST: " + bst.getComparacoes());
            System.out.println("Altura BST: " + bst.getAltura());
            System.out.println("Comparacoes AVL: " + avl.getComparacoes());
            System.out.println("Altura AVL: " + avl.getAltura());

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // inicia menu de interação
        Scanner sc = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Inserir novo registro");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Remover por ID");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opcao: ");
            int opcao = sc.nextInt();

            if (opcao == 1) {//insere um novo nó
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Ano de Entrada: ");
                String ano = sc.nextLine();
                if (ano.equalsIgnoreCase("Não Informado")) ano = "0000";

                System.out.print("Sexo: ");
                String sexo = sc.nextLine();

                System.out.print("Pais de Origem: ");
                String pais = sc.nextLine();

                System.out.print("Raca: ");
                String raca = sc.nextLine();

                Node novoBST = new Node(id, ano, sexo, pais, raca);
                Node novoAVL = new Node(id, ano, sexo, pais, raca);

                long ini = System.nanoTime();
                bst.insert(novoBST);
                long fim = System.nanoTime();
                System.out.println("inserido na BST em " + (fim - ini) / 1_000_000 + " ms. comparações: " + bst.getComparacoes());

                ini = System.nanoTime();
                avl.insert(novoAVL);
                fim = System.nanoTime();
                System.out.println("inserido na AVL em " + (fim - ini) / 1_000_000 + " ms. comparações: " + avl.getComparacoes());

            } else if (opcao == 2) {
                System.out.print("Digite o ID para buscar: ");
                int id = sc.nextInt();

                Node resultado = null;

                // busca na BST
                long iniBST = System.nanoTime();
                resultado = bst.search(id);
                long fimBST = System.nanoTime();
                long tempoBST = (fimBST - iniBST) / 1_000_000;
                int compBST = bst.getComparacoes();

                // busca na AVL
                long iniAVL = System.nanoTime();
                avl.search(id);
                long fimAVL = System.nanoTime();
                long tempoAVL = (fimAVL - iniAVL) / 1_000_000;
                int compAVL = avl.getComparacoes();

                if (resultado != null) {
                    System.out.println("ID: " + resultado.id + ", Ano Entrada: " + resultado.dataEntrada +
                                       ", Sexo: " + resultado.sexo + ", País: " + resultado.paisOrigem +
                                       ", Raça: " + resultado.raca);
                } else {
                    System.out.println("ID não encontrado.");
                }

                System.out.println("BST -> tempo: " + tempoBST + " ms, comparações: " + compBST);
                System.out.println("AVL -> tempo: " + tempoAVL + " ms, comparações: " + compAVL);
           

            } else if (opcao == 3) {
                System.out.print("Digite o ID para remover: ");
                int id = sc.nextInt();

                long ini = System.nanoTime();
                bst.remove(id);
                long fim = System.nanoTime();
                System.out.println("BST -> removido em " + (fim - ini) / 1_000_000 + " ms. comparacoes: " + bst.getComparacoes() + ". altura: " + bst.getAltura());

                ini = System.nanoTime();
                avl.remove(id);
                fim = System.nanoTime();
                System.out.println("AVL -> removido em " + (fim - ini) / 1_000_000 + " ms. comparacoes: " + avl.getComparacoes() + ". altura: " + avl.getAltura());

            } else if (opcao == 4) {
                rodando = false;
            } else {
                System.out.println("opcao invalida.");
            }
        }

        sc.close();
    }
}
