@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {}

class UrgentEmail implements Notifier, Urgent {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

public class NotificationDemo {
    public static void main(String[] args) {
        Notifier email = message ->
                System.out.println("Email: " + message);

        Notifier sms = message ->
                System.out.println("SMS: " + message);

        Notifier[] senders = {email, sms};
        String message = "Exam tomorrow";

        for (Notifier sender : senders) {
            sender.send(message);
        }

        Notifier urgentEmail = new UrgentEmail();

        if (urgentEmail instanceof Urgent) {
            urgentEmail.send(message);
            urgentEmail.send(message);
        }
    }
} 
