[![build](https://github.com/Oh-my-class/oh-my-backend/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/Oh-my-class/oh-my-backend/blob/develop/.github/workflows/codeql-analysis.yml)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/Oh-my-class/oh-my-backend)
![License](https://img.shields.io/badge/license-GNU--3.0-orange)

# Oh-My-Backend

**Designed _for_ students _by_ students**

The REST-API of [_Oh-My-Web_](https://github.com/Oh-my-class/Oh-my-web)

## What is _OMB_?

_Oh-My-Backend_ is a standalone **opensource** REST-API class-management system.

In other words:

You can build your own implementation for a client or just use the already existing [_Oh-my-Web_](https://github.com/Oh-my-class/oh-my-web);
<br/>
A web-app built with React, TypeScript and lots of love! :heart:

## How-to's

### Start the REST-API

#### 1. Clone repository

#### 2. Start the database server

1. Open a Terminal and navigate to the project's root
2. Run `docker compose up -d` to start the database
3. Or: Feel free to use any MySQL database
   - Just make sure it's running on `___:3306`
   - If this is not the case, edit the `spring.datasource.url` to match your port in the`application.yml` file
4. Create a database called 'ohmyclass'

#### 3. Start OhMyClass.Application

- Via IntelliJ:
   - Locate `OhMyClass.java` and hit the run button
- Via maven:
   - Locate the project's root and run `mvn spring-boot:run`

### Send requests

- Postman
   - Import the requests and environment files into postman `/documentation/postman`
- [...]
   - [...]
