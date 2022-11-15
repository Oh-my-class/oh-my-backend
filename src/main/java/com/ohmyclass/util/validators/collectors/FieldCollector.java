package com.ohmyclass.util.validators.collectors;

import com.ohmyclass.util.validators.types.ValidationField;

/**
 * Collects the passed {@link ValidationField}
 * @author Z-100
 */
public interface FieldCollector extends ResultCollector {

	ReasonCollector field(ValidationField field);
}
