package com.zemarques96.workshopmongo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zemarques96.workshopmongo.dto.UserDTO;
import com.zemarques96.workshopmongo.entities.User;
import com.zemarques96.workshopmongo.repositories.UserRepository;
import com.zemarques96.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado"));
    }

    public User insert(User obj) {
        return repository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(String id, User obj) {
        try{
            Optional<User> newObj = repository.findById(obj.getId());
            updateData(newObj, obj);
            return repository.save(newObj.get());
        }
        catch(NoSuchElementException e){
            throw new ObjectNotFoundException("Id não encontrado");
        }
    }

    public void updateData(Optional<User> newObj, User obj) {
        newObj.ifPresent(x -> x.setName(obj.getName()));
        newObj.ifPresent(x -> x.setEmail(obj.getEmail()));
    }

    public User fromDTO(UserDTO objDTO) {
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }

}
