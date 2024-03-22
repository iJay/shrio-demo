package com.example.shirojsp01.service.impl;

import com.example.shirojsp01.mapper.PermsMapper;
import com.example.shirojsp01.model.Perms;
import com.example.shirojsp01.service.PermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermsServiceImpl implements PermsService {

    @Autowired
    private PermsMapper permsMapper;

    @Override
    public List<Perms> findPermsByRoleIds(List<String> roleIds) {
        return permsMapper.findPermsByRoleIds(roleIds);
    }
}
