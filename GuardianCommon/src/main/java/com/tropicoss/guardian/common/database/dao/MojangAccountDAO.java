package com.tropicoss.guardian.common.database.dao;

import com.tropicoss.guardian.common.database.model.MojangAccount;

import java.util.List;

public interface MojangAccountDAO {
    void addMojangAccount(MojangAccount mojangAccount);
    MojangAccount getMojangAccountById(int mojangAccountId);
    List<MojangAccount> getAllMojangAccounts();
    void updateMojangAccount(MojangAccount mojangAccount);
    void deleteMojangAccount(int mojangAccountId);
}
