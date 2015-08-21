//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.08.18 à 12:06:15 PM CEST 
//


package treemap.demo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "conditions"
})
@XmlRootElement(name = "line")
public class Line {

    @XmlAttribute(name = "number", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String number;
    @XmlAttribute(name = "hits", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String hits;
    @XmlAttribute(name = "branch", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String branch;
    @XmlAttribute(name = "condition-coverage", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String conditionCoverage;
    protected List<Conditions> conditions;

    /**
     * Obtient la valeur de la propriété number.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Définit la valeur de la propriété number.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * Obtient la valeur de la propriété hits.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHits() {
        return hits;
    }

    /**
     * Définit la valeur de la propriété hits.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHits(String value) {
        this.hits = value;
    }

    /**
     * Obtient la valeur de la propriété branch.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Définit la valeur de la propriété branch.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranch(String value) {
        this.branch = value;
    }

    /**
     * Obtient la valeur de la propriété conditionCoverage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditionCoverage() {
        return conditionCoverage;
    }

    /**
     * Définit la valeur de la propriété conditionCoverage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditionCoverage(String value) {
        this.conditionCoverage = value;
    }

    /**
     * Gets the value of the conditions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conditions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConditions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Conditions }
     * 
     * 
     */
    public List<Conditions> getConditions() {
        if (conditions == null) {
            conditions = new ArrayList<Conditions>();
        }
        return this.conditions;
    }

}
