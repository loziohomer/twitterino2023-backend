package dev.hlab.twitterino.bll.service;

import dev.hlab.twitterino.dal.dto.*;
import dev.hlab.twitterino.dal.exception.FieldNotValidException;
import dev.hlab.twitterino.dal.model.User;

public interface UserService {
    public void signin(UserSigninDto userSigninDto) throws FieldNotValidException;
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto);
    public GetUserResponseDto getByHandle(GetUserByHanldeDto getUserByHanldeDto);
    public void changePassword(EditPasswordDTO editPasswordDTO);
}
