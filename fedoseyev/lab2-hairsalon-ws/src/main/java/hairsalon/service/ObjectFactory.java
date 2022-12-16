
package hairsalon.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the hairsalon.service package. 
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

    private final static QName _AddService_QNAME = new QName("http://hairsalon/", "addService");
    private final static QName _AddServiceResponse_QNAME = new QName("http://hairsalon/", "addServiceResponse");
    private final static QName _GetFreeTime_QNAME = new QName("http://hairsalon/", "getFreeTime");
    private final static QName _GetFreeTimeResponse_QNAME = new QName("http://hairsalon/", "getFreeTimeResponse");
    private final static QName _GetServices_QNAME = new QName("http://hairsalon/", "getServices");
    private final static QName _GetServicesResponse_QNAME = new QName("http://hairsalon/", "getServicesResponse");
    private final static QName _MakeAppointment_QNAME = new QName("http://hairsalon/", "makeAppointment");
    private final static QName _MakeAppointmentResponse_QNAME = new QName("http://hairsalon/", "makeAppointmentResponse");
    private final static QName _RemoveAppointment_QNAME = new QName("http://hairsalon/", "removeAppointment");
    private final static QName _RemoveAppointmentResponse_QNAME = new QName("http://hairsalon/", "removeAppointmentResponse");
    private final static QName _RemoveService_QNAME = new QName("http://hairsalon/", "removeService");
    private final static QName _RemoveServiceResponse_QNAME = new QName("http://hairsalon/", "removeServiceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: hairsalon.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddService }
     * 
     */
    public AddService createAddService() {
        return new AddService();
    }

    /**
     * Create an instance of {@link AddServiceResponse }
     * 
     */
    public AddServiceResponse createAddServiceResponse() {
        return new AddServiceResponse();
    }

    /**
     * Create an instance of {@link GetFreeTime }
     * 
     */
    public GetFreeTime createGetFreeTime() {
        return new GetFreeTime();
    }

    /**
     * Create an instance of {@link GetFreeTimeResponse }
     * 
     */
    public GetFreeTimeResponse createGetFreeTimeResponse() {
        return new GetFreeTimeResponse();
    }

    /**
     * Create an instance of {@link GetServices }
     * 
     */
    public GetServices createGetServices() {
        return new GetServices();
    }

    /**
     * Create an instance of {@link GetServicesResponse }
     * 
     */
    public GetServicesResponse createGetServicesResponse() {
        return new GetServicesResponse();
    }

    /**
     * Create an instance of {@link MakeAppointment }
     * 
     */
    public MakeAppointment createMakeAppointment() {
        return new MakeAppointment();
    }

    /**
     * Create an instance of {@link MakeAppointmentResponse }
     * 
     */
    public MakeAppointmentResponse createMakeAppointmentResponse() {
        return new MakeAppointmentResponse();
    }

    /**
     * Create an instance of {@link RemoveAppointment }
     * 
     */
    public RemoveAppointment createRemoveAppointment() {
        return new RemoveAppointment();
    }

    /**
     * Create an instance of {@link RemoveAppointmentResponse }
     * 
     */
    public RemoveAppointmentResponse createRemoveAppointmentResponse() {
        return new RemoveAppointmentResponse();
    }

    /**
     * Create an instance of {@link RemoveService }
     * 
     */
    public RemoveService createRemoveService() {
        return new RemoveService();
    }

    /**
     * Create an instance of {@link RemoveServiceResponse }
     * 
     */
    public RemoveServiceResponse createRemoveServiceResponse() {
        return new RemoveServiceResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddService }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddService }{@code >}
     */
    @XmlElementDecl(namespace = "http://hairsalon/", name = "addService")
    public JAXBElement<AddService> createAddService(AddService value) {
        return new JAXBElement<AddService>(_AddService_QNAME, AddService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddServiceResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddServiceResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://hairsalon/", name = "addServiceResponse")
    public JAXBElement<AddServiceResponse> createAddServiceResponse(AddServiceResponse value) {
        return new JAXBElement<AddServiceResponse>(_AddServiceResponse_QNAME, AddServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFreeTime }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFreeTime }{@code >}
     */
    @XmlElementDecl(namespace = "http://hairsalon/", name = "getFreeTime")
    public JAXBElement<GetFreeTime> createGetFreeTime(GetFreeTime value) {
        return new JAXBElement<GetFreeTime>(_GetFreeTime_QNAME, GetFreeTime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFreeTimeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFreeTimeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://hairsalon/", name = "getFreeTimeResponse")
    public JAXBElement<GetFreeTimeResponse> createGetFreeTimeResponse(GetFreeTimeResponse value) {
        return new JAXBElement<GetFreeTimeResponse>(_GetFreeTimeResponse_QNAME, GetFreeTimeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServices }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetServices }{@code >}
     */
    @XmlElementDecl(namespace = "http://hairsalon/", name = "getServices")
    public JAXBElement<GetServices> createGetServices(GetServices value) {
        return new JAXBElement<GetServices>(_GetServices_QNAME, GetServices.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServicesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetServicesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://hairsalon/", name = "getServicesResponse")
    public JAXBElement<GetServicesResponse> createGetServicesResponse(GetServicesResponse value) {
        return new JAXBElement<GetServicesResponse>(_GetServicesResponse_QNAME, GetServicesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeAppointment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakeAppointment }{@code >}
     */
    @XmlElementDecl(namespace = "http://hairsalon/", name = "makeAppointment")
    public JAXBElement<MakeAppointment> createMakeAppointment(MakeAppointment value) {
        return new JAXBElement<MakeAppointment>(_MakeAppointment_QNAME, MakeAppointment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeAppointmentResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakeAppointmentResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://hairsalon/", name = "makeAppointmentResponse")
    public JAXBElement<MakeAppointmentResponse> createMakeAppointmentResponse(MakeAppointmentResponse value) {
        return new JAXBElement<MakeAppointmentResponse>(_MakeAppointmentResponse_QNAME, MakeAppointmentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAppointment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RemoveAppointment }{@code >}
     */
    @XmlElementDecl(namespace = "http://hairsalon/", name = "removeAppointment")
    public JAXBElement<RemoveAppointment> createRemoveAppointment(RemoveAppointment value) {
        return new JAXBElement<RemoveAppointment>(_RemoveAppointment_QNAME, RemoveAppointment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAppointmentResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RemoveAppointmentResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://hairsalon/", name = "removeAppointmentResponse")
    public JAXBElement<RemoveAppointmentResponse> createRemoveAppointmentResponse(RemoveAppointmentResponse value) {
        return new JAXBElement<RemoveAppointmentResponse>(_RemoveAppointmentResponse_QNAME, RemoveAppointmentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveService }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RemoveService }{@code >}
     */
    @XmlElementDecl(namespace = "http://hairsalon/", name = "removeService")
    public JAXBElement<RemoveService> createRemoveService(RemoveService value) {
        return new JAXBElement<RemoveService>(_RemoveService_QNAME, RemoveService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveServiceResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RemoveServiceResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://hairsalon/", name = "removeServiceResponse")
    public JAXBElement<RemoveServiceResponse> createRemoveServiceResponse(RemoveServiceResponse value) {
        return new JAXBElement<RemoveServiceResponse>(_RemoveServiceResponse_QNAME, RemoveServiceResponse.class, null, value);
    }

}
