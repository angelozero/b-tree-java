# B-Tree em Java

Este projeto é uma implementação simples de uma B-Tree em Java, que é uma estrutura de dados amplamente utilizada em sistemas de gerenciamento de banco de dados e sistemas de arquivos. A B-Tree é uma árvore balanceada que permite buscas, inserções e deleções eficientes.

## O que é uma B-Tree?

Uma B-Tree é uma estrutura de dados que mantém os dados ordenados e permite buscas, inserções e deleções em tempo logarítmico. Ela é projetada para funcionar bem em ambientes que leem e escrevem grandes blocos de dados, como discos rígidos e sistemas de banco de dados.

### Estrutura da B-Tree

Em uma B-Tree de grau mínimo `t`:

- Cada nó pode ter no máximo `2t - 1` chaves.
- Cada nó pode ter no máximo `2t` filhos.
- A raiz deve ter pelo menos uma chave.
- Todos os nós folha estão no mesmo nível.

### Funcionalidades

#### Inserção

A inserção de uma nova chave na B-Tree é feita através do método `insert`. Se a raiz estiver cheia, ela é dividida, e a nova chave é inserida no nó apropriado.

```java
public void insert(int key) {
    if (root == null) {
        root = new BTreeNode(minDegree, true);
        root.keys[0] = key;
        root.currentKeyCount = 1;
    } else {
        if (root.currentKeyCount == 2 * minDegree - 1) {
            BTreeNode newRoot = new BTreeNode(minDegree, false);
            newRoot.children[0] = root;
            newRoot.splitChild(0, root);
            int i = 0;
            if (newRoot.keys[0] < key) {
                i++;
            }
            newRoot.children[i].insertNonFull(key);
            root = newRoot;
        } else {
            root.insertNonFull(key);
        }
    }
}
```

#### Consulta

A busca por uma chave é realizada pelo método `search`. O método percorre a árvore, comparando a chave procurada com as chaves armazenadas nos nós.

```java
public boolean search(int key) {
    return (root == null) ? false : root.search(key);
}
```

### Vantagens da B-Tree

1. **Eficiência em Disco:** A B-Tree é otimizada para ler e escrever grandes blocos de dados, tornando-a perfeita para sistemas de arquivos e bancos de dados.
2. **Tempo de Acesso Rápido:** As operações de busca, inserção e deleção são realizadas em tempo O(log n), onde n é o número de chaves na árvore.
3. **Balanceamento Automático:** A B-Tree se mantém balanceada automaticamente, garantindo que a profundidade da árvore seja mínima.

### Desvantagens da B-Tree

1. **Complexidade:** A implementação de uma B-Tree pode ser mais complexa do que outras estruturas de dados, como listas ou árvores binárias.
2. **Sobrecarga de Espaço:** Como cada nó pode ter múltiplas chaves, ele pode ocupar mais espaço em memória do que outras estruturas, especialmente se a árvore estiver subutilizada.

### Conclusão

A B-Tree é uma estrutura de dados poderosa e eficiente para armazenamento e recuperação de dados. Sua capacidade de manter os dados ordenados e sua eficiência em operações de leitura e escrita a tornam uma escolha popular em sistemas de gerenciamento de banco de dados.
