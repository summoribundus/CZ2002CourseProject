/**
 * Notifier interface responsible for sending notification to students
 * @author Li Rui, Ye Ziyuan (names not listed in order)
 * @version 1.0
 * @since 2020-11-22
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
