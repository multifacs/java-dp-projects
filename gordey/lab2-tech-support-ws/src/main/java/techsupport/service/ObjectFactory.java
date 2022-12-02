
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

    private final static QName _ConfirmTask_QNAME = new QName("http://techsupport/", "confirmTask");
    private final static QName _ConfirmTaskResponse_QNAME = new QName("http://techsupport/", "confirmTaskResponse");
    private final static QName _CreateTask_QNAME = new QName("http://techsupport/", "createTask");
    private final static QName _CreateTaskResponse_QNAME = new QName("http://techsupport/", "createTaskResponse");
    private final static QName _FinishTask_QNAME = new QName("http://techsupport/", "finishTask");
    private final static QName _FinishTaskResponse_QNAME = new QName("http://techsupport/", "finishTaskResponse");
    private final static QName _GetAllTasks_QNAME = new QName("http://techsupport/", "getAllTasks");
    private final static QName _GetAllTasksResponse_QNAME = new QName("http://techsupport/", "getAllTasksResponse");
    private final static QName _GetStatus_QNAME = new QName("http://techsupport/", "getStatus");
    private final static QName _GetStatusResponse_QNAME = new QName("http://techsupport/", "getStatusResponse");
    private final static QName _RedoTask_QNAME = new QName("http://techsupport/", "redoTask");
    private final static QName _RedoTaskResponse_QNAME = new QName("http://techsupport/", "redoTaskResponse");
    private final static QName _StartTask_QNAME = new QName("http://techsupport/", "startTask");
    private final static QName _StartTaskResponse_QNAME = new QName("http://techsupport/", "startTaskResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: techsupport.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConfirmTask }
     * 
     */
    public ConfirmTask createConfirmTask() {
        return new ConfirmTask();
    }

    /**
     * Create an instance of {@link ConfirmTaskResponse }
     * 
     */
    public ConfirmTaskResponse createConfirmTaskResponse() {
        return new ConfirmTaskResponse();
    }

    /**
     * Create an instance of {@link CreateTask }
     * 
     */
    public CreateTask createCreateTask() {
        return new CreateTask();
    }

    /**
     * Create an instance of {@link CreateTaskResponse }
     * 
     */
    public CreateTaskResponse createCreateTaskResponse() {
        return new CreateTaskResponse();
    }

    /**
     * Create an instance of {@link FinishTask }
     * 
     */
    public FinishTask createFinishTask() {
        return new FinishTask();
    }

    /**
     * Create an instance of {@link FinishTaskResponse }
     * 
     */
    public FinishTaskResponse createFinishTaskResponse() {
        return new FinishTaskResponse();
    }

    /**
     * Create an instance of {@link GetAllTasks }
     * 
     */
    public GetAllTasks createGetAllTasks() {
        return new GetAllTasks();
    }

    /**
     * Create an instance of {@link GetAllTasksResponse }
     * 
     */
    public GetAllTasksResponse createGetAllTasksResponse() {
        return new GetAllTasksResponse();
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
     * Create an instance of {@link RedoTask }
     * 
     */
    public RedoTask createRedoTask() {
        return new RedoTask();
    }

    /**
     * Create an instance of {@link RedoTaskResponse }
     * 
     */
    public RedoTaskResponse createRedoTaskResponse() {
        return new RedoTaskResponse();
    }

    /**
     * Create an instance of {@link StartTask }
     * 
     */
    public StartTask createStartTask() {
        return new StartTask();
    }

    /**
     * Create an instance of {@link StartTaskResponse }
     * 
     */
    public StartTaskResponse createStartTaskResponse() {
        return new StartTaskResponse();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmTask }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConfirmTask }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "confirmTask")
    public JAXBElement<ConfirmTask> createConfirmTask(ConfirmTask value) {
        return new JAXBElement<ConfirmTask>(_ConfirmTask_QNAME, ConfirmTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmTaskResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConfirmTaskResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "confirmTaskResponse")
    public JAXBElement<ConfirmTaskResponse> createConfirmTaskResponse(ConfirmTaskResponse value) {
        return new JAXBElement<ConfirmTaskResponse>(_ConfirmTaskResponse_QNAME, ConfirmTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTask }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateTask }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "createTask")
    public JAXBElement<CreateTask> createCreateTask(CreateTask value) {
        return new JAXBElement<CreateTask>(_CreateTask_QNAME, CreateTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTaskResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateTaskResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "createTaskResponse")
    public JAXBElement<CreateTaskResponse> createCreateTaskResponse(CreateTaskResponse value) {
        return new JAXBElement<CreateTaskResponse>(_CreateTaskResponse_QNAME, CreateTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinishTask }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FinishTask }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "finishTask")
    public JAXBElement<FinishTask> createFinishTask(FinishTask value) {
        return new JAXBElement<FinishTask>(_FinishTask_QNAME, FinishTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinishTaskResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FinishTaskResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "finishTaskResponse")
    public JAXBElement<FinishTaskResponse> createFinishTaskResponse(FinishTaskResponse value) {
        return new JAXBElement<FinishTaskResponse>(_FinishTaskResponse_QNAME, FinishTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllTasks }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllTasks }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "getAllTasks")
    public JAXBElement<GetAllTasks> createGetAllTasks(GetAllTasks value) {
        return new JAXBElement<GetAllTasks>(_GetAllTasks_QNAME, GetAllTasks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllTasksResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllTasksResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "getAllTasksResponse")
    public JAXBElement<GetAllTasksResponse> createGetAllTasksResponse(GetAllTasksResponse value) {
        return new JAXBElement<GetAllTasksResponse>(_GetAllTasksResponse_QNAME, GetAllTasksResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RedoTask }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RedoTask }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "redoTask")
    public JAXBElement<RedoTask> createRedoTask(RedoTask value) {
        return new JAXBElement<RedoTask>(_RedoTask_QNAME, RedoTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RedoTaskResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RedoTaskResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "redoTaskResponse")
    public JAXBElement<RedoTaskResponse> createRedoTaskResponse(RedoTaskResponse value) {
        return new JAXBElement<RedoTaskResponse>(_RedoTaskResponse_QNAME, RedoTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTask }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StartTask }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "startTask")
    public JAXBElement<StartTask> createStartTask(StartTask value) {
        return new JAXBElement<StartTask>(_StartTask_QNAME, StartTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTaskResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StartTaskResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://techsupport/", name = "startTaskResponse")
    public JAXBElement<StartTaskResponse> createStartTaskResponse(StartTaskResponse value) {
        return new JAXBElement<StartTaskResponse>(_StartTaskResponse_QNAME, StartTaskResponse.class, null, value);
    }

}
