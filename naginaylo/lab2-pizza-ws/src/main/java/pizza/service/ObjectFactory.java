
package pizza.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pizza.service package. 
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

    private final static QName _AddPizza_QNAME = new QName("http://server.pizza/", "addPizza");
    private final static QName _AddPizzaResponse_QNAME = new QName("http://server.pizza/", "addPizzaResponse");
    private final static QName _ChangeStatus_QNAME = new QName("http://server.pizza/", "changeStatus");
    private final static QName _ChangeStatusResponse_QNAME = new QName("http://server.pizza/", "changeStatusResponse");
    private final static QName _GetPizzas_QNAME = new QName("http://server.pizza/", "getPizzas");
    private final static QName _GetPizzasResponse_QNAME = new QName("http://server.pizza/", "getPizzasResponse");
    private final static QName _GetStatus_QNAME = new QName("http://server.pizza/", "getStatus");
    private final static QName _GetStatusResponse_QNAME = new QName("http://server.pizza/", "getStatusResponse");
    private final static QName _OrderPizza_QNAME = new QName("http://server.pizza/", "orderPizza");
    private final static QName _OrderPizzaResponse_QNAME = new QName("http://server.pizza/", "orderPizzaResponse");
    private final static QName _RemovePizza_QNAME = new QName("http://server.pizza/", "removePizza");
    private final static QName _RemovePizzaResponse_QNAME = new QName("http://server.pizza/", "removePizzaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pizza.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddPizza }
     * 
     */
    public AddPizza createAddPizza() {
        return new AddPizza();
    }

    /**
     * Create an instance of {@link AddPizzaResponse }
     * 
     */
    public AddPizzaResponse createAddPizzaResponse() {
        return new AddPizzaResponse();
    }

    /**
     * Create an instance of {@link ChangeStatus }
     * 
     */
    public ChangeStatus createChangeStatus() {
        return new ChangeStatus();
    }

    /**
     * Create an instance of {@link ChangeStatusResponse }
     * 
     */
    public ChangeStatusResponse createChangeStatusResponse() {
        return new ChangeStatusResponse();
    }

    /**
     * Create an instance of {@link GetPizzas }
     * 
     */
    public GetPizzas createGetPizzas() {
        return new GetPizzas();
    }

    /**
     * Create an instance of {@link GetPizzasResponse }
     * 
     */
    public GetPizzasResponse createGetPizzasResponse() {
        return new GetPizzasResponse();
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
     * Create an instance of {@link OrderPizza }
     * 
     */
    public OrderPizza createOrderPizza() {
        return new OrderPizza();
    }

    /**
     * Create an instance of {@link OrderPizzaResponse }
     * 
     */
    public OrderPizzaResponse createOrderPizzaResponse() {
        return new OrderPizzaResponse();
    }

    /**
     * Create an instance of {@link RemovePizza }
     * 
     */
    public RemovePizza createRemovePizza() {
        return new RemovePizza();
    }

    /**
     * Create an instance of {@link RemovePizzaResponse }
     * 
     */
    public RemovePizzaResponse createRemovePizzaResponse() {
        return new RemovePizzaResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPizza }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddPizza }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.pizza/", name = "addPizza")
    public JAXBElement<AddPizza> createAddPizza(AddPizza value) {
        return new JAXBElement<AddPizza>(_AddPizza_QNAME, AddPizza.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPizzaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddPizzaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.pizza/", name = "addPizzaResponse")
    public JAXBElement<AddPizzaResponse> createAddPizzaResponse(AddPizzaResponse value) {
        return new JAXBElement<AddPizzaResponse>(_AddPizzaResponse_QNAME, AddPizzaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ChangeStatus }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.pizza/", name = "changeStatus")
    public JAXBElement<ChangeStatus> createChangeStatus(ChangeStatus value) {
        return new JAXBElement<ChangeStatus>(_ChangeStatus_QNAME, ChangeStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeStatusResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ChangeStatusResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.pizza/", name = "changeStatusResponse")
    public JAXBElement<ChangeStatusResponse> createChangeStatusResponse(ChangeStatusResponse value) {
        return new JAXBElement<ChangeStatusResponse>(_ChangeStatusResponse_QNAME, ChangeStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPizzas }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPizzas }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.pizza/", name = "getPizzas")
    public JAXBElement<GetPizzas> createGetPizzas(GetPizzas value) {
        return new JAXBElement<GetPizzas>(_GetPizzas_QNAME, GetPizzas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPizzasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPizzasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.pizza/", name = "getPizzasResponse")
    public JAXBElement<GetPizzasResponse> createGetPizzasResponse(GetPizzasResponse value) {
        return new JAXBElement<GetPizzasResponse>(_GetPizzasResponse_QNAME, GetPizzasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStatus }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.pizza/", name = "getStatus")
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
    @XmlElementDecl(namespace = "http://server.pizza/", name = "getStatusResponse")
    public JAXBElement<GetStatusResponse> createGetStatusResponse(GetStatusResponse value) {
        return new JAXBElement<GetStatusResponse>(_GetStatusResponse_QNAME, GetStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderPizza }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OrderPizza }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.pizza/", name = "orderPizza")
    public JAXBElement<OrderPizza> createOrderPizza(OrderPizza value) {
        return new JAXBElement<OrderPizza>(_OrderPizza_QNAME, OrderPizza.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderPizzaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OrderPizzaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.pizza/", name = "orderPizzaResponse")
    public JAXBElement<OrderPizzaResponse> createOrderPizzaResponse(OrderPizzaResponse value) {
        return new JAXBElement<OrderPizzaResponse>(_OrderPizzaResponse_QNAME, OrderPizzaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemovePizza }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RemovePizza }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.pizza/", name = "removePizza")
    public JAXBElement<RemovePizza> createRemovePizza(RemovePizza value) {
        return new JAXBElement<RemovePizza>(_RemovePizza_QNAME, RemovePizza.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemovePizzaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RemovePizzaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.pizza/", name = "removePizzaResponse")
    public JAXBElement<RemovePizzaResponse> createRemovePizzaResponse(RemovePizzaResponse value) {
        return new JAXBElement<RemovePizzaResponse>(_RemovePizzaResponse_QNAME, RemovePizzaResponse.class, null, value);
    }

}
