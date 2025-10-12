import java.util.Objects;

public class Card implements Comparable<Card> {
    private final long id;
    private final String name;
    private final Rank rank;
    private long price;

    // constructor
    public Card(long id, String name, Rank rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.price = 0;
    }

    @Override
    public String toString() {
        return "Card{id = " + id + ", name ='" + name + "', rank = " + rank + ", price = " + price + "}";
    }

    // compares two Card objects against their names
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;
        return id == card.id && Objects.equals(name, card.name) && rank == card.rank;
    }

    // unique hash
    @Override
    public int hashCode() {
        return Objects.hash(id, name, rank);
    }

    @Override
    public int compareTo(Card other) {
        int rankComparison = this.rank.compareTo(other.rank);
        if (rankComparison != 0) return rankComparison;

        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) return nameComparison;

        return Long.compare(this.id, other.id);
    }

    // getters + setters for price, ID
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }
}
