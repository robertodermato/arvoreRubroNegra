import java.util.LinkedList;

public class AppARN {

    public static void main(String[] args) {

        ArvoreRubroNegra<MagicCard> listaCartas = new ArvoreRubroNegra<>();

        MagicCard card1 = new MagicCard("Dragão de Shiva", 57.50);
        MagicCard card2 = new MagicCard("Feiticeiro Pródigo", 12.40);
        MagicCard card3 = new MagicCard("Anular", 1.5);
        MagicCard card4 = new MagicCard("Ilha", 0.1);
        MagicCard card5 = new MagicCard("Flor de Lotus",12000.0);
        MagicCard card6 = new MagicCard("Arrefecer", 7.0);

        MagicCard card8 = new MagicCard("Não será adicionada na ARN", 0.0);

        //Testando método add()
        listaCartas.add(card1);
        listaCartas.add(card2);
        listaCartas.add(card3);
        listaCartas.add(card4);
        listaCartas.add(card5);
        listaCartas.add(card6);

        //Testando métod getParent()
        MagicCard pai = listaCartas.getParent(card5);
        System.out.println("\nO pai da carta " + card5 + " é: " + pai);

        //Testando método contains()
        System.out.println("\nA árvore contém a carta card5: " + listaCartas.contains(card5));
        System.out.println("A árvore contém a carta card8: " + listaCartas.contains(card8));

        //Testando método height()
        System.out.println("\nA altura da árvore é: " + listaCartas.height());

        //Testando método size()
        System.out.println("\nO tamanho da árvore é: " + listaCartas.size());

        //Testando método isEmpty()
        ArvoreRubroNegra<MagicCard> arvoreVazia = new ArvoreRubroNegra<>();
        System.out.println("\nA árvore está vazia: " + listaCartas.isEmpty());
        System.out.println("A árvore vazia está vazia: " + arvoreVazia.isEmpty());
        System.out.println("Tamanho da vazia é: " + arvoreVazia.size());

        //Testando método clone()
        ArvoreRubroNegra clone = listaCartas.clone();
        System.out.println("\nÁrvore original: " + listaCartas);
        System.out.println("Árvore clonada:  " + clone);
        System.out.println("Árvore original: " + listaCartas.positionsCentral());
        System.out.println("Árvore clonada:  " + clone.positionsCentral());

        System.out.println("\nLista das cartas ordenadas por valor - método positionsCentral");
        LinkedList <MagicCard> lista = listaCartas.positionsCentral();
        System.out.println(lista);

        System.out.println("\nLista das cartas ordenadas pelo método positionsWidth");
        LinkedList <MagicCard> lista2 = listaCartas.positionsWidth();
        System.out.println(lista2);


    }

}
