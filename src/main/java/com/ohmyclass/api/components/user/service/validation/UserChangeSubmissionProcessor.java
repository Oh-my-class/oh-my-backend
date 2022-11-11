package com.ohmyclass.api.components.user.service.validation;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.entity.User;
import com.ohmyclass.api.components.user.repository.UserRepository;
import com.ohmyclass.api.exceptions.ApiException;
import com.ohmyclass.api.util.validation.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserChangeSubmissionProcessor extends SubmissionProcessor<UserChangeInDTO> {

	private final UserChangeSubmissionValidator userChangeSubmissionValidator;

	private final UserRepository userRepository;

	@Override
	protected ValidationResult validate(UserChangeInDTO userIn) {
		return userChangeSubmissionValidator.validate(userIn);
	}

	@Override
	protected void persist(UserChangeInDTO userIn) {

		User user = userRepository.findByUsername(userIn.resolveHeaderById("username"))
				.orElseThrow(() -> new ApiException("User to edit not found"));

		user.setEmail(userIn.getNewEmail());
		user.setPassword(userIn.getNewPassword());

		userRepository.save(user);
	}

	@Override
	protected void prePersistOperations(UserChangeInDTO userIn) {
		if (userRepository.findByEmail(userIn.getNewEmail()).isPresent()) {
			throw new ApiException("Email already exists");
		}
	}

	@Override
	protected void postPersistOperations(UserChangeInDTO userIn) {
		if (userRepository.findByEmailAndPassword(userIn.getNewEmail(), userIn.getNewPassword()).isEmpty()) {
			throw new ApiException("User not updated correctly");
		}
	}
}
