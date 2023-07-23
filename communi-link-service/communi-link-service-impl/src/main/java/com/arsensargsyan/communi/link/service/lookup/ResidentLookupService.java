package com.arsensargsyan.communi.link.service.lookup;

import java.util.Optional;

import com.arsensargsyan.communi.link.persistence.community.PersistentResident;

public interface ResidentLookupService {

   Optional<PersistentResident> lookup(Long communityId,String username);

}
