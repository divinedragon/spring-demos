package com.divinedragon.authserver.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divinedragon.authserver.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Account findOneByEmail(String username);

}
