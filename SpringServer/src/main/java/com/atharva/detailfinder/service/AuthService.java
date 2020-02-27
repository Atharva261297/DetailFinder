package com.atharva.detailfinder.service;

import com.atharva.detailfinder.dao.DataDao;
import com.atharva.detailfinder.model.Positions;
import com.atharva.detailfinder.model.data.User;
import com.atharva.detailfinder.util.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthService {

    @Value("#{${admin-roles}}")
    private List<Positions> adminRoles;

    @Autowired
    private DataDao dataDao;

    public int verifyUser(final String userId, final String pass) {
        final byte[] hash = dataDao.getUser(userId).getHash();
        if (HashUtils.verifyHash(hash, pass)) {
            return 200;
        } else {
            return 401;
        }
    }

    public int verifyUserWithAdminRole(final String userId, final String pass) {
        final User user = dataDao.getUser(userId);
        final byte[] hash = user.getHash();
        if (HashUtils.verifyHash(hash, pass)) {
            if (adminRoles.contains(user.getPos())) {
                return 200;
            } else {
                return 402;
            }
        } else {
            return 401;
        }
    }

}
