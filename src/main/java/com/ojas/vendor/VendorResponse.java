package com.ojas.vendor;

import org.springframework.http.HttpStatus;

public class VendorResponse {
	
	Object object;
	HttpStatus  status;
	
	public VendorResponse(Object object,HttpStatus  status) {
		this.object=object;
		this.status=status;
	}

}
