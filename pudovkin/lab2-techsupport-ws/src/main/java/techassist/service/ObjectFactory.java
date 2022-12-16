
package techassist.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the techassist.service package. 
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

    private final static QName _ConfirmRequest_QNAME = new QName("http://techassist/", "confirmRequest");
    private final static QName _ConfirmRequestResponse_QNAME = new QName("http://techassist/", "confirmRequestResponse");
    private final static QName _CreateRequest_QNAME = new QName("http://techassist/", "createRequest");
    private final static QName _CreateRequestResponse_QNAME = new QName("http://techassist/", "createRequestResponse");
    private final static QName _DenyRequest_QNAME = new QName("http://techassist/", "denyRequest");
    private final static QName _DenyRequestResponse_QNAME = new QName("http://techassist/", "denyRequestResponse");
    private final static QName _FinishRequest_QNAME = new QName("http://techassist/", "finishRequest");
    private final static QName _FinishRequestResponse_QNAME = new QName("http://techassist/", "finishRequestResponse");
    private final static QName _GetRequests_QNAME = new QName("http://techassist/", "getRequests");
    private final static QName _GetRequestsResponse_QNAME = new QName("http://techassist/", "getRequestsResponse");
    private final static QName _GetStatus_QNAME = new QName("http://techassist/", "getStatus");
    private final static QName _GetStatusResponse_QNAME = new QName("http://techassist/", "getStatusResponse");
    private final static QName _StartRequest_QNAME = new QName("http://techassist/", "startRequest");
    private final static QName _StartRequestResponse_QNAME = new QName("http://techassist/", "startRequestResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: techassist.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConfirmRequest }
     * 
     */
    public ConfirmRequest createConfirmRequest() {
        return new ConfirmRequest();
    }

    /**
     * Create an instance of {@link ConfirmRequestResponse }
     * 
     */
    public ConfirmRequestResponse createConfirmRequestResponse() {
        return new ConfirmRequestResponse();
    }

    /**
     * Create an instance of {@link CreateRequest }
     * 
     */
    public CreateRequest createCreateRequest() {
        return new CreateRequest();
    }

    /**
     * Create an instance of {@link CreateRequestResponse }
     * 
     */
    public CreateRequestResponse createCreateRequestResponse() {
        return new CreateRequestResponse();
    }

    /**
     * Create an instance of {@link DenyRequest }
     * 
     */
    public DenyRequest createDenyRequest() {
        return new DenyRequest();
    }

    /**
     * Create an instance of {@link DenyRequestResponse }
     * 
     */
    public DenyRequestResponse createDenyRequestResponse() {
        return new DenyRequestResponse();
    }

    /**
     * Create an instance of {@link FinishRequest }
     * 
     */
    public FinishRequest createFinishRequest() {
        return new FinishRequest();
    }

    /**
     * Create an instance of {@link FinishRequestResponse }
     * 
     */
    public FinishRequestResponse createFinishRequestResponse() {
        return new FinishRequestResponse();
    }

    /**
     * Create an instance of {@link GetRequests }
     * 
     */
    public GetRequests createGetRequests() {
        return new GetRequests();
    }

    /**
     * Create an instance of {@link GetRequestsResponse }
     * 
     */
    public GetRequestsResponse createGetRequestsResponse() {
        return new GetRequestsResponse();
    }

    /**
     * Create an instance of {@link GetStatus }
     * 
     */
    public GetStatus createGetStatus() {
        return new GetStatus();
    }

    /**
     * Create an instance of {@link GetStatusResponse }
     * 
     */
    public GetStatusResponse createGetStatusResponse() {
        return new GetStatusResponse();
    }

    /**
     * Create an instance of {@link StartRequest }
     * 
     */
    public StartRequest createStartRequest() {
        return new StartRequest();
    }

    /**
     * Create an instance of {@link StartRequestResponse }
     * 
     */
    public StartRequestResponse createStartRequestResponse() {
        return new StartRequestResponse();
    }

    /**
     * Create an instance of {@link Request }
     * 
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConfirmRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "confirmRequest")
    public JAXBElement<ConfirmRequest> createConfirmRequest(ConfirmRequest value) {
        return new JAXBElement<ConfirmRequest>(_ConfirmRequest_QNAME, ConfirmRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmRequestResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConfirmRequestResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "confirmRequestResponse")
    public JAXBElement<ConfirmRequestResponse> createConfirmRequestResponse(ConfirmRequestResponse value) {
        return new JAXBElement<ConfirmRequestResponse>(_ConfirmRequestResponse_QNAME, ConfirmRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "createRequest")
    public JAXBElement<CreateRequest> createCreateRequest(CreateRequest value) {
        return new JAXBElement<CreateRequest>(_CreateRequest_QNAME, CreateRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateRequestResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateRequestResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "createRequestResponse")
    public JAXBElement<CreateRequestResponse> createCreateRequestResponse(CreateRequestResponse value) {
        return new JAXBElement<CreateRequestResponse>(_CreateRequestResponse_QNAME, CreateRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DenyRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DenyRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "denyRequest")
    public JAXBElement<DenyRequest> createDenyRequest(DenyRequest value) {
        return new JAXBElement<DenyRequest>(_DenyRequest_QNAME, DenyRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DenyRequestResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DenyRequestResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "denyRequestResponse")
    public JAXBElement<DenyRequestResponse> createDenyRequestResponse(DenyRequestResponse value) {
        return new JAXBElement<DenyRequestResponse>(_DenyRequestResponse_QNAME, DenyRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinishRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FinishRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "finishRequest")
    public JAXBElement<FinishRequest> createFinishRequest(FinishRequest value) {
        return new JAXBElement<FinishRequest>(_FinishRequest_QNAME, FinishRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinishRequestResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FinishRequestResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "finishRequestResponse")
    public JAXBElement<FinishRequestResponse> createFinishRequestResponse(FinishRequestResponse value) {
        return new JAXBElement<FinishRequestResponse>(_FinishRequestResponse_QNAME, FinishRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequests }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetRequests }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "getRequests")
    public JAXBElement<GetRequests> createGetRequests(GetRequests value) {
        return new JAXBElement<GetRequests>(_GetRequests_QNAME, GetRequests.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequestsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetRequestsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "getRequestsResponse")
    public JAXBElement<GetRequestsResponse> createGetRequestsResponse(GetRequestsResponse value) {
        return new JAXBElement<GetRequestsResponse>(_GetRequestsResponse_QNAME, GetRequestsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStatus }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "getStatus")
    public JAXBElement<GetStatus> createGetStatus(GetStatus value) {
        return new JAXBElement<GetStatus>(_GetStatus_QNAME, GetStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStatusResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStatusResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "getStatusResponse")
    public JAXBElement<GetStatusResponse> createGetStatusResponse(GetStatusResponse value) {
        return new JAXBElement<GetStatusResponse>(_GetStatusResponse_QNAME, GetStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StartRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "startRequest")
    public JAXBElement<StartRequest> createStartRequest(StartRequest value) {
        return new JAXBElement<StartRequest>(_StartRequest_QNAME, StartRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartRequestResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StartRequestResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techassist/", name = "startRequestResponse")
    public JAXBElement<StartRequestResponse> createStartRequestResponse(StartRequestResponse value) {
        return new JAXBElement<StartRequestResponse>(_StartRequestResponse_QNAME, StartRequestResponse.class, null, value);
    }

}
