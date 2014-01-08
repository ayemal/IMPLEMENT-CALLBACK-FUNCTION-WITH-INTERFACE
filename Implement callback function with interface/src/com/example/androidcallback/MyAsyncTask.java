package com.example.androidcallback;

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

import android.os.SystemClock;

import com.example.androidcallback.POJO.XMLCatTagClass;
import com.example.androidcallback.POJO.XMLItemTagClass;
import com.example.androidcallback.POJO.XMLRootClass;

public class MyAsyncTask extends
		ITaskRunner<String, XMLItemTagClass, XMLRootClass> {

	private static final String TAG = "MyAsyncTask";
	protected XMLRootClass rootObject;

	MyAsyncTask(ICallBack<XMLItemTagClass, XMLRootClass> callback) {
		super(callback);
	}

	@Override
	protected XMLRootClass doInBackground(String... params) {

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			URL sourceURL = new URL(params[0]);
			xmlReader.setContentHandler(new DefaultHandler() {

				private XMLCatTagClass catObject;

				@Override
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
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

						publishProgress(itemObject);
						SystemClock.sleep(1000);
 
					}
				}
			});
			xmlReader.parse(new InputSource(sourceURL.openStream()));

		} catch (SSLHandshakeException e) {
			System.out.println("SSLHandshakeException");
		} catch (IOException e) {
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
