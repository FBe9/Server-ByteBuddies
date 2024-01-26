package encrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Alex
 */
@Singleton
@Startup
public class SimetricaServer {

    // Directories
    private static Path workingDirectory;
    private static Path secureDir;

    // Encription salt
    private static byte[] salt = new byte[16];

    @PostConstruct
    public void onStartup() {
        try {
            workingDirectory = Files.createDirectories(Paths.get(System.getProperty("user.home") + "/ByteBuddies/security"));
            secureDir = Files.createDirectories(Paths.get(workingDirectory + "/7072697661746531"));
            if (!new File(System.getProperty("user.home") + "/ByteBuddies/security/7072697661746531/6B657931.dat").exists()) {

                // Random salt
                new Random().nextBytes(salt);
                byte[] array = new byte[16];
                new Random().nextBytes(array);
                String generatedEncryptClave = new String(array, Charset.forName("UTF-8"));
                // Encrypts credentials into a file
                encryptData(generatedEncryptClave, "bytebuddies.sup@gmail.com,tdbj eyaj hmtn fqcz");

                // SimetricaServer symmetric = new SimetricaServer();
                // byte[] foundKey = fileReader(secureDir + "\\6B657931.dat");
                // System.out.println("Cifrado! -> " + mensajeCifrado);
                // System.out.println("-----------");
                // System.out.println("Descifrado! -> " + symmetric.unencryptData(foundKey, fileContent));
                // System.out.println("-----------");
            }
        } catch (IOException ex) {
            Logger.getLogger(SimetricaServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String sendEmail(String emailReceiver) {
        String generatedUserClave;
        try {
            SimetricaServer symmetric = new SimetricaServer();

            byte[] foundKey = fileReader(secureDir + "\\6B657931.dat");
            byte[] fileContent = fileReader(workingDirectory.toString() + "\\pr1v8k3y.dat");

            String credentialContent = symmetric.unencryptData(foundKey, fileContent);
            String[] email = credentialContent.split(",");

            final String GMAIL_HOST = "smtp.gmail.com";
            final String TLS_PORT = "587";

            final String SENDER_USERNAME = email[0];
            final String SENDER_PASSWORD = email[1];

            // protocol properties
            Properties props = System.getProperties();
            props.setProperty("mail.smtps.host", GMAIL_HOST);
            props.setProperty("mail.smtp.port", TLS_PORT);
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtps.auth", "true");

            // close connection upon quit being sent
            props.put("mail.smtps.quitwait", "false");

            Session session = Session.getInstance(props, null);

            // create the message
            final MimeMessage msg = new MimeMessage(session);

            // Generate new user password
            int leftLimit = 48;
            int rightLimit = 122;
            int targetStringLength = 10;
            Random random = new Random();

            generatedUserClave = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            // set recipients and content
            msg.setFrom(new InternetAddress(SENDER_USERNAME));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailReceiver, false));
            msg.setSubject("ByteBuddies Password Reset Request");
            msg.setText("We have received a password reset request for this email: " + emailReceiver
                    + "\nThis is your new temporal password: " + generatedUserClave, "utf-8", "html");
            msg.setSentDate(new Date());

            // send the mail
            Transport transport = session.getTransport("smtps");
            // send the mail
            transport.connect(GMAIL_HOST, SENDER_USERNAME, SENDER_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return generatedUserClave;
    }

    public String encryptData(String clave, String mensaje) {
        String ret = null;
        KeySpec derivedKey = null;
        SecretKeyFactory secretKeyFactory = null;
        try {

            derivedKey = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128);

            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

            byte[] derivedKeyPBK = secretKeyFactory.generateSecret(derivedKey).getEncoded();

            SecretKey derivedKeyPBK_AES = new SecretKeySpec(derivedKeyPBK, 0, derivedKeyPBK.length, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, derivedKeyPBK_AES);
            byte[] encodedMessage = cipher.doFinal(mensaje.getBytes());
            byte[] iv = cipher.getIV();

            byte[] combined = concatArrays(iv, encodedMessage);

            fileWriter(workingDirectory.toString() + "\\pr1v8k3y.dat", combined);
            fileWriter(secureDir + "\\6B657931.dat", derivedKeyPBK);

            ret = new String(encodedMessage);

        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {

        }
        return ret;
    }

    private String unencryptData(byte[] clave, byte[] content) {
        String ret = null;
        try {
            SecretKey privateKey = new SecretKeySpec(clave, 0, clave.length, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(content, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            byte[] decodedMessage = cipher.doFinal(Arrays.copyOfRange(content, 16, content.length));
            ret = new String(decodedMessage);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {

        }
        return ret;
    }

    private byte[] concatArrays(byte[] array1, byte[] array2) {
        byte[] ret = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, ret, 0, array1.length);
        System.arraycopy(array2, 0, ret, array1.length, array2.length);
        return ret;
    }

    private void fileWriter(String path, byte[] text) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}