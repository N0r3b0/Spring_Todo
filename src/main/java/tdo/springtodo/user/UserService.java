package tdo.springtodo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public UserService() {
        super();
    }

    public List<UserModel> findAll() {
        return repository.findByOrderByIduDesc();
    }

    public <S extends UserModel> S save(S entity) {
        return repository.save(entity);
    }

    public Optional<UserModel> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    public <S extends UserModel> List<S> findAll(Example<S> example) {
        return repository.findAll(example);
    }
}
