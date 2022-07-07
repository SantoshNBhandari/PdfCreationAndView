package com.san.pdf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.san.pdf.dto.GetAllResponseDto;
import com.san.pdf.dto.PdfDto;
import com.san.pdf.entity.PdfEntity;
import com.san.pdf.exception.BadRequestParamaterException;
import com.san.pdf.service.PdfService;
import com.san.pdf.util.ValidateParams;

@RestController
@RequestMapping("/pdf")

public class PdfController {
	
	@Autowired
	PdfService pdfService;
  
	
	@GetMapping("/all")
	public GetAllResponseDto getAllPdf(
			@RequestParam(name="offset", defaultValue="0") final int offset,
			@RequestParam(name="limit", defaultValue="10") final int limit) {
		return pdfService.getAllPdf(offset,limit);		
	}
	
	@GetMapping("/get/{id}")
	public PdfDto getPdf(
			@PathVariable(name="id", required=true)final String id,
			@RequestParam(name="name", required=false)String name) {
		ValidateParams.validateParams(id,name);
		return pdfService.getPdf(id,name);
		
	}
	
	@PutMapping("/save")
	public PdfEntity addPdf(
			@RequestBody PdfDto pdf,
			@RequestParam(name="status", defaultValue="new") final String status	
			) {
		ValidateParams.validateBody(pdf);
		ValidateParams.validateParams(status);		
		return pdfService.savePdf(pdf,status);
		
	}

}
