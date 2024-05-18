package com.escooter.api.controller;

import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.dao.DuplicateKeyException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.escooter.api.dto.UserDTO;
import com.escooter.api.model.User;
import com.escooter.api.dto.CreditCardDTO;
import com.escooter.api.model.CreditCard;
import com.escooter.api.dto.BindCreditCardDTO;
import com.escooter.api.dto.MemberCardDTO;
import com.escooter.api.model.MemberCard;
import com.escooter.api.dto.BindMemberCardDTO;
import com.escooter.api.service.PaymentService;
import com.escooter.api.exceptions.CardExpirationException;

/**
 * Controller for handling payment related requests.
 */
@RestController
@RequestMapping("/api")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	/**
	 * Binding credit card data to user's account and returns a result message.
	 * 
	 * @param bindCreditCardDTO User and credit card data include cvv.
	 * @return A ResponseEntity with http status and message.
	 */
	@PostMapping("/bindCreditCard")
	public ResponseEntity<String> bindCreditCard(@RequestBody BindCreditCardDTO bindCreditCardDTO) throws Exception {

		CreditCardDTO creditCardDTO = bindCreditCardDTO.getCreditCardDTO();
		CreditCard creditCard = new CreditCard(creditCardDTO.getCardNumber(), creditCardDTO.getExpirationDate(),creditCardDTO.getCardHolderName());
		UserDTO userDTO = bindCreditCardDTO.getUserDTO();
		User user = new User(userDTO.getAccount());

		// create return message.
		JSONObject message = new JSONObject();
		message.put("status", false);

		if(user.verifyPassword(userDTO.getPassword())) {
			// bind credit card to the user.
			try {
				paymentService.addCreditCard(creditCard, creditCardDTO.getCvv());
				paymentService.bindCreditCard(user, creditCard);
				message.put("status", true);
				message.put("message", "Binding credit card success.");
			} catch (DuplicateKeyException e) {
				message.put("message", "This credit card is already been used.");
			} catch (CardExpirationException e) {
				message.put("message", "This credit card has expirated.");
			} catch (DateTimeParseException e) {
				message.put("message", "Invaild expiration date.");
			}
		} else {
			message.put("message", "Wrong password.");
		}

		// return the result message.
		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}

	/**
	 * Unbinding credit card data to user's account and returns a result message.
	 * 
	 * @param userDTO The user's account data.
	 * @return A ResponseEntity with http status and message.
	 */
	@PostMapping("unbindCreditCard")
	public ResponseEntity<String> unbindCreditCard(@RequestBody UserDTO userDTO) throws Exception {
		User user = new User(userDTO.getAccount());

		// create return message.
		JSONObject message = new JSONObject();
		message.put("status",false);

		// unbind credit card for the user.
		try {
			paymentService.unbindCreditCard(user);
			message.put("status", true);
			message.put("message","Unbind credit card success.");
		} catch (Exception e) {
			message.put("message","Failed to unbind credit card.");
		}

		// return the result message.
		return new ResponseEntity<>(message.toString(),HttpStatus.OK);
	}
	

	/**
	 * Adding member card data to database and returns a successful message.
	 * 
	 * @param memberCardDTO Member card data.
	 * @return A ResponseEntity with http status and message.
	 */
	@PostMapping("/bindMemberCard")
	public ResponseEntity<String> bindMemberCard(@RequestBody BindMemberCardDTO bindMemberCardDTO) throws Exception {

		MemberCardDTO memberCardDTO = bindMemberCardDTO.getMemberCardDTO();
		MemberCard memberCard = new MemberCard(memberCardDTO.getCardNumber(),memberCardDTO.getExpirationDate());
		UserDTO userDTO = bindMemberCardDTO.getUserDTO();
		User user = new User(userDTO.getAccount());

		// create return message.
		JSONObject message = new JSONObject();
		message.put("status", false);

		// bind member card to the user.
		try {
			paymentService.addMemberCard(memberCard);
			paymentService.bindMemberCard(user, memberCard);
			message.put("status", true);
			message.put("message", "Binding member card success.");
		} catch (DuplicateKeyException e) {
			message.put("message", "This member card is already been used.");
		} catch (CardExpirationException e) {
			message.put("message", "This member card has expirated.");
		} catch (DateTimeParseException e) {
			message.put("message", "Invaild expiration date.");
		}

		// return the result message.
		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}

	@PostMapping("getCards")
	public ResponseEntity<String> getCards(@RequestParam String account) throws Exception {

		// create return message.
		JSONObject message = new JSONObject();
		message.put("status", false);

		// gets card data from the payment service.
		CreditCard creditCard = paymentService.getCreditCard(account);
		MemberCard memberCard = paymentService.getMemberCard(account);

		// puts card data into the message.
		ObjectMapper objectMapper = new ObjectMapper();
		message.put("status", true);
		message.put("message","gets cards information success.");
		message.put("creditCard",new JSONObject(objectMapper.writeValueAsString(creditCard)));
		message.put("memberCard",new JSONObject(objectMapper.writeValueAsString(memberCard)));
		System.out.println(objectMapper.writeValueAsString(memberCard));
		return new ResponseEntity<>(message.toString(),HttpStatus.OK);
	}
}