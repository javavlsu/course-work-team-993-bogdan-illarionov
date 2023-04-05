package com.company.storage;

import com.company.viewModels.LoginViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.storage.models.User;

@Service
public class UserRepositoryService {

    @Autowired
    public IUserRepository lotRepository;

    public User LoginUser(LoginViewModel viewModel)
    {
        var user = lotRepository.findAll().stream().filter(x -> x.getLogin().equals(viewModel.getUsername())).findFirst().get();

        return user;
    }
}