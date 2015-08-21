//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.08.18 à 12:06:15 PM CEST 
//


package treemap.demo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sources",
    "packages"
})
@XmlRootElement(name = "coverage")
public class Coverage {

    @XmlAttribute(name = "line-rate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String lineRate;
    @XmlAttribute(name = "branch-rate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String branchRate;
    @XmlAttribute(name = "lines-covered", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String linesCovered;
    @XmlAttribute(name = "lines-valid", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String linesValid;
    @XmlAttribute(name = "branches-covered", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String branchesCovered;
    @XmlAttribute(name = "branches-valid", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String branchesValid;
    @XmlAttribute(name = "complexity", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String complexity;
    @XmlAttribute(name = "version", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String version;
    @XmlAttribute(name = "timestamp", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String timestamp;
    protected Sources sources;
    @XmlElement(required = true)
    protected Packages packages;

    /**
     * Obtient la valeur de la propriété lineRate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineRate() {
        return lineRate;
    }

    /**
     * Définit la valeur de la propriété lineRate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineRate(String value) {
        this.lineRate = value;
    }

    /**
     * Obtient la valeur de la propriété branchRate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchRate() {
        return branchRate;
    }

    /**
     * Définit la valeur de la propriété branchRate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchRate(String value) {
        this.branchRate = value;
    }

    /**
     * Obtient la valeur de la propriété linesCovered.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinesCovered() {
        return linesCovered;
    }

    /**
     * Définit la valeur de la propriété linesCovered.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinesCovered(String value) {
        this.linesCovered = value;
    }

    /**
     * Obtient la valeur de la propriété linesValid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinesValid() {
        return linesValid;
    }

    /**
     * Définit la valeur de la propriété linesValid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinesValid(String value) {
        this.linesValid = value;
    }

    /**
     * Obtient la valeur de la propriété branchesCovered.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchesCovered() {
        return branchesCovered;
    }

    /**
     * Définit la valeur de la propriété branchesCovered.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchesCovered(String value) {
        this.branchesCovered = value;
    }

    /**
     * Obtient la valeur de la propriété branchesValid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchesValid() {
        return branchesValid;
    }

    /**
     * Définit la valeur de la propriété branchesValid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchesValid(String value) {
        this.branchesValid = value;
    }

    /**
     * Obtient la valeur de la propriété complexity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplexity() {
        return complexity;
    }

    /**
     * Définit la valeur de la propriété complexity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplexity(String value) {
        this.complexity = value;
    }

    /**
     * Obtient la valeur de la propriété version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Définit la valeur de la propriété version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtient la valeur de la propriété timestamp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Définit la valeur de la propriété timestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimestamp(String value) {
        this.timestamp = value;
    }

    /**
     * Obtient la valeur de la propriété sources.
     * 
     * @return
     *     possible object is
     *     {@link Sources }
     *     
     */
    public Sources getSources() {
        return sources;
    }

    /**
     * Définit la valeur de la propriété sources.
     * 
     * @param value
     *     allowed object is
     *     {@link Sources }
     *     
     */
    public void setSources(Sources value) {
        this.sources = value;
    }

    /**
     * Obtient la valeur de la propriété packages.
     * 
     * @return
     *     possible object is
     *     {@link Packages }
     *     
     */
    public Packages getPackages() {
        return packages;
    }

    /**
     * Définit la valeur de la propriété packages.
     * 
     * @param value
     *     allowed object is
     *     {@link Packages }
     *     
     */
    public void setPackages(Packages value) {
        this.packages = value;
    }

}
