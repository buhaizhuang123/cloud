package com.bu.fourLevel.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author haizhuangbu
 * @date 2024/2/28 10:30
 * @mark EnggFourLeverDto
 */
@Entity
@Table(name = "Eng_Four_Lever")
@Data
public class EngFourLeverDto {

    @Column(name = "chinese_name")
    @Id
    private String chineseName;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "phonetic_symbol")
    private String phoneticSymbol;

}
