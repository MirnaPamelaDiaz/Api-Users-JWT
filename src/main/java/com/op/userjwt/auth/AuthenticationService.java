package com.op.userjwt.auth;

import com.op.userjwt.entity.Role;
import com.op.userjwt.entity.UserApp;
import com.op.userjwt.repository.UserAppRepository;
import com.op.userjwt.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserAppRepository REPOSITORY;
    private final PasswordEncoder PASS_ENCODER;
    private final JwtService JWT_SERVICE;
    private  final AuthenticationManager AUTH_MANAGER;
    public AuthenticationResponse register(RegisterRequest request){
        var userApp = UserApp.builder()
                .apellido(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .password(PASS_ENCODER.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        REPOSITORY.save(userApp);
        var jwtToken = JWT_SERVICE.generateToken(userApp);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        AUTH_MANAGER.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var userApp = REPOSITORY.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = JWT_SERVICE.generateToken(userApp);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
