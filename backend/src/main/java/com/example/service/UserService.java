package com.example.service;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.model.UserSubscription;
import com.example.repository.UserRepository;
import com.example.repository.UserSubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MESSAGE = "user with email %s not found";
    private final UserRepository userRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return  userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    public User getUserForConfirmation(String code){
        List<User> users = userRepository.findAll();
        for(User user : users){
            if(user.getVerificationCode().equals(code)) return user;
        }
        return null;
    }

    public Boolean subscribe(UserSubscription subscription){
        //System.out.println("-----------pozvan sam");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        List<UserSubscription> subscriptions = user.getSubscriptions();
        if(subscriptions == null) return false;
        for(UserSubscription s : subscriptions){
            if(subscription.getEntity().equals(s.getEntity()) && subscription.getIdOfEntity() == s.getIdOfEntity()) return false;
        }
        userSubscriptionRepository.deleteAll();
        subscriptions.add(subscription);
        userRepository.save(user);
        return true;
    }

    public List<UserSubscription> getAllSubscriptions(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        return user.getSubscriptions();
    }

    public Boolean deleteSubscription(Long id){
        userSubscriptionRepository.deleteById(id);
        return true;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
