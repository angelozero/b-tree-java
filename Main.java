public class Main {

    public static void main(String[] args) {
        BTree bTree = new BTree(3); // Cria uma B-Tree de grau mínimo 3

        // Inserindo chaves
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(12);
        bTree.insert(30);
        bTree.insert(7);
        bTree.insert(17);

        // Consultando chaves
        System.out.println("Chave 6 encontrada? " + bTree.search(6)); // Saída: true
        System.out.println("Chave 15 encontrada? " + bTree.search(15)); // Saída: false
    }
}
