package com.harish.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harish.model.UserInfo;
import com.harish.model.mutation.MessageRes;
import com.harish.repository.UserInfoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserInfo", description = "API for userinfo")
@RestController
public class UserInfoController {

	final private UserInfoRepository userInfoRepository;

//    private HashData hashData = new HashData();
	public UserInfoController(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}

	@Operation(summary = "Create a new user", description = "Create a new user with username , fullname and password", tags = {
			"userinfo" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserInfo.class))) })
	@PostMapping("/register")
	public MessageRes create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
		MessageRes res = new MessageRes();
		String username = body.get("username");
		if (userInfoRepository.existsByUsername(username)) {
			res.setMessage("Username already existed");
			return res;
		}
		String password = body.get("password");
		String encodedPassword = new BCryptPasswordEncoder().encode(password);
//        String hashedPassword = hashData.get_SHA_512_SecurePassword(password);
		String email = body.get("email");
		String address = body.get("address");
		userInfoRepository.save(new UserInfo(username, encodedPassword, email, address));
		res.setMessage("new user created");
		return res;
	}

}
