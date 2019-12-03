/*
 * Ávore rubro-negra baseada na árvore de Zarar Siddiqi (Arsenalist)
 * disponível em: https://github.com/Arsenalist/Red-Black-Tree-Java-Implementation/blob/master/src/RedBlackTree.java
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArvoreRubroNegra<T extends Comparable<T>> {

    public class NodoRubroNegro<E extends Comparable<E>> {

        // cores possiveis
        public static final int BLACK = 0;
        public static final int RED = 1;

        // a chave para cada nodo
        public E key;

        NodoRubroNegro<E> parent;
        NodoRubroNegro<E> left;
        NodoRubroNegro<E> right;

        // número de elementos à esquerda de cada nodo
        public int numLeft = 0;
        // número de elementos à direita de cada nodo
        public int numRight = 0;

        public int color;

        //construtor para poder construir os nodos folhas nil
        NodoRubroNegro() {
            color = BLACK;
            numLeft = 0;
            numRight = 0;
            parent = null;
            left = null;
            right = null;
        }

        //construtor para colocar os objetos
        NodoRubroNegro(E key) {
            this();
            this.key = key;
        }
    }

    // Raiz inicializada com nil
    private NodoRubroNegro<T> nil = new NodoRubroNegro<T>();
    private NodoRubroNegro<T> root;

    public ArvoreRubroNegra() {
        root = nil;
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

    // @param: x, nodo sobre o qual será executada uma rotação para esquerda
    private void leftRotate(NodoRubroNegro<T> x) {

        // chama leftRotateFixup() que faz update em numLeft e numRight
        leftRotateFixup(x);

        // faz o left rotate
        NodoRubroNegro<T> y;
        y = x.right;
        x.right = y.left;

        //checa de y.left exsite e faz mudanças necessárias
        if (!isNil(y.left))
            y.left.parent = x;
        y.parent = x.parent;

        //pai do x é nulo
        if (isNil(x.parent))
            root = y;

            // x é o filho da esquerda
        else if (x.parent.left == x)
            x.parent.left = y;

            // x é o filho da direita
        else
            x.parent.right = y;

        // termina o leftRotate
        y.left = x;
        x.parent = y;
    }

    //retirna raiz
    public NodoRubroNegro getRoot(){
        return root;
    }

    /**
    * @param: x, Nodo sobre o qual o leftRotate é executado.
    * Esse método faz um update nos valores de numLeft e numRight que serão afetados pelo leftRotate.
    */
    private void leftRotateFixup(NodoRubroNegro x) {

        // Caso 1: Somente x, x.right e x.right.right sempre são não nil.
        if (isNil(x.left) && isNil(x.right.left)) {
            x.numLeft = 0;
            x.numRight = 0;
            x.right.numLeft = 1;
        }

        // Caso 2: x.right.left também existe em adição ao Caso 1
        else if (isNil(x.left) && !isNil(x.right.left)) {
            x.numLeft = 0;
            x.numRight = 1 + x.right.left.numLeft +
                    x.right.left.numRight;
            x.right.numLeft = 2 + x.right.left.numLeft +
                    x.right.left.numRight;
        }

        // Caso 3: x.left existe em adição ao Caso 1
        else if (!isNil(x.left) && isNil(x.right.left)) {
            x.numRight = 0;
            x.right.numLeft = 2 + x.left.numLeft + x.left.numRight;

        }
        // Caso 4: x.left e x.right.left existem em adição ao Caso 1
        else {
            x.numRight = 1 + x.right.left.numLeft +
                    x.right.left.numRight;
            x.right.numLeft = 3 + x.left.numLeft + x.left.numRight +
                    x.right.left.numLeft + x.right.left.numRight;
        }

    }

    /**
    * @param: x, nodo sobre o qual o rightRotate é executado
    * Atualiza os valores de numLeft e numRight afetados pela rotação.
    */
    private void rightRotate(NodoRubroNegro<T> y) {

        // chama rightRotateFixup para ajustar os valores de numRight e numLeft
        rightRotateFixup(y);

        // faz a rotação
        NodoRubroNegro<T> x = y.left;
        y.left = x.right;

        // checa se x.right existe
        if (!isNil(x.right))
            x.right.parent = y;
        x.parent = y.parent;

        // y.parent é nil
        if (isNil(y.parent))
            root = x;

            // y é o filho da direita
        else if (y.parent.right == y)
            y.parent.right = x;

            // y é o filho da esquerda
        else
            y.parent.left = x;
        x.right = y;

        y.parent = x;

    }

    /**
    * @param: y, nodo sobre o qual o righRotate é executado.
    * Atualiza os valores de numLeft e numRight afetaods pela rotação
    */
    private void rightRotateFixup(NodoRubroNegro y) {

        // Caso 1: Somente y, y.left e y.left.left existem.
        if (isNil(y.right) && isNil(y.left.right)) {
            y.numRight = 0;
            y.numLeft = 0;
            y.left.numRight = 1;
        }

        // Caso 2: y.left.right também existe
        else if (isNil(y.right) && !isNil(y.left.right)) {
            y.numRight = 0;
            y.numLeft = 1 + y.left.right.numRight +
                    y.left.right.numLeft;
            y.left.numRight = 2 + y.left.right.numRight +
                    y.left.right.numLeft;
        }

        // Caso 3: y.right também existe em adição ao caso1
        else if (!isNil(y.right) && isNil(y.left.right)) {
            y.numLeft = 0;
            y.left.numRight = 2 + y.right.numRight + y.right.numLeft;

        }

        // Caso 4: y.right e y.left.right existem
        else {
            y.numLeft = 1 + y.left.right.numRight +
                    y.left.right.numLeft;
            y.left.numRight = 3 + y.right.numRight +
                    y.right.numLeft +
                    y.left.right.numRight + y.left.right.numLeft;
        }

    }

    /**
     * Adiciona elementos na árvore
     *
     * @param: novoNodo, objeto a ser inserido
     *
     * Notação O (log n)
     */
    public void add(T novoNodo) {
        add(new NodoRubroNegro<T>(novoNodo));
    }

    // insere novoNodo no local adequado e faz update nos valores de numLeft e numRight.
    private void add(NodoRubroNegro<T> novoNodo) {

        // Cria uma referência para raiz e inicializa um nodo como mil
        NodoRubroNegro<T> y = nil;
        NodoRubroNegro<T> x = root;

        // Enquanto não se chega ao final da árvore continue vendo onde colocar o nonoNodo
        while (!isNil(x)) {
            y = x;

            // se novoNodo.key é menor que key, vai para esquerda
            if (novoNodo.key.compareTo(x.key) < 0) {

                // atualiza x.numLeft
                x.numLeft++;
                x = x.left;
            }

            // senao vai para direita
            else {

                // atualiza x.numRight
                x.numRight++;
                x = x.right;
            }
        }
        // y é referência para o pai
        novoNodo.parent = y;

        // Dependendo do valor de y.key, coloca o novoNodo à esquerda ou à direita
        if (isNil(y))
            root = novoNodo;
        else if (novoNodo.key.compareTo(y.key) < 0)
            y.left = novoNodo;
        else
            y.right = novoNodo;

        // inicializa os filhos do novonodo com nil e sua cor para vemelho
        novoNodo.left = nil;
        novoNodo.right = nil;
        novoNodo.color = NodoRubroNegro.RED;

        // chama insertFixup
        insertFixup(novoNodo);

    }


    /**
    * @param: novoNodo, nodo  que foi inserido e que pode ter causado violação nas regras da ARN
    * Arruma as propriedades da ARN
    */
    private void insertFixup(NodoRubroNegro<T> novoNodo) {

        NodoRubroNegro<T> y = nil;

        // nodo inserido é da cor vermelha, logo seu pai não pode ser vermelho
        while (novoNodo.parent.color == NodoRubroNegro.RED) {

            //se o pai do novoNodo e um filho da esquerda
            if (novoNodo.parent == novoNodo.parent.parent.left) {

                // y é o primo do novoNodo
                y = novoNodo.parent.parent.right;

                // Caso 1: se y for vermelho: recolorir
                if (y.color == NodoRubroNegro.RED) {
                    novoNodo.parent.color = NodoRubroNegro.BLACK;
                    y.color = NodoRubroNegro.BLACK;
                    novoNodo.parent.parent.color = NodoRubroNegro.RED;
                    novoNodo = novoNodo.parent.parent;
                }
                // Caso 2: se y for negro e novoNodo for um filho da direita
                else if (novoNodo == novoNodo.parent.right) {

                    // leftRotate ao redor do pai do novoNodo
                    novoNodo = novoNodo.parent;
                    leftRotate(novoNodo);
                }

                // Caso 3: y é negro e novoNodo é um filho da esquerda
                else {
                    // recolore e rotaciona ao redor do avo do novoNodo
                    novoNodo.parent.color = NodoRubroNegro.BLACK;
                    novoNodo.parent.parent.color = NodoRubroNegro.RED;
                    rightRotate(novoNodo.parent.parent);
                }
            }

            // se o pai do novoNodo for um filho da direita
            else {

                // y é o primo do novoNodo
                y = novoNodo.parent.parent.left;

                // Caso 1: se y for vermelho, recolore
                if (y.color == NodoRubroNegro.RED) {
                    novoNodo.parent.color = NodoRubroNegro.BLACK;
                    y.color = NodoRubroNegro.BLACK;
                    novoNodo.parent.parent.color = NodoRubroNegro.RED;
                    novoNodo = novoNodo.parent.parent;
                }

                // Caso 2: se y for negro e novonodo um filho da esquerda
                else if (novoNodo == novoNodo.parent.left) {
                    // rightRotate ao redor do pai do novoNodo
                    novoNodo = novoNodo.parent;
                    rightRotate(novoNodo);
                }

                // Caso 3: se y é negro e novonodo um filho da direita
                else {
                    // recolore e rotaciona ao redor do avô do novoNodo
                    novoNodo.parent.color = NodoRubroNegro.BLACK;
                    novoNodo.parent.parent.color = NodoRubroNegro.RED;
                    leftRotate(novoNodo.parent.parent);
                }
            }
        }

        root.color = NodoRubroNegro.BLACK;

    }

    /**
    * @param: node, um nodo
    * @return: node, retorna o nodo com a menor key com raiz em node
    */
    public NodoRubroNegro<T> getSmallest(NodoRubroNegro<T> node) {
        while (!isNil(node.left))
            node = node.left;
        return node;
    }

    /**
    * @param: x, um nodo cujo sucessor queremos achar
    * @return: retorna o nodo com a maior chave a partir de x.key
    */
    public NodoRubroNegro<T> treeSuccessor(NodoRubroNegro<T> x) {

        // se x.left não for nulo, chama getSmallest à direita, ou seja, o segundo maior valor depois de x.key
        if (!isNil(x.left))
            return getSmallest(x.right);

        NodoRubroNegro<T> y = x.parent;

        // enquanto x for filho da direita...
        while (!isNil(y) && x == y.right) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    /**
    * @param: v, nodo que será removido
    * Remove v
    */
    public void remove(NodoRubroNegro<T> v) {

        NodoRubroNegro<T> z = searchRefNode(v.key);

        NodoRubroNegro<T> x = nil;
        NodoRubroNegro<T> y = nil;

        // se qualquer um dos filhos de z for nil, ele será removido
        if (isNil(z.left) || isNil(z.right))
            y = z;

            // senão removeremos o sucessor de z
        else y = treeSuccessor(z);

        // x será o filho da esquerda ou da direita de y (y só pode ter um filho, já que é o menor dessa subárvore)
        if (!isNil(y.left))
            x = y.left;
        else
            x = y.right;

        // liga pai de x com pai de y
        x.parent = y.parent;

        // se pai de y é nil, então x é a raiz
        if (isNil(y.parent))
            root = x;

            // se y é um filho da esquerda, deixa x no seu lugar
        else if (!isNil(y.parent.left) && y.parent.left == y)
            y.parent.left = x;

            // senão, se y é um filho da direita, deixa x no seu lugar
        else if (!isNil(y.parent.right) && y.parent.right == y)
            y.parent.right = x;

        // se y não for o z, transfere dados do y para o z
        if (y != z) {
            z.key = y.key;
        }

        // faz update nos valores de numLeft e numRight
        fixNodeData(x, y);

        // se a cor de y for negra, isso é uma violação das regras da ARN, então chama removeFixup()
        if (y.color == NodoRubroNegro.BLACK)
            removeFixup(x);
    }

    /**
    * @param: y, nodo que foi deletado da ARN
    * @param: key, valor da key que estava no y originalmente
    */
    private void fixNodeData(NodoRubroNegro<T> x, NodoRubroNegro<T> y) {

        NodoRubroNegro<T> current = nil;
        NodoRubroNegro<T> track = nil;

        // se x for nil, vai atualizando y.parent
        if (isNil(x)) {
            current = y.parent;
            track = y;
        }

        // se x não for nil, vai atualizando x.parent
        else {
            current = x.parent;
            track = x;
        }

        //enquanto não se chega na raiz
        while (!isNil(current)) {
            // se o nodo deletado tem uma key diferente do atual
            if (y.key != current.key) {

                // se o nodo deletado é maior que a current.key, atualiza numRight
                if (y.key.compareTo(current.key) > 0)
                    current.numRight--;

                // se o nodo deletado e menor que current.key, atualiza numLeft
                if (y.key.compareTo(current.key) < 0)
                    current.numLeft--;
            }

            // se o nodo deletado tem a mesma key do current
            else {
                // casos onde o node current tem algum filho nil
                if (isNil(current.left))
                    current.numLeft--;
                else if (isNil(current.right))
                    current.numRight--;

                    // casos onde current tem 2 filhos. Tem que ver se track é o da esquerda ou o da direita
                else if (track == current.right)
                    current.numRight--;
                else if (track == current.left)
                    current.numLeft--;
            }

            track = current;
            current = current.parent;

        }

    }

    /**
    * @param: x, o filho do nodo deletado pelo remove
    * Restaura as propriedades da ARN que podem ter sido violadas durante o remove
    */
    private void removeFixup(NodoRubroNegro<T> x) {

        NodoRubroNegro<T> w;


        while (x != root && x.color == NodoRubroNegro.BLACK) {

            // se x é o pai do filho da esquerda
            if (x == x.parent.left) {

                // w vira o irmão de x
                w = x.parent.right;

                // Caso 1, w é rubro
                if (w.color == NodoRubroNegro.RED) {
                    w.color = NodoRubroNegro.BLACK;
                    x.parent.color = NodoRubroNegro.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }

                // Caso 2, ambos filhos de w são negros
                if (w.left.color == NodoRubroNegro.BLACK &&
                        w.right.color == NodoRubroNegro.BLACK) {
                    w.color = NodoRubroNegro.RED;
                    x = x.parent;
                } else {
                    // Caso 3, filho da diretia de w é negro
                    if (w.right.color == NodoRubroNegro.BLACK) {
                        w.left.color = NodoRubroNegro.BLACK;
                        w.color = NodoRubroNegro.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    // Caso 4, w é ngro e seu filho da direita é vermelho
                    w.color = x.parent.color;
                    x.parent.color = NodoRubroNegro.BLACK;
                    w.right.color = NodoRubroNegro.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            }
            // se x é o filho da direita
            else {

                // deixa w como irmão da esquerda
                w = x.parent.left;

                // Caso 1, w é rubro
                if (w.color == NodoRubroNegro.RED) {
                    w.color = NodoRubroNegro.BLACK;
                    x.parent.color = NodoRubroNegro.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }

                // Caso 2, ambos filhos de w são negros
                if (w.right.color == NodoRubroNegro.BLACK &&
                        w.left.color == NodoRubroNegro.BLACK) {
                    w.color = NodoRubroNegro.RED;
                    x = x.parent;
                } else {
                    // Caso 3, filho da esquerda de w é negro
                    if (w.left.color == NodoRubroNegro.BLACK) {
                        w.right.color = NodoRubroNegro.BLACK;
                        w.color = NodoRubroNegro.RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }

                    // Caso 4, w é negro e seu filho da esquerda é vermelho
                    w.color = x.parent.color;
                    x.parent.color = NodoRubroNegro.BLACK;
                    w.left.color = NodoRubroNegro.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }

        // x fica negro para garantir as propriedades da ARN
        x.color = NodoRubroNegro.BLACK;
    }

    /**
    * @param: key, a key de cujo nodo estamos procurando
    * @return: retorna um nodo com a key. Se não encontrar retorn nulo
    * notação O(log n)
    */
    public NodoRubroNegro<T> searchRefNode(T key) {

        // Inicializa com a raiz para percorrer toda árvore
        NodoRubroNegro<T> current = root;

        while (!isNil(current)) {

            if (current.key.equals(key))
                return current;

            else if (current.key.compareTo(key) < 0)
                current = current.right;

            else
                current = current.left;
        }

        return null;
    }

    /**
    * @param: key, qualquer objeto com Comparable
    * @return: retorna o número de elementos maiores que key
    */
    public int numGreater(T key) {
        return findNumGreater(root, key);
    }

    /**
    * @param: key, qualquer objeto com Comparable
    * @return: retorna o número de elementos menores que key
    */
    public int numSmaller(T key) {
        return findNumSmaller(root, key);
    }

    /**
    * @param node, a raiz da árvore
    * @param key, a key que desejamos comparar
    * @return: retorna o número de nodos maiores que key
    */
    public int findNumGreater(NodoRubroNegro<T> node, T key) {
        if (isNil(node))
            return 0;

            // se a key for menor que a node.key, então todos elementos da direita são maiores
            // adiciona esses elementos e procura na esquerda
        else if (key.compareTo(node.key) < 0)
            return 1 + node.numRight + findNumGreater(node.left, key);

            // se a key for maior que node.key, então procura na direita
        else
            return findNumGreater(node.right, key);

    }

    /**
     * Retorna uma lista organizada de keys maiores que key. Tamanho da lista não será maior que maxReturned
     *
     * @param key         Key a ser procurada
     * @param maxReturned número máximo de resultados
     * @return retorna a lista com keys maiores que key
     */
    public List<T> getGreaterThan(T key, Integer maxReturned) {
        List<T> list = new ArrayList<T>();
        getGreaterThan(root, key, list);
        return list.subList(0, Math.min(maxReturned, list.size()));
    }

    private void getGreaterThan(NodoRubroNegro<T> node, T key,
                                List<T> list) {
        if (isNil(node)) {
            return;
        } else if (node.key.compareTo(key) > 0) {
            getGreaterThan(node.left, key, list);
            list.add(node.key);
            getGreaterThan(node.right, key, list);
        } else {
            getGreaterThan(node.right, key, list);
        }
    }

    /**
    * @param: node, a raiz da árvore
    * @param key, key a ser comparada
    * @return: retorna o número de nodos menores que key
    */
    public int findNumSmaller(NodoRubroNegro<T> node, T key) {

        if (isNil(node)) return 0;

        else if (key.compareTo(node.key) <= 0)
            return findNumSmaller(node.left, key);

            // se key for maior que node.key, todos os elementos a esquerda são menores e são adicionados
            // daí procura na direirta
        else
            return 1 + node.numLeft + findNumSmaller(node.right, key);

    }

    /**
    * @param: node, nodo a ser checado se é nil
    * @return: retorna true se for nil, caso contrário retorna false
    */
    private boolean isNil(NodoRubroNegro node) {
        return node == nil;
    }

    /**
     * Retorna o pai de um objeto fornecido
     *
     * @param: objeto, objeto cujo pai queremos encontrar
     * @return: retorna o objeto contigo no nodo pai do objeto fornecido
     *
     * Notação O (log n), pois usa o searchRefNode
     */
    public T getParent(T objeto) {
        if (objeto == null) return null;
        if (isEmpty()) return null;
        NodoRubroNegro<T> filho = searchRefNode(objeto);
        if (filho == null) return null;
        NodoRubroNegro<T> pai = filho.parent;
        if (pai == null) return null; //se o filho for a root
        T objetoPai = pai.key;
        return objetoPai;
    }

    /**
     * Verifica se um elemento está armazenado na árvore ou não
     *
     * @param: objeto, objeto que queremos saber se existe ou não na árvore
     * @return: retorna true se o objeto existir, em caso contrário, retorna false
     *
     * Notação O (log n), pois usa o searchRefNode
     */
    public boolean contains(T objeto) {
        NodoRubroNegro<T> nAux = searchRefNode(objeto);
        return (nAux != null);
    }

    /**
     * Retorna a altura da árvore
     *
     * @return: altura da árvore
     *
     * Notação O (n)
     */
    public int height() {
        if (isEmpty()) return -1;
        return heightAux(root);
    }

    private int heightAux(NodoRubroNegro target) {
        int height = 0;
        int heightLeft = 0;
        int heightRight = 0;


        if (isNil(target.left) && isNil(target.right)) return 1;

        if (isNil(target.left) == false) heightLeft = height + heightAux(target.left);
        if (isNil(target.right) == false) heightRight = height + heightAux(target.right);

        if (heightLeft > heightRight) height = 1 + heightLeft;
        else height = 1 + heightRight;

        return height;
    }

    public int height2() {
        //Implemente este metodo (de preferencia de forma recursiva)
        if (isEmpty()) return -1;
        return heightAux(root);
    }

    private int heightAux2(NodoRubroNegro aux) {
        int hAux = 1;
        int hAuxL = 0;
        int hAuxR = 0;
        if (aux.left != null) {
            hAuxL = heightAux2(aux.left);
        }
        if (aux.right != null) {
            hAuxR = heightAux2(aux.right);
        }

        if (hAuxL > hAuxR) {
            hAux = hAux + hAuxL;
        } else {
            hAux = hAux + hAuxR;
        }
        return hAux;
    }


    /**
     * Verifica quantos elementos tem na árvore
     *
     * @return: o número de elementos da árvore
     *
     * Notação O (1)
     */
    public int size() {
        if (isNil(root)) return 0;
        // soma os elementos da esquerda e os da direita, mais a raiz
        return root.numLeft + root.numRight + 1;
    }

    /**
     * Verifica se a árvore está vazia ou não
     *
     * @return: retorna true se a árvore está vazia, ou seja se size() é zero, caso contrário retorna false
     *
     * Notação O (1)
     */
    public boolean isEmpty() {
        return isNil(root);
    }

     /**
     * Retorna uma cópia da árvore.
     *
     * @return ArvoreRubroNegra, uma cópia desta árvore.
     *
     * Notação O (n)
     */
    @Override
    public ArvoreRubroNegra clone() {
        ArvoreRubroNegra clone = new ArvoreRubroNegra();
        cloneAux(root, clone);
        return clone;
    }

    private void cloneAux(NodoRubroNegro n, ArvoreRubroNegra clone) {
        if (isNil(n) == false) {
            clone.add(n.key);
            cloneAux(n.left, clone);
            cloneAux(n.right, clone);
        }

    }

    /**
     * Retorna uma lista com todos os elementos da árvore na ordem de
     * caminhamento central. Chama um método auxiliar recursivo.
     *
     * @return LinkedList, lista com os elementos da árvore
     *
     * Notação O (n)
     */
    public LinkedList positionsCentral() {
        LinkedList lista = new LinkedList();
        if (isEmpty() == false) positionsCentralAux(lista, root);
        return lista;
    }

    private void positionsCentralAux(LinkedList lista, NodoRubroNegro nodo) {
        if (nodo != null && nodo.key != null) {
            positionsCentralAux(lista, nodo.left);
            lista.add(nodo.key);
            positionsCentralAux(lista, nodo.right);
        }
    }

    /**
     * Retorna uma lista com todos os elementos da árvore na ordem de
     * caminhamento pré. Chama um método auxiliar recursivo.
     *
     * @return LinkedList, lista com os elementos da árvore
     *
     * Notação O (n)
     */
    public LinkedList positionsPre() {
        LinkedList lista = new LinkedList();
        if (isEmpty() == false) positionsPreAux(lista, root);
        return lista;
    }

    private void positionsPreAux(LinkedList lista, NodoRubroNegro nodo) {
        if (nodo != null && nodo.key != null) {
            lista.add(nodo.key);
            positionsCentralAux(lista, nodo.left);
            positionsCentralAux(lista, nodo.right);
        }
    }

    /**
     * Retorna uma lista com todos os elementos da árvore na ordem de
     * caminhamento pós. Chama um método auxiliar recursivo.
     *
     * @return LinkedList, lista com os elementos da árvore
     *
     * Notação O (n)
     */
    public LinkedList positionsPos() {
        LinkedList lista = new LinkedList();
        if (isEmpty() == false) positionsPosAux(lista, root);
        return lista;
    }

    private void positionsPosAux(LinkedList lista, NodoRubroNegro nodo) {
        if (nodo != null && nodo.key != null) {
            positionsCentralAux(lista, nodo.left);
            positionsCentralAux(lista, nodo.right);
            lista.add(nodo.key);
        }
    }

    /**
     * Retorna uma lista com todos os elementos da árvore na ordem de
     * caminhamento em largura.
     *
     * @return LinkedList, lista com os elementos da árvore
     *
     * Notação O (n)
     */
    public LinkedList positionsWidth() {
        Queue<NodoRubroNegro> fila = new Queue<>();
        LinkedList lista = new LinkedList();

        if (isEmpty()) return null;

        fila.enqueue(root);

        while (!fila.isEmpty()) {
            if (isNil(fila.head().left) == false) fila.enqueue(fila.head().left);
            if (isNil(fila.head().right) == false) fila.enqueue(fila.head().right);
            lista.add(fila.dequeue().key);
        }

        return lista;
    }

}
