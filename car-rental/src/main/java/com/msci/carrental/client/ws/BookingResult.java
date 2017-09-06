
package com.msci.carrental.client.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bookingResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bookingResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reference" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="bookingRequest" type="{http://service.carrental.msci.com/}bookingRequest" minOccurs="0"/>
 *         &lt;element name="bookingStatus" type="{http://service.carrental.msci.com/}bookingStatus" minOccurs="0"/>
 *         &lt;element name="carInstances" type="{http://service.carrental.msci.com/}carInstance" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bookingResult", propOrder = {
    "reference",
    "bookingRequest",
    "bookingStatus",
    "carInstances"
})
public class BookingResult {

    protected long reference;
    protected BookingRequest bookingRequest;
    protected BookingStatus bookingStatus;
    protected List<CarInstance> carInstances;

    /**
     * Gets the value of the reference property.
     * 
     */
    public long getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     */
    public void setReference(long value) {
        this.reference = value;
    }

    /**
     * Gets the value of the bookingRequest property.
     * 
     * @return
     *     possible object is
     *     {@link BookingRequest }
     *     
     */
    public BookingRequest getBookingRequest() {
        return bookingRequest;
    }

    /**
     * Sets the value of the bookingRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BookingRequest }
     *     
     */
    public void setBookingRequest(BookingRequest value) {
        this.bookingRequest = value;
    }

    /**
     * Gets the value of the bookingStatus property.
     * 
     * @return
     *     possible object is
     *     {@link BookingStatus }
     *     
     */
    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    /**
     * Sets the value of the bookingStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link BookingStatus }
     *     
     */
    public void setBookingStatus(BookingStatus value) {
        this.bookingStatus = value;
    }

    /**
     * Gets the value of the carInstances property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the carInstances property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCarInstances().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CarInstance }
     * 
     * 
     */
    public List<CarInstance> getCarInstances() {
        if (carInstances == null) {
            carInstances = new ArrayList<CarInstance>();
        }
        return this.carInstances;
    }

}
