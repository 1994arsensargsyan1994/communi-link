package com.arsensargsyan.communi.link.persistence.community.repository;

import com.arsensargsyan.communi.link.common.CommunityType;
import com.arsensargsyan.communi.link.persistence.community.PersistentCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<PersistentCommunity, Long> {

    boolean existsByNameAndType(String name, CommunityType type);

    @Modifying
    @Query("update PersistentCommunity c set c.currentCount = c.currentCount- 1 where c.id = :communityId")
    void cancel(@Param("communityId") Long communityId);
}