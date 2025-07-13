package dev.dav1dhoust0n.workfromhere.spaces.service;

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
        // NPE to be addressed
        return spaceResourceRepository.findById(id).orElse(null);
    }


    public SpaceResource createSpace(SpaceResource spaceResource) {
        // Check if space exists
        if (spaceResourceRepository.findById(spaceResource.getId()).isEmpty()) {
            // NPE to be addressed
            throw new NullPointerException("Space with id " + spaceResource.getId() + " does not exist");
        }

        return spaceResourceRepository.save(spaceResource);
    }


    public void updateSpace(SpaceResource updatedSpace, long id) {
        SpaceResource originalSpace =  spaceResourceRepository.findById(id).orElse(null);

        if  (originalSpace == null) {
            // NPE to be addressed
            throw new NullPointerException("Space with id " + id + " does not exist");
        }

        updateSpaceResource(updatedSpace, originalSpace);

        spaceResourceRepository.save(updatedSpace);
    }

    public void deleteSpace(long id) {
        // Check if space exists
        if (spaceResourceRepository.findById(id).isEmpty()) {
            // NPE to be addressed
            throw new NullPointerException("Space with id " + id + " does not exist");
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
