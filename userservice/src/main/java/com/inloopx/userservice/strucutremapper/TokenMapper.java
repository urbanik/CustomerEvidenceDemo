package com.inloopx.userservice.strucutremapper;


import com.inloopx.userservice.dto.TokenDto;
import com.inloopx.userservice.entity.Token;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class})
public interface TokenMapper {

    TokenMapper INSTANCE = Mappers.getMapper(TokenMapper.class);

    TokenDto tokenToTokenDto(Token token);

    Token tokenDtoToToken(TokenDto tokenDto);
}
