/*
 * This class represents the item of a loan, with its entries, options and results.
 */
package loanutils;

import com.google.common.eventbus.EventBus;
import controllers.EntryPanelUpdate;
import controllers.OptionPanelUpdate;
import controllers.TabbedPanelUpdate;

import java.io.Serializable;

/**
 * This class represents the item of a loan, with its entries, options and results.
 *
 * @author jean-blas imbert
 */
public final class LoanItem implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Suivant le type de loan, le calcul n'est pas le même.
     */
    public static enum LoanType implements Serializable {

        /**
         * Détermine la mensualité connaissant les 3 autres
         */
        MENSUALITE,
        /**
         * Détermine le taux connaissant les 3 autres
         */
        TAUX,
        /**
         * Détermine le montant connaissant les 3 autres
         */
        MONTANT,
        /**
         * Détermine la durée connaissant les 3 autres
         */
        DUREE
    }
    /**
     * The item loan type
     */
    private LoanType type = LoanType.MENSUALITE;
    /**
     * Name of this loan item
     */
    private String name = null;
    //Entries
    /**
     * The amount of the loan, in euros
     */
    private Float amount = 0F;
    /**
     * The rate of the loan, in percents
     */
    private Float taux = 0F;
    /**
     * The amount of one monthly payment, in euros
     */
    private Float mensualite = 0F;
    /**
     * The duration of the loan, in months
     */
    private Float duree = 0F;
    //Options
    /**
     * The bank fees, in euros
     */
    private Float frais = 0F;
    /**
     * The user monthly income, in euros
     */
    private Float salary = 0F;
    /**
     * The insurance of the loan, in percents
     */
    private Float insurance = 0F;

    // Event Bus
    private EventBus evtBus = null;


//Methods
    /**
     * Default constructor
     */
    public LoanItem(EventBus pEvtBus) {
        evtBus = pEvtBus;
    }

    /**
     * Clone this
     *
     * @return the clone of this
     */
    @Override
    public LoanItem clone() {
        LoanItem lClone = new LoanItem(evtBus);
        lClone.setAmount(getAmount());
        lClone.setDuree(getDuree());
        lClone.setFrais(getFrais());
        lClone.setInsurance(getInsurance());
        lClone.setMensualite(getMensualite());
        lClone.setName(getName());
        lClone.setTaux(getTaux());
        lClone.setSalary(getSalary());
        lClone.setLoanType(getLoanType());
        return lClone;
    }


    /**
     * Aware the subscribers that this item is updated
     */
    public void updateChanges() {
        evtBus.post(new EntryPanelUpdate(this));
        evtBus.post(new OptionPanelUpdate(this));
        evtBus.post(new TabbedPanelUpdate(this));
    }

//getters and setters
    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float pAmount) {
        this.amount = pAmount;
    }

    public Float getTaux() {
        return taux;
    }

    public void setTaux(Float pTaux) {
        this.taux = pTaux;
    }

    public Float getMensualite() {
        return mensualite;
    }

    public void setMensualite(Float pMensualite) {
        this.mensualite = pMensualite;
    }

    public Float getDuree() {
        return duree;
    }

    public void setDuree(Float pDuree) {
        this.duree = pDuree;
    }

    public Float getFrais() {
        return frais;
    }

    public void setFrais(Float pFrais) {
        this.frais = pFrais;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float pSalary) {
        this.salary = pSalary;
    }

    public Float getInsurance() {
        return insurance;
    }

    public void setInsurance(Float pInsurance) {
        this.insurance = pInsurance;
    }

    public void setName(final String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public LoanType getLoanType() {
        return type;
    }

    public void setLoanType(LoanType pType) {
        this.type = pType;
    }
}
