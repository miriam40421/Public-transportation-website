
# 🚌 Public Transportation System – Spring Boot Backend

## 📘 Overview  
This is a backend project for a public transportation management system, built using **Java + Spring Boot**. It allows searching for bus lines, stations, and routes, and uses **H2 in-memory database** for quick development and testing.

---

## ✨ Features

- 🔍 Search stations by name or ID  
- 🚍 Retrieve bus lines by number or direction  
- 🧭 View route information including stops and order  
- 🗺️ Filter lines that pass through specific stations  
- 🧪 Lightweight data management using H2 database

---

## 🛠 Technologies Used

- Java 17+  
- Spring Boot  
- Spring Web, Spring Data JPA  
- H2 (in-memory database)  
- Maven  
- Visual Studio Code (recommended IDE)

---

## 🗂 Project Structure

```
/src/main/java
  /controllers      # REST endpoints
  /services         # Business logic
  /repositories     # JPA repositories
  /models           # Entity classes
  Application.java  # Entry point

/src/main/resources
  application.properties  # Configuration
  data.sql / schema.sql   # Optional: preload data
```

---

## ⚙️ Setup & Run Instructions

### Prerequisites

- Java JDK 17+  
- Maven (`mvn`) installed  
- (Optional) Visual Studio Code with Java Extension Pack

### Run the Project

1. Clone the project  
2. Open the terminal and navigate to the project root  
3. Run the app using Maven:
   ```bash
   mvn spring-boot:run
   ```

> You can also build and run the JAR file:
```bash
mvn clean package
java -jar target/your-app-name.jar
```

---

## 💾 Database (H2)

- H2 in-memory DB starts with the app  
- Accessible via browser:  
  [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

Default credentials (can be set in `application.properties`):
```properties
spring.datasource.url=jdbc:h2:mem:transportdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

---

## 🧪 Testing

- Unit and integration tests (if included)  
- Data can be initialized using `data.sql`  

---

## 📄 License

MIT 

---

## 📬 Contact

For support or questions: miriam40421@gmail.com
