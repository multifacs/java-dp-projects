
package publiclibrary.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publiclibrary.service package. 
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

    private final static QName _AddBook_QNAME = new QName("http://publiclibrary/", "addBook");
    private final static QName _AddBookResponse_QNAME = new QName("http://publiclibrary/", "addBookResponse");
    private final static QName _AddCount_QNAME = new QName("http://publiclibrary/", "addCount");
    private final static QName _AddCountResponse_QNAME = new QName("http://publiclibrary/", "addCountResponse");
    private final static QName _GetAllBooks_QNAME = new QName("http://publiclibrary/", "getAllBooks");
    private final static QName _GetAllBooksResponse_QNAME = new QName("http://publiclibrary/", "getAllBooksResponse");
    private final static QName _RentCount_QNAME = new QName("http://publiclibrary/", "rentCount");
    private final static QName _RentCountResponse_QNAME = new QName("http://publiclibrary/", "rentCountResponse");
    private final static QName _ReturnCount_QNAME = new QName("http://publiclibrary/", "returnCount");
    private final static QName _ReturnCountResponse_QNAME = new QName("http://publiclibrary/", "returnCountResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publiclibrary.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddBook }
     * 
     */
    public AddBook createAddBook() {
        return new AddBook();
    }

    /**
     * Create an instance of {@link AddBookResponse }
     * 
     */
    public AddBookResponse createAddBookResponse() {
        return new AddBookResponse();
    }

    /**
     * Create an instance of {@link AddCount }
     * 
     */
    public AddCount createAddCount() {
        return new AddCount();
    }

    /**
     * Create an instance of {@link AddCountResponse }
     * 
     */
    public AddCountResponse createAddCountResponse() {
        return new AddCountResponse();
    }

    /**
     * Create an instance of {@link GetAllBooks }
     * 
     */
    public GetAllBooks createGetAllBooks() {
        return new GetAllBooks();
    }

    /**
     * Create an instance of {@link GetAllBooksResponse }
     * 
     */
    public GetAllBooksResponse createGetAllBooksResponse() {
        return new GetAllBooksResponse();
    }

    /**
     * Create an instance of {@link RentCount }
     * 
     */
    public RentCount createRentCount() {
        return new RentCount();
    }

    /**
     * Create an instance of {@link RentCountResponse }
     * 
     */
    public RentCountResponse createRentCountResponse() {
        return new RentCountResponse();
    }

    /**
     * Create an instance of {@link ReturnCount }
     * 
     */
    public ReturnCount createReturnCount() {
        return new ReturnCount();
    }

    /**
     * Create an instance of {@link ReturnCountResponse }
     * 
     */
    public ReturnCountResponse createReturnCountResponse() {
        return new ReturnCountResponse();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBook }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddBook }{@code >}
     */
    @XmlElementDecl(namespace = "http://publiclibrary/", name = "addBook")
    public JAXBElement<AddBook> createAddBook(AddBook value) {
        return new JAXBElement<AddBook>(_AddBook_QNAME, AddBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBookResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddBookResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publiclibrary/", name = "addBookResponse")
    public JAXBElement<AddBookResponse> createAddBookResponse(AddBookResponse value) {
        return new JAXBElement<AddBookResponse>(_AddBookResponse_QNAME, AddBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCount }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddCount }{@code >}
     */
    @XmlElementDecl(namespace = "http://publiclibrary/", name = "addCount")
    public JAXBElement<AddCount> createAddCount(AddCount value) {
        return new JAXBElement<AddCount>(_AddCount_QNAME, AddCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCountResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddCountResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publiclibrary/", name = "addCountResponse")
    public JAXBElement<AddCountResponse> createAddCountResponse(AddCountResponse value) {
        return new JAXBElement<AddCountResponse>(_AddCountResponse_QNAME, AddCountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllBooks }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllBooks }{@code >}
     */
    @XmlElementDecl(namespace = "http://publiclibrary/", name = "getAllBooks")
    public JAXBElement<GetAllBooks> createGetAllBooks(GetAllBooks value) {
        return new JAXBElement<GetAllBooks>(_GetAllBooks_QNAME, GetAllBooks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllBooksResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllBooksResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publiclibrary/", name = "getAllBooksResponse")
    public JAXBElement<GetAllBooksResponse> createGetAllBooksResponse(GetAllBooksResponse value) {
        return new JAXBElement<GetAllBooksResponse>(_GetAllBooksResponse_QNAME, GetAllBooksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RentCount }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RentCount }{@code >}
     */
    @XmlElementDecl(namespace = "http://publiclibrary/", name = "rentCount")
    public JAXBElement<RentCount> createRentCount(RentCount value) {
        return new JAXBElement<RentCount>(_RentCount_QNAME, RentCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RentCountResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RentCountResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publiclibrary/", name = "rentCountResponse")
    public JAXBElement<RentCountResponse> createRentCountResponse(RentCountResponse value) {
        return new JAXBElement<RentCountResponse>(_RentCountResponse_QNAME, RentCountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnCount }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReturnCount }{@code >}
     */
    @XmlElementDecl(namespace = "http://publiclibrary/", name = "returnCount")
    public JAXBElement<ReturnCount> createReturnCount(ReturnCount value) {
        return new JAXBElement<ReturnCount>(_ReturnCount_QNAME, ReturnCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnCountResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReturnCountResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publiclibrary/", name = "returnCountResponse")
    public JAXBElement<ReturnCountResponse> createReturnCountResponse(ReturnCountResponse value) {
        return new JAXBElement<ReturnCountResponse>(_ReturnCountResponse_QNAME, ReturnCountResponse.class, null, value);
    }

}
