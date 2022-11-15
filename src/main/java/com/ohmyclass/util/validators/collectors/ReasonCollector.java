package com.ohmyclass.util.validators.collectors;

import com.ohmyclass.util.validators.types.ValidationReason;

/**
 * Collects the passed {@link ValidationReason}
 * @author Z-100
 */
public interface ReasonCollector extends ResultCollector {

	FieldCollector reason(ValidationReason reason);
}
