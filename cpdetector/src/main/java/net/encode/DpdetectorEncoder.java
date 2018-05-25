package net.encode;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 获取文件流或者文件的编码格式<br>
 * http://cpdetector.sourceforge.net/
 * @author adolf.felix
 */
public class DpdetectorEncoder {
	public static void main(String[] args) throws IOException {
		String ansi = "data/ansi.txt";
		String unicode = "data/unicode.txt";
		String utf8 = "data/utf-8.txt";
		
		System.out.println("************* 1 ***************");
		System.out.println("ansi code=" + getEncodeByFile(ansi));
		System.out.println("unicode code=" + getEncodeByFile(unicode));
		System.out.println("utf8 code=" + getEncodeByFile(utf8));
		
		System.out.println("\n************* 2 ***************");
		System.out.println("ansi code=" + getEncodeByIS(ansi));
		System.out.println("unicode code=" + getEncodeByIS(unicode));
		System.out.println("utf8 code=" + getEncodeByIS(utf8));
	}

	private static String getEncodeByIS(String file) throws FileNotFoundException {
		// BufferInputStream重写了父类FilterInputStream的mark和reset方法, 其有支持 mark和reset方法的能力。
		// 而FileInputStream则没有重写父类InputStream的这两个方法, 其不具有mark和reset方法的能力。
		InputStream in = new BufferedInputStream(new FileInputStream(file));

		/**
		 * detector是探测器, 它把探测任务交给具体的探测实现类的实例完成。
		 * cpDetector内置了一些常用的探测实现类, 这些探测实现类的实例可以通过add方法 加进来, 如ParsingDetector、
		 * JChardetFacade、ASCIIDetector、UnicodeDetector。
		 * detector按照"谁最先返回非空的探测结果, 就以该结果为准"的原则返回探测到的
		 * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar
		 * cpDetector是基于统计学原理的, 不保证完全正确。
		 */
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		/**
		 * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
		 * 指示是否显示探测过程的详细信息, 为false不显示。
		 */
		detector.add(new ParsingDetector(false));
		// UnicodeDetector用于Unicode家族编码的测定
		detector.add(UnicodeDetector.getInstance());
		/**
		 * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
		 * 测定。所以, 一般有了这个探测器就可满足大多数项目的要求, 如果你还不放心, 可以
		 * 再多加几个探测器, 比如下面的ASCIIDetector、UnicodeDetector等。
		 */
		detector.add(JChardetFacade.getInstance());
		// ASCIIDetector用于ASCII编码测定
		detector.add(ASCIIDetector.getInstance());

		
		Charset charset = null;
		try {
			charset = detector.detectCodepage(in, 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 默认为GBK
		String charsetName = "GBK";
		if (charset != null) {
			if (charset.name().equals("US-ASCII")) {
				charsetName = "ISO_8859_1";
			} else {
				charsetName = charset.name();
			}
		}
		
		return charsetName;
	}
	
	private static String getEncodeByFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		
		/**
		 * detector是探测器, 它把探测任务交给具体的探测实现类的实例完成。
		 * cpDetector内置了一些常用的探测实现类, 这些探测实现类的实例可以通过add方法 加进来, 如ParsingDetector、
		 * JChardetFacade、ASCIIDetector、UnicodeDetector。
		 * detector按照"谁最先返回非空的探测结果, 就以该结果为准"的原则返回探测到的
		 * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar
		 * cpDetector是基于统计学原理的, 不保证完全正确。
		 */
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		/**
		 * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
		 * 指示是否显示探测过程的详细信息, 为false不显示。
		 */
		detector.add(new ParsingDetector(false));
		// UnicodeDetector用于Unicode家族编码的测定
		detector.add(UnicodeDetector.getInstance());
		/**
		 * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
		 * 测定。所以, 一般有了这个探测器就可满足大多数项目的要求, 如果你还不放心, 可以
		 * 再多加几个探测器, 比如下面的ASCIIDetector、UnicodeDetector等。
		 */
		detector.add(JChardetFacade.getInstance());
		// ASCIIDetector用于ASCII编码测定
		detector.add(ASCIIDetector.getInstance());
		
		
		Charset charset = null;
		try {
			charset = detector.detectCodepage(file.toURI().toURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 默认为GBK
		String charsetName = "GBK";
		if (charset != null) {
			if (charset.name().equals("US-ASCII")) {
				charsetName = "ISO_8859_1";
			} else {
				charsetName = charset.name();
			}
		}
		
		return charsetName;
	}
}
