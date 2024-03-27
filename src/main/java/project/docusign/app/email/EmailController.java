package project.docusign.app.email;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class EmailController {
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/email")
	public Email addEmail(@RequestBody Email email) throws UnsupportedEncodingException, MessagingException {
		return emailService.addEmail(email);
	}
	
	
	@RequestMapping("/email")
	public String getEmailLast() {
		return emailService.getEmailLast();
	}
}
