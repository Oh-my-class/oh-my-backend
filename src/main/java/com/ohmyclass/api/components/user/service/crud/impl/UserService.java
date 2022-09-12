package com.ohmyclass.api.components.user.service.crud.impl;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.components.user.entity.User;
import com.ohmyclass.api.components.user.repository.IUserRepository;
import com.ohmyclass.api.components.user.service.crud.IUserService;
import com.ohmyclass.api.components.user.service.mapper.AUserMapper;
import com.ohmyclass.api.util.communication.CreateResponseService;
import com.ohmyclass.api.util.communication.Response;
import com.ohmyclass.util.validate.Validate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Component
@AllArgsConstructor
public class UserService implements IUserService {

	private final IUserRepository userRepo;
	private final AUserMapper userMapper;

	@Override
	public Response<UserOutDTO> login(UserInDTO userIn) {

		System.out.println("login reached");

		Validate.notNull(userIn);

		Predicate<Optional<User>> loginIsValid = Optional::isPresent;

		Optional<User> potentialUser = userRepo.findUserByEmailAndPassword(userIn.getEmail(), userIn.getPassword());

		return getAndValidate(potentialUser, loginIsValid);
	}

	@Override
	public Response<UserOutDTO> register(UserInDTO userIn) {

		Validate.notNull(userIn);


		Optional<User> potentialUser = userRepo.findUserByUsernameOrEmail(userIn.getUsername(), userIn.getEmail());

		if (potentialUser.isPresent())
			CreateResponseService.newError(new Response<UserOutDTO>(), "User already exists");

		User user = new User();
		user.create(userIn.getUsername(), userIn.getEmail(), userIn.getPassword());
		userRepo.save(user);

		Predicate<Optional<User>> userSavedCorrectly = Optional::isPresent;

		return getAndValidate(userRepo.findUserByEmailAndPassword(user.getEmail(), user.getPassword()), userSavedCorrectly);
	}

	@Override
	public Response<UserOutDTO> getUser(UserInDTO userIn) {

		Validate.notNull(userIn);

		Predicate<Optional<User>> userIsPresent = Optional::isPresent;

		Optional<User> potentialUser = userRepo.findUserByUsernameOrEmail(userIn.getUsername(), userIn.getEmail());

		return getAndValidate(potentialUser, userIsPresent);
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
				.findUserByEmailAndPassword(userIn.getEmail(), userIn.getPassword());

		return getAndValidate(potentiallyUpdatedUserFromDatabase, userUpdatedCorrectly);
	}

	@Override
	public Response<Boolean> delete(UserInDTO userIn) {

		Validate.notNull(userIn);

		userRepo.delete(userMapper.inDTOToEntity(userIn));

		Response<Boolean> response = new Response<>();
		response.setT(true);

		if (userRepo.findUserByUsernameOrEmail(userIn.getUsername(), userIn.getEmail()).isPresent()) {
			response.setT(false);
			CreateResponseService.newError(response, "User not deleted successfully");
		}

		return response;
	}

	@Override
	public Response<UserOutDTO> passwordForgotten(UserInDTO user) {

		//TODO ?? Just annotate with @Deprecated ?
		return null;
	}

	private void updateUserDetails(UserInDTO userIn) {
		Optional<User> potentialUser = userRepo.findUserByEmailAndPassword(userIn.getEmail(), userIn.getPassword());

		if (potentialUser.isPresent()) {
			User updatedUser = potentialUser.get();
			updatedUser.setEmail(userIn.getEmail());
			updatedUser.setPassword(userIn.getPassword());

			userRepo.save(updatedUser);
		}
	}

	private Response<UserOutDTO> getAndValidate(Optional<User> potentialUser, Predicate<Optional<User>> predicate) {

		Response<UserOutDTO> response = new Response<>();

		if (predicate.test(potentialUser))
			response.setT(userMapper.entityToOutDTO(potentialUser.get()));
		else
			CreateResponseService.newError(response, "User doesn't exist");

		return response;
	}
}
