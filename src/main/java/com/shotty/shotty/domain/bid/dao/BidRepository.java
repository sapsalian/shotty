package com.shotty.shotty.domain.bid.dao;

import com.shotty.shotty.domain.bid.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    boolean existsByApplyId(Long applyId);
    void deleteByApplyId(Long applyId);

    @Query("DELETE FROM Bid bid WHERE bid.id IN (" +
                    "SELECT bid.id " +
                    "FROM Bid bid " +
                    "INNER JOIN Apply apply ON bid.apply.id = apply.id " +
                    "INNER JOIN Influencer influencer ON apply.influencer.id = influencer.id " +
                    "WHERE :influencerId = influencer.id" +
                    ")"
    )
    void deleteAllByInfluencerId(@Param("influencerId") Long influencerId);
}
