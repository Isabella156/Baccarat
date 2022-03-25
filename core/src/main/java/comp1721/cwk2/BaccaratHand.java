package comp1721.cwk2;

// Implement the BaccaratHand class here
public class BaccaratHand extends CardCollection{
    public BaccaratHand(){
        super();
    }
    @Override
    public int value(){
        int value = super.value();
        if(value >= 10){
            value -= 10;
        }
        return value;
    }

    public boolean isNatural(){
        return value() == 8 || value() == 9;
    }

    public String toString(){
        String str1 = "";
        for(Card card: cards){
            str1 += card.toString();
            str1 += " ";
        }
        return str1.trim();
    }
}