# Project Overview: Web Service - Politician and Social Media Integration System

## Members
- YING Beining(Email: Beining.Ying@etu.cyu.fr)
- SUN Jiaduo(Email: Jiaduo.Sun@etu.cyu.fr)


## Introduction
This project aims to develop a comprehensive system for managing and displaying information about politicians and their affiliated political parties, along with integrating social media interactions. The system comprises two main components: a web service backend and a Java-based client frontend. The web services are implemented using both RESTful and WS-* standards, employing JAX-RS and JAX-WS frameworks respectively. An optional feature includes interfacing with a database for persistent storage of political data. Additionally, the system integrates with external social media services(Twitter/X) to fetch recent posts related to political entities.

## Services Description

### Web Services
- **Technology Used**: Java, JAX-WS, JAX-RS
- **Operations**:
  1. **Add Politician**: Allows the addition of a politician's details to the system.
  2. **Get Politician Information**: Retrieves detailed information about politicians, including their party affiliation.
  3. **Add Political Party**: Enables the creation of political party entries in the system.
  4. **Get Political Party Information**: Fetches details about a specific political party.

- **External Service Integration**: The service also includes functionality to interact with external social media APIs (using platforms like RapidAPI) to retrieve the latest posts or photos associated with political parties.

### WS-* Services
- Implemented using JAX-WS to support SOAP-based operations which include adding and fetching politician and party information.

### REST Services
- Implemented using JAX-RS to provide RESTful access to similar operations as WS-* but through HTTP-based methods.

## Clients Description
- **Technology Used**: Java
- **Functionality**:
  - **Call Local Web Services**: The client is capable of interacting with both RESTful and WS-* services provided by the local server.
  - **Call External Web Service**: The client also makes calls to an external social media service to gather additional data related to political parties.

## Use Case Example
### Scenario: Retrieving Politician and Related Social Media Data

#### Steps:
1. **Add Politician**:
   - The client sends a request to add a politician named "John Doe", a member of the "Green Party".
   - The web service stores this information and acknowledges the addition.

2. **Query Politician**:
   - A user queries the system for information on "John Doe".
   - The service retrieves details including his party affiliation.

3. **Fetch Social Media Posts**:
   - Using the retrieved party information, the client calls an external social media API to fetch recent posts related to the "Green Party".
   - These posts are then displayed alongside John Doe's political profile.

#### Result:
The user receives a comprehensive overview of John Doe, including his political affiliations and recent social media activity related to his party, all through a single interface provided by the client.
