package com.arsensargsyan.communi.link.persistence.community.repository;

import java.util.Optional;

import com.arsensargsyan.communi.link.persistence.community.PersistentResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<PersistentResident, Long> {

    Optional<PersistentResident> findByCommunityIdAndUsername(Long communityId, String username);
}