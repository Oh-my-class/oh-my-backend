package com.ohmyclass.util.validators.collectors;

import com.ohmyclass.util.validators.other.ValidationReason;

public interface ReasonCollector extends ResultCollector{

	LocationCollector reason(ValidationReason reason);
}
