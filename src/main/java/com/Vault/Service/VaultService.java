package com.Vault.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Custom.Validator;
import com.Vault.Dao.VaultDao;
import com.Vault.Dto.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Vault.Dto.Vault_cl;

@Service
public class VaultService {

    @Autowired
    VaultDao vaultDao;

    //	save Vault
    public ResponseStructure<Vault_cl> saveVault(Vault_cl vault_cl) {
        ResponseStructure<Vault_cl> responseStructure = new ResponseStructure<Vault_cl>();
        Vault_cl vault_cl1 = vaultDao.saveVault(vault_cl);
        if (vault_cl1 != null) {
            responseStructure.setData(vault_cl1);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Vault saved successfully");
        } else {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Vault has failed to save");
        }
        return responseStructure;
    }

    public ResponseStructure<List<Vault_cl>> saveAllVault(List<Map<String, String>> vaults) {
            ResponseStructure<List<Vault_cl>> responseStructure = new ResponseStructure<>();
            List<Vault_cl> savedVaults = new ArrayList<>();
            List<String> errorMessages = new ArrayList<>();
            int count = 0;

            for (Map<String, String> vaultData : vaults) {
                // Validate the vault data against the expected structure
                String key = vaultData.get("key");
                String value = vaultData.get("value");

                // Create a Vault_cl object
                Vault_cl vault = new Vault_cl();
                vault.setKey(key);
                vault.setValue(value);

                // Validate the Vault_cl object
                String validationResult = Validator.isValidVaultList(vaults);
                if (validationResult == null) {
                    // Object is valid, save it
                    Vault_cl savedVault = vaultDao.saveVault(vault);
                    if (savedVault != null) {
                        savedVaults.add(savedVault);
                    }
                } else {
                    errorMessages.add(validationResult);
                    count++;
                }
            }

            // Handle case where some vaults were invalid
            if (count > 0) {
                responseStructure.setData(List.of());
                responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
                responseStructure.setMessage("Validation errors: " + errorMessages);
            } else if (!savedVaults.isEmpty()) {
                // Handle success case
                responseStructure.setData(savedVaults);
                responseStructure.setStatusCode(HttpStatus.CREATED.value());
                responseStructure.setMessage("Vaults saved successfully");
            } else {
                // Handle case where no vaults were saved
                responseStructure.setData(List.of());
                responseStructure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                responseStructure.setMessage("No vaults saved or some vaults failed to save");
            }

            return responseStructure;
        }

    //	get Vault by id
    public ResponseStructure<Vault_cl> getVaultById(Integer id) {
        ResponseStructure<Vault_cl> responseStructure = new ResponseStructure<Vault_cl>();
        Vault_cl vault_cl1 = vaultDao.getVaultById(id);
        if (vault_cl1 != null) {
            responseStructure.setData(vault_cl1);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Vault got by id");
        } else {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Vault don't exists");
        }
        return responseStructure;
    }

    //	get all Vaults
    public ResponseStructure<List<Vault_cl>> getAllVault() {
        ResponseStructure<List<Vault_cl>> responseStructure = new ResponseStructure<List<Vault_cl>>();
        List<Vault_cl> vault_cl1 = vaultDao.getAllVault();
        if (vault_cl1.size() > 0) {
            responseStructure.setData(vault_cl1);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Here are the list of all Vaults");
        } else {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("No Vault record exists in database");
        }
        return responseStructure;
    }


    //	update Vault
    public ResponseStructure<Vault_cl> updateVault(Vault_cl vault_cl, Integer id) {
        ResponseStructure<Vault_cl> responseStructure = new ResponseStructure<Vault_cl>();
        Vault_cl vault_cl1 = vaultDao.updateVault(vault_cl, id);
        if (vault_cl1 != null) {
            responseStructure.setData(vault_cl1);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Vault updated successfully");
        } else {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Vault don't exists");
        }
        return responseStructure;
    }

    //	delete Vault
    public ResponseStructure<String> deleteVault(Integer id) {
        ResponseStructure<String> responseStructure = new ResponseStructure<String>();
        boolean isTrue = vaultDao.deleteVault(id);
        if (isTrue) {
            responseStructure.setData("Vault selected");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Vault deleted successfully");
        } else {
            responseStructure.setData("Vault not selected");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Vault has failed to get delete");
        }
        return responseStructure;
    }
}