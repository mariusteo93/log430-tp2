package controllers;

import loanutils.CalcLoanItem;
import loanutils.FormatterFactory;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TabbedPanelDiffUpdate extends PanelUpdate {

    private List<JLabel> labelList = new ArrayList<JLabel>();

    public TabbedPanelDiffUpdate(Object source){
        super(source);
    }

    public void update() {
        setLabels();
    }

    public void setPanel(JPanel panel) {
        Component[] components = panel.getComponents();

        for(Component c : components) {
            if (c.getClass().equals("JLabel")) {
                labelList.add((JLabel) c);
            }
        }
    }

    private void setLabels() {
        Double lMensHorsAss1 = CalcLoanItem.computeMensHorsAss(super.getItem());
        if (lMensHorsAss1 == null) {
            lMensHorsAss1 = 0D;
        }
        Double lMensHorsAss2 = CalcLoanItem.computeMensHorsAss(super.getItem());
        if (lMensHorsAss2 == null) {
            lMensHorsAss2 = 0D;
        }
        Double lDiffMensHorsAss = lMensHorsAss1 - lMensHorsAss2;
        Double lMensAss1 = CalcLoanItem.computeMensAss(super.getItem());
        if (lMensAss1 == null) {
            lMensAss1 = 0D;
        }
        Double lMensAss2 = CalcLoanItem.computeMensAss(super.getItem());
        if (lMensAss2 == null) {
            lMensAss2 = 0D;
        }
        Double lDiffMensAss = lMensAss1 - lMensAss2;
        Double lMens1 = lMensHorsAss1 + lMensAss1;
        Double lMens2 = lMensHorsAss2 + lMensAss2;
        Double lDiffMens = lMens1 - lMens2;
        Double lCoutHorsAss1 = lMensHorsAss1 * super.getItem().getDuree() * 12D - super.getItem().getAmount();
        Double lCoutHorsAss2 = lMensHorsAss2 * super.getItem().getDuree() * 12D - super.getItem().getAmount();
        Double lDiffCoutHorsAss = lCoutHorsAss1 - lCoutHorsAss2;
        Double lCoutAss1 = lMensAss1 * super.getItem().getDuree() * 12D;
        Double lCoutAss2 = lMensAss2 * super.getItem().getDuree() * 12D;
        Double lDiffCoutAss = lCoutAss1 - lCoutAss2;
        Double lCout1 = lCoutHorsAss1 + lCoutAss1;
        Double lCout2 = lCoutHorsAss2 + lCoutAss2;
        Double lDiffCout = lCout1 - lCout2 + super.getItem().getFrais() - super.getItem().getFrais();
        Double lTauxEff1 = CalcLoanItem.calcTauxEff(super.getItem());
        Double lTauxEff2 = CalcLoanItem.calcTauxEff(super.getItem());
        Double lDiffTauxEff = (lTauxEff1 == null ? 0D : lTauxEff1) - (lTauxEff2 == null ? 0D : lTauxEff2);
        Double lPctSalary1 = super.getItem().getSalary().equals(0F) ? 0F : lMens1 / super.getItem().getSalary() * 100D;
        Double lPctSalary2 = super.getItem().getSalary().equals(0F) ? 0F : lMens2 / super.getItem().getSalary() * 100D;
        Double lDiffPctSalary = lPctSalary1 - lPctSalary2;
        Double lPerYear1 = lMens1 * 12D;
        Double lPerYear2 = lMens2 * 12D;
        Double lDiffPerYear = lPerYear1 - lPerYear2;

        for (Iterator<JLabel> it = labelList.listIterator(); it.hasNext();) {
            JLabel label = it.next();

            if (label.getName() == "menLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffMensHorsAss.floatValue()));
            }
            else if (label.getName() == "assLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffMensAss.floatValue()));
            }
            else if (label.getName() == "totLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffMens.floatValue()));
            }
            else if (label.getName() == "menCostLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffCoutHorsAss.floatValue()));
            }
            else if (label.getName() == "assCostLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffCoutAss.floatValue()));
            }
            else if (label.getName() == "totCostLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffCout.floatValue()));
            }
            else if (label.getName() == "effLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffTauxEff.floatValue()));
            }
            else if (label.getName() == "pctLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffPctSalary.floatValue()));
            }
            else if(label.getName() == "ytaLabel") {
                label.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffPerYear.floatValue()));
            }
        }
    }
}
