package com.arsensargsyan.communi.link.persistence.community.repository;

import com.arsensargsyan.communi.link.common.CommunityType;
import com.arsensargsyan.communi.link.persistence.community.PersistentCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<PersistentCommunity, Long> {

    boolean existsByNameAndType(String name, CommunityType type);
}