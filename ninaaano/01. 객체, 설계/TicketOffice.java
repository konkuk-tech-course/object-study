import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 매표소에는 관람객에게 판매할 티켓과 티켓의 판매금액이 보관돼 있어야한다
 */
public class TicketOffice {
    private Long amount; // 판매금액
    private List<Ticket> tickets = new ArrayList<>(); // 판매하거나 교환해 줄 티켓의 목록

    /**
     *  여기서 Ticket ... tickets 은 '가변인자'라고 부른다
     *  가변인자란?
     *  '매개변수 타입 ... 변수명'
     *  가변인자가 내부적으로 '배열'을 생성하고 매개변수에 들어온 원소들을 해당 배열에 배치한다
     *  가변인자 외에도 다른 매개변수가 더 있다면 가변인자는 마지막에 선언해줘야한다
     */

    public TicketOffice(Long amount, Ticket ... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    // 편의를 위해 tickets의 컬렉션에서 맨 첫번째 위치에 저장된 Ticket을 반환
    public Ticket getTickets() {
        return tickets.remove(0);
    }

    // 판매 금액을 더하거나 차감하는 메소드
    public void minusAmount(Long amount){
        this.amount -= amount;
    }

    public void plusAmount(Long amount){
        this.amount += amount;
    }
}
