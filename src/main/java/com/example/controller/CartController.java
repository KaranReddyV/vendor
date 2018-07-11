package com.example.controller;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Cart;
import com.example.entity.Moto;
import com.example.repo.CartRepo;
import com.example.repo.MotoRepo;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartRepo cartRepo;
	@Autowired
	MotoRepo motoRepo;
	Logger logger = LoggerFactory.getLogger(CartController.class);

	@PostMapping("save/{id}")
	public Cart saveCart(@RequestBody Cart cart, @PathVariable("id") Long id) {
		Optional<Moto> m = motoRepo.findById(id);
		this.logger.info("in cartController.saveCart()", id, cart);
		Moto moto = m.get();
		cart.setMoto(moto);
		return cartRepo.save(cart);
	}

	@GetMapping("{id}")
	public Optional<Cart> getCart(@PathVariable("id") Long id) {
		this.logger.info("in cartController getCart()" + id);
		return cartRepo.findById(id);

	}

}
