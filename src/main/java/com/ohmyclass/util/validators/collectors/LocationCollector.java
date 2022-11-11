package com.ohmyclass.util.validators.collectors;

import com.ohmyclass.util.validators.other.ValidationLocation;

public interface LocationCollector extends ResultCollector {

	ReasonCollector location(ValidationLocation location);
}
