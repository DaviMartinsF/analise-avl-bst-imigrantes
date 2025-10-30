package gov;

public class AVL {
    private Node raiz;
    private int comparacoes;

    public AVL() {
        raiz = null;
        comparacoes = 0;
    }

    public void insert(Node novo) {
        raiz = insertRec(raiz, novo);
    }

    private Node insertRec(Node node, Node novo) {
        if (node == null) return novo;

        comparacoes++;
        if (novo.id < node.id) node.left = insertRec(node.left, novo);
        else if (novo.id > node.id) node.right = insertRec(node.right, novo);
        else return node; // duplicado

        updateHeight(node);
        return balance(node);
    }

    public Node search(int id) {
        comparacoes = 0;
        Node atual = raiz;
        while (atual != null) {
            comparacoes++;
            if (id == atual.id) return atual;
            if (id < atual.id) atual = atual.left;
            else atual = atual.right;
        }
        return null;
    }

    public void remove(int id) {
        comparacoes = 0;
        raiz = removeRec(raiz, id);
    }

    private Node removeRec(Node node, int id) {
        if (node == null) return null;

        comparacoes++;
        if (id < node.id) node.left = removeRec(node.left, id);
        else if (id > node.id) node.right = removeRec(node.right, id);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node menor = minValueNode(node.right);
            node.id = menor.id;
            node.dataEntrada = menor.dataEntrada;
            node.sexo = menor.sexo;
            node.paisOrigem = menor.paisOrigem;
            node.raca = menor.raca;

            node.right = removeRec(node.right, menor.id);
        }

        updateHeight(node);
        return balance(node);
    }

    private Node minValueNode(Node node) {
        Node atual = node;
        while (atual.left != null) atual = atual.left;
        return atual;
    }

    private void updateHeight(Node node) {
        int altEsq = node.left != null ? node.left.height : 0;
        int altDir = node.right != null ? node.right.height : 0;
        node.height = 1 + Math.max(altEsq, altDir);
    }

    private int getBalance(Node node) {
        if (node == null) return 0;
        int altEsq = node.left != null ? node.left.height : 0;
        int altDir = node.right != null ? node.right.height : 0;
        return altEsq - altDir;
    }

    private Node balance(Node node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0) node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1) {
            if (getBalance(node.right) > 0) node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public int getAltura() {
        return getAlturaRec(raiz);
    }

    private int getAlturaRec(Node node) {
        if (node == null) return -1;
        int alturaEsq = getAlturaRec(node.left);
        int alturaDir = getAlturaRec(node.right);
        return 1 + Math.max(alturaEsq, alturaDir);
    }

    public int getComparacoes() {
        return comparacoes;
    }
}
