package controllers;

import loanutils.CalcLoanItem;
import loanutils.FormatterFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TabbedPanelUpdate extends PanelUpdate {

    private List<JLabel> labelList = new ArrayList<JLabel>();

    public TabbedPanelUpdate(Object source){
        super(source);
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
        Double lMensHorsAss = CalcLoanItem.computeMensHorsAss( super.getItem() );
        if (lMensHorsAss == null) {
            lMensHorsAss = 0D;
        }
        Double lMensAss = CalcLoanItem.computeMensAss( super.getItem() );
        if (lMensAss == null) {
            lMensAss = 0D;
        }
        Double lMens = lMensHorsAss + lMensAss;
        Double lCoutHorsAss = lMensHorsAss * super.getItem().getDuree()
                * 12D - super.getItem().getAmount();
        Double lCoutAss = lMensAss * super.getItem().getDuree() * 12D;
        Double lCout = lCoutHorsAss + lCoutAss + (super.getItem().getFrais() == null?
                0D : super.getItem().getFrais());
        Double lTauxEff = CalcLoanItem.calcTauxEff( super.getItem() );
        Double lPctSalary = super.getItem().getSalary().equals(0F) ? 0F :
                lMens / super.getItem().getSalary() * 100D;
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
