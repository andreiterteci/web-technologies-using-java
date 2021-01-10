package ro.fmi.HeathTracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fmi.HeathTracker.domain.User;
import ro.fmi.HeathTracker.exception.UserNotFoundException;
import ro.fmi.HeathTracker.mapper.AccountMapper;
import ro.fmi.HeathTracker.model.dto.AccountModel;
import ro.fmi.HeathTracker.repository.UserRepository;
import ro.fmi.HeathTracker.util.PrincipalUtil;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;

    public AccountModel getCurrent() {
        return userRepository.findByEmail(PrincipalUtil.getPrincipal())
                .map(AccountMapper::toModel)
                .orElseThrow(UserNotFoundException::new);
    }

    public void update(final AccountModel accountModel) {
        User user = userRepository.findByEmail(PrincipalUtil.getPrincipal()).orElseThrow(UserNotFoundException::new);
        user.setFirstName(accountModel.getFirstName());
        user.setLastName(accountModel.getLastName());
        user.setEmail(accountModel.getEmail());
        user.setFitnessPlan(accountModel.getFitnessPlan());
        user.setGender(accountModel.getGender());
        user.setHeight(accountModel.getHeight());
        user.setWeight(accountModel.getWeight());
        user.setWeightGoal(accountModel.getWeightGoal());
        userRepository.save(user);
    }
}
