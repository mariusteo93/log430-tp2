package controllers;

import loanutils.CalcLoanItem;
import loanutils.FloatJTextField;
import loanutils.FormatterFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OptionPanelUpdate extends PanelUpdate {

    private List<FloatJTextField> floatJTextFieldList = new ArrayList<FloatJTextField>();
    private LoanControler controler = null;

    public OptionPanelUpdate(Object source){
        super(source);
    }

    public void setPanel(JPanel panel) {
        Component[] components = panel.getComponents();

        for (Component c : components) {
            if (c.getClass().equals("FloatJTextField")) {
                floatJTextFieldList.add((FloatJTextField) c);
            }
        }
    }

    public void setControler(LoanControler pControler) {
        controler = pControler;
    }

    public void update() {
        for (FloatJTextField fjtf : floatJTextFieldList) {
            if (fjtf.getName() == "afeTF") {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol( super.getItem().getFrais() ));
                fjtf.setEditable( !controler.isDiffed() );
            }
            else if (fjtf.getName() == "assTF") {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol( super.getItem().getInsurance() ));
                fjtf.setEditable( !controler.isDiffed() );
            }
            else if (fjtf.getName() == "notTF") {
                Double lNotFee = CalcLoanItem.computeNotaryFee( super.getItem() );
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol( lNotFee.floatValue() ));
                fjtf.setEditable( !controler.isDiffed() );
            }
            else if (fjtf.getName() == "salTF") {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol( super.getItem().getSalary() ));
                fjtf.setEditable( !controler.isDiffed() );
            }
        }
    }
}
