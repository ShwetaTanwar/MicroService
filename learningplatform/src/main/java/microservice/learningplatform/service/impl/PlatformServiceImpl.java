package microservice.learningplatform.service.impl;

import microservice.learningplatform.dto.PlatformDto;
import microservice.learningplatform.entities.Platform;
import microservice.learningplatform.exception.ResourceNotFoundException;
import microservice.learningplatform.repositories.PlatformRepository;
import microservice.learningplatform.service.PlatformService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class PlatformServiceImpl implements PlatformService {
    @Autowired
        private ModelMapper modelMapper;

        private PlatformDto convertToDTO(Platform platform) {
            return modelMapper.map(platform, PlatformDto.class);
        }

        private Platform DtoToEntity(PlatformDto platformDto) {
            return modelMapper.map(platformDto, Platform.class);
        }

    @Autowired
    private PlatformRepository platformRepository;
    @Override
    public PlatformDto create(PlatformDto platformDto) {
        String randomPlatformId= UUID.randomUUID().toString();
        Platform platform = DtoToEntity(platformDto);
        platform.setId(randomPlatformId);
        Platform savedPlatform = platformRepository.save(platform);
        return convertToDTO(savedPlatform);
    }

    @Override
    public List<PlatformDto> getAll() {
        List<Platform> platforms = platformRepository.findAll();
        return platforms.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

//    public PlatformDto getByName(String name) {
//        Platform platform = this.platformRepository.findByName(name)
//                .orElseThrow(() -> new ResourceNotFoundException("Platform not found with name: " + name));
//        return convertToDTO(platform);
//    }
    @Override
    public List<PlatformDto> getByName(String name)
    {
        List<Platform> platforms = platformRepository.findByNameContaining(name);
        if (platforms.isEmpty())
        {
          throw new ResourceNotFoundException("No platforms found with name containing: " + name);
        }
        List<PlatformDto> platformDtos = platforms.stream().map((platform)->modelMapper.map(platform,PlatformDto.class)).
                collect(Collectors.toList());
                return platformDtos;
        //return platforms.stream().map(this::convertToDTO).collect(Collectors.toList());

    }
    @Override
    public List<PlatformDto> getById(String id) {
        List<Platform> platforms = platformRepository.findByIdContaining(id);
        if (platforms.isEmpty())
        {
            throw new ResourceNotFoundException("No platforms found with id containing: " + id);
        }
        List<PlatformDto> platformDtos = platforms.stream().map((platform)->modelMapper.map(platform,PlatformDto.class)).
                collect(Collectors.toList());
        return platformDtos;
    }

    @Override
    public PlatformDto updatePlatform(String id, PlatformDto newPlatformDto) {

         return platformRepository.findById(id)
                        .map(existingUser -> {
                                    Platform updatePlatform = DtoToEntity(newPlatformDto);
                                    updatePlatform.setId(existingUser.getId());
                                    return convertToDTO(platformRepository.save(updatePlatform));
                                })
                 .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }




}
