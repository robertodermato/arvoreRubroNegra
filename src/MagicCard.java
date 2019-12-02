public class MagicCard implements Comparable<MagicCard> {

    private String nome;
    private Double custo;

    public MagicCard(String nome, Double custo) {
        this.nome = nome;
        this.custo = custo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    @Override
    public int compareTo(MagicCard outraCard) {
        return custo.compareTo(outraCard.getCusto());
    }

    @Override
    public String toString() {
        return nome + " Custo: R$ " + custo;
    }
}
