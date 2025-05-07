package com.example.server_354.Services;

import com.example.server_354.object.RequestedRoles;
import com.example.server_354.object.RoleRequest;
import com.example.server_354.object.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRequestRepository userRoleRequestRepository;

    public UserService(UserRepository userRepository, UserRoleRequestRepository userRoleRequestRepository) {
        this.userRepository = userRepository;
        this.userRoleRequestRepository = userRoleRequestRepository;
    }

    public void addUserRoleRequest(RoleRequest roleRequest){
        userRoleRequestRepository.save(roleRequest);
    }

    public void updateRequestedRoleStatus(String role,String email){
        System.out.println(role);
        userRoleRequestRepository.updateRequestedRoleStatus(role,email);
    }

    public List<RequestedRoles> getAllRequestedRoles(){
        return userRoleRequestRepository.getRequestedRolesWithUserNames();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public boolean getResponseByEmail(String email){
        return userRepository.getResponseByEmail(email) == 1;
    }

    public void createUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public boolean updateUser(String fullName, String email, String password, String phoneNumber, String role) {
        Optional<User> userOptional = userRepository.getUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if(!Objects.equals(fullName, "")){
                user.setFullName(fullName);
            }
            if(!Objects.equals(fullName, "")){
                user.setPassword(password);
            }
            if(!Objects.equals(fullName, "")){
                user.setPhoneNumber(phoneNumber);
            }
            if(!Objects.equals(fullName, "")){
                user.setRole(role);
            }
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
