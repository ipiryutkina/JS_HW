package ru.hw.hw15.tsk2;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

class QueryExecutor {

    private final PreparedStatement ps;

    public QueryExecutor(Connection conn) throws SQLException {
        ps = conn.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
    }

    public ResultSet execute(String departmentId, LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        ps.setString(0, departmentId);
        ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
        ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));
        // execute query and get the results
        return ps.executeQuery();
    }
}

class HTMLReportConstructor {
    private Sender ntfr;

    public HTMLReportConstructor() {
        ntfr = new Sender();
    }

    public String execute(ResultSet results) throws SQLException {
        // create a StringBuilder holding a resulting html
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        double totals = 0;
        while (results.next()) {
            // process each row of query results
            resultingHtml.append("<tr>"); // add row start tag
            resultingHtml.append("<td>").append(results.getString("emp_name")).append("</td>"); // appending employee name
            resultingHtml.append("<td>").append(results.getDouble("salary")).append("</td>"); // appending employee salary for period
            resultingHtml.append("</tr>"); // add row end tag
            totals += results.getDouble("salary"); // add salary to totals
        }
        resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
        resultingHtml.append("</table></body></html>");
        return resultingHtml.toString();
    }
}

class Sender {

    private JavaMailSenderImpl mailSender;

    public Sender() {
        // now when the report is built we need to send it to the recipients list
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // we're going to use google mail to send this message
        mailSender.setHost("mail.google.com");
    }

    void execute(String resultingHtml, String recipients) throws MessagingException {
        // construct the message
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipients);
        // setting message text, last parameter 'true' says that it is HTML format
        helper.setText(resultingHtml, true);
        helper.setSubject("Monthly department salary report");
        // send the message
        mailSender.send(message);
    }
}

public class SalaryHtmlReportNotifier {

    private QueryExecutor qe;
    private HTMLReportConstructor hrc;
    private Sender sender;

    public SalaryHtmlReportNotifier(Connection databaseConnection) throws SQLException {
        this.qe = new QueryExecutor(databaseConnection);
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        try {
            sender.execute(hrc.execute(qe.execute(departmentId, dateFrom, dateTo)), recipients);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

