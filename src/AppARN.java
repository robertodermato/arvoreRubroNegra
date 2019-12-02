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


        listaCartas.add(card1);
        listaCartas.add(card2);
        listaCartas.add(card3);
        listaCartas.add(card4);
        listaCartas.add(card5);
        listaCartas.add(card6);

        System.out.println("Lista das cartas odrdenadas por valor - método positionsCentral");
        LinkedList <MagicCard> lista = listaCartas.positionsCentral();

        System.out.println(lista);

    }

}
