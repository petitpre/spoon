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
    "methods",
    "lines"
})
@XmlRootElement(name = "class")
public class Class {

    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute(name = "filename", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String filename;
    @XmlAttribute(name = "line-rate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String lineRate;
    @XmlAttribute(name = "branch-rate", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String branchRate;
    @XmlAttribute(name = "complexity", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String complexity;
    @XmlElement(required = true)
    protected Methods methods;
    @XmlElement(required = true)
    protected Lines lines;

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propriété filename.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Définit la valeur de la propriété filename.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilename(String value) {
        this.filename = value;
    }

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
     * Obtient la valeur de la propriété methods.
     * 
     * @return
     *     possible object is
     *     {@link Methods }
     *     
     */
    public Methods getMethods() {
        return methods;
    }

    /**
     * Définit la valeur de la propriété methods.
     * 
     * @param value
     *     allowed object is
     *     {@link Methods }
     *     
     */
    public void setMethods(Methods value) {
        this.methods = value;
    }

    /**
     * Obtient la valeur de la propriété lines.
     * 
     * @return
     *     possible object is
     *     {@link Lines }
     *     
     */
    public Lines getLines() {
        return lines;
    }

    /**
     * Définit la valeur de la propriété lines.
     * 
     * @param value
     *     allowed object is
     *     {@link Lines }
     *     
     */
    public void setLines(Lines value) {
        this.lines = value;
    }

}
