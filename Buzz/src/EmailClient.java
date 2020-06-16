import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.*;
import java.io.*;

public class EmailClient
{
    public static void main(String[] args)
    {
        email();
    }

    private static void email()
    {
        String file_s = "";
        try
        {
            file_s = readFileAsString("src/message.txt");
        }
        catch (IOException io)
        {
            System.out.println(io);
        }

        Address[] addresses = new Address[3];
        try
        {
            addresses[0] = new InternetAddress("dvc5@uw.edu");
            addresses[1] = new InternetAddress("abyall19@uw.edu");
            addresses[2] = new InternetAddress("dilt@uw.edu");

        }
        catch (javax.mail.internet.AddressException addy)
        {
            System.out.println(addy);
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.cgrmtn-carmichaels.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.user","toddalt1@cgrmtn-carmichaels.com");
        Session session = Session.getInstance(props, null);

        try
        {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom("test.at.thebuzz@gmail.com");

            msg.setRecipients(Message.RecipientType.TO, addresses);

            msg.setSubject("The Buzz email services Test: Java");
            msg.setSentDate(new Date());
            msg.setText(file_s);

            Transport.send(msg, "toddalt1@cgrmtn-carmichaels.com", "");
        }
        catch (MessagingException mex)
        {
            System.out.println("send failed, exception: " + mex);
            mex.printStackTrace();
        }
    }

    private static String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
}
