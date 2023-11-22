package org.bebeaubn.commons.configs;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


// ConfigsRepository 이거 생성 해줘야 함

@Service
@RequiredArgsConstructor
public class ConfigDeleteService {
    private final ConfigsRepository repository;

    public void delete(String code) {
        Configs configs = repository.findById(code).orElse(null);
        if (configs == null) {
            return;
        }

        repository.delete(configs);
        repository.flush();
    }
}