
package techsupport.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the techsupport.service package. 
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

    private final static QName _GetRequests_QNAME = new QName("http://techsupport/", "getRequests");
    private final static QName _GetRequestsResponse_QNAME = new QName("http://techsupport/", "getRequestsResponse");
    private final static QName _GetStatus_QNAME = new QName("http://techsupport/", "getStatus");
    private final static QName _GetStatusResponse_QNAME = new QName("http://techsupport/", "getStatusResponse");
    private final static QName _RequestConfirm_QNAME = new QName("http://techsupport/", "requestConfirm");
    private final static QName _RequestConfirmResponse_QNAME = new QName("http://techsupport/", "requestConfirmResponse");
    private final static QName _RequestCreate_QNAME = new QName("http://techsupport/", "requestCreate");
    private final static QName _RequestCreateResponse_QNAME = new QName("http://techsupport/", "requestCreateResponse");
    private final static QName _RequestDeny_QNAME = new QName("http://techsupport/", "requestDeny");
    private final static QName _RequestDenyResponse_QNAME = new QName("http://techsupport/", "requestDenyResponse");
    private final static QName _RequestFinish_QNAME = new QName("http://techsupport/", "requestFinish");
    private final static QName _RequestFinishResponse_QNAME = new QName("http://techsupport/", "requestFinishResponse");
    private final static QName _RequestStart_QNAME = new QName("http://techsupport/", "requestStart");
    private final static QName _RequestStartResponse_QNAME = new QName("http://techsupport/", "requestStartResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: techsupport.service
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link RequestConfirm }
     * 
     */
    public RequestConfirm createRequestConfirm() {
        return new RequestConfirm();
    }

    /**
     * Create an instance of {@link RequestConfirmResponse }
     * 
     */
    public RequestConfirmResponse createRequestConfirmResponse() {
        return new RequestConfirmResponse();
    }

    /**
     * Create an instance of {@link RequestCreate }
     * 
     */
    public RequestCreate createRequestCreate() {
        return new RequestCreate();
    }

    /**
     * Create an instance of {@link RequestCreateResponse }
     * 
     */
    public RequestCreateResponse createRequestCreateResponse() {
        return new RequestCreateResponse();
    }

    /**
     * Create an instance of {@link RequestDeny }
     * 
     */
    public RequestDeny createRequestDeny() {
        return new RequestDeny();
    }

    /**
     * Create an instance of {@link RequestDenyResponse }
     * 
     */
    public RequestDenyResponse createRequestDenyResponse() {
        return new RequestDenyResponse();
    }

    /**
     * Create an instance of {@link RequestFinish }
     * 
     */
    public RequestFinish createRequestFinish() {
        return new RequestFinish();
    }

    /**
     * Create an instance of {@link RequestFinishResponse }
     * 
     */
    public RequestFinishResponse createRequestFinishResponse() {
        return new RequestFinishResponse();
    }

    /**
     * Create an instance of {@link RequestStart }
     * 
     */
    public RequestStart createRequestStart() {
        return new RequestStart();
    }

    /**
     * Create an instance of {@link RequestStartResponse }
     * 
     */
    public RequestStartResponse createRequestStartResponse() {
        return new RequestStartResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequests }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetRequests }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "getRequests")
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
    @XmlElementDecl(namespace = "http://techsupport/", name = "getRequestsResponse")
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
    @XmlElementDecl(namespace = "http://techsupport/", name = "getStatus")
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
    @XmlElementDecl(namespace = "http://techsupport/", name = "getStatusResponse")
    public JAXBElement<GetStatusResponse> createGetStatusResponse(GetStatusResponse value) {
        return new JAXBElement<GetStatusResponse>(_GetStatusResponse_QNAME, GetStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestConfirm }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestConfirm }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "requestConfirm")
    public JAXBElement<RequestConfirm> createRequestConfirm(RequestConfirm value) {
        return new JAXBElement<RequestConfirm>(_RequestConfirm_QNAME, RequestConfirm.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestConfirmResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestConfirmResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "requestConfirmResponse")
    public JAXBElement<RequestConfirmResponse> createRequestConfirmResponse(RequestConfirmResponse value) {
        return new JAXBElement<RequestConfirmResponse>(_RequestConfirmResponse_QNAME, RequestConfirmResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestCreate }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestCreate }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "requestCreate")
    public JAXBElement<RequestCreate> createRequestCreate(RequestCreate value) {
        return new JAXBElement<RequestCreate>(_RequestCreate_QNAME, RequestCreate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestCreateResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestCreateResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "requestCreateResponse")
    public JAXBElement<RequestCreateResponse> createRequestCreateResponse(RequestCreateResponse value) {
        return new JAXBElement<RequestCreateResponse>(_RequestCreateResponse_QNAME, RequestCreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestDeny }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestDeny }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "requestDeny")
    public JAXBElement<RequestDeny> createRequestDeny(RequestDeny value) {
        return new JAXBElement<RequestDeny>(_RequestDeny_QNAME, RequestDeny.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestDenyResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestDenyResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "requestDenyResponse")
    public JAXBElement<RequestDenyResponse> createRequestDenyResponse(RequestDenyResponse value) {
        return new JAXBElement<RequestDenyResponse>(_RequestDenyResponse_QNAME, RequestDenyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestFinish }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestFinish }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "requestFinish")
    public JAXBElement<RequestFinish> createRequestFinish(RequestFinish value) {
        return new JAXBElement<RequestFinish>(_RequestFinish_QNAME, RequestFinish.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestFinishResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestFinishResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "requestFinishResponse")
    public JAXBElement<RequestFinishResponse> createRequestFinishResponse(RequestFinishResponse value) {
        return new JAXBElement<RequestFinishResponse>(_RequestFinishResponse_QNAME, RequestFinishResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestStart }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestStart }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "requestStart")
    public JAXBElement<RequestStart> createRequestStart(RequestStart value) {
        return new JAXBElement<RequestStart>(_RequestStart_QNAME, RequestStart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestStartResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RequestStartResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "requestStartResponse")
    public JAXBElement<RequestStartResponse> createRequestStartResponse(RequestStartResponse value) {
        return new JAXBElement<RequestStartResponse>(_RequestStartResponse_QNAME, RequestStartResponse.class, null, value);
    }

}
