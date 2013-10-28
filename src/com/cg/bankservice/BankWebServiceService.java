
package com.cg.bankservice;

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
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "BankWebServiceService", targetNamespace = "http://webservice.cg.com/", wsdlLocation = "http://10.102.54.138:9999/BankWebService/BankService?wsdl")
public class BankWebServiceService
    extends Service
{

    private final static URL BANKWEBSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException BANKWEBSERVICESERVICE_EXCEPTION;
    private final static QName BANKWEBSERVICESERVICE_QNAME = new QName("http://webservice.cg.com/", "BankWebServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://10.102.54.138:9999/BankWebService/BankService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BANKWEBSERVICESERVICE_WSDL_LOCATION = url;
        BANKWEBSERVICESERVICE_EXCEPTION = e;
    }

    public BankWebServiceService() {
        super(__getWsdlLocation(), BANKWEBSERVICESERVICE_QNAME);
    }

    public BankWebServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), BANKWEBSERVICESERVICE_QNAME, features);
    }

    public BankWebServiceService(URL wsdlLocation) {
        super(wsdlLocation, BANKWEBSERVICESERVICE_QNAME);
    }

    public BankWebServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BANKWEBSERVICESERVICE_QNAME, features);
    }

    public BankWebServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BankWebServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BankWebService
     */
    @WebEndpoint(name = "BankWebServicePort")
    public BankWebService getBankWebServicePort() {
        return super.getPort(new QName("http://webservice.cg.com/", "BankWebServicePort"), BankWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BankWebService
     */
    @WebEndpoint(name = "BankWebServicePort")
    public BankWebService getBankWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.cg.com/", "BankWebServicePort"), BankWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BANKWEBSERVICESERVICE_EXCEPTION!= null) {
            throw BANKWEBSERVICESERVICE_EXCEPTION;
        }
        return BANKWEBSERVICESERVICE_WSDL_LOCATION;
    }

}