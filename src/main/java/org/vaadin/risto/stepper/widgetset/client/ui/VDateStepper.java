package org.vaadin.risto.stepper.widgetset.client.ui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;

/**
 * @author Risto Yrjänä / Vaadin Ltd.
 * 
 */
public class VDateStepper extends VAbstractStepper<Date, Integer> {

    private DateStepField dateStepField;

    private DateTimeFormat dateFormat;

    public VDateStepper() {
        setDateFormat(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT));
    }

    @Override
    protected boolean isValidForType(String value) {
        try {
            getDateFormat().parse(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Date methods are not deprecated in GWT
    @SuppressWarnings("deprecation")
    @Override
    public String getDecreasedValue(String startValue) {
        Date dateValue = getDateFormat().parse(startValue);

        switch (dateStepField) {

        case DAY:
            int day = dateValue.getDate();
            day -= getStepAmount();
            dateValue.setDate(day);
            break;

        case MONTH:
            int month = dateValue.getMonth();
            month -= getStepAmount();
            dateValue.setMonth(month);
            break;

        case YEAR:
            int year = dateValue.getYear();
            year -= getStepAmount();
            dateValue.setYear(year);
            break;

        default:
            GWT.log("Unknown date step field " + dateStepField);
        }

        return getDateFormat().format(dateValue);

    }

    // Date methods are not deprecated in GWT
    @SuppressWarnings("deprecation")
    @Override
    public String getIncreasedValue(String startValue) {
        Date dateValue = getDateFormat().parse(startValue);
        switch (dateStepField) {

        case DAY:
            int day = dateValue.getDate();
            day += getStepAmount();
            dateValue.setDate(day);
            break;

        case MONTH:
            int month = dateValue.getMonth();
            month += getStepAmount();
            dateValue.setMonth(month);
            break;

        case YEAR:
            int year = dateValue.getYear();
            year += getStepAmount();
            dateValue.setYear(year);
            break;

        default:
            GWT.log("DateFieldIndex was out of bounds", null);
        }
        return getDateFormat().format(dateValue);
    }

    @Override
    public Date parseStringValue(String value) {
        if (value == null || "".equals(value)) {
            return null;
        } else {
            return getDateFormat().parse(value);
        }
    }

    @Override
    protected boolean isSmallerThanMax(String stringValue) {
        Date value = getDateFormat().parse(stringValue);

        if (getMaxValue() != null && value.after(getMaxValue())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected boolean isLargerThanMin(String stringValue) {
        Date value = getDateFormat().parse(stringValue);

        if (getMinValue() != null && value.before(getMinValue())) {
            return false;
        } else {
            return true;
        }
    }

    public DateTimeFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateTimeFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateStepField getDateStepField() {
        return dateStepField;
    }

    public void setDateStepField(DateStepField dateStepField) {
        this.dateStepField = dateStepField;
    }

    @Override
    public Integer parseStepAmount(String value) {
        if (value == null || "".equals(value)) {
            return null;
        } else {
            return Integer.parseInt(value);
        }
    }

}