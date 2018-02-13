package controllers;

import loanutils.CalcLoanItem;
import loanutils.FloatJTextField;
import loanutils.FormatterFactory;
import loanutils.LoanItem;

public class OptionPanelUpdate  {
    FloatJTextField afeTF=null;
    FloatJTextField assTF=null;
    FloatJTextField notTF=null;
    FloatJTextField salTF=null;
    LoanControler controler=null;

    public OptionPanelUpdate( LoanItem pItem){
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

    public void setParam(FloatJTextField AfeTF, FloatJTextField AssTF, FloatJTextField NotTF, FloatJTextField SalTF, LoanControler Controler){
        afeTF=AfeTF;
        assTF=AssTF;
        notTF=NotTF;
        salTF=SalTF;
        controler=Controler;
    }
}
