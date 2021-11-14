package com.demo.utils;

import java.io.InputStream;
import java.util.Base64;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import com.demo.helpers.TypeMap;
import net.sf.jett.transform.ExcelTransformer;

public class XlsxUtils {

	private static final Logger logger = Logger.getAnonymousLogger();	

	public static void generateXlsx(HttpServletResponse response, String template, String outputFileName, TypeMap dataSource) throws Exception{
		long generationStart = System.currentTimeMillis();

		InputStream inputStream = XlsxUtils.class.getClassLoader().getResourceAsStream(template);
		Workbook workBook = new ExcelTransformer().transform(inputStream, dataSource);
		addResponseHeader(response, outputFileName);
		workBook.write(response.getOutputStream());

		logger.info(() -> "Generating of " + outputFileName + " document took " + (System.currentTimeMillis() - generationStart));
	}

	private static void addResponseHeader(HttpServletResponse response, String outputFileName) {
		response.addHeader("Content-type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String encoded = Base64.getEncoder().encodeToString(outputFileName.getBytes());
		String contentDisposition = "attachment; filename=\"=?UTF-8?B?" + encoded + "?=\"";
		response.addHeader("Content-disposition", contentDisposition);
	}
}
