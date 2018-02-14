package controllers;

import javax.swing.*;

public abstract class AbstractPanelUpdater {
    /**
     * Initialize the different components of the panel, i.e.: FloatJTextFields, JCheckBoxes, etc.
     * @param panel
     */
    abstract void setParams(Object panel);
}
