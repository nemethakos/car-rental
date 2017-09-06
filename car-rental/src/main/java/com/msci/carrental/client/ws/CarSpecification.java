
package com.msci.carrental.client.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for carSpecification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="carSpecification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="automaticTransmission" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="carTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="example" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="group" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfLargeSuitcases" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numberOfPassangers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numberOfSmallSuitcases" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "carSpecification", propOrder = {
    "automaticTransmission",
    "carTypeName",
    "description",
    "example",
    "group",
    "numberOfLargeSuitcases",
    "numberOfPassangers",
    "numberOfSmallSuitcases"
})
public class CarSpecification {

    protected boolean automaticTransmission;
    protected String carTypeName;
    protected String description;
    protected String example;
    protected String group;
    protected int numberOfLargeSuitcases;
    protected int numberOfPassangers;
    protected int numberOfSmallSuitcases;

    /**
     * Gets the value of the automaticTransmission property.
     * 
     */
    public boolean isAutomaticTransmission() {
        return automaticTransmission;
    }

    /**
     * Sets the value of the automaticTransmission property.
     * 
     */
    public void setAutomaticTransmission(boolean value) {
        this.automaticTransmission = value;
    }

    /**
     * Gets the value of the carTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarTypeName() {
        return carTypeName;
    }

    /**
     * Sets the value of the carTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarTypeName(String value) {
        this.carTypeName = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the example property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExample() {
        return example;
    }

    /**
     * Sets the value of the example property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExample(String value) {
        this.example = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroup(String value) {
        this.group = value;
    }

    /**
     * Gets the value of the numberOfLargeSuitcases property.
     * 
     */
    public int getNumberOfLargeSuitcases() {
        return numberOfLargeSuitcases;
    }

    /**
     * Sets the value of the numberOfLargeSuitcases property.
     * 
     */
    public void setNumberOfLargeSuitcases(int value) {
        this.numberOfLargeSuitcases = value;
    }

    /**
     * Gets the value of the numberOfPassangers property.
     * 
     */
    public int getNumberOfPassangers() {
        return numberOfPassangers;
    }

    /**
     * Sets the value of the numberOfPassangers property.
     * 
     */
    public void setNumberOfPassangers(int value) {
        this.numberOfPassangers = value;
    }

    /**
     * Gets the value of the numberOfSmallSuitcases property.
     * 
     */
    public int getNumberOfSmallSuitcases() {
        return numberOfSmallSuitcases;
    }

    /**
     * Sets the value of the numberOfSmallSuitcases property.
     * 
     */
    public void setNumberOfSmallSuitcases(int value) {
        this.numberOfSmallSuitcases = value;
    }

}
