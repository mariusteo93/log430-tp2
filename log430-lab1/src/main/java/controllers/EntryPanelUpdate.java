package controllers;

import loanutils.FloatJTextField;
import loanutils.FormatterFactory;
import loanutils.LoanItem;

import javax.swing.*;
import java.util.EventObject;

public class EntryPanelUpdate  {
    FloatJTextField amoTF=null;
    FloatJTextField tauTF=null;
    FloatJTextField monTF=null;
    FloatJTextField timTF=null;

    JCheckBox amoCB=null;
    JCheckBox tauCB=null;
    JCheckBox monCB=null;
    JCheckBox timCB=null;
    LoanControler controler=null;

    /**
     * Constructs a prototypical Event.
     *
     * @param pItem The object on which the Event initially occurred.
     */
    public EntryPanelUpdate(LoanItem pItem) {
        monTF.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getMensualite()));
        tauTF.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getTaux()));
        timTF.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getDuree()));
        amoTF.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getAmount()));
        amoCB.setSelected(pItem.getLoanType() != LoanItem.LoanType.MONTANT);
        tauCB.setSelected(pItem.getLoanType() != LoanItem.LoanType.TAUX);
        timCB.setSelected(pItem.getLoanType() != LoanItem.LoanType.DUREE);
        monCB.setSelected(pItem.getLoanType() != LoanItem.LoanType.MENSUALITE);
        monTF.setEditable(!controler.isDiffed());
        tauTF.setEditable(!controler.isDiffed());
        timTF.setEditable(!controler.isDiffed());
        amoTF.setEditable(!controler.isDiffed());
        amoCB.setEnabled(!controler.isDiffed());
        tauCB.setEnabled(!controler.isDiffed());
        timCB.setEnabled(!controler.isDiffed());
        monCB.setEnabled(!controler.isDiffed());
    }

    public void setParam(FloatJTextField AmoTF, FloatJTextField TauTF, FloatJTextField MonTF, FloatJTextField TimTF, JCheckBox AmoCB
            , JCheckBox TauCB, JCheckBox MonCB, JCheckBox TimCB, LoanControler Controler ){
        amoTF=AmoTF;
        tauTF=TauTF;
        monTF=MonTF;
        timTF=TimTF;

        amoCB=AmoCB;
        tauCB=TauCB;
        monCB=MonCB;
        timCB=TimCB;
        controler=Controler;
    }
}
