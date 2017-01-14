package de.lebk.verein.payment;

import de.lebk.verein.member.Member;

/**
 *
 * @author sopaetel
 */
public class Payment {

    private Member member;
    private PaymentState state;
    private double amount;

    public Payment(Member member, double amount) {
        this.member = member;
        this.amount = amount;
        this.state = PaymentState.OPEN;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public PaymentState getState() {
        return state;
    }

    public void setState(PaymentState state) {
        this.state = state;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
