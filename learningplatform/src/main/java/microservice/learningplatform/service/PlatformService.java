package microservice.learningplatform.service;

import microservice.learningplatform.dto.PlatformDto;
import microservice.learningplatform.entities.Platform;

import java.util.List;
import java.util.Optional;

public interface PlatformService {
    PlatformDto create(PlatformDto platformDto);
    List<PlatformDto> getAll();

    List<PlatformDto> getByName(String name);

    PlatformDto updatePlatform(String id, PlatformDto newPlatformDto);

    List<PlatformDto> getById(String id);
    // PlatformDto getByName(String name);



}
