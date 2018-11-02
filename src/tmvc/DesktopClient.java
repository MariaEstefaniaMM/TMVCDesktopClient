package tmvc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import service.LoggerInterface;

public class DesktopClient {
	
	private static String level;
	private static String message;

    private static URL wsdlURL;
    private static QName serviceName;
    private static QName portName;
    private static String address;
    private static Service logger;
    private static LoggerInterface loggerInterface;

    public static void setUp() throws MalformedURLException {
        address = "http://localhost:8081/WebService";
        wsdlURL = new URL(address+"?wsdl");
        serviceName = new QName ("http://logger.tmvc/", "TMVCLoggerService");
        logger = Service.create(wsdlURL, serviceName);
        portName = new QName("http://logger.tmvc/","TMVCLoggerPort");
        loggerInterface = logger.getPort(portName, LoggerInterface.class);
    }        

    public static void main(String[] args) {
    	try {
            setUp();
            System.out.println("finished");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduzca nivel (warn, debug, error, process):");
        level = scan.nextLine();
        System.out.println("Introduzca mensaje:");
        message = scan.nextLine();
        loggerInterface.log(level, message);  
    }
}
