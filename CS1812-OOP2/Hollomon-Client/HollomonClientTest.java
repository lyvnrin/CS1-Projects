import java.util.List;  // Add this import for List

public class HollomonClientTest {

    public static void main(String[] args) {
        HollomonClientTest test = new HollomonClientTest();
        test.testLogin();
        test.testGetCards();
        test.testGetOffers();
        test.testGetCredits();
    }

    // Q3 login tests
    public void testLogin() {
        HollomonClient client = new HollomonClient("testserver.com", 8080);
        List<Card> cards = client.login("testuser", "testpassword");
        if (cards != null && cards.size() > 0) {
            System.out.println("testLogin passed");
        } else {
            System.out.println("testLogin failed");
        }
    }

    // Q5 tests
    // 5.1 retrieving cards
    public void testGetCards() {
        HollomonClient client = new HollomonClient("testserver.com", 8080);
        List<Card> cards = client.getCards();
        if (cards != null && cards.size() > 0) {
            System.out.println("testGetCards passed");
        } else {
            System.out.println("testGetCards failed");
        }
    }

    // 5.2 returning cards on offer
    public void testGetOffers() {
        HollomonClient client = new HollomonClient("testserver.com", 8080);
        List<Card> offers = client.getOffers();
        if (offers != null && offers.size() > 0) {
            System.out.println("testGetOffers passed");
        } else {
            System.out.println("testGetOffers failed");
        }
    }

    // 5.3 returning player credit balance
    public void testGetCredits() {
        HollomonClient client = new HollomonClient("testserver.com", 8080);
        long credits = client.getCredits();
        if (credits >= 0) {
            System.out.println("testGetCredits passed");
        } else {
            System.out.println("testGetCredits failed");
        }
    }
}
