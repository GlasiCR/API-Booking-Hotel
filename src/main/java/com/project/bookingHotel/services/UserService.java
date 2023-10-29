package com.project.bookingHotel.services;

import com.project.bookingHotel.dtos.UserCreateDto;
import com.project.bookingHotel.model.Booking;
import com.project.bookingHotel.model.User;
import com.project.bookingHotel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity createUser(UserCreateDto user){
      if(userRepository.findByEmail(user.email()) != null){
            return ResponseEntity.badRequest().body("Já há usuário cadastrado com esse e-mail");
        }
        String passwordEncrypt = new BCryptPasswordEncoder().encode(user.password());
        User newUser = new User(user.name(), user.email(), passwordEncrypt, user.role());
        userRepository.save(newUser);

        return ResponseEntity.ok().body("Usuário cadastrado com sucesso");
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public ResponseEntity<User> findUserById(Long id){
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Booking>> findBookingByIdUser(Long id){
        Optional <User> userAlready = userRepository.findById(id);
        if(userAlready.isPresent()){
            User usersBooking = userAlready.get();
            return ResponseEntity.ok().body(usersBooking.getBookingsOfUser());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<User> updateUserById(Long id, User user){
        return userRepository.findById(id)
                .map(userUpdate -> {
                    userUpdate.setName((user.getName()));
                    userUpdate.setEmail(user.getEmail());
                    userUpdate.setPassword((userUpdate.getPassword()));
                    User updateUser = userRepository.save(user);
                    return ResponseEntity.ok().body(updateUser);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteUserById(Long id){
        return userRepository.findById(id)
                .map(userDelete -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
