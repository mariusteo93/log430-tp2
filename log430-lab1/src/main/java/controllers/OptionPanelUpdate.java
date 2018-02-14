package controllers;

import loanutils.CalcLoanItem;
import loanutils.FloatJTextField;
import loanutils.FormatterFactory;
import loanutils.LoanItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class OptionPanelUpdate extends EventObject {

    private List<FloatJTextField> floatJTextFieldList = new ArrayList<FloatJTextField>();
    private Object controler=null;

    public OptionPanelUpdate( Object source, Object panel, Object controler){
        super(source);

        setParams(panel);

//        afeTF.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getFrais()));
//        assTF.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getInsurance()));
//        Double lNotFee = CalcLoanItem.computeNotaryFee(pItem);
//        notTF.setText(FormatterFactory.fmtCurrencyNoSymbol(lNotFee.floatValue()));
//        salTF.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getSalary()));
//        afeTF.setEditable(!controler.isDiffed());
//        assTF.setEditable(!controler.isDiffed());
//        notTF.setEditable(!controler.isDiffed());
//        salTF.setEditable(!controler.isDiffed());
    }

    private void setParams(Object panel) {
        Component[] components = ((JPanel) panel).getComponents();

        for (Component c : components) {
            if (c.getClass().equals("FloatJTextField")) {
                floatJTextFieldList.add((FloatJTextField) c);
            }
        }
    }

    public void compute(Object pItem) {
        for (FloatJTextField fjtf : floatJTextFieldList) {
            if (fjtf.getName() == "afeTF") {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol( ((LoanItem) pItem).getFrais() ));
                fjtf.setEditable( !((LoanControler)controler).isDiffed() );
            }
            else if (fjtf.getName() == "assTF") {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol( ((LoanItem) pItem).getInsurance() ));
                fjtf.setEditable( !((LoanControler)controler).isDiffed() );
            }
            else if (fjtf.getName() == "notTF") {
                Double lNotFee = CalcLoanItem.computeNotaryFee( (LoanItem) pItem);
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol( lNotFee.floatValue() ));
                fjtf.setEditable( !((LoanControler)controler).isDiffed() );
            }
            else if (fjtf.getName() == "salTF") {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol( ((LoanItem) pItem).getSalary() ));
                fjtf.setEditable(!((LoanControler)controler).isDiffed());
            }
        }
    }
}
