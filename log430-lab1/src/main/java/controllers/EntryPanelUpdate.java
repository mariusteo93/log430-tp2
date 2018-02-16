package controllers;

import loanutils.FloatJTextField;
import loanutils.FormatterFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntryPanelUpdate extends PanelUpdate {
    private List<FloatJTextField> floatJTextFields = new ArrayList<FloatJTextField>();
    private List<JCheckBox> jCheckBoxes = new ArrayList<JCheckBox>();
    private Object controler = null;

    public EntryPanelUpdate(Object source) {
        super(source);
    }


    public void setPanel(JPanel panel){
        Component[] components = panel.getComponents();

        for (Component c : components) {
            if (c.getClass().equals(FloatJTextField.class)) {
                floatJTextFields.add((FloatJTextField) c);
            }
            else if(c.getClass().equals(JCheckBox.class)) {
                jCheckBoxes.add((JCheckBox) c);
            }
        }
    }

    public void setControler(Object pControler) {
        controler = pControler;
    }

    public void update() {
        setFJtf();
        setJCB();
    }

    private void setFJtf() {
        for (FloatJTextField fjtf : floatJTextFields) {

            String name = fjtf.getName();

            if (name.equals("monTF")) {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol((super.getItem()).getMensualite()));
                fjtf.setEditable(!((LoanControler)controler).isDiffed());
            }
            else if(name.equals("tauTF")) {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol((super.getItem()).getTaux()));
                fjtf.setEditable(!((LoanControler)controler).isDiffed());
            }
            else if(name.equals("timTF")) {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol((super.getItem()).getDuree()));
                fjtf.setEditable(!((LoanControler)controler).isDiffed());
            }
            else if(name.equals("amoTF")) {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol((super.getItem()).getAmount()));
            }
        }
    }

    private void setJCB() {
        for (JCheckBox jcb : jCheckBoxes) {

            String name = jcb.getName();

            if (name.equals("monCB")) {
                jcb.setSelected((super.getLoanType() != super.getLoanType().MENSUALITE));
                jcb.setEnabled(!((LoanControler)controler).isDiffed());
            }
            else if(name.equals("tauCB")) {
                jcb.setSelected((super.getLoanType() != super.getLoanType().TAUX));
                jcb.setEnabled(!((LoanControler)controler).isDiffed());
            }
            else if(name.equals("timTB")) {
                jcb.setSelected((super.getLoanType() != super.getLoanType().DUREE));
                jcb.setEnabled(!((LoanControler)controler).isDiffed());
            }
            else if(name.equals("amoCB")) {
                jcb.setSelected((super.getLoanType() != super.getLoanType().MONTANT));
                jcb.setEnabled(!((LoanControler)controler).isDiffed());
            }
        }
    }
}
