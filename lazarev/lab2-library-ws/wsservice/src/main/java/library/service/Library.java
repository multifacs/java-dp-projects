
package library.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Library", targetNamespace = "http://service.ws/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Library {


    /**
     * 
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllBooks", targetNamespace = "http://service.ws/", className = "library.service.GetAllBooks")
    @ResponseWrapper(localName = "getAllBooksResponse", targetNamespace = "http://service.ws/", className = "library.service.GetAllBooksResponse")
    @Action(input = "http://service.ws/Library/getAllBooksRequest", output = "http://service.ws/Library/getAllBooksResponse")
    public List<String> getAllBooks();

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addCopyBook", targetNamespace = "http://service.ws/", className = "library.service.AddCopyBook")
    @ResponseWrapper(localName = "addCopyBookResponse", targetNamespace = "http://service.ws/", className = "library.service.AddCopyBookResponse")
    @Action(input = "http://service.ws/Library/addCopyBookRequest", output = "http://service.ws/Library/addCopyBookResponse")
    public void addCopyBook(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "takeBook", targetNamespace = "http://service.ws/", className = "library.service.TakeBook")
    @ResponseWrapper(localName = "takeBookResponse", targetNamespace = "http://service.ws/", className = "library.service.TakeBookResponse")
    @Action(input = "http://service.ws/Library/takeBookRequest", output = "http://service.ws/Library/takeBookResponse")
    public boolean takeBook(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addNewBook", targetNamespace = "http://service.ws/", className = "library.service.AddNewBook")
    @ResponseWrapper(localName = "addNewBookResponse", targetNamespace = "http://service.ws/", className = "library.service.AddNewBookResponse")
    @Action(input = "http://service.ws/Library/addNewBookRequest", output = "http://service.ws/Library/addNewBookResponse")
    public void addNewBook(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        List<String> arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "returnBook", targetNamespace = "http://service.ws/", className = "library.service.ReturnBook")
    @ResponseWrapper(localName = "returnBookResponse", targetNamespace = "http://service.ws/", className = "library.service.ReturnBookResponse")
    @Action(input = "http://service.ws/Library/returnBookRequest", output = "http://service.ws/Library/returnBookResponse")
    public void returnBook(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

}