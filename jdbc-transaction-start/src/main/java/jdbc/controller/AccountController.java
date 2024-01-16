//: jdbc.controller.AccountController.java


package jdbc.controller;


import com.google.common.collect.ImmutableList;
import jdbc.domain.model.Account;
import jdbc.domain.model.TransferRequestDto;
import jdbc.service.AccountTransferService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
class AccountController {

    private final AccountTransferService transferService;

    @GetMapping
    ResponseEntity<List<Account>> findAll() {
        var allAccounts = ImmutableList.copyOf(this.transferService.allAccounts());
        return ResponseEntity.ok(allAccounts);
    }

    @GetMapping("/{id}")
    ResponseEntity<Account> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.transferService.findById(id));
    }

    @PostMapping("/trans")
    ResponseEntity<?> transfer(@NonNull @RequestBody TransferRequestDto transReq) {

        return ResponseEntity.ok(this.transferService.transferMoney(transReq));
    }

}///:~