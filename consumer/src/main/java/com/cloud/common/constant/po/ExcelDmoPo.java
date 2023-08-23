package com.cloud.common.constant.po;

import com.cloud.common.constant.excel.Cell;
import com.cloud.common.constant.excel.Sheet;
import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2023/8/18 17:32
 * @mark ExcelDmoPo
 */
@Sheet(name = "默认")
@Data
public class ExcelDmoPo {

    @Cell(name = "序号", index = 0)
    private Integer id;


    @Cell(name = "name", index = 1)
    private String name;




}
