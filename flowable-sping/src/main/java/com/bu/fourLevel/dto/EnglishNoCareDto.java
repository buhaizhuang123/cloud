package com.bu.fourLevel.dto;

import com.bu.fourLevel.inter.NotIsZs;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


/**
 * @author haizhuangbu
 * @date 2023/12/5 11:10
 * @mark EnglishNoCareDto
 */
@Data
public class EnglishNoCareDto {

    @NotBlank(message = "englishName 不能为空")
    @NotIsZs(message = "englishName 不能为 ZS")
    private String englishName;

}
