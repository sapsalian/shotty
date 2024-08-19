package com.shotty.shotty.domain.balance.api;

import com.shotty.shotty.domain.balance.application.BalanceService;
import com.shotty.shotty.domain.balance.dto.BalanceResDto;
import com.shotty.shotty.global.common.custom_annotation.annotation.TokenId;
import com.shotty.shotty.global.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;

    @GetMapping("/api/balance")
    @Operation(summary = "잔액 조회")
    public ResponseEntity<ResponseDto<BalanceResDto>> getBalance(@TokenId Long userId) {
        BalanceResDto balanceResDto = balanceService.getBalanceByUserId(userId);

        ResponseDto<BalanceResDto> responseDto = new ResponseDto<>(
                2002,
                "잔액 조회 성공",
                balanceResDto
        );

        return ResponseEntity.ok(responseDto);
    }
}