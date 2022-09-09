# Security concept

Filter for basic auth.
Application ---Call to backend for basic auth token--> save
Every request ---> Basic auth token validate here --> then controller
Every request ---> Filter for uname & pw in body of request. validate against DB