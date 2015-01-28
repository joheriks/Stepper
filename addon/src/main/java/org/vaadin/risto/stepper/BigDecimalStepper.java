package org.vaadin.risto.stepper;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <p>
 * Field that allows stepping through values via given up/down controls.
 * Supports values of type BigDecimal. Default value is 0.
 * </p>
 * 
 * @author Marcin Wisnicki
 */
public class BigDecimalStepper extends AbstractDecimalStepper<BigDecimal> {

    private static final long serialVersionUID = 1L;

    public BigDecimalStepper() {
        super(BigDecimal.ZERO, BigDecimal.ONE);
        setNumberOfDecimals(Integer.MAX_VALUE);
    }

    public BigDecimalStepper(String caption) {
        this();
        setCaption(caption);
    }

    @Override
    public Class<? extends BigDecimal> getType() {
        return BigDecimal.class;
    }

    protected BigDecimal convertToValueType(Number parsedValue) {
        return (BigDecimal) parsedValue;
    }

    @Override
    protected DecimalFormat getDecimalFormat() {
        DecimalFormat format = super.getDecimalFormat();
        format.setParseBigDecimal(true);
        return format;
    }

}
