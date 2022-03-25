package comp1721.cwk2;
import java.util.Collections;
// Implement the Shoe class here
public class Shoe extends CardCollection{
    public Shoe(int decks){
        if(decks != 6 && decks != 8){
            throw new CardException("Invalid number of decks!");
        }
        for(int i = 0; i < decks; i++){
            for (Card.Suit suit1: Card.Suit.values()){
                for(Card.Rank rank1: Card.Rank.values()){
                    BaccaratCard card1 = new BaccaratCard(rank1, suit1);
                    cards.add(card1);
                }
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card deal(){
        if(cards.isEmpty()){
            throw new CardException("Empty shoe!");
        }else{
            Card card = cards.get(0);
            cards.remove(0);
            return card;
        }
    }
}
