package org.bebeaubn.commons.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.bebeaubn.entities.Configs;
import org.bebeaubn.repositories.ConfigsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigSaveService {
    private final ConfigsRepository repository;

    public <T> void save(String code,T value ){
        Configs config =repository.findById(code).orElseGet(Configs::new);
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        String json = null;
        try {
            json = om.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        config.setCode(code);
        config.setValue(json);


        repository.saveAndFlush(config);


    }
    }

