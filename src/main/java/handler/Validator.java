package handler;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.StringReader;

/**
 * This class includes method for validation XML file by XSD file
 */
public class Validator {

    /**
     * Method which validate XML file by XSD file
     * @param xml - XML-file that needs to be validated
     * @param xsd - XSD-pattern
     * @return status of operation - true if the action is successful
     * false - if exception thrown
     */
    public static boolean validateXMLByXSD(String xml, String xsd) {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(new StreamSource(new StringReader(xsd)))
                    .newValidator()
                    .validate(new StreamSource(new StringReader(xml)));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
