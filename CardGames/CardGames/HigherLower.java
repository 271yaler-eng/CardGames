//--------------------------------------------------------------------
// HigherLower.java         by Dale/Joyce/Weems              Chapter 6
//
// Plays Higher or Lower? with user through command line interface. 
//--------------------------------------------------------------------
import java.util.Random;
import java.util.Scanner;

public class HigherLower 
{
    private static final int STARTING_MONEY = 100;
    private static final int SHUFFLE_THRESHOLD = 10;

    private static LinkedLog<Card> deck;
    private static LinkedLog<Card> discardPile;
    private static Random rand = new Random();
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        deck = new LinkedLog<Card>("Deck");
        discardPile = new LinkedLog<Card>("Discard Pile");

        createDeck();
        shuffleDeck();

        int m = STARTING_MONEY;

        System.out.println("Your money: " + m);

        while(m > 0)
        {
            if(deck.size() < SHUFFLE_THRESHOLD)
            {
                recycleCards();
            }

            if(deck.size() < 3)
            {
                System.out.println("Deck does not have a sufficient amount of cards");
                break;
            }

            int bet = getBet(input, m);

            System.out.println("Your money: " + m);

            while(bet == 0)
            {
                System.out.println("Please enter another bet 1 - " + m + ": ");
                bet = getBet(input, m);
            }

            Card C1 = deck.remove(0);
            Card C2 = deck.remove(0);

            System.out.println("Card 1: " + C1);
            System.out.println("Card 2: " + C2);

            int risk = bet;

            int compareCards = C1.compareTo(C2);

            if(compareCards != 0 && m >= bet * 2)
            {
                System.out.println("Would you like to double your bet?(y/n): ");
                String response = input.nextLine().trim();

                if(response.equalsIgnoreCase("y"))
                {
                    risk *= 2;
                }
                while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n"))
                {
                    System.out.println("Would you like to double your bet?(y/n): ");
                    response = input.nextLine().trim();
                }
            }

            if(compareCards == 0)
            {
                m += risk;

                System.out.println("The cards have the same rank!");
                System.out.println("You automatically win " + risk + " chips.");
            }
            else if(Math.abs(C1.getRank().ordinal() - C2.getRank().ordinal()) == 1)
            {
                m -= risk;

                System.out.println("The cards are one rank apart.");
                System.out.println("You lost: " + risk + "chips");
            }
            else
            {
                Card C3 = deck.remove(0);

                System.out.println("Card 3: " + C3);
                
                int low = Math.min(C1.getRank().ordinal(), C2.getRank().ordinal());
                int high = Math.max(C1.getRank().ordinal(), C2.getRank().ordinal());
                int C3value = C3.getRank().ordinal();

                if(C3value > low && C3value < high)
                {
                    m += risk;
                    System.out.println("The third card was in between the other two cards.");
                    System.out.println("You win: " + risk + " chips");
                }
                else{
                    m -= risk;
                    System.out.println("The third card was not in between the other two cards.");
                    System.out.println("You lost: " + risk + " chips");
                }
                discardPile.add(C3);
            }
            discardPile.add(C1);
            discardPile.add(C2);
        }

    }

    public static void createDeck()
    {
        for(Card.Suit suit : Card.Suit.values())
        {
            for(Card.Rank rank : Card.Rank.values())
            {
                deck.add(new Card(rank, suit));
            }
        }
    }

    public static void shuffleDeck()
    {
        for(int i = deck.size() - 1; i > 0; i--)
        {
            int random = rand.nextInt(i+1);
            Card temp = deck.get(i);
            deck.set(i, deck.get(random));
            deck.set(random, temp);
        }
    }

    public static boolean isBetween(Card C1, Card C2, Card C3)
    {
        if((C1.compareTo(C3) < 0 && C2.compareTo(C3) > 0) || (C1.compareTo(C3) > 0 && C2.compareTo(C3) < 0))
        {
            return true;
        }
        return false;
    }

    public static int getBet(Scanner input, int money)
    {
        while(true)
        {
            System.out.println("You have $" + money + ". Enter a number 1 - " + money + " to make your bet: ");

            String bet = input.nextLine().trim();

            try
            {
                int b = Integer.parseInt(bet);

                if(b > 0 && b <= money)
                {
                    return b;
                }
                if(b == 0)
                {
                    return 0;
                }

                System.out.println("Try a different bet: ");
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please enter a whole number");
            }
        }
    }

    public static void recycleCards()
    {
        if(deck.size() < 10)
        {
            for(int i = discardPile.size() - 1; i >= 0; i--)
            {
                deck.add(discardPile.get(i));
                discardPile.remove(i);
            }
        }
        shuffleDeck();
    }
}