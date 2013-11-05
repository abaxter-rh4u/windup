package org.jboss.windup.reporting.integration.forge;

import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.windup.metadata.type.XmlMetadata;
import org.jboss.windup.metadata.util.LocationAwareXmlReader;
import org.jboss.windup.metadata.util.NamespaceMapContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ExampleServiceCreation {

	private static final XPathFactory factory = XPathFactory.newInstance();
	private static final Log LOG = LogFactory.getLog(ExampleServiceCreation.class);
	
	public static void createService(XmlMetadata xmlMetadata) {
		//*/providers/jms-provider/jms-bus[@busid = //*/services/service/listeners/jms-listener[@is-gateway='true']/@busidref]/@busid
		//*[local-name()='jms-bus'][@busid = //*[local-name()='jms-listener'][@is-gateway='true']/@busidref]/jms-message-filter/@dest-type
		//*[local-name()='jms-bus'][@busid = //*[local-name()='jms-listener'][@is-gateway='true']/@busidref]/jms-message-filter/@dest-name

		XPath xpath = factory.newXPath();
		NamespaceMapContext context = new NamespaceMapContext(new HashMap<String, String>());
		xpath.setNamespaceContext(context);
		
		XPathExpression expression = expression = xpath.compile("//*[local-name()='jms-listener']");

		Document doc = xmlMetadata.getParsedDocument();
        
        final NodeList nodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);

        if (nodes != null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Found results for: " + meta.getFilePointer().getAbsolutePath());
            }
            for (int i = 0; i < nodes.getLength(); i++) {
                Integer lineNumber = (Integer) LocationAwareXmlReader.getLineNumber(nodes.item(i));
                String match = convertNode(nodes.item(i));
                if (inline && lineNumber != null) {
                    createLineNumberMeta(meta, lineNumber, this.matchDescription, nodes.item(i));
                }
                else {
                    createSummaryMeta(meta, this.matchDescription, match);
                }
            }
            isProcessed = true;
	}
        
    public void xpathFromNode(Node node) {
    	
    }

}
