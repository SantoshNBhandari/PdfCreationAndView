/**
 * 
 */
/**
 * @author Win10
 *
 */
package com.san.pdf.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import com.san.pdf.dto.PdfDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "pdfcompleteddetails")
public class PdfEntity {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pdfId;
	
	
	@Column
	private String header;
	
	@Column
	private String type;
	
	@Column
	private byte[] EncodedContent;
	
	@Column
	private String Content;
	
	
	public PdfEntity(PdfDto pdf) {
		this.header=pdf.getHeader();
		this.type=pdf.getType();
		this.Content=pdf.getContent();
	}
	
	
	
}