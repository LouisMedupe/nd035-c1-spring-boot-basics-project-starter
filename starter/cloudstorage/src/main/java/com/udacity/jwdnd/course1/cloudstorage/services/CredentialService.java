package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getAllCredentials(Integer userId) {
        return credentialMapper.findAllCredential(userId);
    }

    public void CreateOrUpdateCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encodedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);



        if (credential.getCredentialId() != null) {
            credentialMapper.updateCredential(credential);
        } else {
            credentialMapper.insertCredential(new Credential(null,credential.getUrl(), credential.getUsername(), encodedKey, encodedPassword, credential.getUserId()));

        }
    }

    public void deleteCredential(Credential credential) {
        credentialMapper.deleteCredential(credential);
    }

    public String decryptPassword(Credential credential) {
        return encryptionService.decryptValue(credential.getPassword(), credential.getKey());
    }
}
