package com.viniciusdevassis.storelab.presentation.validators.validators;

import com.viniciusdevassis.storelab.infrastructure.repositories.UserRepository;
import com.viniciusdevassis.storelab.presentation.validators.annotations.EmailAvailable;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailAvailableValidator implements ConstraintValidator<EmailAvailable, String> {

    private final UserRepository repository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !repository.existsByEmail(email);
    }
}
