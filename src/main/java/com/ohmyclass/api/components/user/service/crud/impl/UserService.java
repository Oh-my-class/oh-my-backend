package com.ohmyclass.api.components.user.service.crud.impl;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.components.user.entity.User;
import com.ohmyclass.api.components.user.repository.IUserRepository;
import com.ohmyclass.api.components.user.service.crud.IUserService;
import com.ohmyclass.api.components.user.service.mapper.AUserMapper;
import com.ohmyclass.api.util.communication.Response;
import com.ohmyclass.api.util.validation.ValidationResult;
import com.ohmyclass.api.util.validation.http.ValidationStatus;
import com.ohmyclass.util.validate.Validate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Component
@AllArgsConstructor
public class UserService implements IUserService {

	private final IUserRepository userRepo;

	private final AUserMapper userMapper;

	private final PasswordEncoder passwordEncoder;


	public User saveUser(User user) {
		log.info("Saving user {}", user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}


	@Override
	public Response<String> register(UserInDTO userIn) {

		return null;
	}

	public User getUser(String username) {

		return userRepo.findByUsername(username);
	}


	@Override
	public Response<UserOutDTO> getUser(UserInDTO userIn) {

		Validate.notNull(userIn);

		Predicate<Optional<User>> userIsPresent = Optional::isPresent;

		Optional<User> potentialUser = userRepo.findByUsernameOrEmail(userIn.getUsername(), userIn.getEmail());

		return potentialUser.isPresent() ? new Response<UserOutDTO>(userMapper.entityToOutDTO(potentialUser.get())) : new Response<>(null, null);
	}

	@Override
	public Response<UserOutDTO> update(UserChangeInDTO userIn) {

		Validate.notNull(userIn);

		Predicate<Optional<User>> userUpdatedCorrectly = (updatedUser) -> {
			if (updatedUser.isEmpty())
				return false;

			return Objects.equals(updatedUser.get().getEmail(), userIn.getNewEmail())
					&& Objects.equals(updatedUser.get().getPassword(), userIn.getPassword());
		};

		updateUserDetails(userIn);

		Optional<User> potentiallyUpdatedUserFromDatabase = userRepo
				.findByEmailAndPassword(userIn.getEmail(), userIn.getPassword());

		return null; //validateUserExists(potentiallyUpdatedUserFromDatabase, userUpdatedCorrectly);
	}

	@Override
	public Response<Boolean> delete(UserInDTO userIn) {

		Validate.notNull(userIn);

		userRepo.delete(userMapper.inDTOToEntity(userIn));

		Response<Boolean> response = new Response<>();
		response.setData(true);

//		if (userRepo.findUserByUsernameOrEmail(userIn.getUsername(), userIn.getEmail()).isPresent()) {
//			response.setData(false);
//			CreateResponseService.newError(response, "User not deleted successfully");
//		}

		return response;
	}

	@Override
	public Response<UserOutDTO> passwordForgotten(UserInDTO user) {

		//TODO ?? Just annotate with @Deprecated ?
		return null;
	}

	private void updateUserDetails(UserInDTO userIn) {
		Optional<User> potentialUser = userRepo.findByEmailAndPassword(userIn.getEmail(), userIn.getPassword());

		if (potentialUser.isPresent()) {
			User updatedUser = potentialUser.get();
			updatedUser.setEmail(userIn.getEmail());
			updatedUser.setPassword(userIn.getPassword());

			userRepo.save(updatedUser);
		}
	}

	private void validateUserExists(Optional<User> potentialUser,
			Predicate<Optional<User>> predicate, ValidationResult validationResult) {

//		if (predicate.test(potentialUser))
//			validationResult.add(ValidationStatus.ERROR, "User doesn't exist");

		if (potentialUser.isEmpty())
			validationResult.add(ValidationStatus.ERROR, "User doesn't exist");
	}


}
