//: jdbc.service.AccountNotFoundException.java


package jdbc.service;


public class AccountNotFoundException extends RuntimeException {

    private final int id;

    public AccountNotFoundException(int id) {
        super();
        this.id = id;
    }

    public int id() {
        return id;
    }

}///:~