package com.ssafy.sulnaeeum.model.drink.entity;

import com.ssafy.sulnaeeum.model.drink.dto.DrinkDetailDto;
import com.ssafy.sulnaeeum.model.drink.dto.DrinkDto;
import com.ssafy.sulnaeeum.model.drink.dto.MyPageDrinkDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="drink")
@Getter
public class Drink {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long drinkId; // auto_increment PK

    @Column(length = 25)
    private String drinkName; // 이름

    @Column(length = 2000)
    private String drinkInfo; // 정보

    private String drinkImage; // 이미지

    private String drinkSaleUrl; // 판매 사이트

    @Column(length = 100)
    private String drinkPrice; // 가격 (won)

    @Column(length = 100)
    private String drinkAmount; // 양 (ml)

    private int drinkLevel; // 도수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_type_id")
    private DrinkType drinkType; // 주종

    private int likeCnt; // 찜 개수

    private int reviewCnt; // 리뷰 개수

    private double avgScore; // 평점 평균

    @Schema(description = "단 맛 점수")
    private int sweetScore;

    @Schema(description = "신 맛 점수")
    private int sourScore;

    @Schema(description = "향 점수")
    private int flavorScore;

    @Schema(description = "목넘김 점수")
    private int throatScore;

    @Schema(description = "바디감 점수")
    private int bodyScore;

    @Schema(description = "청량감 점수")
    private int refreshScore;

    // Entity -> DTO 변환
    public DrinkDto toDto() {
        return DrinkDto.builder()
                .drinkId(this.drinkId)
                .drinkName(this.drinkName)
                .drinkInfo(this.drinkInfo)
                .drinkImage(this.drinkImage)
                .drinkSaleUrl(this.drinkSaleUrl)
                .drinkPrice(this.drinkPrice)
                .drinkAmount(this.drinkAmount)
                .drinkLevel(this.drinkLevel)
                .drinkTypeDto(this.drinkType.toDto())
                .likeCnt(this.likeCnt)
                .reviewCnt(this.reviewCnt)
                .avgScore(this.avgScore).build();
    }

    // DrinkEntity -> DrinkDetailDto (like, clear는 없음 - 나중에 set 할 예정)
    public DrinkDetailDto toDrinkDetailDto() {
        return DrinkDetailDto.builder()
                .drinkId(this.drinkId)
                .drinkName(this.drinkName)
                .drinkInfo(this.drinkInfo)
                .drinkImage(this.drinkImage)
                .drinkSaleUrl(this.drinkSaleUrl)
                .drinkPrice(this.drinkPrice)
                .drinkAmount(this.drinkAmount)
                .drinkLevel(this.drinkLevel)
                .drinkTypeName(this.drinkType.getDrinkTypeName())
                .avgScore(this.avgScore).build();
    }

    // Entity -> MyLikeDrinkDto 변환
    public MyPageDrinkDto toMyLikeDrinkDto() {
        return MyPageDrinkDto.builder()
                .drinkId(this.drinkId)
                .drinkName(this.drinkName)
                .drinkImage(this.drinkImage)
                .drinkAmount(this.drinkAmount)
                .drinkLevel(this.drinkLevel)
                .drinkType(this.drinkType.toDto().getDrinkTypeName())
                .build();
    }
}