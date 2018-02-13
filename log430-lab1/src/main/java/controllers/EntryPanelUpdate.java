package controllers;

import loanutils.FloatJTextField;
import loanutils.FormatterFactory;
import loanutils.LoanItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class EntryPanelUpdate  {
    private List<FloatJTextField> floatJTextFields = new ArrayList<FloatJTextField>();
    private List<JCheckBox> jCheckBoxes = new ArrayList<JCheckBox>();
    private LoanControler controler=null;
    private LoanItem pItem;

    /**
     * Constructs a prototypical Event.
     *
     * @param pItem The object on which the Event initially occurred.
     */
    public EntryPanelUpdate(LoanItem pItem) {
        this.pItem = pItem;

        setFJtf();
        setJCB();
    }

    public void setParam(JPanel panel){
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

    private void setFJtf() {
        for (FloatJTextField fjtf : floatJTextFields) {

            String name = fjtf.getName();

            if (name.equals("monTF")) {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getMensualite()));
                fjtf.setEditable(!controler.isDiffed());
            }
            else if(name.equals("tauTF")) {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getTaux()));
                fjtf.setEditable(!controler.isDiffed());
            }
            else if(name.equals("timTF")) {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getDuree()));
                fjtf.setEditable(!controler.isDiffed());
            }
            else if(name.equals("amoTF")) {
                fjtf.setText(FormatterFactory.fmtCurrencyNoSymbol(pItem.getAmount()));
            }
        }
    }

    private void setJCB() {
        for (JCheckBox jcb : jCheckBoxes) {

            String name = jcb.getName();

            if (name.equals("monCB")) {
                jcb.setSelected(pItem.getLoanType() != LoanItem.LoanType.MENSUALITE);
                jcb.setEnabled(!controler.isDiffed());
            }
            else if(name.equals("tauCB")) {
                jcb.setSelected(pItem.getLoanType() != LoanItem.LoanType.TAUX);
                jcb.setEnabled(!controler.isDiffed());
            }
            else if(name.equals("timTB")) {
                jcb.setSelected(pItem.getLoanType() != LoanItem.LoanType.DUREE);
                jcb.setEnabled(!controler.isDiffed());
            }
            else if(name.equals("amoCB")) {
                jcb.setSelected(pItem.getLoanType() != LoanItem.LoanType.MONTANT);
                jcb.setEnabled(!controler.isDiffed());
            }

        }
    }
}
