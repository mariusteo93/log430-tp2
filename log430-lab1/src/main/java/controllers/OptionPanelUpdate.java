package controllers;

import loanutils.CalcLoanItem;
import loanutils.FloatJTextField;
import loanutils.FormatterFactory;
import loanutils.LoanItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OptionPanelUpdate extends AbstractPanelUpdater {

    private List<FloatJTextField> floatJTextFieldList = new ArrayList<FloatJTextField>();

    FloatJTextField afeTF=null;
    FloatJTextField assTF=null;
    FloatJTextField notTF=null;
    FloatJTextField salTF=null;
    LoanControler controler=null;

    public OptionPanelUpdate( LoanItem pItem, JPanel panel){
        setParams(panel);

        afeTF.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getFrais()));
        assTF.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getInsurance()));
        Double lNotFee = CalcLoanItem.computeNotaryFee(pItem);
        notTF.setText(FormatterFactory.fmtCurrencyNoSymbol(lNotFee.floatValue()));
        salTF.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getSalary()));
        afeTF.setEditable(!controler.isDiffed());
        assTF.setEditable(!controler.isDiffed());
        notTF.setEditable(!controler.isDiffed());
        salTF.setEditable(!controler.isDiffed());
    }

    void setParams(Object panel) {
        Component[] components = ((JPanel) panel).getComponents();

        for (Component c : components) {
            if (c.getClass().equals("FloatJTextField")) {
                floatJTextFieldList.add((FloatJTextField) c);
            }
        }


    }
}
