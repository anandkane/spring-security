package com.ssai.handsonch11.authserver.user.service;

import com.ssai.handsonch11.authserver.user.dao.entity.OtpEntity;
import com.ssai.handsonch11.authserver.user.dao.entity.UserEntity;
import com.ssai.handsonch11.authserver.user.dao.repo.OtpRepository;
import com.ssai.handsonch11.authserver.user.dao.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OtpRepository otpRepository;

    public UserService() {
    }

    // Find out why this constructor injection is not working
//    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, OtpRepository otpRepository) {
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//        this.otpRepository = otpRepository;
//    }

    public void addUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(UserEntity authUser) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(authUser.getUsername());
        BadCredentialsException badCredentialsException = new BadCredentialsException("Bad user credentials");
        UserEntity user = optionalUser.orElseThrow(() -> badCredentialsException);
        if (!passwordEncoder.matches(authUser.getPassword(), user.getPassword())) throw badCredentialsException;

        Optional<OtpEntity> optionalOtp = otpRepository.findByUsername(user.getUsername());
        OtpEntity otp = optionalOtp.orElseGet(() -> new OtpEntity(user.getUsername(), ""));
        otp.setOtp(GenerateCodeUtil.getnerateCode());
        otpRepository.save(otp);
    }

    public boolean validate(OtpEntity authOtp) {
        Optional<OtpEntity> optionalOtp = otpRepository.findByUsername(authOtp.getUsername());
        final boolean[] matches = {false};
        optionalOtp.ifPresent(otp -> {
            if (otp.getOtp().equals(authOtp.getOtp())) matches[0] = true;
        });
        return matches[0];
    }
}
