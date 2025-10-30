package gov;

public class Node {
    int id;
    String dataEntrada;
    String sexo;
    String paisOrigem;
    String raca;

    public Node left, right;
    int height;

    public Node(int id, String dataEntrada, String sexo, String paisOrigem, String raca) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.sexo = sexo;
        this.paisOrigem = paisOrigem;
        this.raca = raca;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}
