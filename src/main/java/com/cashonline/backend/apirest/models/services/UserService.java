package com.cashonline.backend.apirest.models.services;

import com.cashonline.backend.apirest.models.dao.IUserDao;
import com.cashonline.backend.apirest.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>)userDao.findAll() ;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(int id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDao.deleteById(id);
    }

}
