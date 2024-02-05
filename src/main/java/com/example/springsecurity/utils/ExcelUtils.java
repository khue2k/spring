package com.example.springsecurity.utils;

import com.example.springsecurity.reflection.ExcelColumn;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
    public static <T> List<T> convertRowsToObject(int ignoreRow, Sheet sheet, Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DataFormatter dataFormatter = new DataFormatter();
        List<T> result = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        Constructor<?> defaultConstructor = clazz.getDeclaredConstructor();
        Iterator<Row> rowIterator = sheet.rowIterator();
        int count = 0;
        while (rowIterator.hasNext()) {
            count++;
            if (count <= ignoreRow) {
                rowIterator.next();
                continue;
            }
            Row row = rowIterator.next();
            T t = (T) defaultConstructor.newInstance();
            for (Field field : fields) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof ExcelColumn) {
                        ExcelColumn excelColumn = (ExcelColumn) annotation;
                        int index = excelColumn.index();
                        Cell cell = row.getCell(index, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        String valueOfCell = dataFormatter.formatCellValue(cell);
                        Method set = clazz.getDeclaredMethod(toSetter(field.getName()), String.class);
                        set.invoke(t, valueOfCell);
                    }
                }
            }
            result.add(t);
        }
        return result;
    }

    public static String toSetter(String field) {
        return "set" + Character.toUpperCase(field.charAt(0)) + field.substring(1);
    }
}
