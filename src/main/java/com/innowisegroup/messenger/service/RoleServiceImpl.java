package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.exception.NotFoundException;
import com.innowisegroup.messenger.model.Role;
import com.innowisegroup.messenger.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("The role with id " + id + " does not exists"));
    }
}
