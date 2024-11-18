package org.wora.wainting___list.common.exception;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class SmallIntToByteCouverter implements AttributeConverter<Byte, Short> {

    @Override
    public Short convertToDatabaseColumn(Byte attribute) {
        return (attribute != null) ? attribute.shortValue() : null;
    }

    @Override
    public Byte convertToEntityAttribute(Short dbData) {
        return (dbData != null) ? dbData.byteValue() : null;
    }
}
