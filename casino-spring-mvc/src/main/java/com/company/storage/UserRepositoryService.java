package com.company.storage;

import com.company.models.view.LoginViewModel;
import com.company.storage.jpa.IRoleRepository;
import com.company.storage.jpa.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.storage.models.StorageUser;

@Service
public class UserRepositoryService {

    @Autowired
    public IUserRepository userRepository;

    @Autowired
    public IRoleRepository roleRepository;

    public StorageUser LoginUser(LoginViewModel viewModel)
    {
        var user = userRepository.findAll().stream().filter(x -> x.getLogin().equals(viewModel.getUsername())).findFirst().get();

        var roles = roleRepository.findAll();

        return user;
    }
}