package com.people.utils;

import com.people.utils.domain.TEST1;
import com.people.utils.domain.TEST2;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {

    public static boolean compareObjects(Object o1, Object o2) {
        try {
            List<Field> fields1 = getFields(o1);
            List<Field> fields2 = getFields(o2);
            boolean found;
            Field field2Temp = null;
            for (Field field1 : fields1) {
                found = false;
                for (Field field2 : fields2) {
                    if (field1.getName().equals(field2.getName())) {
                        if (!field1.get(o1).equals(field2.get(o2))) {
                            System.out.println("Value " + field1.get(o1) + " for field " + field1 + " does not match the value " + field2.get(o2) + " for field " + field2);
                            return false;
                        }
                        field2Temp = field2;
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("Field " + field1 + " has not been found in the object " + o2.getClass());
                    return false;
                } else {
                    fields2.remove(field2Temp);
                }
            }
            if (fields2.size() > 0) {
                for (Field field : fields2) {
                    System.out.println("Field " + field + " has not been found in the object " + o1.getClass());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static List<Field> getFields(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        return new ArrayList<>(Arrays.asList(fields));
    }

    public static void main(String[] args) {
        TEST1 test1 = new TEST1("TEST", BigDecimal.ONE);
        TEST2 test2 = new TEST2("TEST", BigDecimal.ONE, LocalDateTime.now());

        System.out.println(ReflectionUtils.compareObjects(test1, test2));

    }
}
