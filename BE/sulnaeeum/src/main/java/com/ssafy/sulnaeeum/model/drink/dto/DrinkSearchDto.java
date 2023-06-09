package com.ssafy.sulnaeeum.model.drink.dto;

import com.ssafy.sulnaeeum.model.drink.entity.Drink;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "전통주 정보 (검색용)")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DrinkSearchDto {

    @Schema(description = "아이디 (auto_increment)")
    private Long drinkId;

    @Schema(description = "이름")
    private String drinkName;

    @Schema(description = "이미지")
    private String drinkImage;

    // 생성자 (List<Entity> -> List<DTO> 변환을 위함)
    public DrinkSearchDto(Drink drink) {
        this.drinkId = drink.getDrinkId();
        this.drinkName = drink.getDrinkName();
        this.drinkImage = drink.getDrinkImage();
    }
}
