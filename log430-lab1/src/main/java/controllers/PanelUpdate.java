package controllers;

import loanutils.LoanItem;

import java.util.EventObject;

public class PanelUpdate extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public PanelUpdate(Object source) {
        super(source);
    }

    public LoanItem getItem() {
        return (LoanItem) super.getSource();
    }

    public LoanItem.LoanType getLoanType() {
       return ( (LoanItem) super.getSource() ).getLoanType();

    }
}
