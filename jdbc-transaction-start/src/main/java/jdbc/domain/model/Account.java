//: jdbc.domain.model.Account.java


package jdbc.domain.model;


import lombok.NonNull;


public record Account(long id, String name, long amount) {

    public static Account of(
            final int id, @NonNull final String name, final long amount) {

        return new Account(id, name, amount);
    }

}///:~