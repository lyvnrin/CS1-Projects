import java.io.*;
import java.net.*;
import java.util.*;

public class HollomonClient {
    private String server;
    private int port;
    private Socket socket;
    private BufferedReader inputStream;
    private PrintWriter outputStream;

    // constructor
    public HollomonClient(String server, int port) {
        this.server = server;
        this.port = port;
    }

    // reading cards
    private CardInputStream getCardInputStream() throws IOException {
        return new CardInputStream(socket.getInputStream());
    }

    // retrieving cards
    public List<Card> login(String username, String password) {
        List<Card> cards = new ArrayList<>();
        try {
            socket = new Socket(server, port);
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = new PrintWriter(socket.getOutputStream(), true);

            outputStream.println(username.toLowerCase());
            outputStream.println(password);
            
            String response = inputStream.readLine();
            if (!response.equals("User " + username.toLowerCase() + " logged in successfully.")) {
                return null;
            }
            
            CardInputStream cardInput = getCardInputStream();
            Card card;
            while ((card = cardInput.readCard()) != null) {
                cards.add(card);
            }

            Collections.sort(cards);
            return cards;
        } catch (IOException e) {
            System.out.println("Error during login: " + e.getMessage());
            return null;
        }
    }

    public List<Card> getCards() {
        List<Card> cards = new ArrayList<>();
        try {
            outputStream.println("CARDS");
            CardInputStream cardInput = getCardInputStream();
            Card card;
            while ((card = cardInput.readCard()) != null) {
                cards.add(card);
            }
            Collections.sort(cards);
        } catch (IOException e) {
            System.out.println("Error retrieving cards: " + e.getMessage());
        }
        return cards;
    }

    // cards on offer
    public List<Card> getOffers() {
        List<Card> cards = new ArrayList<>();
        try {
            outputStream.println("OFFERS");
            CardInputStream cardInput = getCardInputStream();
            Card card;
            while ((card = cardInput.readCard()) != null) {
                cards.add(card);
            }
            Collections.sort(cards);
        } catch (IOException e) {
            System.out.println("Error retrieving offers: " + e.getMessage());
        }
        return cards;
    }

    // returning cards into sale
    public boolean sellCard(Card card, long price) {
        try {
            outputStream.println("SELL " + card.getId() + " " + price);
            String response = inputStream.readLine();
            return "OK".equals(response);
        } catch (IOException e) {
            System.out.println("Error selling card: " + e.getMessage());
            return false;
        }
    }

    // buying
    public boolean buyCard(Card card) {
        try {
            long credits = getCredits();
            if (credits < card.getPrice()) {
                return false;
            }
            
            outputStream.println("BUY " + card.getId());
            String response = inputStream.readLine();
            return "OK".equals(response);
        } catch (IOException e) {
            System.out.println("Error buying card: " + e.getMessage());
            return false;
        }
    }

    // selling
    public boolean sellCardAgain(Card card, long price) {
        try {
            outputStream.println("SELL " + card.getId() + " " + price);
            String response = inputStream.readLine();
            return "OK".equals(response);
        } catch (IOException e) {
            System.out.println("Error selling card again: " + e.getMessage());
            return false;
        }
    }

    // returning credit balance
    public long getCredits() {
        try {
            outputStream.println("CREDITS");
            String creditResponse = inputStream.readLine();
            String confirmation = inputStream.readLine();
            
            if (!"OK".equals(confirmation)) {
                throw new IOException("Unexpected response: " + confirmation);
            }
            return Long.parseLong(creditResponse);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error retrieving credits: " + e.getMessage());
            return -1;
        }
    }

    // closing connection
    public void close() {
        try {
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
