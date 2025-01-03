public class BTreeNode {
    int[] keys;               // Chaves armazenadas no nó
    int minDegree;            // Grau mínimo (número mínimo de filhos)
    BTreeNode[] children;     // Filhos
    int currentKeyCount;      // Número atual de chaves
    boolean isLeaf;           // Verdadeiro se é um nó folha

    BTreeNode(int minDegree, boolean isLeaf) {
        this.minDegree = minDegree;
        this.isLeaf = isLeaf;
        this.keys = new int[2 * minDegree - 1]; // Inicializa o array de chaves
        this.children = new BTreeNode[2 * minDegree]; // Inicializa o array de filhos
        this.currentKeyCount = 0; // Inicializa o contador de chaves
    }

    // Método para inserir uma chave em um nó não cheio
    public void insertNonFull(int key) {
        int i = currentKeyCount - 1; // Índice para a inserção

        if (isLeaf) {
            // Se o nó é uma folha, insere a chave diretamente
            while (i >= 0 && keys[i] > key) {
                keys[i + 1] = keys[i]; // Move as chaves maiores para a direita
                i--;
            }
            keys[i + 1] = key; // Insere a nova chave
            currentKeyCount++; // Atualiza o contador de chaves

        } else {
            // Se o nó não é uma folha, encontra o filho onde a chave deve ser inserida
            while (i >= 0 && keys[i] > key) {
                i--;
            }

            // Se o filho está cheio, divide-o
            if (children[i + 1].currentKeyCount == 2 * minDegree - 1) {
                splitChild(i + 1, children[i + 1]);

                // Decide qual filho usar para a inserção
                if (keys[i + 1] < key) {
                    i++;
                }
            }
            children[i + 1].insertNonFull(key); // Insere a chave no filho apropriado
        }
    }

    // Método para dividir um filho
    public void splitChild(int i, BTreeNode y) {
        BTreeNode newChild = new BTreeNode(y.minDegree, y.isLeaf);
        newChild.currentKeyCount = minDegree - 1; // Número de chaves no novo filho

        // Copia as chaves do nó y para o novo filho
        for (int j = 0; j < minDegree - 1; j++) {
            newChild.keys[j] = y.keys[j + minDegree];
        }

        // Se y não é uma folha, copia também os filhos
        if (!y.isLeaf) {
            for (int j = 0; j < minDegree; j++) {
                newChild.children[j] = y.children[j + minDegree];
            }
        }

        y.currentKeyCount = minDegree - 1; // Atualiza o contador de chaves do nó y

        // Move os filhos e as chaves para abrir espaço para o novo filho
        for (int j = currentKeyCount; j >= i + 1; j--) {
            children[j + 1] = children[j]; // Move os filhos para a direita
        }

        children[i + 1] = newChild; // Insere o novo filho

        for (int j = currentKeyCount - 1; j >= i; j--) {
            keys[j + 1] = keys[j]; // Move as chaves para a direita
        }

        keys[i] = y.keys[minDegree - 1]; // Move a chave do nó y para cima

        currentKeyCount++; // Atualiza o contador de chaves
    }

    // Método para buscar uma chave
    public boolean search(int key) {
        int i = 0;

        // Encontra o índice da chave
        while (i < currentKeyCount && key > keys[i]) {
            i++;
        }

        // Verifica se a chave foi encontrada
        if (i < currentKeyCount && keys[i] == key) {
            return true; // Chave encontrada
        }

        // Se o nó é uma folha, a chave não pode ser encontrada
        if (isLeaf) {
            return false;
        }

        // Procura no filho apropriado
        return children[i].search(key);
    }
}
