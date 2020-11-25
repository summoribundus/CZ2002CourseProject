/**
 * Notifier interface responsible for sending notification to students
 */
public interface Notifier {
    /**
     * Notify students
     * @param subject subject of message
     * @param content content of message
     * @param recepients recipient
     */
    public void notify(String subject, String content, String recepients);
}
