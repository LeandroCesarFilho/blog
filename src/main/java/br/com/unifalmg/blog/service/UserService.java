package br.com.unifalmg.blog.service;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.exception.InvalidUserException;
import br.com.unifalmg.blog.exception.UserNotFoundException;
import br.com.unifalmg.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User findById(Integer id){
        if (Objects.isNull(id)){
            throw new IllegalArgumentException("Id null when fetching for an user.");
        }
        return repository.findById(id).orElseThrow(() ->
                    new UserNotFoundException(
                            String.format("No user found for id %d", id))
                );
            }

    public User add(User user) {
        if(Objects.isNull(user) || Objects.isNull(user.getName())
                || Objects.isNull(user.getUsername()) || Objects.isNull(user.getEmail()) || user.getName().isEmpty()
                || user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getWebsite().isEmpty()) {
            throw new InvalidUserException();
        }
        return repository.save(user);
    }

    public User edit(Integer id, User user) {
        if (Objects.isNull(id) || Objects.isNull(user) || Objects.isNull(user.getName())
                || Objects.isNull(user.getUsername()) || Objects.isNull(user.getEmail()) || Objects.isNull(user.getWebsite())
                || user.getName().isEmpty() || user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getWebsite().isEmpty()) {
            throw new IllegalArgumentException("Id or updated user is null.");
        }
        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isPresent()) {
            User editUser = optionalUser.get();
            editUser.setName(user.getName());
            editUser.setEmail(user.getEmail());
            editUser.setUsername(user.getUsername());
            editUser.setWebsite(user.getWebsite());

        }
        return repository.save(editUser);
    }


}


