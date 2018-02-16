package controllers;

import loanutils.CalcLoanItem;
import loanutils.FormatterFactory;
import loanutils.LoanItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.ListIterator;

public class TabbedPanelUpdate extends EventObject implements InterfacePanelUpdater {

    private List<JLabel> labelList = new ArrayList<JLabel>();
    private Object item;

    public TabbedPanelUpdate(Object source){
        super(source);

        item = super.getSource();
    }


    public void setPanel(JPanel panel) {
        Component[] components = panel.getComponents();

        for (Component c : components) {
            if (c.getClass().equals(JLabel.class)) {
                labelList.add((JLabel) c);
            }
        }
    }

    public void update() {
        setLabels();
    }

    private void setLabels(){
        Double lMensHorsAss = CalcLoanItem.computeMensHorsAss((LoanItem) item);
        if (lMensHorsAss == null) {
            lMensHorsAss = 0D;
        }
        Double lMensAss = CalcLoanItem.computeMensAss((LoanItem) item);
        if (lMensAss == null) {
            lMensAss = 0D;
        }
        Double lMens = lMensHorsAss + lMensAss;
        Double lCoutHorsAss = lMensHorsAss * ((LoanItem) item).getDuree()
                * 12D - ((LoanItem) item).getAmount();
        Double lCoutAss = lMensAss * ((LoanItem) item).getDuree() * 12D;
        Double lCout = lCoutHorsAss + lCoutAss + (((LoanItem) item).getFrais() == null?
                0D : ((LoanItem) item).getFrais());
        Double lTauxEff = CalcLoanItem.calcTauxEff((LoanItem) item);
        Double lPctSalary = ((LoanItem) item).getSalary().equals(0F) ? 0F :
                lMens / ((LoanItem) item).getSalary() * 100D;
        Double lPerYear = lMens * 12D;




        for (ListIterator<JLabel> it = labelList.listIterator(); it.hasNext();) {

            JLabel label = it.next();

            if(label.getName() == "menLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lMensHorsAss.floatValue()));
            }
            else if (label.getName() == "assLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lMensAss.floatValue()));
            }
            else if (label.getName() == "totLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lMens.floatValue()));
            }
            else if (label.getName() == "menCostLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lCoutHorsAss.floatValue()));
            }
            else if (label.getName() == "assCostLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lCoutAss.floatValue()));
            }
            else if (label.getName() == "totCostLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lCout.floatValue()));
            }
            else if (label.getName() == "effLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lTauxEff == null ? 0F :
                        lTauxEff.floatValue()));
            }
            else if (label.getName() == "pctLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lPctSalary.floatValue()));
            }
            else if (label.getName() == "ytaLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lPerYear.floatValue()));
            }
        }
    }

}
