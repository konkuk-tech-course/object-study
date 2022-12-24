package org.example;

public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audieceCount){
        Money fee = screening.calculateFee(audieceCount);
        return new Reservation(customer,screening,fee,audieceCount);
    }

}
