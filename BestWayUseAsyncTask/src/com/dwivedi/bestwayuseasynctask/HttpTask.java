package com.dwivedi.bestwayuseasynctask;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.SSLHandshakeException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.dwivedi.bestwayuseasynctask.POJO.XMLCatTagClass;
import com.dwivedi.bestwayuseasynctask.POJO.XMLItemTagClass;
import com.dwivedi.bestwayuseasynctask.POJO.XMLRootClass;
import com.dwivedi.bestwayuseasynctask.Task.AsyncTaskRunner;
import com.dwivedi.bestwayuseasynctask.Task.IAsyncTaskRunner;

public class HttpTask extends AsyncTaskRunner<String, XMLRootClass> {

	public XMLRootClass rootObject;

	public HttpTask(IAsyncTaskRunner<XMLRootClass> asyncTaskRunner) {
		super(asyncTaskRunner);
	}

	 
	@Override
	protected XMLRootClass doInBackground(String... url) {

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			URL sourceURL = new URL(url[0]);
			xmlReader.setContentHandler(new XMLHandler());
 			xmlReader.parse(new InputSource(sourceURL.openStream()));

		}catch (SSLHandshakeException e) {
			System.out.println("SSLHandshakeException");
  		}
		catch (IOException e) {
				System.out.println("IOException");
 		} catch (SAXException e) {
			System.out.println("SAXException");
		} catch (Exception e) {
			System.out.println("Exception");
		}
		return rootObject;
	}

	class XMLHandler extends DefaultHandler {

		private XMLCatTagClass catObject;

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			if (localName.equalsIgnoreCase("main")) {
				rootObject = new XMLRootClass();
			}
			if (localName.equalsIgnoreCase("cat")) {
				catObject = new XMLCatTagClass();
				catObject.setName(attributes.getValue("name"));
				rootObject.catTagClasses.add(catObject);
			}
			if (localName.equalsIgnoreCase("item")) {
				XMLItemTagClass itemObject = new XMLItemTagClass();
				itemObject.setUrl(attributes.getValue("url"));
				catObject.itemTagClasses.add(itemObject);
			}
		}
	}

}
