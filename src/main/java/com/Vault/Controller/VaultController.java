package com.Vault.Controller;

import java.util.List;
import java.util.Map;

import com.Vault.Dto.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Vault.Dto.Vault_cl;
import com.Vault.Service.VaultService;

@RestController
public class VaultController {

    @Autowired
    VaultService vaultService;

    // save vault
    @PostMapping("/vault")
    public ResponseStructure<Vault_cl> saveVault(@RequestBody Vault_cl vault_cl) {
        return vaultService.saveVault(vault_cl);
    }

    @PostMapping("/vault/saveAll")
    public ResponseStructure<List<Vault_cl>> saveAllVault(@RequestBody List<Map<String, String>> vaults) {
        // Delegate to service method that accepts List<Map<String, String>>
        return vaultService.saveAllVault(vaults);
    }
    // get vault by id
    @GetMapping("/vault/{id}")
    public ResponseStructure<Vault_cl> getVaultById(@PathVariable int id) {
        return vaultService.getVaultById(id);
    }

    // get all vaults
    @GetMapping("/vault")
    public ResponseStructure<List<Vault_cl>> getAllVault() {
        return vaultService.getAllVault();
    }

    // update vault
    @PutMapping("/vault/{id}")
    public ResponseStructure<Vault_cl> updateVault(@RequestBody Vault_cl vault_cl, @PathVariable Integer id) {
        return vaultService.updateVault(vault_cl, id);
    }

    // delete vault
    @DeleteMapping("/vault/{id}")
    public ResponseStructure<String> deleteVault(@PathVariable int id) {
        return vaultService.deleteVault(id);
    }
}