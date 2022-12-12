/**
 * 이벤트 당첨자는 티켓으로 교환할 초대장을 가지고 있고
 * 당첨되지 않은 사람은 티켓을 사기 위해 현금을 가지고있다.
 * 관람객이 가지고 올 수 있는 소지품은 초대장, 현금, 티켓 세 가지 뿐이다.
 * 관람객은 소지품을 보관할 용도로 가방을 들고 올 수 있다
 */

public class Bag{
    private Long amount; // 현금
    private Invitation invitation; // 초대자
    private Ticket ticket; // 티켓

    // 초대장 없이 현금만 보관
    public Bag(long amount) {
        this(null,amount);
    }

    // 현금과 초대장을 함께 보관
    public Bag(Invitation invitation, long amount){
        this.invitation = invitation;
        this.amount = amount;
    }

    // 초대장의 보유 여부를 판단하는 메서드
    public boolean hasInvitation(){
        return invitation != null;
    }

    // 티켓의 소유 여부를 판단하는 메서드
    public boolean hasTicket(){
        return ticket != null;
    }

    // 현금을 증가 시키거나 감소시키는 메서드
    public void minusAmount(Long amount){
        this.amount -= amount;
    }

    public void plusAmount(Long amount){
        this.amount += amount;
    }


    // 초대장을 티켓으로 교환하는 메서드
    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }


}