package com.example.myrok.service;

import com.example.myrok.domain.Member;
import com.example.myrok.dto.jwt.JwtTokenDto;
import com.example.myrok.exception.CustomException;
import com.example.myrok.repository.MemberRepository;
import com.example.myrok.type.ELoginProvider;
import com.example.myrok.type.ErrorCode;
import com.example.myrok.util.OAuth2Util;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuth2Service {

    public final OAuth2Util oAuth2Util;
    public final MemberRepository memberRepository;

    public String getRedirectUrl(ELoginProvider provider) {
        if (provider == ELoginProvider.GOOGLE) {
            return oAuth2Util.getGoogleRedirectUrl();
        }
        return null;
    }

    public String getAccessToken(String authorizationCode, ELoginProvider provider) {
        String accessToken = null;
        if (provider == ELoginProvider.GOOGLE) {
            accessToken = oAuth2Util.getGoogleAccessToken(authorizationCode);
        }
        return accessToken;
    }

    public void login(HttpServletResponse response, String accessToken, ELoginProvider provider) throws IOException {
        String tempId = null;
        if (provider == ELoginProvider.GOOGLE) {
            tempId = oAuth2Util.getGoogleUserInfo(accessToken);
        }

        if (tempId == null) {
            throw new CustomException(ErrorCode.RESOURCE_NOT_FOUND);
        }

        final String socialId = tempId;
        final String password = UUID.randomUUID().toString();

        //category id 없이 save 안됨 -> null값 허용 or .category_id(0)으로 초기에 통일,,
        Member member = memberRepository.findBySocialIdAndProvider(socialId, provider)
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .socialId(socialId)
                                .password(password)
                                .provider(provider)
                                .build()));
    }
}
