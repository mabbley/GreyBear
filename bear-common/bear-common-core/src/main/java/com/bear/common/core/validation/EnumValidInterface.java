package com.bear.common.core.validation;

import com.bear.common.core.exception.ValidationException;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Created by mby on 2019/4/17.
 */
public interface EnumValidInterface {

    Integer getCode();
    String getMessage();

    default void isTrue(boolean expression,String message) {

        if (!expression)
            throw new ValidationException(getCode(), message);
        else return;
    }

    default void isTrue(boolean expression) {
        isTrue(expression,getMessage());
    }

    default void isEquals(Object obj1, Object obj2,String message) {

        if (!obj2.equals(obj1))
            throw new ValidationException(getCode(), message);
        else return;
    }


    default void isEquals(Object obj1, Object obj2) {
        isEquals(obj1,obj2,getMessage());
    }

    default void isNull(Object object, String message) {

        if (object != null)
            throw new ValidationException(getCode(), message);
        else return;
    }

    default void isNull(Object object) {
        isNull(object,getMessage());
    }

    default void notNull(Object object, String message) {

        if (object == null)
            throw new ValidationException(getCode(), message);
        else return;
    }

    default void notNull(Object object) {

        notNull(object,getMessage());
    }

    default void notEmpty(Object object, String message) {

        if (object == null)
            throw new ValidationException(getCode(), message);
        if (object.toString().length() < 1)
            throw new ValidationException(getCode(), message);
        else return;
    }

    default void notEmpty(Object object) {
        notEmpty(object,getMessage());
    }


    default void notEmpty(Collection<?> collection, String message) {

        if (CollectionUtils.isEmpty(collection))
            throw new ValidationException(getCode(), message);
        else return;
    }

    default void notEmpty(Collection<?> collection) {
        notEmpty(collection,getMessage());
    }

    default void notBT(Object o, Object o1, Object o2, String message) {
        if (o instanceof Integer) {
            int param1 = ((Integer) o).intValue();
            int param2 = ((Integer) o1).intValue();
            int param3 = ((Integer) o2).intValue();
            if (param1 < param2 || param1 > param3) {
                throw new ValidationException(getCode(), message);
            }
        } else if (o instanceof BigDecimal) {
            BigDecimal param1 = (BigDecimal) o;
            BigDecimal param2 = (BigDecimal) o1;
            BigDecimal param3 = (BigDecimal) o2;
            if (param1.compareTo(param2) == -1 || param1.compareTo(param3) == 1) {
                throw new ValidationException(getCode(), message);
            }
        } else if (o instanceof Double) {
            Double param1 = (Double) o;
            Double param2 = (Double) o1;
            Double param3 = (Double) o2;
            if (param1.compareTo(param2) == -1 || param1.compareTo(param3) == 1) {
                throw new ValidationException(getCode(), message);
            }
        } else if (o instanceof Float) {
            Float param1 = (Float) o;
            Float param2 = (Float) o1;
            Float param3 = (Float) o2;
            if (param1.compareTo(param2) == -1 || param1.compareTo(param3) == 1) {
                throw new ValidationException(getCode(), message);
            }
        } else if (o instanceof Date) {
            Date param1 = (Date) o;
            Date param2 = (Date) o1;
            Date param3 = (Date) o2;
            if (param1.compareTo(param2) == -1 || param1.compareTo(param3) == 1) {
                throw new ValidationException(getCode(), message);
            }
        } else if (o instanceof Long) {
            Long param1 = (Long) o;
            Long param2 = (Long) o1;
            Long param3 = (Long) o2;
            if (param1.compareTo(param2) == -1 || param1.compareTo(param3) == 1) {
                throw new ValidationException(getCode(), message);
            }
        }
    }

    default void notBT(Object o, Object o1, Object o2) {
        notBT(o,o1,o2,getMessage());
    }
}
