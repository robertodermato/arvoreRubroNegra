public class ArvoreRubroNegraMagicCard extends ArvoreRubroNegra<MagicCard> {

    /**
    * @param: nome, atributo a ser procurado
    * @return: retorna a magicCard que contém o atributo procurado. Retorna null se não encontrar
    * A partir de um atributo, procurar se o objeto está armazenado na árvore (se não estiver, retorna null):
    * Notação O (log n)
    */

    public MagicCard get(String nome){
        if (nome == null) return null;
        if (isEmpty()) return null;

        NodoRubroNegro<MagicCard> raiz = getRoot();

        MagicCard carta = getAux(nome, raiz);

        return carta;
    }

    private MagicCard getAux (String nome, NodoRubroNegro<MagicCard> nodo){

        if (nodo != null && nodo.key != null) {
            if (nodo.key.getNome().equalsIgnoreCase(nome)) return nodo.key;
            if (nodo.key.getNome().compareTo(nome) >0 ) getAux(nome, nodo.left);
            if (nodo.key.getNome().compareTo(nome) <0 ) getAux(nome, nodo.right);
        }

        return null;
    }



}
