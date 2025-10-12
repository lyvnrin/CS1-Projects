import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CardTest {
    public static void main(String[] args) {
        
        // Q2 Tests
        // card objects
        Card card1 = new Card(1, "Vampire", Rank.UNIQUE);
        Card card2 = new Card(2, "Phoenix", Rank.RARE);
        Card card3 = new Card(3, "Ogre", Rank.COMMON);
        Card card4 = new Card(4, "Elf Queen", Rank.UNCOMMON);
        Card card5 = new Card(5, "Vampire", Rank.UNIQUE); // duplicate

        // 2.1 toString test
        System.out.println("Testing toString(): ");
        System.out.println(card1);
        System.out.println(card2);

        // 2.2 equals method test
        assert card1.equals(card5): "!!: card 1 should be equal to card 5";
        assert !card1.equals(card2) : "!!: card1 should not be equal to card 2";

        // 2.3 hash code method test
        assert card1.hashCode() == card5.hashCode() : "!!: card1 + card5 should have the same hashCode";
        assert card1.hashCode() != card2.hashCode() : "!!: card1 + card2 should have different hashCodes";

        // 2.4 compareTo method test
        assert card1.compareTo(card2) < 0 : "!!: card1 should come before card2";
        assert card2.compareTo(card3) < 0 : "!!: card2 should come before card3";
        assert card3.compareTo(card4) > 0 : "!!: card3 should come after card4";
        assert card1.compareTo(card5) == 0 : "!!: card1 + card5 should be equal in comparison";

        // 2.5 hashSet behaviour test - no duplicates (card 5)
        Set<Card> cardHashSet = new HashSet<>();
        cardHashSet.add(card1);
        cardHashSet.add(card2);
        cardHashSet.add(card3);
        cardHashSet.add(card4);
        cardHashSet.add(card5); 
        
        assert cardHashSet.size() == 4 : "!!: HashSet should only contain unique elements";
        
        // 2.6 treeSet behaviour test - correct sorting
        Set<Card> cardTreeSet = new TreeSet<>();
        cardTreeSet.add(card1);
        cardTreeSet.add(card2);
        cardTreeSet.add(card3);
        cardTreeSet.add(card4);
        
        System.out.println("\nTesting TreeSet sorting:");
        for (Card card : cardTreeSet) {
            System.out.println(card);
        }
    }
}
