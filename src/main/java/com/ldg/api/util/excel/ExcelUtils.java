package com.ldg.api.util.excel;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import com.ldg.api.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils {
	public static <T> void createExcelWorkBook(Workbook workbook, List<T> data, Map<String, String> columns) {
		List<String> fields = new ArrayList<String>();
		List<String> heads = new ArrayList<String>();
		for (String key : columns.keySet()) {
			fields.add(key);
			heads.add(columns.get(key));
		}
		int rowNum = 0;
		///// 创建表头
		Sheet sheet = workbook.createSheet();
		Row row = sheet.createRow(rowNum);
		setCellValue(heads, row);
		//sheet.autoSizeColumn(rowNum);
		rowNum++;
		/////
		for (T t : data) {
			row = sheet.createRow(rowNum);
			rowNum++;
			Class clazz = t.getClass();
			int cellNum = 0;
			for (String field : fields) {
				Field fl = null;
				try {
					fl = clazz.getDeclaredField(field);
				} catch (NoSuchFieldException e) {
					try {
						fl = clazz.getSuperclass().getDeclaredField(field);
					} catch (NoSuchFieldException e1) {
						e1.printStackTrace();
					} catch (SecurityException e1) {
						e1.printStackTrace();
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				fl.setAccessible(true);
				Object objValue = null;
				try {
					objValue = fl.get(t);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				setCellValue(objValue, row, cellNum);
				cellNum++;
			}
		}
		for (int i = 0; i < fields.size(); i++) {
			sheet.setColumnWidth(i, 20 * 256); 
		}
	}

	private static <E> void setCellValue(List<E> list, Row row) {
		int cellNum = 0;
		for (E e : list) {
			Cell cell = row.createCell(cellNum);
			cellNum++;
			if (e == null) {
				continue;
			}
			String value = e.toString();
			cell.setCellValue(value);// 设置单元格内容
			// cell.setCellStyle(cellStyle);//设置单元格样式
			// cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
			// cell.setCellComment(comment);//添加注释

		}
	}

	private static void setCellValue(Object obj, Row row, int cellNum) {
		Cell cell = row.createCell(cellNum);
		if (obj != null) {
			if ("java.util.Date".equals(obj.getClass().getName())) {
				Date d = (Date) obj;
				cell.setCellValue(DateFormatUtils.format(d,DateUtil.yyyy_MM_dd));
			} else {
				cell.setCellValue(obj.toString());// 设置单元格内容
			}
		} else {
			cell.setCellValue("");// 设置单元格内容
		}
	}

	public static String getFileName(String fileNameSource, HttpServletRequest request) {
		/**
		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
		 * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
		 * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
		 */
		String agent = request.getHeader("USER-AGENT");
		try {
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
				String newFileName = URLEncoder.encode(fileNameSource, "UTF-8");
				newFileName = StringUtils.replace(newFileName, "+", "%20");
				if (newFileName.length() > 150) {
					newFileName = new String(fileNameSource.getBytes("GB2312"), "ISO8859-1");
					newFileName = StringUtils.replace(newFileName, " ", "%20");
				}
				return newFileName;
			}
			if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
				return MimeUtility.encodeText(fileNameSource, "UTF-8", "B");
			}
			return fileNameSource;
		} catch (Exception ex) {
			return fileNameSource;
		}
	}
}
