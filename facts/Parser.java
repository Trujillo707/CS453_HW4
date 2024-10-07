package facts;

import java.io.*;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.xml.sax.helpers.DefaultHandler;

public class Parser{
	private Handler handler;
	private String xmlFileDir;
	public Parser (String fileName){
		try{
			xmlFileDir = fileName;
			File file = new File(fileName);
			handler = new Handler();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser =  factory.newSAXParser();
			saxParser.parse (file, handler);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public FactList getFactList(){
		return handler.getList();
	}

	/**
	 *  This method rewrites the XML based on the current FactList with an appeneded new fact.
	 *
	 *
	 * @throws IOException Couldn't open XML
	 */
	public void writeXML(Fact newFact) throws IOException {
		FileWriter filewriter = new FileWriter(xmlFileDir);

		filewriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		filewriter.write("<fact-list>\n");

		FactList theList = handler.getList();
		theList.set(newFact);
		for (int i = 0; i < theList.getSize(); i++) {
			filewriter.write("\t<fact>\n");
			filewriter.write("\t\t<fact-text>" + theList.get(i).getText() +"</fact-text>\n");
			filewriter.write("\t\t<author>" + theList.get(i).getAuthor() + "</author>\n");
			filewriter.write("\t\t<fact-type>" + theList.get(i).getType() + "</fact-type>\n");
			filewriter.write("\t</fact>\n");
		}

		filewriter.write("</fact-list>\n");

		filewriter.close();
	}
}
