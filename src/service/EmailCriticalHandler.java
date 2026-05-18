package service;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Level;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class EmailCriticalHandler extends Handler {

    private final String fromEmail = "vash_email@gmail.com";
    private final String password = "vash_app_password_abcd";
    private final String toEmail = "admin_email@gmail.com";

    @Override
    public void publish(LogRecord record) {
        if (record.getLevel() != Level.SEVERE) {
            return;
        }

        String messageBody = record.getMessage();
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            record.getThrown().printStackTrace(pw);
            messageBody += "\n===== Stack Trace =====\n" + sw.toString();
        }

        final String finalMessage = messageBody;

        System.err.println("\n!!! КРИТИЧНА ПОМИЛКА !!!");
        System.err.println(finalMessage);

        new Thread(() -> sendEmail("КРИТИЧНА ПОМИЛКА", finalMessage)).start();
    }

    private void sendEmail(String subject, String body) {
        if (fromEmail.contains("vash_email")) {
            System.err.println(">>> [EMAIL MOCK] Email не надіслано (немає налаштувань).");
            return;
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);
            System.out.println(">>> [EMAIL SENT] Звіт надіслано.");
        } catch (MessagingException e) {
            System.err.println("Помилка відправки email: " + e.getMessage());
        }
    }

    @Override public void flush() {}
    @Override public void close() throws SecurityException {}
}