package controllers;

import javax.swing.*;

public interface InterfacePanelUpdater {
    /**
     * Initialize the different components of the panel, i.e.: FloatJTextFields, JCheckBoxes, etc.
     * @param panel
     */
    public void setParams(JPanel panel);
}
