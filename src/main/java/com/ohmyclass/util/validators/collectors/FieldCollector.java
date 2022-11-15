package com.ohmyclass.util.validators.collectors;

import com.ohmyclass.util.validators.other.ValidationField;

public interface FieldCollector extends ResultCollector {

	ReasonCollector field(ValidationField location);
}
