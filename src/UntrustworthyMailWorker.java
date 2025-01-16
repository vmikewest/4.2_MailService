public class UntrustworthyMailWorker implements MailService{
    private final RealMailService realMailService = new RealMailService();
    private MailService[] workers;
    public UntrustworthyMailWorker(MailService[] mailServices){
        workers = mailServices;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        Sendable processed = mail;
        for (int i = 0; i < workers.length; i++) {
            processed = workers[i].processMail(processed);
        }

        return realMailService.processMail(mail);
    }

    public MailService getRealMailService(){
        return realMailService;
    }
}
