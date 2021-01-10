package ro.fmi.HeathTracker.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.fmi.HeathTracker.model.dto.AccountModel;
import ro.fmi.HeathTracker.model.responses.RestResponseModel;
import ro.fmi.HeathTracker.service.AccountService;
import ro.fmi.HeathTracker.service.MessageService;
import ro.fmi.HeathTracker.util.ResponseEntityUtil;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<AccountModel> getCurrent() {
        return ResponseEntity.ok(accountService.getCurrent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountModel> getForEdit(@PathVariable final String id) {
        return ResponseEntity.ok(accountService.getCurrent());
    }

    @PostMapping
    public ResponseEntity<RestResponseModel> update(@RequestBody final AccountModel accountModel,
                                                    final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntityUtil.getRestResponseModelWithErrors(messageService.getAllErrors(bindingResult));
        }

        accountService.update(accountModel);

        return ResponseEntityUtil.getRestResponseModel();
    }
}
