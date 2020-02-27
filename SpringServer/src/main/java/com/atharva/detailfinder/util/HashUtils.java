package com.atharva.detailfinder.util;

import lombok.NonNull;
import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class HashUtils {

    private static final int SALT_LENGTH = 16;
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 128;
    private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String VERIFICATION_TEXT = "x**yqF6%QwUA&Z?=SzU4?SVCFsjC^%w3*c6KE$y+LYh!m7Kr=MHJMhkC79en&Sdq";

    private static byte[] generateSalt() {
        final SecureRandom random = new SecureRandom();
        final byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] generateHashUsingSalt(@NonNull final byte[] salt, @NonNull final String string)
            throws InvalidKeySpecException, NoSuchAlgorithmException {

        final KeySpec spec = new PBEKeySpec(string.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
        final SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALGORITHM);

        return factory.generateSecret(spec).getEncoded();
    }

    public static byte[] getHash(@NonNull final String encodedPass) {
        try {
            final byte[] salt = generateSalt();
            final byte[] hash = generateHashUsingSalt(salt, encodedPass);
            return ArrayUtils.addAll(salt, hash);
        } catch (final InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new HashException("Exception while hashing", e);
        }
    }

    public static boolean verifyHash(@NonNull final byte[] hashFromDb, @NonNull final String encodedPass) {
        try {
            final byte[] hash = generateHashUsingSalt(ArrayUtils.subarray(hashFromDb, 0, 16), encodedPass);
            return Arrays.equals(ArrayUtils.subarray(hashFromDb, 16, hashFromDb.length), hash);
        } catch (final InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new HashException("Exception while hashing", e);
        }
    }
}
