/**
 * 소극장을 구현한 클래스
 */
public class Theater {

    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    // 관람객을 맞이하는 메서드
    public void enter(Audience audience){
        if(audience.getBag().hasInvitation()){
            Ticket ticket = ticketSeller.getTicketOffice().getTickets();
            audience.getBag().setTicket(ticket);
        }else{
            Ticket ticket = ticketSeller.getTicketOffice().getTickets();
            audience.getBag().minusAmount(ticket.getFee());
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }

}
