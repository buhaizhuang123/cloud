package com.cloud.common.utls;

import com.cloud.common.PO.CellPo;
import com.cloud.common.PO.SheetPo;
import com.cloud.common.constant.excel.Cell;
import com.cloud.common.constant.excel.Sheet;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2023/8/18 16:55
 * @mark ExcelUtils
 */
@Sheet
public class ExcelUtils<T> {

    private ExcelUtils() {
    }

    public static <T> HSSFWorkbook upload(List<T> dataList, Class<T> excelPo) {
        ExcelUtils<T> tExcelUtils = new ExcelUtils<>();
        return tExcelUtils.create(dataList, excelPo);
    }


    public HSSFWorkbook create(List<T> dataList, Class<T> excelPo) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        SheetPo sheetPo = parseSheet(excelPo);

        HSSFSheet sheet = workbook.createSheet(StringUtils.isBlank(sheetPo.getSheetName()) ? " 0 " : sheetPo.getSheetName());
        // 长度自适应
        sheet.autoSizeColumn(0);
        // 组装表头信息
        HSSFRow title = sheet.createRow(0);
        sheetPo.getCellPos().stream()
                .peek(i -> this.createTitle(title, i)).count();
        return workbook;
    }

    private void createTitle(HSSFRow title, CellPo cellPo) {
        Integer index = cellPo.getIndex();
        HSSFCell cell = title.createCell(index);
        cell.setCellValue(cellPo.getCellName());
    }

    /**
     * @param excelPo 文档数据结构
     * @return 结构对象
     */
    private SheetPo parseSheet(Class<T> excelPo) {
        SheetPo sheetPo = new SheetPo();
        // 文档格式 反射解析
        // 文档
        Sheet sheet = excelPo.getDeclaredAnnotation(Sheet.class);
        if (sheet != null) {
            sheetPo.setSheetName(sheet.name());

        }
        // 解析属性 对应单元格
        Field[] fields = excelPo.getDeclaredFields();
        List<CellPo> cellPos = Arrays.stream(fields)
                .map(this::createCell)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        sheetPo.setCellPos(cellPos);
        return sheetPo;
    }


    private CellPo createCell(Field field) {
        CellPo cellPo = null;
        // 破坏封装性
        field.setAccessible(true);
        String name = field.getName();
        Cell cell = field.getAnnotation(Cell.class);
        if (cell != null) {
            cellPo = new CellPo();
            cellPo.setCellName(cell.name());
            cellPo.setIndex(cell.index());
        }
        return cellPo;
    }


}
