package com.san.pdf.service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.san.pdf.dao.PdfDao;
import com.san.pdf.dto.GetAllResponseDto;
import com.san.pdf.dto.PdfDto;
import com.san.pdf.entity.PdfEntity;
import com.san.pdf.exception.BadRequestParamaterException;

@Service
public class PdfService {

	@Autowired
	PdfDao pdfDao;

	public PdfEntity savePdf(PdfDto pdf, String status) {
		String content = pdf.getContent();
		byte[] encodedContent = createPdf(content, pdf.getHeader());
		PdfEntity pf = new PdfEntity(pdf);
		pf.setEncodedContent(encodedContent);
		return pdfDao.save(pf);
	}

	private static byte[] createPdf(String content, String header) {
		byte[] encoded = null;
		try {
			String dest = "C:/Users/Win10/Downloads/" + header + ".pdf";
			PdfWriter writer = new PdfWriter(dest);
			PdfDocument pdfDoc = new PdfDocument(writer);
			Document document = new Document(pdfDoc);
			document.add(new Paragraph(content));
			document.close();
			byte[] inFileBytes = Files.readAllBytes(Paths.get(dest));
			encoded = java.util.Base64.getEncoder().encode(inFileBytes);
		} catch (Exception e) {
			System.out.println("Exception");
		}
		return encoded;

	}

	public PdfDto getPdf(String id, String name) {
		PdfEntity pdf = pdfDao.findById(Integer.parseInt(id)).orElseThrow(()->new BadRequestParamaterException("Not there"));
		PdfDto pf = new PdfDto();
			String encodedContent = new String(java.util.Base64.getDecoder().decode(pdf.getContent()),
					StandardCharsets.UTF_8);
			pf.setContent(pdf.getContent());
			pf.setHeader(pdf.getHeader());
			pf.setType(pdf.getType());
		return pf;
	}

	public GetAllResponseDto getAllPdf(int offset, int limit) {
		GetAllResponseDto getAllResponse = new GetAllResponseDto();
		try {
			List<PdfEntity> pf = pdfDao.findAll().subList(offset, offset + limit);
			List<PdfDto> allResponse = new ArrayList<>();
			pf.stream().forEach(e -> {
				PdfDto dto = new PdfDto();
				dto.setPdfId(e.getPdfId());
				dto.setContent(e.getContent());
				dto.setHeader(e.getHeader());
				dto.setType(e.getType());
				allResponse.add(dto);
			});
			getAllResponse.setDtoList(allResponse);
		} catch (Exception e) {
			throw new BadRequestParamaterException("Not there");
		}
		return getAllResponse;
	}

}
