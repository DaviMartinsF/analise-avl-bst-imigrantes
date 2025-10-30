package gov;

public class BST {
    private Node root;
    private int comparacoes;

    public void insert(Node novo) {
        comparacoes = 0;
        if (root == null) {
            root = novo;
            return;
        }
        Node atual = root;
        while (true) {
            comparacoes++;
            if (novo.id < atual.id) {
                if (atual.left == null) {
                    atual.left = novo;
                    break;
                } else {
                    atual = atual.left;
                }
            } else {
                if (atual.right == null) {
                    atual.right = novo;
                    break;
                } else {
                    atual = atual.right;
                }
            }
        }
    }

    public Node search(int id) {
        comparacoes = 0;
        Node atual = root;
        while (atual != null) {
            comparacoes++;
            if (id == atual.id) {
                return atual;
            } else if (id < atual.id) {
                atual = atual.left;
            } else {
                atual = atual.right;
            }
        }
        return null;
    }

    public void remove(int id) {
        comparacoes = 0;
        root = removeIterativo(root, id);
    }

    private Node removeIterativo(Node root, int id) {
        Node parent = null;
        Node atual = root;

        while (atual != null && atual.id != id) {
            comparacoes++;
            parent = atual;
            if (id < atual.id) {
                atual = atual.left;
            } else {
                atual = atual.right;
            }
        }

        if (atual == null) return root;

        if (atual.left == null || atual.right == null) {
            Node novoFilho = (atual.left != null) ? atual.left : atual.right;

            if (parent == null) return novoFilho;

            if (parent.left == atual) {
                parent.left = novoFilho;
            } else {
                parent.right = novoFilho;
            }
        } else {
            Node sucessorParent = atual;
            Node sucessor = atual.right;
            while (sucessor.left != null) {
                comparacoes++;
                sucessorParent = sucessor;
                sucessor = sucessor.left;
            }

            if (sucessorParent != atual) {
                sucessorParent.left = sucessor.right;
                sucessor.right = atual.right;
            }
            sucessor.left = atual.left;

            if (parent == null) {
                return sucessor;
            }
            if (parent.left == atual) {
                parent.left = sucessor;
            } else {
                parent.right = sucessor;
            }
        }

        return root;
    }

    public int getAltura() {
        return getAlturaIterativa();
    }

    private int getAlturaIterativa() {
        if (root == null) return -1;

        java.util.Queue<Node> fila = new java.util.LinkedList<>();
        fila.add(root);
        int altura = -1;

        while (!fila.isEmpty()) {
            int nivelSize = fila.size();
            for (int i = 0; i < nivelSize; i++) {
                Node atual = fila.poll();
                if (atual.left != null) fila.add(atual.left);
                if (atual.right != null) fila.add(atual.right);
            }
            altura++;
        }

        return altura;
    }

    public int getComparacoes() {
        return comparacoes;
    }
}
