package com.san.pdf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.san.pdf.entity.PdfEntity;

@Repository
public interface PdfDao extends JpaRepository<PdfEntity, Integer> {

}
