/**
Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
*/
public interface MailService {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";
    Sendable processMail(Sendable mail);
}