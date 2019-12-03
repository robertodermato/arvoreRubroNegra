public class ArvoreRubroNegraMagicCard extends ArvoreRubroNegra<MagicCard> {

    /**
    * @param: nome, atributo a ser procurado
    * @return: retorna a magicCard que contém o atributo procurado. Retorna null se não encontrar
    * A partir de um atributo, procurar se o objeto está armazenado na árvore (se não estiver, retorna null):
    * Notação O (log n)
    */

    public MagicCard get(Double custo){
        if (custo == null) return null;
        if (isEmpty()) return null;

        NodoRubroNegro<MagicCard> raiz = getRoot();

        MagicCard carta = getAux(custo, raiz);

        return carta;
    }

    private MagicCard getAux (Double custo, NodoRubroNegro<MagicCard> nodo){

        if (nodo != null && nodo.key != null) {
            if (custo == nodo.key.getCusto()) return nodo.key;
            if (custo < nodo.key.getCusto() ) getAux(custo, nodo.left);
            if (custo > nodo.key.getCusto()) getAux(custo, nodo.right);
        }

        return null;
    }



}
