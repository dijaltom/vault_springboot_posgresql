package com.Vault.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Vault.Dto.Vault_cl;

public interface VaultRepository extends JpaRepository<Vault_cl, Integer> {

}