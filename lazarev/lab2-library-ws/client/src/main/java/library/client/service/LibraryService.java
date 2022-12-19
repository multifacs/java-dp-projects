
package library.client.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "LibraryService", targetNamespace = "http://service.ws/", wsdlLocation = "http://localhost:8080/library?wsdl")
public class LibraryService
    extends Service
{

    public final static URL LIBRARYSERVICE_WSDL_LOCATION;
    public final static WebServiceException LIBRARYSERVICE_EXCEPTION;
    public final static QName LIBRARYSERVICE_QNAME = new QName("http://service.ws/", "LibraryService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/library?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LIBRARYSERVICE_WSDL_LOCATION = url;
        LIBRARYSERVICE_EXCEPTION = e;
    }

    public LibraryService() {
        super(__getWsdlLocation(), LIBRARYSERVICE_QNAME);
    }

    public LibraryService(WebServiceFeature... features) {
        super(__getWsdlLocation(), LIBRARYSERVICE_QNAME, features);
    }

    public LibraryService(URL wsdlLocation) {
        super(wsdlLocation, LIBRARYSERVICE_QNAME);
    }

    public LibraryService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LIBRARYSERVICE_QNAME, features);
    }

    public LibraryService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LibraryService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Library
     */
    @WebEndpoint(name = "LibraryPort")
    public Library getLibraryPort() {
        return super.getPort(new QName("http://service.ws/", "LibraryPort"), Library.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Library
     */
    @WebEndpoint(name = "LibraryPort")
    public Library getLibraryPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.ws/", "LibraryPort"), Library.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LIBRARYSERVICE_EXCEPTION!= null) {
            throw LIBRARYSERVICE_EXCEPTION;
        }
        return LIBRARYSERVICE_WSDL_LOCATION;
    }

}
