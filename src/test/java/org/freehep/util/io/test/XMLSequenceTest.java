// Copyright 2003, FreeHEP.
package org.freehep.util.io.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.freehep.util.io.XMLSequence;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/** 
 * @author Mark Donszelmann
 * @version $Id: src/test/java/org/freehep/util/io/test/XMLSequenceTest.java effd8b4f3966 2005/11/19 07:52:18 duns $
 */
public class XMLSequenceTest {

    public static void main(String[] args) 
            throws IOException, 
                   ParserConfigurationException,
                   SAXException {
                    
        if (args.length != 1) {
            System.out.println("Usage: XMLSequenceInputStreamTest filename");
            System.exit(1);
        }
        File file = new File(args[0]);
        XMLSequence sequence = new XMLSequence(new BufferedInputStream(new FileInputStream(file)));
        if (sequence.markSupported()) {
            sequence.mark((int)file.length()+1);
        }
    
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XMLReader xmlReader = factory.newSAXParser().getXMLReader();
    
        int i = 0;
        while (sequence.hasNext()) {
            InputStream input = sequence.next();
            InputSource source = new InputSource(input);
            xmlReader.parse(source);
            input.close();
            i++;
            System.out.println("Parsed XML section: "+i);
        }
        
        // try reset...
        if (sequence.markSupported()) {
            System.out.println("Reset file");
            sequence.reset();
            i = 0;
            while (sequence.hasNext()) {
                InputStream input = sequence.next();
                InputSource source = new InputSource(input);
                xmlReader.parse(source);
                input.close();
                i++;
                System.out.println("Parsed XML section: "+i);
            }
        } else {
            System.out.println("Reset not supported");
        }
        sequence.close();
    }
}
