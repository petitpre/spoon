import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import treemap.demo.Coverage;
import treemap.demo.Method;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;

/**
 * Created by nicolas on 18/08/2015.
 */
public class TreeMap {
	private static final String Q = "'";

	public static void main(String[] args) throws Exception {
		try {
			JAXBContext jc = JAXBContext.newInstance("treemap.demo");

			Unmarshaller unmarshaller = jc.createUnmarshaller();

			// use our own parser to disable DTD validating
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setValidating(false);
			XMLReader xmlReader = spf.newSAXParser().getXMLReader();

			InputSource inputSource = new InputSource(new FileInputStream(args[0]));
			SAXSource source = new SAXSource(xmlReader, inputSource);

			Coverage coverage = (Coverage) unmarshaller.unmarshal(source);

			PrintStream out = new PrintStream(new File(args[1]));

			out.println("var datas = [");

			out.println("['spoon', null,0,0,'spoon'],");
			for (treemap.demo.Package pack : coverage.getPackages().getPackage()) {
				//				out.println("['" + pack.getName() + "', 'spoon',0,0],");

				for (treemap.demo.Class clz : pack.getClasses().getClazz()) {
					out.println(
							"[" +
									Q + getShortName(clz) + Q + "," +
									Q + "spoon" + Q + "," +
									clz.getLines().getLine().size() + "," +
									clz.getLineRate() + "," +
									Q + clz.getName() + Q +
									"],");

					for (Method method : clz.getMethods().getMethod()) {

						out.println(
								"[" +
										Q + getSignature(getShortName(clz), method) + Q + "," +
										Q + getShortName(clz) + Q + "," +
										method.getLines().getLine().size() + "," +
										method.getLineRate() + "," +
										Q + getSignature(clz.getName(), method) + Q +
										"],");

					}
				}
			}

			out.println("];");

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getShortName(treemap.demo.Class clz) {
		if (clz.getName().lastIndexOf('.') > 0) {
			return clz.getName().substring(clz.getName().lastIndexOf('.') + 1);
		}
		return clz.getName();

	}

	public static String getSignature(String clazz, Method method) {
		return clazz + "#" + method.getName() + " " + method.getSignature();
	}

}
