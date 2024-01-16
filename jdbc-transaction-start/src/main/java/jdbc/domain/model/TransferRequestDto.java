//: jdbc.domain.model.TransferRequestDto.java


package jdbc.domain.model;


public record TransferRequestDto(
        int senderAccountId, int receiverAccountId, long amount) {

}///:~