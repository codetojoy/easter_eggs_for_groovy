import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.transform.sax.SAXSource
import javax.xml.parsers.SAXParserFactory
import org.xml.sax.SAXException 
import org.xml.sax.InputSource
import org.xml.sax.SAXParseException
import org.xml.sax.ErrorHandler

class MyErrorHandler implements ErrorHandler {
    final def exceptions

    MyErrorHandler(def exceptions) {
        this.exceptions = exceptions
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        exceptions.add(exception)
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        exceptions.add(exception)
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        exceptions.add(exception)
    }
}

/*
def getNodeName(xmlFile, lineNumber) {
  def xmlLine =  xmlFile.readLines().get(lineNumber)  
  def node = new XmlSlurper().parseText(xmlLine.toString())
  node.name()
}
*/

def getNodeName(xmlFile, lineNumber) {
    def result = "unknown"
    def count = 1
    def NODE_REGEX = /.*?<(.*?)>.*/ 
    def br 

    try {
        br = new BufferedReader(new FileReader(xmlFile)) 
        String line
        def isDone = false
        while ((! isDone) && (line = br.readLine()) != null) {
            if (count == lineNumber) {
                def matcher = (line =~ NODE_REGEX) 
                if (matcher.matches()) {
                    result = matcher[0][1]
                }
                isDone = true
            }
            count++
        }
    } finally {
        br.close()
    }

    return result
}

def validateXMLSchema(String xsdPath, String xmlPath) {
    def result = false

    final def exceptions = new LinkedList<SAXParseException>()
    try {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        Schema schema = factory.newSchema(new File(xsdPath))
        Validator validator = schema.newValidator()  
        validator.setErrorHandler(new MyErrorHandler(exceptions))
      
        def xmlFile = new File(xmlPath)
        validator.validate(new StreamSource(xmlFile))
        exceptions.each {
            println 'node name: ' + getNodeName(xmlFile, it.lineNumber) + ' lineNumber : ' + it.lineNumber + '; message : ' + it.message
        }
     } catch (IOException | SAXException e) {
        println("Exception: line ${e.lineNumber} " + e.getMessage());
     }
     result = exceptions.isEmpty()
     return result
}

// ----- main

validateXMLSchema('b.xsd', 'a.xml')

println "Ready."
