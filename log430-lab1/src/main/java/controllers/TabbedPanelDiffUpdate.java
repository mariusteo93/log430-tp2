package controllers;

import loanutils.CalcLoanItem;
import loanutils.FormatterFactory;
import loanutils.LoanItem;

import javax.swing.*;

public class TabbedPanelDiffUpdate extends AbstractPanelUpdater {

    JLabel menLabel=null;
    JLabel assLabel=null;
    JLabel totLabel=null;
    JLabel menCostLabel=null;
    JLabel assCostLabel=null;
    JLabel totCostLabel=null;
    JLabel effLabel=null;
    JLabel pctLabel=null;
    JLabel ytaLabel=null;

    public TabbedPanelDiffUpdate(LoanItem pItem1, LoanItem pItem2){
        Double lMensHorsAss1 = CalcLoanItem.computeMensHorsAss(pItem1);
        if (lMensHorsAss1 == null) {
            lMensHorsAss1 = 0D;
        }
        Double lMensHorsAss2 = CalcLoanItem.computeMensHorsAss(pItem2);
        if (lMensHorsAss2 == null) {
            lMensHorsAss2 = 0D;
        }
        Double lDiffMensHorsAss = lMensHorsAss1 - lMensHorsAss2;
        menLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffMensHorsAss.floatValue()));
        Double lMensAss1 = CalcLoanItem.computeMensAss(pItem1);
        if (lMensAss1 == null) {
            lMensAss1 = 0D;
        }
        Double lMensAss2 = CalcLoanItem.computeMensAss(pItem2);
        if (lMensAss2 == null) {
            lMensAss2 = 0D;
        }
        Double lDiffMensAss = lMensAss1 - lMensAss2;
        assLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffMensAss.floatValue()));
        Double lMens1 = lMensHorsAss1 + lMensAss1;
        Double lMens2 = lMensHorsAss2 + lMensAss2;
        Double lDiffMens = lMens1 - lMens2;
        totLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffMens.floatValue()));
        Double lCoutHorsAss1 = lMensHorsAss1 * pItem1.getDuree() * 12D - pItem1.getAmount();
        Double lCoutHorsAss2 = lMensHorsAss2 * pItem2.getDuree() * 12D - pItem2.getAmount();
        Double lDiffCoutHorsAss = lCoutHorsAss1 - lCoutHorsAss2;
        menCostLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffCoutHorsAss.floatValue()));
        Double lCoutAss1 = lMensAss1 * pItem1.getDuree() * 12D;
        Double lCoutAss2 = lMensAss2 * pItem2.getDuree() * 12D;
        Double lDiffCoutAss = lCoutAss1 - lCoutAss2;
        assCostLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffCoutAss.floatValue()));
        Double lCout1 = lCoutHorsAss1 + lCoutAss1;
        Double lCout2 = lCoutHorsAss2 + lCoutAss2;
        Double lDiffCout = lCout1 - lCout2 + pItem1.getFrais() - pItem2.getFrais();
        totCostLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffCout.floatValue()));
        Double lTauxEff1 = CalcLoanItem.calcTauxEff(pItem1);
        Double lTauxEff2 = CalcLoanItem.calcTauxEff(pItem2);
        Double lDiffTauxEff = (lTauxEff1 == null ? 0D : lTauxEff1) - (lTauxEff2 == null ? 0D : lTauxEff2);
        effLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffTauxEff.floatValue()));
        Double lPctSalary1 = pItem1.getSalary().equals(0F) ? 0F : lMens1 / pItem1.getSalary() * 100D;
        Double lPctSalary2 = pItem2.getSalary().equals(0F) ? 0F : lMens2 / pItem2.getSalary() * 100D;
        Double lDiffPctSalary = lPctSalary1 - lPctSalary2;
        pctLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffPctSalary.floatValue()));
        Double lPerYear1 = lMens1 * 12D;
        Double lPerYear2 = lMens2 * 12D;
        Double lDiffPerYear = lPerYear1 - lPerYear2;
        ytaLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lDiffPerYear.floatValue()));
    }

    public void setParam( JLabel MenLabel, JLabel AssLabel, JLabel TotLabel, JLabel MenCostLabel, JLabel AssCostLabel, JLabel TotCostLabel, JLabel EffLabel, JLabel PctLabel,
                          JLabel YtaLabel){

        menLabel=MenLabel;
        assLabel=AssLabel;
        totLabel=TotLabel;
        assCostLabel=AssCostLabel;
        totCostLabel=TotCostLabel;
        effLabel=EffLabel;
        pctLabel=PctLabel;
        ytaLabel=YtaLabel;

    }

    public void setParams(Object panel) {

    }
}
