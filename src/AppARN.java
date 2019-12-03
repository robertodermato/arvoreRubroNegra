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

        //Testando método add
        listaCartas.add(card1);
        listaCartas.add(card2);
        listaCartas.add(card3);
        listaCartas.add(card4);
        listaCartas.add(card5);
        listaCartas.add(card6);

        //Testando métod getParent
        MagicCard pai = listaCartas.getParent(card5);
        System.out.println("\nO pai da carta " + card5 + " é: " + pai);

        //Testando método contains
        System.out.println("\nA árvore contém a carta card5: " + listaCartas.contains(card5));
        System.out.println("\nA árvore contém a carta card8: " + listaCartas.contains(card8));

        //Testando método height
        System.out.println("\nA altura da árvore é: " + listaCartas.height());

        //Testando método

        //Testando método

        //Testando método

        System.out.println("\nLista das cartas ordenadas por valor - método positionsCentral");
        LinkedList <MagicCard> lista = listaCartas.positionsCentral();
        System.out.println(lista);

        System.out.println("\nLista das cartas ordenadas pelo método positionsWidth");
        LinkedList <MagicCard> lista2 = listaCartas.positionsWidth();
        System.out.println(lista2);


    }

}
