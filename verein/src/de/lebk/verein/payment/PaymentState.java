package de.lebk.verein.payment;

/**
 * Created by marcel on 22.12.16.
 */
public enum PaymentState {
    PAID("paid"),
    OPEN("open");

    private final String text;

    PaymentState(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    /* (non-Javadoc)
		 * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
