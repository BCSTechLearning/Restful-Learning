package com.BCS.training.Start.bird;

import com.BCS.training.Start.EntityNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoProperties;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.OAuth;

import javax.validation.Valid;

@RestController
@RequestMapping("/birds")
public class BirdController {


    @Autowired
    private BirdService birdService;

    @GetMapping(value = "/{birdId}")
    @ApiOperation(value = "Display Welcome message to the Uer", response = Bird.class)
    public Bird getBird(@PathVariable("birdId") Long birdId) throws EntityNotFoundException {
        return birdService.getBird(birdId);
    }

    @GetMapping(value = "/params")
    @ApiOperation(value = "Display Welcome message to the User", response = Bird.class)
    public Bird getBirdRequestParam(@RequestParam("birdId") Long birdId) throws EntityNotFoundException {
        return birdService.getBird(birdId);
    }

    @GetMapping(value = "/noexception/{birdId}")
    @ApiOperation(value = "Display Welcome message to the Uer", response = Bird.class)
    public Bird getBirdNoException(@PathVariable("birdId") Long birdId) throws EntityNotFoundException {
        return birdService.getBirdNoException(birdId);
    }

    @PostMapping
    @ApiOperation(value = "Display Welcome message to the Uer", response = Bird.class)
    public Bird createBird(@RequestBody @Valid Bird bird){
        return birdService.createBird(bird);
    }
}
