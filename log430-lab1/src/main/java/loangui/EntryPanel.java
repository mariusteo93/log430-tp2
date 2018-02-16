/*
 * The panels for entries components
 */
package loangui;

import com.google.common.eventbus.Subscribe;
import controllers.EntryPanelUpdate;
import controllers.LoanControler;
import loanutils.FloatJTextField;
import loanutils.FrameUtils;
import loanutils.LoanItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static loanutils.MyBundle.translate;

/**
 * The panels for entries components
 *
 * @author jean-blas imbert
 */
public class EntryPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    /**
     * Amount text field
     */
    private FloatJTextField amoTF = new FloatJTextField(10);
    /**
     * Taux text field
     */
    private FloatJTextField tauTF = new FloatJTextField(10);
    /**
     * Monthly fee text field
     */
    private FloatJTextField monTF = new FloatJTextField(10);
    /**
     * Duration text field
     */
    private FloatJTextField timTF = new FloatJTextField(10);
    /**
     * Checkbox to select the amount
     */
    private JCheckBox amoCB = new JCheckBox();
    /**
     * Checkbox to select the rate
     */
    private JCheckBox tauCB = new JCheckBox();
    /**
     * Checkbox to select the month length
     */
    private JCheckBox monCB = new JCheckBox();
    /**
     * Checkbox to select the duration
     */
    private JCheckBox timCB = new JCheckBox();
    /**
     * The loan controler
     */
    private LoanControler controler = null;
    /**
     * This field stores the current value when the mouse enter a text field
     */
    private Float curValue = null;

    /**
     * Constructor
     */
    public EntryPanel(final LoanControler pControler) {
        controler = pControler;

        layoutComponents();
        synchronizeCBandTF();
        //Add the check box action listener
        ActionListener lAL = buildCBActionListener();
        amoCB.addActionListener(lAL);
        tauCB.addActionListener(lAL);
        monCB.addActionListener(lAL);
        timCB.addActionListener(lAL);
        //Add text field focus listener
        FocusListener lFocusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent pEvent) {
                curValue = ((FloatJTextField) pEvent.getSource()).getValue();
            }

            @Override
            public void focusLost(FocusEvent pEvent) {
                Float lNewValue = ((FloatJTextField) pEvent.getSource()).getValue();
                if ((curValue == null && lNewValue != null) || (curValue != null && !curValue.equals(lNewValue))) {
                    controler.updateEntry(amoTF.getValue(), tauTF.getValue(), monTF.getValue(), timTF.getValue());
                }
                curValue = null;
            }
        };
        amoTF.addFocusListener(lFocusListener);
        tauTF.addFocusListener(lFocusListener);
        monTF.addFocusListener(lFocusListener);
        timTF.addFocusListener(lFocusListener);

        name__ALL_THE_THINGS();
    }

    @Subscribe
    public void updateChanges(EntryPanelUpdate event) {
        event.setPanel(this);
        event.setControler(controler);
        event.update();
    }

    /**
     * The check box action listener (same for all checkbox)
     *
     * @return the filled action listener
     */
    private ActionListener buildCBActionListener() {
        ActionListener lAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent pEvent) {
                JCheckBox lCB = ((JCheckBox) pEvent.getSource());
                if (amoCB.isSelected() && tauCB.isSelected() && monCB.isSelected() && timCB.isSelected()) {
                    JOptionPane.showMessageDialog(null, translate("only3over4"), translate("warning"), JOptionPane.WARNING_MESSAGE);
                    lCB.setSelected(false);
                }
                synchronizeCBandTF();
            }
        };
        return lAL;
    }

    /**
     * Synchronize the checkbox and the text fields. A text field is enable if and only if its corresponding checkbox is
     * selected.
     */
    public final void synchronizeCBandTF() {
        amoTF.setEnabled(amoCB.isSelected());
        tauTF.setEnabled(tauCB.isSelected());
        monTF.setEnabled(monCB.isSelected());
        timTF.setEnabled(timCB.isSelected());
        LoanItem.LoanType lType = LoanItem.LoanType.MENSUALITE;
        if (!amoCB.isSelected()) {
            lType = LoanItem.LoanType.MONTANT;
        } else if (!tauCB.isSelected()) {
            lType = LoanItem.LoanType.TAUX;
        } else if (!timCB.isSelected()) {
            lType = LoanItem.LoanType.DUREE;
        }
        controler.setLoanType(lType);
    }

    /**
     * Lay out the components
     */
    private void layoutComponents() {
        JLabel lAmoLabel = new JLabel(translate("montant"));
        lAmoLabel.setToolTipText(translate("montanttooltip"));
        amoTF.setToolTipText(translate("montanttooltip"));
        JLabel lTauLabel = new JLabel(translate("taux"));
        lTauLabel.setToolTipText(translate("tauxtooltip"));
        tauTF.setToolTipText(translate("tauxtooltip"));
        JLabel lMenLabel = new JLabel(translate("mensualite"));
        lMenLabel.setToolTipText(translate("mensualitetooltip"));
        monTF.setToolTipText(translate("mensualitetooltip"));
        JLabel lTimLabel = new JLabel(translate("duree"));
        lTimLabel.setToolTipText(translate("dureetooltip"));
        timTF.setToolTipText(translate("dureetooltip"));
        setBorder(BorderFactory.createTitledBorder(translate("entrees")));
        setToolTipText(translate("entreestooltip"));
        FrameUtils.buildPanelGroup(this, new JComponent[][]{{amoCB, lAmoLabel, amoTF, new JLabel("\u20ac")},
            {tauCB, lTauLabel, tauTF, new JLabel("%")}, {monCB, lMenLabel, monTF, new JLabel("\u20ac")},
            {timCB, lTimLabel, timTF, new JLabel(translate("an"))}});
    }

    /**
     * ─────────────────────────────▄██▄
     ─────────────────────────────▀███
     ───────────────▄▄▄▄▄────────────█
     ──────────────▀▄────▀▄──────────█
     ──────────▄▀▀▀▄─█▄▄▄▄█▄▄─▄▀▀▀▄──█
     ─────────█──▄──█────────█───▄─█─█
     ─────────▀▄───▄▀────────▀▄───▄▀─█
     ──────────█▀▀▀────────────▀▀▀─█─█
     ▄▀▄▄▀▄────█──▄█▀█▀█▀█▀█▀█▄────█─█
     █▒▒▒▒█────█──█████████████▄───█─█
     █▒▒▒▒█────█──██████████████▄──█─█
     █▒▒▒▒█────█───██████████████▄─█─█
     █▒▒▒▒█────█────██████████████─█─█
     █▒▒▒▒█────█───██████████████▀─█─█
     █▒▒▒▒█───██───██████████████──█─█
     ▀████▀──██▀█──█████████████▀──█▄█
     ──██───██──▀█──█▄█▄█▄█▄█▄█▀──▄█▀
     ──██──██────▀█─────────────▄▀▓█
     ──██─██──────▀█▀▄▄▄▄▄▄▄▄▄▀▀▓▓▓█
     ──████────────█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█
     ──███─────────█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█
     ──██──────────█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█
     */
  private void name__ALL_THE_THINGS() {

      // Name __ALL__ the FloatJTextFields
      amoTF.setName("amoTF");
      tauTF.setName("tauTF");
      monTF.setName("monTF");
      timTF.setName("timTF");

      // Name __ALL__ the JCheckboxes
      amoCB.setName("amoCB");
      tauCB.setName("tauCB");
      monCB.setName("monCB");
      timCB.setName("timCB");
  }
}
