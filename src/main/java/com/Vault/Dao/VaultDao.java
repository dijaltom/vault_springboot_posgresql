package com.Vault.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vault.Dto.Vault_cl;
import com.Vault.Repository.VaultRepository;

@Repository
public class VaultDao {

    @Autowired
    VaultRepository vaultRepository;

    // save vault
    public Vault_cl saveVault(Vault_cl vault) {
        return vaultRepository.save(vault);
    }

    // get vault by id
    public Vault_cl getVaultById(int id) {
        Optional<Vault_cl> optional = vaultRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        } else return optional.get();
    }

    // get all vaults
    public List<Vault_cl> getAllVault() {
        return vaultRepository.findAll();
    }

    // update vault
    public Vault_cl updateVault(Vault_cl vault, int id) {
        Vault_cl existingvault = getVaultById(id);
        if (existingvault != null) {
            existingvault.setKey(vault.getKey());
            existingvault.setValue(vault.getValue());
            return vaultRepository.save(existingvault);
        } else return null;
    }

    // delete vault
    public Boolean deleteVault(int id) {
        Optional<Vault_cl> optional = vaultRepository.findById(id);
        if (optional.isPresent()) {
            vaultRepository.delete(optional.get());
            return true;
        } else return false;
    }
}