package controllers;

import loanutils.CalcLoanItem;
import loanutils.FormatterFactory;
import loanutils.LoanItem;

import javax.swing.*;
import java.util.EventObject;

public class TabbedPanelUpdate extends EventObject {

    JLabel menLabel=null;
    JLabel assLabel=null;
    JLabel totLabel=null;
    JLabel menCostLabel=null;
    JLabel assCostLabel=null;
    JLabel totCostLabel=null;
    JLabel effLabel=null;
    JLabel pctLabel=null;
    JLabel ytaLabel=null;

    public TabbedPanelUpdate(Object source){
        super(source);
//
//        Double lMensHorsAss = CalcLoanItem.computeMensHorsAss(pItem);
//        if (lMensHorsAss == null) {
//            lMensHorsAss = 0D;
//        }
//        menLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lMensHorsAss.floatValue()));
//        Double lMensAss = CalcLoanItem.computeMensAss(pItem);
//        if (lMensAss == null) {
//            lMensAss = 0D;
//        }
//        assLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lMensAss.floatValue()));
//        Double lMens = lMensHorsAss + lMensAss;
//        totLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lMens.floatValue()));
//        Double lCoutHorsAss = lMensHorsAss * pItem.getDuree() * 12D - pItem.getAmount();
//        menCostLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lCoutHorsAss.floatValue()));
//        Double lCoutAss = lMensAss * pItem.getDuree() * 12D;
//        assCostLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lCoutAss.floatValue()));
//        Double lCout = lCoutHorsAss + lCoutAss + (pItem.getFrais() == null? 0D : pItem.getFrais());
//        totCostLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lCout.floatValue()));
//        Double lTauxEff = CalcLoanItem.calcTauxEff(pItem);
//        effLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lTauxEff == null ? 0F : lTauxEff.floatValue()));
//        Double lPctSalary = pItem.getSalary().equals(0F) ? 0F : lMens / pItem.getSalary() * 100D;
//        pctLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lPctSalary.floatValue()));
//        Double lPerYear = lMens * 12D;
//        ytaLabel.setText(FormatterFactory.fmtCurrencyNoSymbol(lPerYear.floatValue()));
    }

//    public void setParam( JLabel MenLabel, JLabel AssLabel, JLabel TotLabel, JLabel MenCostLabel, JLabel AssCostLabel, JLabel TotCostLabel, JLabel EffLabel, JLabel PctLabel,
//                          JLabel YtaLabel){
//
//        menLabel=MenLabel;
//        assLabel=AssLabel;
//        totLabel=TotLabel;
//        assCostLabel=AssCostLabel;
//        totCostLabel=TotCostLabel;
//        effLabel=EffLabel;
//        pctLabel=PctLabel;
//        ytaLabel=YtaLabel;
//
//    }
//
//    void setParams(Object panel) {
//        System.out.println("YEE");
//    }
}
