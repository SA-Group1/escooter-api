package com.escooter.api.controller;

import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.dao.DuplicateKeyException;

import com.escooter.api.dto.UserDTO;
import com.escooter.api.model.User;
import com.escooter.api.dto.CreditCardDTO;
import com.escooter.api.model.CreditCard;
import com.escooter.api.dto.BindCreditCardDTO;
import com.escooter.api.dto.MemberCardDTO;
import com.escooter.api.model.MemberCard;
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
	 * Binding credit card data to user account and returns a result message.
	 * 
	 * @param bindCreditCardDTO User and credit card data include cvv.
	 * @return A ResponseEntity with http status and message.
	 */
	@PostMapping("/bindCreditCard")
	public ResponseEntity<String> test(@RequestBody BindCreditCardDTO bindCreditCardDTO) throws Exception {

		CreditCardDTO creditCardDTO = bindCreditCardDTO.getCreditCardDTO();
		CreditCard creditCard = new CreditCard(creditCardDTO.getCardNumber(), creditCardDTO.getExpirationDate(),creditCardDTO.getCardHolderName());
		UserDTO userDTO = bindCreditCardDTO.getUserDTO();
		User user = new User(userDTO.getAccount());

		// create return message.
		JSONObject message = new JSONObject();
		message.put("status", false);

		// bind credit card to user.
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

		// return the result.
		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}

	/**
	 * Adding member card data to database and returns a successful message.
	 * 
	 * @param memberCardDTO Member card data.
	 * @return A ResponseEntity with http status and message.
	 */
	@PostMapping("/bindMemberCard")
	public ResponseEntity<String> bindMemberCard(@RequestBody MemberCardDTO memberCardDTO) {

		// call service to add member card.
		MemberCard memberCard = new MemberCard(memberCardDTO.getCardNumber(),memberCardDTO.getExpirationDate());
		paymentService.bindMemberCard(memberCard);

		// create return message.
		JSONObject message = new JSONObject();
		try {
			message.put("status", true);
			message.put("message", "Adding member card success");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}
}