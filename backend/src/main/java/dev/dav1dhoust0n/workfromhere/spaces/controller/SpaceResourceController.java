package dev.dav1dhoust0n.workfromhere.spaces.controller;

import dev.dav1dhoust0n.workfromhere.spaces.model.SpaceResource;
import dev.dav1dhoust0n.workfromhere.spaces.service.SpaceResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpaceResourceController {

    // Hard-Coded for now, needs to put inside an env file
    private final String API_VERSION = "1";
    private final String SPACES_URI = "/api/v" + API_VERSION + "/spaces/";

    private final SpaceResourceService spaceResourceService;

    public SpaceResourceController(SpaceResourceService spaceResourceService) {
        this.spaceResourceService = spaceResourceService;
    }

    @GetMapping(value = SPACES_URI)
    public List<SpaceResource> getAllSpaces() {
        return ResponseEntity
                .ok(spaceResourceService.getAllSpaces());
    }

    @GetMapping(value = SPACES_URI + "{id}")
    public ResponseEntity<SpaceResource> getSpaceById(@RequestParam long id) {
        return ResponseEntity
                .ok(spaceResourceService.getSpaceById(id));
    }

    @PostMapping(value = SPACES_URI)
    public ResponseEntity<SpaceResource> createSpace(@RequestBody SpaceResource spaceResource) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(spaceResourceService.createSpace(spaceResource));
    }

    @PutMapping(value = SPACES_URI + "{id}")
    public ResponseEntity<Void> updateSpaceById(@RequestBody SpaceResource updatedSpace, @PathVariable long id) {
        spaceResourceService.updateSpace(updatedSpace, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = SPACES_URI + "{id}")
    public ResponseEntity<Void> deleteSpaceById(@RequestParam long id) {
        spaceResourceService.deleteSpace(id);

        return ResponseEntity.noContent().build();
    }
}
