import java.io.*;

public class CardInputStream extends InputStream {
    private BufferedReader reader;

    // constructor
    public CardInputStream(InputStream input) {
        super();
        this.reader = new BufferedReader(new InputStreamReader(input));
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    // readResponses method
    public String readResponse() throws IOException {
        return reader.readLine();
    }

    public Card readCard() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            //System.out.println("End of stream reached.");
            return null;
        }

        if (line.equals("OK")) {
            //System.out.println("Received OK. No more cards.");
            return null;
        }

        if (!line.equals("CARD")) {
            throw new IOException("Unexpected format: " + line);
        }

        try {
            long id = Long.parseLong(reader.readLine());
            String name = reader.readLine();
            Rank rank = Rank.valueOf(reader.readLine());
            long price = Long.parseLong(reader.readLine());

            Card card = new Card(id, name, rank);
            card.setPrice(price);
            //System.out.println("Card details: ID=" + id + ", Name=" + name + ", Rank=" + rank + ", Price=" + price);

            return card;
        } catch (NumberFormatException | NullPointerException e) {
            throw new IOException("Invalid card format received", e);
        }
    }

    @Override
    public int read() throws IOException {
        return reader.read();
    }
}
