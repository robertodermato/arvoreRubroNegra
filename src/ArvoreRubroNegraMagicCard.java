public class ArvoreRubroNegraMagicCard extends ArvoreRubroNegra<MagicCard> {

    /**
     * A partir de um atributo, procurar se o objeto está armazenado na árvore (se não estiver, retorna null):
     *
     * @param: nome, atributo a ser procurado
     * @return: retorna a magicCard que contém o atributo procurado. Retorna null se não encontrar
     *
     * Notação O (log n)
     */

    public MagicCard getObj(Double custo){
        if (custo == null) return null;
        if (isEmpty()) return null;

        NodoRubroNegro<MagicCard> raiz = getRoot();

        MagicCard carta = getAux(custo, raiz);

        return carta;
    }

    private MagicCard getAux (Double custo, NodoRubroNegro<MagicCard> nodo){

        if (isNil(nodo) == false && nodo.key != null) {
            if (custo.equals(nodo.key.getCusto())) return nodo.key;
            if (isNil(nodo.left) == false && custo < nodo.key.getCusto() ) getAux(custo, nodo.left);
            if (isNil(nodo.right) == false && custo > nodo.key.getCusto()) getAux(custo, nodo.right);
        }

        return null;
    }



}
