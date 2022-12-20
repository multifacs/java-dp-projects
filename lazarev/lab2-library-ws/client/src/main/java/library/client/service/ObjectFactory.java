
package library.client.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the library.client.service package. 
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

    private final static QName _AddCopyBook_QNAME = new QName("http://service.ws/", "addCopyBook");
    private final static QName _AddCopyBookResponse_QNAME = new QName("http://service.ws/", "addCopyBookResponse");
    private final static QName _AddNewBook_QNAME = new QName("http://service.ws/", "addNewBook");
    private final static QName _AddNewBookResponse_QNAME = new QName("http://service.ws/", "addNewBookResponse");
    private final static QName _GetAllBooks_QNAME = new QName("http://service.ws/", "getAllBooks");
    private final static QName _GetAllBooksResponse_QNAME = new QName("http://service.ws/", "getAllBooksResponse");
    private final static QName _ReturnBook_QNAME = new QName("http://service.ws/", "returnBook");
    private final static QName _ReturnBookResponse_QNAME = new QName("http://service.ws/", "returnBookResponse");
    private final static QName _TakeBook_QNAME = new QName("http://service.ws/", "takeBook");
    private final static QName _TakeBookResponse_QNAME = new QName("http://service.ws/", "takeBookResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: library.client.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddCopyBook }
     * 
     */
    public AddCopyBook createAddCopyBook() {
        return new AddCopyBook();
    }

    /**
     * Create an instance of {@link AddCopyBookResponse }
     * 
     */
    public AddCopyBookResponse createAddCopyBookResponse() {
        return new AddCopyBookResponse();
    }

    /**
     * Create an instance of {@link AddNewBook }
     * 
     */
    public AddNewBook createAddNewBook() {
        return new AddNewBook();
    }

    /**
     * Create an instance of {@link AddNewBookResponse }
     * 
     */
    public AddNewBookResponse createAddNewBookResponse() {
        return new AddNewBookResponse();
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
     * Create an instance of {@link ReturnBook }
     * 
     */
    public ReturnBook createReturnBook() {
        return new ReturnBook();
    }

    /**
     * Create an instance of {@link ReturnBookResponse }
     * 
     */
    public ReturnBookResponse createReturnBookResponse() {
        return new ReturnBookResponse();
    }

    /**
     * Create an instance of {@link TakeBook }
     * 
     */
    public TakeBook createTakeBook() {
        return new TakeBook();
    }

    /**
     * Create an instance of {@link TakeBookResponse }
     * 
     */
    public TakeBookResponse createTakeBookResponse() {
        return new TakeBookResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCopyBook }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddCopyBook }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.ws/", name = "addCopyBook")
    public JAXBElement<AddCopyBook> createAddCopyBook(AddCopyBook value) {
        return new JAXBElement<AddCopyBook>(_AddCopyBook_QNAME, AddCopyBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCopyBookResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddCopyBookResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.ws/", name = "addCopyBookResponse")
    public JAXBElement<AddCopyBookResponse> createAddCopyBookResponse(AddCopyBookResponse value) {
        return new JAXBElement<AddCopyBookResponse>(_AddCopyBookResponse_QNAME, AddCopyBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewBook }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddNewBook }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.ws/", name = "addNewBook")
    public JAXBElement<AddNewBook> createAddNewBook(AddNewBook value) {
        return new JAXBElement<AddNewBook>(_AddNewBook_QNAME, AddNewBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewBookResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddNewBookResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.ws/", name = "addNewBookResponse")
    public JAXBElement<AddNewBookResponse> createAddNewBookResponse(AddNewBookResponse value) {
        return new JAXBElement<AddNewBookResponse>(_AddNewBookResponse_QNAME, AddNewBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllBooks }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllBooks }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.ws/", name = "getAllBooks")
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
    @XmlElementDecl(namespace = "http://service.ws/", name = "getAllBooksResponse")
    public JAXBElement<GetAllBooksResponse> createGetAllBooksResponse(GetAllBooksResponse value) {
        return new JAXBElement<GetAllBooksResponse>(_GetAllBooksResponse_QNAME, GetAllBooksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnBook }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReturnBook }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.ws/", name = "returnBook")
    public JAXBElement<ReturnBook> createReturnBook(ReturnBook value) {
        return new JAXBElement<ReturnBook>(_ReturnBook_QNAME, ReturnBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnBookResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReturnBookResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.ws/", name = "returnBookResponse")
    public JAXBElement<ReturnBookResponse> createReturnBookResponse(ReturnBookResponse value) {
        return new JAXBElement<ReturnBookResponse>(_ReturnBookResponse_QNAME, ReturnBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TakeBook }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TakeBook }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.ws/", name = "takeBook")
    public JAXBElement<TakeBook> createTakeBook(TakeBook value) {
        return new JAXBElement<TakeBook>(_TakeBook_QNAME, TakeBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TakeBookResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TakeBookResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.ws/", name = "takeBookResponse")
    public JAXBElement<TakeBookResponse> createTakeBookResponse(TakeBookResponse value) {
        return new JAXBElement<TakeBookResponse>(_TakeBookResponse_QNAME, TakeBookResponse.class, null, value);
    }

}
