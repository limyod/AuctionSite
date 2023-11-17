package org.yl.auction.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yl.auction.repository.UserRepo;
import org.yl.auction.Exception.UserExistException;
import org.yl.auction.entity.User;
import org.yl.auction.model.UserDTO;
import org.yl.auction.security.UserPrincipal;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder){
        this.encoder = encoder;
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findUserByEmailAddress(email);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Not found!");
        }

        return new UserPrincipal(optionalUser.get());
    }

    @Override
    public void saveUser(UserDTO userDto) throws UserExistException {
        if(userRepo.findUserByEmailAddress(userDto.getEmailAddress()).isPresent())
        {
            throw new UserExistException("User exist");
        }else {

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            User user = modelMapper.map(userDto, User.class);
            user.setPassword(encoder.encode(userDto.getPassword()));

            userRepo.save(user);
        }
    }

        @Override
    public User findUserByEmailAddress(String email) throws UserExistException {
        Optional<User> optionalUser = userRepo.findUserByEmailAddress(email);
        if(optionalUser.isPresent()) {
            return userRepo.findUserByEmailAddress(email).get();
        }
        throw new UserExistException("User does not exist");
    }



}
