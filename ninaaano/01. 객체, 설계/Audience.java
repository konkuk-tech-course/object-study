/**
 * 관람객이라는 개념을 구현하는 클래스
 * 관람객은 소지품을 보관하기 위해 가방을 소지할 수 있다
 */
public class Audience {
    private Bag bag;

    public Audience(Bag bag){
        this.bag = bag;
    }

    public Bag getBag(){
        return bag;
    }
}
