package encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * The Asymmetric Server class for decrypting data and performing hash
 * operations.
 *
 * @author irati
 */
public class AsimetricaServer {

    private static Logger LOGGER = Logger.getLogger(AsimetricaServer.class.getName());


    /**
     * Decrypts encrypted data using EC private key.
     *
     * @param password The encrypted password to decrypt.
     * @return The decrypted password.
     */
    public static String decryptData(String password) {
        byte[] decryptedData = null;
        String passwordReceived = null;
        try {
          
           InputStream fis = AsimetricaServer.class.getResourceAsStream("privateKey.der");


            byte[] privateKeyBytes = new byte[fis.available()];
            fis.read(privateKeyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            // Convert hex string to bytes
            LOGGER.info("Received password: " + password);
            byte[] encryptedData = dehexadecimal(password);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decryptedData = cipher.doFinal(encryptedData);
            passwordReceived = new String(decryptedData);
            LOGGER.info("La password real: " + password);

        } catch (Exception e) {
            LOGGER.info("Error during decryptData" + e.getMessage());
        }
        return passwordReceived;
    }

    /**
     * Hashes the input text using the MD5 algorithm.
     *
     * @param text The text to be hashed.
     * @return The hexadecimal representation of the hashed text.
     */
    public static String hashText(String text) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md5.digest(text.getBytes());

            // Convert the byte array to a hexadecimal representation
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = String.format("%02X", b);
                hexStringBuilder.append(hex);
            }

            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts a hexadecimal string to a byte array.
     *
     * @param hexString The hexadecimal string to convert.
     * @return The byte array representing the hexadecimal string.
     */
    public static byte[] dehexadecimal(String hexString) {
        int len = hexString.length();
        byte[] result = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            result[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return result;
    }
}
