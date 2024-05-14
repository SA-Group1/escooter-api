package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.escooter.api.dto.CreditCardDTO;
import com.escooter.api.model.CreditCard;
import com.escooter.api.dto.MemberCardDTO;
import com.escooter.api.model.MemberCard;
import com.escooter.api.service.PaymentService;


/**
 * Controller for handling payment related requests.
 */
@RestController
@RequestMapping("/api")
public class PaymentController {
	@Autowired
    private PaymentService paymentService;

	/**
	 * Adding credit card data to database and returns a successful message
	 *
	 * @param creditCardDTO Credit card data include cvv
	 * @param cvv Credit card cvv number
	 * @return A ResponseEntity with http status and message
	 */
	 @PostMapping("/bindCreditCard")
	 public ResponseEntity<String> addCreditCard(@RequestBody CreditCardDTO creditCardDTO){
 
		 // call service to add credit card
		 CreditCard creditCard = new CreditCard(creditCardDTO.getCardNumber(), creditCardDTO.getExpirationDate(), creditCardDTO.getCardHolderName());
		 paymentService.bindCreditCard(creditCard,creditCardDTO.getCvv());
 
		 // create return message
		 JSONObject message = new JSONObject();
		 try {
			 message.put("status", true);
			 message.put("message", "Adding credit card success");
		 } catch (JSONException e) {
			 e.printStackTrace();
		 }
 
		 return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	 }

	/**
	 * Adding member card data to database and returns a successful message
	 *
	 * @param memberCardDTO Member card data
	 * @return A ResponseEntity with http status and message
	 */
	@PostMapping("/bindMemberCard")
	public ResponseEntity<String> bindMemberCard(@RequestBody MemberCardDTO memberCardDTO){

		// call service to add member card
		MemberCard memberCard = new MemberCard(memberCardDTO.getCardNumber(),memberCardDTO.getExpirationDate());
        paymentService.bindMemberCard(memberCard);

		// create return message
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