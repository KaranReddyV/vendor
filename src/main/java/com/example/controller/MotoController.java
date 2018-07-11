package com.example.controller;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.MotoBean;
import com.example.entity.Moto;
import com.example.repo.MotoRepo;
import com.ojas.vendor.VendorResponse;

@RestController
@RequestMapping("motoe")
public class MotoController {
	private static final Logger logger = LogManager.getLogger(MotoController.class);

	@Autowired
	MotoRepo motoRepo;

	@PostMapping("/save")
	public Moto saveMoto(@RequestBody Moto moto) {

		Assert.notNull(moto, "should not be null");
		return motoRepo.save(moto);
	}

	@PutMapping("/update")
	public ResponseEntity<Moto> updateMoto(@RequestBody MotoBean motoBean) {
		Long id = motoBean.getId();
		try {
			Assert.notNull(id, "id should be given");
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Moto>(HttpStatus.NO_CONTENT);
		}
		Optional<Moto> motoOptional = motoRepo.findById(id);
		Moto moto = motoOptional.get();
		moto.setId(motoBean.getId());
		return new ResponseEntity<Moto>(motoRepo.save(moto), HttpStatus.OK);
	}

	@GetMapping("/getMoto/{id}")
	public ResponseEntity<Moto> getMoto(@PathParam("id") Long id) {
		try {
			Assert.notNull(id, "please give id");
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Moto>(HttpStatus.NO_CONTENT);
		}
		Optional<Moto> motoOPt = motoRepo.findById(id);
		return new ResponseEntity<Moto>(motoOPt.get(), HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public VendorResponse getAll(int pageNo,int pageSize,Pageable pagable) {
		
		Page<Moto> motoList = motoRepo.findAll(pagable);
		logger.info("in motoController.getAll()",motoList);
		return new VendorResponse(motoList, HttpStatus.OK);

	}
}
