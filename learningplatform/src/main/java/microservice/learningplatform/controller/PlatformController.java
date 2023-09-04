package microservice.learningplatform.controller;
import microservice.learningplatform.dto.PlatformDto;
import microservice.learningplatform.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platforms/")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @PostMapping
    public ResponseEntity<PlatformDto> createPlatform(@RequestBody PlatformDto platformDto) {
        PlatformDto createdPlatform = platformService.create(platformDto);
        return new ResponseEntity<>(createdPlatform, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlatformDto>> getAllPlatforms() {
        List<PlatformDto> platforms = platformService.getAll();
        return new ResponseEntity<>(platforms, HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<PlatformDto>> searchPlatformByName(@PathVariable("name") String name) {
        List<PlatformDto> result = platformService.getByName(name);
        return new ResponseEntity<List<PlatformDto>>(result, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<List<PlatformDto>> searchPlatformById(@PathVariable("id") String id) {
        List<PlatformDto> result = platformService.getById(id);
        return new ResponseEntity<List<PlatformDto>>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PlatformDto> updateInfo(@PathVariable String id, @RequestBody PlatformDto newPlatformDto)
    {
        PlatformDto updadteInfo= platformService.updatePlatform(id,newPlatformDto);
        if(updadteInfo==null)
        {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updadteInfo);
    }



}
