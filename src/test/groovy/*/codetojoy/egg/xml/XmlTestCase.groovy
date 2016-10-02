
package net.codetojoy.egg.xml

import javax.xml.XMLConstants
import javax.xml.transform.stream.*
import javax.xml.transform.*
import javax.xml.validation.SchemaFactory

// these are closer to integration tests than 'unit'
class XmlTestCase extends GroovyTestCase {
    
    void testXslValidation_Green() {
        def document = 'data/shiporder.xml'
        def xsd = 'data/shiporder.xsd'
        def xsdStr = new File(xsd).getText()
        def documentStr = new File(document).getText()
        
        def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        def schema = factory.newSchema(new StreamSource(new StringReader(xsdStr)))
        def validator = schema.newValidator()

        // test
        validator.validate(new StreamSource(new StringReader(documentStr)))
    }

    void testXslValidation_Red() {
        def document = 'data/bad_shiporder.xml'
        def xsd = 'data/shiporder.xsd'
        def xsdStr = new File(xsd).getText()
        def documentStr = new File(document).getText()
        
        def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        def schema = factory.newSchema(new StreamSource(new StringReader(xsdStr)))
        def validator = schema.newValidator()

        try {
            // test
            validator.validate(new StreamSource(new StringReader(documentStr)))            
        } catch(org.xml.sax.SAXParseException ex) {
            // happy path
        }
    }
    
    void testXslTransform() {
        def xmlStr = new File('data/persons.xml').getText().trim()
        def xslStr = new File('data/persons.xsl').getText().trim()

        def factory = TransformerFactory.newInstance()
        def transformer = factory.newTransformer(new StreamSource(new StringReader(xslStr)))
        def output = new StringWriter()
                
        // test
        transformer.transform(new StreamSource(new StringReader(xmlStr)), new StreamResult(output)) 
        
        // TODO: use regexes
        assertTrue( output.toString().indexOf("Nancy") != -1 )
        assertTrue( output.toString().indexOf("John") != -1 )
    }
}

