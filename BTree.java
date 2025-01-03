public class BTree {
    BTreeNode root;           // Raiz da B-Tree
    int minDegree;            // Grau mínimo da B-Tree

    // Construtor da B-Tree
    BTree(int minDegree) {
        this.root = null;     // Inicializa a raiz como nula
        this.minDegree = minDegree; // Define o grau mínimo
    }

    // Método para inserir uma nova chave na B-Tree
    public void insert(int key) {
        if (root == null) {
            // Se a árvore estiver vazia, cria um novo nó raiz
            root = new BTreeNode(minDegree, true);
            root.keys[0] = key; // Adiciona a chave ao nó raiz
            root.currentKeyCount = 1; // Atualiza o contador de chaves

        } else {
            // Se a raiz está cheia, precisamos dividir
            if (root.currentKeyCount == 2 * minDegree - 1) {
                BTreeNode newRoot = new BTreeNode(minDegree, false);
                newRoot.children[0] = root; // Coloca a antiga raiz como filho do novo nó raiz
                newRoot.splitChild(0, root); // Divide a antiga raiz
                int i = 0;

                // Determina onde a nova chave deve ser inserida
                if (newRoot.keys[0] < key) {
                    i++;
                }

                newRoot.children[i].insertNonFull(key); // Insere a chave no nó filho apropriado
                root = newRoot; // Atualiza a raiz

            } else {
                root.insertNonFull(key); // Insere a chave na raiz, se não estiver cheia
            }
        }
    }

    // Método para buscar uma chave na B-Tree
    public boolean search(int key) {
        return (root == null) ? false : root.search(key); // Busca no nó raiz
    }
}
