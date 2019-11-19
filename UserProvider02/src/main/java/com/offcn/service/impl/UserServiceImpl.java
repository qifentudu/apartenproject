package com.offcn.service.impl;

import com.offcn.dao.UserDao;
import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;
    @Override
    public void add(User user) {
        dao.save(user);
    }

    @Override
    public void update(User user) {
        dao.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
            dao.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User findOne(Long id) {
        return dao.findById(id).get();
    }
}
