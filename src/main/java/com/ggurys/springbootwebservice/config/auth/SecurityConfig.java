package com.ggurys.springbootwebservice.config.auth;

import com.ggurys.springbootwebservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security의 설정을 활성화시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable() //h2-console 화면을 사요하기 위해 해당 옵션을 disable
                .and()
                    .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점 이 옵션이 있어야 antMatchers 사용가능
                    // antMatchers란 권한 관리 대상을 지정하는 옵션, URL,HTTP 메소드별로 관리 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() // "/"등 지정된 URL은 permitAll()옵션을 통해 전체 열람 권한을 부야
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated() // 설정된 값들 이외 나머지 URL을 나타내고 authenticated() 경우 추가 할 시 나머지 URL들은 모두 인증된 사용자에게만 허용 (로그인 된 사용자만)
                .and()
                    .logout().logoutSuccessUrl("/") // 로그아웃 설공시 해당 URL로 이동
                .and()
                    .oauth2Login() // OAuth2Login에 대한 여러 설정 진입점
                        .userInfoEndpoint() // 로그인 성공 후 사용자 정보를 가져올 때의 설정 담당
                            .userService(customOAuth2UserService); // 소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체로 등록, 사용자 정보 가져온 후 추가로 진행하고자 하는 기능 명시 가능

    }
}