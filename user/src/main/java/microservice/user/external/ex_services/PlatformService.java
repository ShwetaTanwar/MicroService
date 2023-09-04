package microservice.user.external.ex_services;

import microservice.user.entity.Platform;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "LEARNING-PLATFORM-SERVICE")
public interface PlatformService
{
    @GetMapping("/platforms/find/{id}")
    Platform[] getPlatform(@PathVariable String id);
}
