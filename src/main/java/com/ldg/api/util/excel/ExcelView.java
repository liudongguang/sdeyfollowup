package com.ldg.api.util.excel;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdey.api.vo.tongji.DisplayTJINFO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;



public class ExcelView extends AbstractXlsxView {
	private String fileName;

	public ExcelView(String fileName) {
		this.fileName = fileName;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("999999999999999999999999999999");
		List<DisplayTJINFO> list = (List<DisplayTJINFO>) model.get("data");
		Map<String, String> columns = (Map<String, String>) model.get("columns");
		ExcelUtils.createExcelWorkBook(workbook, list, columns);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String encodeText = ExcelUtils.getFileName(fileName, request);
		response.setHeader("Content-disposition", "attachment;filename=\"" + encodeText+"\"");
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
}
