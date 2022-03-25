package comp1721.cwk2;

// Implement the BaccaratCard class here
public class BaccaratCard extends Card{

    public BaccaratCard(Rank r, Suit s){
        super(r,s);
    }
    @Override
    public int value(){
        int value = super.value();
        if(value >= 10){
            value -= 10;
        }
        return value;
    }
}