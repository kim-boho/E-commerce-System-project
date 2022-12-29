package com.groupad.backend.bl;

import com.groupad.backend.exception.UserExceptions.login.IncorrectPasswordException;
import com.groupad.backend.exception.UserExceptions.login.LoginException;
import com.groupad.backend.exception.UserExceptions.login.UserNotFoundException;
import com.groupad.backend.exception.UserExceptions.signup.AccountCreationException;
import com.groupad.backend.exception.UserExceptions.signup.EmailAlreadyInUseException;
import com.groupad.backend.exception.UserExceptions.signup.InvalidEmailException;
import com.groupad.backend.exception.UserExceptions.signup.InvalidPasswordException;
import com.groupad.backend.exception.UserExceptions.signup.NoNameException;
import com.groupad.backend.model.User;
import com.groupad.backend.repository.UserRepository;

import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.validator.routines.EmailValidator;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public class UserBL {
    /*
     * This class is responsible for handling all the business logic related to
     * user validation
     * 
     * @Michelangelo-Granato
     */
    // Minimum eight characters, at least one uppercase letter,
    // one lowercase letter and one number
    private static final String STRONG_PASSORD_MESSAGE = "Password be between 8 and 20 characters long, contain at least one uppercase letter, one lowercase letter and one number.";
    private static final String STRONG_PASSORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private UserRepository repository;

    public UserBL(UserRepository repository) {
        this.repository = repository;
    }

    public Boolean login(User user) throws LoginException {
        Optional<User> userFindResult = repository.findByEmail(user.getEmail());
        if (!userFindResult.isPresent())
            throw new UserNotFoundException(user.getEmail());

        try {
            Boolean result = validatePassword(user.getPassword(), userFindResult.get().getPassword());
            if (!result)
                throw new IncorrectPasswordException("Incorrect Password.");

            return result;
        } catch (Exception e) {
            throw new LoginException(e);
        }
    }

    public User addNewUser(User newUser) throws AccountCreationException {
        validate(newUser);
        // if validation passes, hash password, then save to db
        try {
            newUser.setPassword(generateHashedPassword(newUser.getPassword()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AccountCreationException("Could not generate password hash");
        }
        return repository.save(newUser);
    }

    // #region validations
    private void validate(User newUser) throws AccountCreationException {
        validateName(newUser.getFName(), newUser.getLName());
        validateEmail(newUser.getEmail());
        validatePassword(newUser.getPassword());
    }

    private void validateName(String fName, String lName) throws NoNameException {
        String message = "";
        if (fName == null || fName.isEmpty())
            message += "First name required.\n";

        if (lName == null || lName.isEmpty())
            message += "Last name required.";
        if (!message.equals(""))
            throw new NoNameException(message);
    }

    private void validatePassword(String password) throws AccountCreationException {
        if (password == null || password.isEmpty()) {
            throw new InvalidPasswordException("Password cannot be empty.");
        }
        if (!Pattern.matches(STRONG_PASSORD_REGEX, password)) {
            throw new InvalidPasswordException(STRONG_PASSORD_MESSAGE);
        }
    }

    private void validateEmail(String email) throws AccountCreationException {
        // validate email is ok
        if (email == null || !EmailValidator.getInstance().isValid(email)) {
            throw new InvalidEmailException(email);
        }
        // validate email is not already in use
        if (repository.existsByEmail(email)) {
            throw new EmailAlreadyInUseException(email);
        }
    }

    // #endregion
    // #region Hash Gen

    private static String generateHashedPassword(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    // Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    // #endregion
    // #region login password validation
    private static boolean validatePassword(String originalPassword, String storedPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);

        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(),
                salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
    // #endregion
}
