/**
 * Class responsible for sending email to students
 */

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


class Email implements Notifier {
	/**
	 * username: username of account
	 * password: password of account
	 */
	private final String username="mystarsoodpgroup3@gmail.com", password="cz2002oodp";//To be added

	/**
	 * Send email to students notify them of course registration results
	 * @param subject subject of message
	 * @param content content of message
	 * @param recepients recipient
	 */
	public void notify(String subject, String content, String recepients) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recepients)); // "to_username_a@gmail.com, to_username_b@yahoo.com"
			message.setSubject(subject);
			message.setText(content);

			Transport.send(message);

			System.out.println("Notification has been sent.");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}