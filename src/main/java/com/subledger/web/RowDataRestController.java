package com.subledger.web;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.subledger.dao.balanceSheet.BalanceSheetRepository;
import com.subledger.dao.balanceSheet.ColdataRepository;
import com.subledger.dao.balanceSheet.RowDataRepository;
import com.subledger.entities.balanceSheet.BalanceSheet;
import com.subledger.entities.balanceSheet.Coldata;
import com.subledger.entities.balanceSheet.Colstr;
import com.subledger.entities.balanceSheet.RowData;

@CrossOrigin(origins = "*")
@RestController
public class RowDataRestController {

	@Autowired
	private RowDataRepository rowDataRepository;
	
	@Autowired
	private BalanceSheetRepository bsRepository;
	
	@Transactional
	@DeleteMapping("/rowdatas/{id}")
	public Long deleteRowData(@PathVariable Long id) {
		return rowDataRepository.deleteById(id);
	}
		
	@Transactional
	@PutMapping("/rowdatas/{id}")
	public RowData updateRowData(@PathVariable Long id,@RequestBody String str) {
		RowData rdUpdate= rowDataRepository.findOne(id);
		rdUpdate.setName(str);
		return rowDataRepository.save(rdUpdate);
	}
	
	@Transactional
	@PostMapping("/rowdatas/{idBS}")
	public RowData addRowData(@PathVariable Long idBS,@RequestBody String name) {
		BalanceSheet bs = bsRepository.findOne(idBS);
		RowData rd = new RowData(null, name,null, bs);
		return rowDataRepository.save(rd);
	}		
}
