

### PROJECT SETUP & TOOLS
1. JDK 17
2. [MySQL](https://dev.mysql.com/doc/refman/8.0/en/installing.html).
3. [Postman](https://www.postman.com/downloads/) for testing endpoints.


### RUN LOCALLY
On the root folder run:
```
mvn spring-boot:run
```
### CODE STANDARS
* Code must be in English.
* The controllers should finish with suffix "Controller". Example: UserController.
* The services should finish with suffix "Service". Example: UserService.
* The repositories should finish with suffix "Repository". Example: UserRepository.
* The interfaces should start with prefix "I". Example: IUserRepository.
* The implementations should finish with suffix "Impl". Example: UserServiceImpl.
* The DTOs should finish with suffix "Dto". Example: UserDto, UserRequestDto.
* Should usage DTOs for request and response.
* Package names are in singular.
* The names of attributes/fields from Java classes must be written using camel case. Example: firstName.
* The name of columns in the entities must be written using underscore and uppercase. Example: FIRST_NAME. The name of the tables is always in plural, but the entity name should be in singular.
* Exceptions should be handled by an implementation of ControllerAdvice.
* Messages to user can't be hardcoded them should be handled. Some refs here and here.

### GIT STANDARS.
#### Format
* Always create the branch from develop
* The branch name format is: feature/{jiraTicket#}.
* The pull request title format is: {jiraTicket#}: {jiraTitle}.
* The commits format is: {jiraTicket#}: {commitDescription}. Small commits are a nice to have.
* The pull request has to contain only the changes related to the scope defined in the ticket.
* Pull request should always be from your current branch to develop

### BRANCHES
* ```dev```-> this branch serves as an integration branch for features. All features must start from this branch and after it's finished it gets merged back into develop.

### ```BACKEND```
#### Some features added:
* modelMapper dependency
* RestExceptionHandler and ApiErrorDTO that responses a JSON with error message.
* ErrorEnum for handling messages return in response, and not hardcode.
