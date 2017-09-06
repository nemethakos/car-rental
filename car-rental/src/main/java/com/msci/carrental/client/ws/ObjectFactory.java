
package com.msci.carrental.client.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.msci.carrental.client.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAvailableCarsForRental_QNAME = new QName("http://service.carrental.msci.com/", "getAvailableCarsForRental");
    private final static QName _BookACarResponse_QNAME = new QName("http://service.carrental.msci.com/", "bookACarResponse");
    private final static QName _GetDetailedSpecificationForACarType_QNAME = new QName("http://service.carrental.msci.com/", "getDetailedSpecificationForACarType");
    private final static QName _GetDetailedSpecificationForACarTypeResponse_QNAME = new QName("http://service.carrental.msci.com/", "getDetailedSpecificationForACarTypeResponse");
    private final static QName _GetBookingResultsForIds_QNAME = new QName("http://service.carrental.msci.com/", "getBookingResultsForIds");
    private final static QName _BookACar_QNAME = new QName("http://service.carrental.msci.com/", "bookACar");
    private final static QName _GetAvailableCarsForRentalResponse_QNAME = new QName("http://service.carrental.msci.com/", "getAvailableCarsForRentalResponse");
    private final static QName _GetBookingResultsForIdsResponse_QNAME = new QName("http://service.carrental.msci.com/", "getBookingResultsForIdsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.msci.carrental.client.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookACarResponse }
     * 
     */
    public BookACarResponse createBookACarResponse() {
        return new BookACarResponse();
    }

    /**
     * Create an instance of {@link GetDetailedSpecificationForACarType }
     * 
     */
    public GetDetailedSpecificationForACarType createGetDetailedSpecificationForACarType() {
        return new GetDetailedSpecificationForACarType();
    }

    /**
     * Create an instance of {@link GetDetailedSpecificationForACarTypeResponse }
     * 
     */
    public GetDetailedSpecificationForACarTypeResponse createGetDetailedSpecificationForACarTypeResponse() {
        return new GetDetailedSpecificationForACarTypeResponse();
    }

    /**
     * Create an instance of {@link GetAvailableCarsForRental }
     * 
     */
    public GetAvailableCarsForRental createGetAvailableCarsForRental() {
        return new GetAvailableCarsForRental();
    }

    /**
     * Create an instance of {@link GetBookingResultsForIdsResponse }
     * 
     */
    public GetBookingResultsForIdsResponse createGetBookingResultsForIdsResponse() {
        return new GetBookingResultsForIdsResponse();
    }

    /**
     * Create an instance of {@link GetBookingResultsForIds }
     * 
     */
    public GetBookingResultsForIds createGetBookingResultsForIds() {
        return new GetBookingResultsForIds();
    }

    /**
     * Create an instance of {@link BookACar }
     * 
     */
    public BookACar createBookACar() {
        return new BookACar();
    }

    /**
     * Create an instance of {@link GetAvailableCarsForRentalResponse }
     * 
     */
    public GetAvailableCarsForRentalResponse createGetAvailableCarsForRentalResponse() {
        return new GetAvailableCarsForRentalResponse();
    }

    /**
     * Create an instance of {@link BookingResult }
     * 
     */
    public BookingResult createBookingResult() {
        return new BookingResult();
    }

    /**
     * Create an instance of {@link CarInstance }
     * 
     */
    public CarInstance createCarInstance() {
        return new CarInstance();
    }

    /**
     * Create an instance of {@link BookingRequest }
     * 
     */
    public BookingRequest createBookingRequest() {
        return new BookingRequest();
    }

    /**
     * Create an instance of {@link CarSpecification }
     * 
     */
    public CarSpecification createCarSpecification() {
        return new CarSpecification();
    }

    /**
     * Create an instance of {@link BookingStatus }
     * 
     */
    public BookingStatus createBookingStatus() {
        return new BookingStatus();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvailableCarsForRental }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.carrental.msci.com/", name = "getAvailableCarsForRental")
    public JAXBElement<GetAvailableCarsForRental> createGetAvailableCarsForRental(GetAvailableCarsForRental value) {
        return new JAXBElement<GetAvailableCarsForRental>(_GetAvailableCarsForRental_QNAME, GetAvailableCarsForRental.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookACarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.carrental.msci.com/", name = "bookACarResponse")
    public JAXBElement<BookACarResponse> createBookACarResponse(BookACarResponse value) {
        return new JAXBElement<BookACarResponse>(_BookACarResponse_QNAME, BookACarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDetailedSpecificationForACarType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.carrental.msci.com/", name = "getDetailedSpecificationForACarType")
    public JAXBElement<GetDetailedSpecificationForACarType> createGetDetailedSpecificationForACarType(GetDetailedSpecificationForACarType value) {
        return new JAXBElement<GetDetailedSpecificationForACarType>(_GetDetailedSpecificationForACarType_QNAME, GetDetailedSpecificationForACarType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDetailedSpecificationForACarTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.carrental.msci.com/", name = "getDetailedSpecificationForACarTypeResponse")
    public JAXBElement<GetDetailedSpecificationForACarTypeResponse> createGetDetailedSpecificationForACarTypeResponse(GetDetailedSpecificationForACarTypeResponse value) {
        return new JAXBElement<GetDetailedSpecificationForACarTypeResponse>(_GetDetailedSpecificationForACarTypeResponse_QNAME, GetDetailedSpecificationForACarTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBookingResultsForIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.carrental.msci.com/", name = "getBookingResultsForIds")
    public JAXBElement<GetBookingResultsForIds> createGetBookingResultsForIds(GetBookingResultsForIds value) {
        return new JAXBElement<GetBookingResultsForIds>(_GetBookingResultsForIds_QNAME, GetBookingResultsForIds.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookACar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.carrental.msci.com/", name = "bookACar")
    public JAXBElement<BookACar> createBookACar(BookACar value) {
        return new JAXBElement<BookACar>(_BookACar_QNAME, BookACar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAvailableCarsForRentalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.carrental.msci.com/", name = "getAvailableCarsForRentalResponse")
    public JAXBElement<GetAvailableCarsForRentalResponse> createGetAvailableCarsForRentalResponse(GetAvailableCarsForRentalResponse value) {
        return new JAXBElement<GetAvailableCarsForRentalResponse>(_GetAvailableCarsForRentalResponse_QNAME, GetAvailableCarsForRentalResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBookingResultsForIdsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.carrental.msci.com/", name = "getBookingResultsForIdsResponse")
    public JAXBElement<GetBookingResultsForIdsResponse> createGetBookingResultsForIdsResponse(GetBookingResultsForIdsResponse value) {
        return new JAXBElement<GetBookingResultsForIdsResponse>(_GetBookingResultsForIdsResponse_QNAME, GetBookingResultsForIdsResponse.class, null, value);
    }

}
