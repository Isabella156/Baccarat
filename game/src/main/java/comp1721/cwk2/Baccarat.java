package comp1721.cwk2;
import java.util.Scanner;

public class Baccarat {
    private static int rounds = 0;
    private static int playerWins = 0;
    private static int bankerWins = 0;
    private static int ties = 0;
    private static boolean interactive = false;
    private static final BaccaratHand PLAYER = new BaccaratHand();
    private static final BaccaratHand BANKER = new BaccaratHand();
    private static final Shoe SHOE1 = new Shoe(6);
    private static String winner = "";

    // prepare for the game
    public static void preparation(){
        SHOE1.shuffle();
        deal();
        cardInformation();
    }

    // determine if one or two players are natural
    public static void natural(){
        if(PLAYER.isNatural() && !BANKER.isNatural()){
            winner += "Player";
            playerWins ++;
        }else if(BANKER.isNatural() && !PLAYER.isNatural()){
            winner += "Banker";
            bankerWins ++;
        }else if(BANKER.isNatural() && PLAYER.isNatural()){
            winner += "Player and Banker";
            ties++;
        }
    }

    // interface of a game
    public static void game(){
        int newPlayerValue;
        int newBankerValue;
        boolean first;

        while(SHOE1.size() >= 6){
            first = false;
            winner = "";
            rounds ++;
            System.out.println("Round " + rounds);
            if(rounds == 1){
                preparation();
            }else{
                // clear the cards from the previous round
                PLAYER.discard();
                BANKER.discard();
                // redistribute cards
                deal();
                cardInformation();
            }
            int playerValue = PLAYER.value();
            int bankerValue = BANKER.value();
            newPlayerValue = -1;
            newBankerValue = -1;

            natural();
            if(!winner.equals("") && !winner.equals("Player and Banker")){
                System.out.println(winner + " win!\n");
                first = true;
            }else if(winner.equals("Player and Banker")){
                System.out.println("Ties!\n");
                first = true;
            }
            if(!first){
                if(playerValue <= 5){
                    System.out.println("Dealing third card to player...");
                    Card card1 = SHOE1.deal();
                    PLAYER.add(card1);
                    newPlayerValue = card1.value();
                }
                if(((bankerValue == 3 && newPlayerValue!= -1 &&
                        newPlayerValue != 8) ||
                        (bankerValue == 4 &&
                                newPlayerValue >= 2 && newPlayerValue <= 7) ||
                        (bankerValue == 5 &&
                                newPlayerValue >= 4 && newPlayerValue <= 7) ||
                        (bankerValue == 6 &&
                                (newPlayerValue == 6 || newPlayerValue == 7)) ||
                        bankerValue <=2 && newPlayerValue != -1) ||
                        (newPlayerValue == -1 && bankerValue <= 5)){
                    System.out.println("Dealing third card to banker...");
                    BANKER.add(SHOE1.deal());
                    newBankerValue = 0;
                }

                if(newPlayerValue != -1 || newBankerValue != -1){
                    Baccarat.cardInformation();
                }
                natural();
                if(!winner.equals("") && !winner.equals("Player and Banker")){
                    System.out.println(winner + " win!\n");
                }else if(winner.equals("")){
                    System.out.println("Tie!\n");
                    ties++;
                }
            }

            if(interactive){
                String answer;
                while(true){
                    System.out.print("Another round?(y/n):");
                    Scanner sc = new Scanner(System.in);
                    answer = sc.next();
                    if(answer.equals("y") || answer.equals("n")){
                        break;
                    }else{
                        System.out.println("Invalid input!");
                    }
                }
                if(answer.equals("n")){
                    break;
                }
            }
        }
        gameInformation();
    }

    // display the information of the game
    public static void gameInformation(){
        System.out.println(rounds + " rounds played");
        System.out.println(playerWins + " player wins");
        System.out.println(bankerWins + " banker wins");
        System.out.println(ties + " ties");
    }

    // display the information of the card
    public static void cardInformation(){
        System.out.println("Player: " + PLAYER.toString() + " = "
                + PLAYER.value());
        System.out.println("Banker: " + BANKER.toString() + " = "
                + BANKER.value());
    }

    // deal the cards to the player and banker
    public static void deal(){
        PLAYER.add(SHOE1.deal());
        BANKER.add(SHOE1.deal());
        PLAYER.add(SHOE1.deal());
        BANKER.add(SHOE1.deal());
    }

    public static void main(String[] args){
        if(args != null && args.length != 0 && args[0].compareTo("-i") == 0){
            interactive = true;
        }
        game();
    }
}