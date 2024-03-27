package com.bu.fourLevel.dao;

import com.bu.fourLevel.dto.EngFourLeverDto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author haizhuangbu
 * @date 2024/2/28 10:37
 * @mark EngFourLevelRepository
 */
public interface EngFourLevelRepository extends JpaRepository<EngFourLeverDto, String> {
}
