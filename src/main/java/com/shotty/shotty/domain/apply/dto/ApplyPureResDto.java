package com.shotty.shotty.domain.apply.dto;

import com.shotty.shotty.domain.apply.domain.Apply;
import com.shotty.shotty.domain.bid.domain.Bid;
import com.shotty.shotty.domain.influencer.domain.Influencer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record ApplyPureResDto(
        @Schema(description = "지원 식별자",example = "1")
        Long applyId,
        @Schema(description = "지원 제목",example = "지원합니다")
        String title,
        @Schema(description = "지원 내용",example = "내용입니다.")
        String content,
        @Schema(description = "지원 영상 url",example = "https://videos.pexels.com/video-files/3009534/3009534-sd_640_360_24fps.mp4")
        String videoLink,
        @Schema(description = "지원 일자",example = "2024-08-22")
        LocalDate appliedAt,
        @Schema(description = "지원한 인플루언서 식별자",example = "154")
        Long influencerId,
        @Schema(description = "지원한 인플루언서 이름",example = "나는인플루언서")
        String influencerName,
        @Schema(description = "지원한 인플루언서의 프로필사진 url",example = "https://d2v80xjmx68n4w.cloudfront.net/gigs/Z63CJ1694753430.jpg")
        String profileImage,
        @Schema(description = "지원의 입찰 여부",example = "true")
        Boolean bidded,
        @Schema(description = "업로드된 쇼츠 영상 id", example = "CdHc0rl3IM4")
        String shortsId,
        @Schema(description = "최종 승인 여부", example = "false")
        Boolean accepted
) {
    public static ApplyPureResDto from(Apply apply) {
        Influencer influencer = apply.getInfluencer();
        Bid bid = apply.getBid();

        boolean bidded = false;
        String shortsId = null;
        boolean accepted = false;

        if (bid != null) {
            bidded = true;
            shortsId = bid.getShortsId();
            accepted = bid.getAccepted();
        }

        return new ApplyPureResDto(
                apply.getId(),
                apply.getTitle(),
                apply.getContent(),
                apply.getVideoLink(),
                apply.getCreatedAt().toLocalDate(),
                influencer.getId(),
                influencer.getUser().getName(),
                influencer.getProfile_image(),
                bidded,
                shortsId,
                accepted
        );
    }
}
