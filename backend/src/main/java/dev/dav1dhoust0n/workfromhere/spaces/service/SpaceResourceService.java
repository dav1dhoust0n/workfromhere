package dev.dav1dhoust0n.workfromhere.spaces.service;

import dev.dav1dhoust0n.workfromhere.spaces.exception.SpaceResourceException;
import dev.dav1dhoust0n.workfromhere.spaces.model.SpaceResource;
import dev.dav1dhoust0n.workfromhere.spaces.repository.SpaceResourceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpaceResourceService {

    private final SpaceResourceRepository spaceResourceRepository;

    public SpaceResourceService(SpaceResourceRepository spaceResourceRepository) {
        this.spaceResourceRepository = spaceResourceRepository;
    }

    public List<SpaceResource> getAllSpaces() {
        return spaceResourceRepository.findAll();
    }

    public SpaceResource getSpaceById(long id) {
        return spaceResourceRepository
                .findById(id)
                .orElseThrow(() -> new SpaceResourceException("Space with id " + id + " not found"));
    }


    public SpaceResource createSpace(SpaceResource spaceResource) {
        // Check if space exists
        SpaceResource spaceExists = getSpaceById(spaceResource.getId());
        if (spaceExists != null) {
            throw new SpaceResourceException("Space with id " + spaceResource.getId() + " not found");
        }

        return spaceResourceRepository.save(spaceResource);
    }


    public void updateSpace(SpaceResource updatedSpace, long id) {
        SpaceResource originalSpace =  getSpaceById(id);

        if  (originalSpace == null) {
            throw new SpaceResourceException("Space with id " + id + " not found");
        }

        updateSpaceResource(updatedSpace, originalSpace);

        spaceResourceRepository.save(updatedSpace);
    }

    public void deleteSpace(long id) {
        // Check if space exists
        if (getSpaceById(id) == null) {
            throw new SpaceResourceException("Space with id " + id + " not found");
        }

        spaceResourceRepository.deleteById(id);
    }

    private void updateSpaceResource(SpaceResource updatedSpace, SpaceResource originalSpace) {

        if (originalSpace != null &&  updatedSpace != null) {
            originalSpace.setName(updatedSpace.getName());
            originalSpace.setDescription(updatedSpace.getDescription());
            originalSpace.setType(updatedSpace.getType());
            originalSpace.setWifiAvailable(updatedSpace.isWifiAvailable());
            originalSpace.setWifiPassword(updatedSpace.getWifiPassword());
            originalSpace.setWifiSpeed(updatedSpace.getWifiSpeed());
        }
    }
}
