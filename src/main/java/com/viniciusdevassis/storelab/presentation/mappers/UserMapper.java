package com.viniciusdevassis.storelab.presentation.mappers;

import com.viniciusdevassis.storelab.domain.entities.User;
import com.viniciusdevassis.storelab.presentation.dtos.RegisterDTO;
import com.viniciusdevassis.storelab.presentation.dtos.ResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    ResponseDTO userToResponseDTO(User user);
}
