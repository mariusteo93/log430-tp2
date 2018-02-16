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

public class OptionPanelUpdate extends EventObject implements InterfacePanelUpdater {

    private List<FloatJTextField> floatJTextFieldList = new ArrayList<FloatJTextField>();
    private Object loanItem;
    private Object controler=null;


    public OptionPanelUpdate(Object source){
        super(source);

        loanItem = super.getSource();
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
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol( ((LoanItem) loanItem).getFrais() ));
                fjtf.setEditable( !((LoanControler)controler).isDiffed() );
            }
            else if (fjtf.getName() == "assTF") {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol( ((LoanItem) loanItem).getInsurance() ));
                fjtf.setEditable( !((LoanControler)controler).isDiffed() );
            }
            else if (fjtf.getName() == "notTF") {
                Double lNotFee = CalcLoanItem.computeNotaryFee( (LoanItem) loanItem);
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol( lNotFee.floatValue() ));
                fjtf.setEditable( !((LoanControler)controler).isDiffed() );
            }
            else if (fjtf.getName() == "salTF") {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol( ((LoanItem) loanItem).getSalary() ));
                fjtf.setEditable(!((LoanControler)controler).isDiffed());
            }
        }
    }
}
