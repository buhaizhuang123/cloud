package com.cloud.common.PO;

import lombok.Data;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/8/18 17:08
 * @mark SheetPo
 */
@Data
public class SheetPo {

    private String sheetName;

    private List<CellPo> cellPos;


}
