public class Thief implements MailService{
    private static int StolenValue;
    private static int MinValue;

    public Thief(int m) {
        MinValue = m;
    }

    public int getStolenValue() {
        return StolenValue;
    }
    @Override
    public Sendable processMail(Sendable mail) {
        if(mail.getClass() == MailPackage.class) {

            Package pac = ((MailPackage)mail).getContent();

            if(pac.getPrice() >= MinValue){

                StolenValue += pac.getPrice();

                mail = new MailPackage(mail.getFrom(), mail.getTo(),new Package("stones instead of " + pac.getContent(), 0));

            }

        }

        return mail;
    }
}
