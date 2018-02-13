/*
 * This component represent the tabbed pane items panel
 */
package loangui;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import loanutils.CalcLoanItem;
import loanutils.ChangeListener;
import loanutils.DiffListener;
import loanutils.LoanItem;
import loanutils.FormatterFactory;
import loanutils.FrameUtils;
import static loanutils.MyBundle.translate;

/**
 * This component represent the tabbed pane items panel
 *
 * @author jean-blas imbert
 */
public class TabbedPanel extends JPanel implements ChangeListener, DiffListener {

    private static final long serialVersionUID = 1L;
    /**
     * Monthly without insurance amount label
     */
    private JLabel menLabel = new JLabel("0");
    /**
     * Monthly insurance only label
     */
    private JLabel assLabel = new JLabel("0");
    /**
     * Monthly total amount label
     */
    private JLabel totLabel = new JLabel("0");
    /**
     * Cost value label
     */
    private JLabel menCostLabel = new JLabel("0");
    /**
     * Cost insurance label
     */
    private JLabel assCostLabel = new JLabel("0");
    /**
     * Cost total amount label
     */
    private JLabel totCostLabel = new JLabel("0");
    /**
     * Effective rate value label
     */
    private JLabel effLabel = new JLabel("0");
    /**
     * Percentage of the salary label
     */
    private JLabel pctLabel = new JLabel("0");
    /**
     * Year total amount label
     */
    private JLabel ytaLabel = new JLabel("0");

    /**
     * Constructor
     */
    public TabbedPanel() {
        setLayout(new GridLayout(1, 3, 5, 5));
        add(buildMonthlyPanel());
        add(buildCostPanel());
        add(buildDiversPanel());
    }

    /**
     * Fill the components with their respective values
     *
     * @param pItem the Loan item corresponding to this panel
     */
    @Override
    public void itemChanged(final LoanItem pItem) {

    }

    // Subscribe to TabbedPanelUpdate event
//    @Subscribe
//    public void update(TabbedPanelUpdate event) {
//
//    }

    /**
     * Compute the real difference between two loan items
     *
     * @param pItem1 the first loan item
     * @param pItem2 the second loan item
     */
    @Override
    public void itemDiffed(final LoanItem pItem1, final LoanItem pItem2) {

    }

    /**
     * Construct the panel that contains the monthly result components
     *
     * @return the filled panel
     */
    private JPanel buildMonthlyPanel() {
        JLabel lMenLabel = new JLabel(translate("mensualitesvalue"));
        lMenLabel.setToolTipText(translate("mensualitesvaluetooltip"));
        menLabel.setToolTipText(translate("mensualitesvaluetooltip"));
        JLabel lAssLabel = new JLabel(translate("mensualiteassurance"));
        lAssLabel.setToolTipText(translate("mensualiteassurancetooltip"));
        assLabel.setToolTipText(translate("mensualiteassurancetooltip"));
        JLabel lTotLabel = new JLabel(translate("mensualitetotal"));
        lTotLabel.setToolTipText(translate("mensualitetotaltooltip"));
        totLabel.setToolTipText(translate("mensualitetotaltooltip"));
        JPanel lPanel = new JPanel();
        lPanel.setBorder(BorderFactory.createTitledBorder(translate("mensualites")));
        lPanel.setToolTipText(translate("mensualitestooltip"));
        FrameUtils.buildPanelGroup(lPanel, new JComponent[][]{{lMenLabel, menLabel, new JLabel("\u20ac")},
            {lAssLabel, assLabel, new JLabel("\u20ac")},
            {lTotLabel, totLabel, new JLabel("\u20ac")}});
        return lPanel;
    }

    /**
     * Construct the panel that contains the cost result components
     *
     * @return the filled panel
     */
    private JPanel buildCostPanel() {
        JLabel lMenCostLabel = new JLabel(translate("costvalue"));
        lMenCostLabel.setToolTipText(translate("costvaluetooltip"));
        menCostLabel.setToolTipText(translate("costvaluetooltip"));
        JLabel lAssCostLabel = new JLabel(translate("costassurance"));
        lAssCostLabel.setToolTipText(translate("costassurancetooltip"));
        assCostLabel.setToolTipText(translate("costassurancetooltip"));
        JLabel lTotCostLabel = new JLabel(translate("costtotal"));
        lTotCostLabel.setToolTipText(translate("costtotaltooltip"));
        totCostLabel.setToolTipText(translate("costtotaltooltip"));
        JPanel lPanel = new JPanel();
        lPanel.setBorder(BorderFactory.createTitledBorder(translate("cost")));
        lPanel.setToolTipText(translate("costtooltip"));
        FrameUtils.buildPanelGroup(lPanel, new JComponent[][]{{lMenCostLabel, menCostLabel, new JLabel("\u20ac")},
            {lAssCostLabel, assCostLabel, new JLabel("\u20ac")},
            {lTotCostLabel, totCostLabel, new JLabel("\u20ac")}});
        return lPanel;
    }

    /**
     * Construct the panel that contains the divers result components
     *
     * @return the filled panel
     */
    private JPanel buildDiversPanel() {
        JLabel lEffLabel = new JLabel(translate("effvalue"));
        lEffLabel.setToolTipText(translate("effvaluetooltip"));
        effLabel.setToolTipText(translate("effvaluetooltip"));
        JLabel lPctLabel = new JLabel(translate("pctsalary"));
        lPctLabel.setToolTipText(translate("pctsalarytooltip"));
        pctLabel.setToolTipText(translate("pctsalarytooltip"));
        JLabel lYtaLabel = new JLabel(translate("yta"));
        lYtaLabel.setToolTipText(translate("ytatooltip"));
        ytaLabel.setToolTipText(translate("ytatooltip"));
        JPanel lPanel = new JPanel();
        lPanel.setBorder(BorderFactory.createTitledBorder(translate("divers")));
        lPanel.setToolTipText(translate("diverstooltip"));
        FrameUtils.buildPanelGroup(lPanel, new JComponent[][]{{lEffLabel, effLabel, new JLabel("%")},
            {lPctLabel, pctLabel, new JLabel("%")},
            {lYtaLabel, ytaLabel, new JLabel("\u20ac")}});
        return lPanel;
    }
}
