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

        int StartingMoney = STARTING_MONEY;

        while(StartingMoney > 0 && deck.size() >= 3)
        {
            if(deck.size() < SHUFFLE_THRESHOLD)
            {
                shuffleDeck();
            }

            if(deck.size() < 3)
            {
                System.out.println("Deck does not have a sufficient amount of cards");
            }
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
        for(int i = deck.size() - 1; i >= 0; i--)
        {
            int random = rand.nextInt(i+1);
            Card temp = deck.get(i);
            deck.set(i, deck.remove(random));
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