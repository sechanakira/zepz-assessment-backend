# zepz-assessment-backend
Zepz Assessment Backend

Requirements:
1. Java 17
2. Maven
3. Mongo DB instance

Configuration:
1. Set the mongodb url in application.properties
2. Set the server port in application.properties. The default port is 8484

Exposed endpoints:
1. http://localhost:8484/activities/sync : Fetches and persists activities
2. http://localhost:8484/activities : Fetches all persisted activities
3. http://localhost:8484/activities/search?type=social : Searches persisted activities by type e.g social in this case
