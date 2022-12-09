
package car.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the car.service package. 
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

    private final static QName _AddCar_QNAME = new QName("http://car/", "addCar");
    private final static QName _AddCarResponse_QNAME = new QName("http://car/", "addCarResponse");
    private final static QName _ChangeCar_QNAME = new QName("http://car/", "changeCar");
    private final static QName _ChangeCarResponse_QNAME = new QName("http://car/", "changeCarResponse");
    private final static QName _GetAllCars_QNAME = new QName("http://car/", "getAllCars");
    private final static QName _GetAllCarsResponse_QNAME = new QName("http://car/", "getAllCarsResponse");
    private final static QName _GetByModel_QNAME = new QName("http://car/", "getByModel");
    private final static QName _GetByModelResponse_QNAME = new QName("http://car/", "getByModelResponse");
    private final static QName _OrderCar_QNAME = new QName("http://car/", "orderCar");
    private final static QName _OrderCarResponse_QNAME = new QName("http://car/", "orderCarResponse");
    private final static QName _ReturnCar_QNAME = new QName("http://car/", "returnCar");
    private final static QName _ReturnCarResponse_QNAME = new QName("http://car/", "returnCarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: car.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddCar }
     * 
     */
    public AddCar createAddCar() {
        return new AddCar();
    }

    /**
     * Create an instance of {@link AddCarResponse }
     * 
     */
    public AddCarResponse createAddCarResponse() {
        return new AddCarResponse();
    }

    /**
     * Create an instance of {@link ChangeCar }
     * 
     */
    public ChangeCar createChangeCar() {
        return new ChangeCar();
    }

    /**
     * Create an instance of {@link ChangeCarResponse }
     * 
     */
    public ChangeCarResponse createChangeCarResponse() {
        return new ChangeCarResponse();
    }

    /**
     * Create an instance of {@link GetAllCars }
     * 
     */
    public GetAllCars createGetAllCars() {
        return new GetAllCars();
    }

    /**
     * Create an instance of {@link GetAllCarsResponse }
     * 
     */
    public GetAllCarsResponse createGetAllCarsResponse() {
        return new GetAllCarsResponse();
    }

    /**
     * Create an instance of {@link GetByModel }
     * 
     */
    public GetByModel createGetByModel() {
        return new GetByModel();
    }

    /**
     * Create an instance of {@link GetByModelResponse }
     * 
     */
    public GetByModelResponse createGetByModelResponse() {
        return new GetByModelResponse();
    }

    /**
     * Create an instance of {@link OrderCar }
     * 
     */
    public OrderCar createOrderCar() {
        return new OrderCar();
    }

    /**
     * Create an instance of {@link OrderCarResponse }
     * 
     */
    public OrderCarResponse createOrderCarResponse() {
        return new OrderCarResponse();
    }

    /**
     * Create an instance of {@link ReturnCar }
     * 
     */
    public ReturnCar createReturnCar() {
        return new ReturnCar();
    }

    /**
     * Create an instance of {@link ReturnCarResponse }
     * 
     */
    public ReturnCarResponse createReturnCarResponse() {
        return new ReturnCarResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddCar }{@code >}
     */
    @XmlElementDecl(namespace = "http://car/", name = "addCar")
    public JAXBElement<AddCar> createAddCar(AddCar value) {
        return new JAXBElement<AddCar>(_AddCar_QNAME, AddCar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddCarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://car/", name = "addCarResponse")
    public JAXBElement<AddCarResponse> createAddCarResponse(AddCarResponse value) {
        return new JAXBElement<AddCarResponse>(_AddCarResponse_QNAME, AddCarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeCar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ChangeCar }{@code >}
     */
    @XmlElementDecl(namespace = "http://car/", name = "changeCar")
    public JAXBElement<ChangeCar> createChangeCar(ChangeCar value) {
        return new JAXBElement<ChangeCar>(_ChangeCar_QNAME, ChangeCar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeCarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ChangeCarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://car/", name = "changeCarResponse")
    public JAXBElement<ChangeCarResponse> createChangeCarResponse(ChangeCarResponse value) {
        return new JAXBElement<ChangeCarResponse>(_ChangeCarResponse_QNAME, ChangeCarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCars }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllCars }{@code >}
     */
    @XmlElementDecl(namespace = "http://car/", name = "getAllCars")
    public JAXBElement<GetAllCars> createGetAllCars(GetAllCars value) {
        return new JAXBElement<GetAllCars>(_GetAllCars_QNAME, GetAllCars.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCarsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllCarsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://car/", name = "getAllCarsResponse")
    public JAXBElement<GetAllCarsResponse> createGetAllCarsResponse(GetAllCarsResponse value) {
        return new JAXBElement<GetAllCarsResponse>(_GetAllCarsResponse_QNAME, GetAllCarsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByModel }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetByModel }{@code >}
     */
    @XmlElementDecl(namespace = "http://car/", name = "getByModel")
    public JAXBElement<GetByModel> createGetByModel(GetByModel value) {
        return new JAXBElement<GetByModel>(_GetByModel_QNAME, GetByModel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByModelResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetByModelResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://car/", name = "getByModelResponse")
    public JAXBElement<GetByModelResponse> createGetByModelResponse(GetByModelResponse value) {
        return new JAXBElement<GetByModelResponse>(_GetByModelResponse_QNAME, GetByModelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderCar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OrderCar }{@code >}
     */
    @XmlElementDecl(namespace = "http://car/", name = "orderCar")
    public JAXBElement<OrderCar> createOrderCar(OrderCar value) {
        return new JAXBElement<OrderCar>(_OrderCar_QNAME, OrderCar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderCarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OrderCarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://car/", name = "orderCarResponse")
    public JAXBElement<OrderCarResponse> createOrderCarResponse(OrderCarResponse value) {
        return new JAXBElement<OrderCarResponse>(_OrderCarResponse_QNAME, OrderCarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnCar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReturnCar }{@code >}
     */
    @XmlElementDecl(namespace = "http://car/", name = "returnCar")
    public JAXBElement<ReturnCar> createReturnCar(ReturnCar value) {
        return new JAXBElement<ReturnCar>(_ReturnCar_QNAME, ReturnCar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnCarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReturnCarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://car/", name = "returnCarResponse")
    public JAXBElement<ReturnCarResponse> createReturnCarResponse(ReturnCarResponse value) {
        return new JAXBElement<ReturnCarResponse>(_ReturnCarResponse_QNAME, ReturnCarResponse.class, null, value);
    }

}
