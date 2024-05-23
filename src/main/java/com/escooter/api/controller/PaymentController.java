package com.escooter.api.controller;

import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.BindCreditCardDTO;
import com.escooter.api.dto.BindMemberCardDTO;
import com.escooter.api.dto.MemberCardDTO;
import com.escooter.api.dto.UserDTO;
import com.escooter.api.exceptions.CardExpiredException;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.CreditCard;
import com.escooter.api.model.MemberCard;
import com.escooter.api.model.User;
import com.escooter.api.model.UserCredentials;
import com.escooter.api.service.UserService;
import com.escooter.api.util.JsonResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controller for handling payment related requests.
 */
@RestController
@RequestMapping("/api")
public class PaymentController {
	@Autowired
	private UserService userService;

    /**
     * Binds credit card data to the user's account and returns a result message.
     * 
     * @param bindCreditCardDTO User and credit card data including CVV.
     * @return A ResponseEntity with HTTP status and message.
     */
	@PostMapping("bindCreditCard")
	public ResponseEntity<String> bindCreditCard(@RequestBody BindCreditCardDTO bindCreditCardDTO) {
		CreditCard creditCard = bindCreditCardDTO.getCreditCard();
		String cvv = bindCreditCardDTO.getCvv();
		UserCredentials userCredentials = bindCreditCardDTO.getUserCredentials();
		try {
			userService.bindCreditCard(userCredentials, creditCard , cvv);
			return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Bind credit card success."), HttpStatus.ACCEPTED);
		} catch (UserCredentialsException e){
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Wrong password."), HttpStatus.UNAUTHORIZED);
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("This credit card is already been used."), HttpStatus.CONFLICT);
		} catch (CardExpiredException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("This credit card has expired."), HttpStatus.BAD_REQUEST);
		} catch (DateTimeParseException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid expiration date."), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to bind credit card.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Unbinding credit card data to user's account and returns a result message.
	 * 
	 * @param userDTO The user's account data.
	 * @return A ResponseEntity with http status and message.
	 */
	@PostMapping("unbindCreditCard")
	public ResponseEntity<String> unbindCreditCard(@RequestBody UserDTO userDTO) {
		
		User user = new User(userDTO.getAccount());

		try {
			// create return message.
			JSONObject message = new JSONObject();
			message.put("status",false);
	
			if(user.verifyPassword(userDTO.getPassword())){
				// unbind credit card for the user.
				try {
					paymentService.unbindCreditCard(user);
					message.put("status", true);
					message.put("message","Unbind credit card success.");
				} catch (Exception e) {
					message.put("message","Failed to unbind credit card.");
				}
			} else {
				message.put("message", "Wrong password.");
			}
	
			// return the result message.
			return new ResponseEntity<>(message.toString(),HttpStatus.OK);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("unbind credit card failed.",HttpStatus.OK);
	}
	

	/**
	 * Adding member card data to database and returns a successful message.
	 * 
	 * @param memberCardDTO Member card data.
	 * @return A ResponseEntity with http status and message.
	 */
	@PostMapping("bindMemberCard")
	public ResponseEntity<String> bindMemberCard(@RequestBody BindMemberCardDTO bindMemberCardDTO) {

		MemberCardDTO memberCardDTO = bindMemberCardDTO.getMemberCardDTO();
		MemberCard memberCard = new MemberCard(memberCardDTO.getCardNumber(),memberCardDTO.getExpirationDate());
		UserDTO userDTO = bindMemberCardDTO.getUserDTO();
		User user = new User(userDTO.getAccount());

		try {
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
			} catch (CardExpiredException e) {
				message.put("message", "This member card has expirated.");
			} catch (DateTimeParseException e) {
				message.put("message", "Invaild expiration date.");
			}
	
			// return the result message.
			return new ResponseEntity<>(message.toString(), HttpStatus.OK);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Bind member card failed.",HttpStatus.OK);
	}

	/**
	 * Gets card information for the user.
	 * 
	 * @param userDTO User's account.
	 * @return A ResponseEntity with http status and result message.
	 */
	@PostMapping("getCards")
	public ResponseEntity<String> getCards(@RequestBody UserDTO userDTO) {

		User user = new User(userDTO.getAccount());

		try {
			// create return message.
			JSONObject message = new JSONObject();
			message.put("status", false);

			// gets card data from the payment service.
			CreditCard creditCard = paymentService.getCreditCard(user);
			MemberCard memberCard = paymentService.getMemberCard(user);
			
			// puts card data into the message.
			ObjectMapper objectMapper = new ObjectMapper();
			message.put("status", true);
			message.put("message","gets cards information success.");
			message.put("creditCard",new JSONObject(objectMapper.writeValueAsString(creditCard)));
			message.put("memberCard",new JSONObject(objectMapper.writeValueAsString(memberCard)));
			
			// return the result message.
			return new ResponseEntity<>(message.toString(),HttpStatus.OK);
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("get card information failed",HttpStatus.OK);
	}
}