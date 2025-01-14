# Enviro365 Waste Sorting API

This is a Spring Boot application for the waste sorting mobile application at Enviro365. It provides RESTful APIs to manage waste categories, including waste category lookup, disposal guidelines retrieval, and recycling tips display.

## Features
- Retrieve all waste categories.
- Retrieve a specific waste category by ID.
- Create a new waste category.
- Update an existing waste category.
- Delete a waste category.
- Input validation and structured JSON responses.

## Prerequisites
- Java 17+
- Maven 3.6+
- H2 Database (in-memory)

## Getting Started

### Clone the Repository
```bash
git clone <repository_url>
cd <repository_directory>
```

### Build and Run
1. **Build the application:**
   ```bash
   mvn clean install
   ```
2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

### Access H2 Console
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

#### Base URL: `http://localhost:8080/api/categories`

### Sample Request and Response

#### Create a New Category
**Request:**
```http
POST /api/categories
Content-Type: application/json

{
  "name": "Plastic",
  "description": "Non-biodegradable material",
  "disposalGuideline": "Place in blue recycling bin.",
  "tips": "Clean and sort before recycling."
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Plastic",
  "description": "Non-biodegradable material",
  "disposalGuideline": "Place in blue recycling bin.",
  "tip": "Clean and sort before recycling."
}
```

### Error Handling
Validation errors return structured JSON responses. For example:

**Request:**
```http
POST /api/categories HTTP/1.1
Content-Type: application/json

{
  "name": "",
  "description": "Description exceeding the maximum allowed length..."
}
```

**Response:**
```json
{
  "name": "Category name is required.",
  "description": "Description must not exceed 500 characters."
}
```

## Technologies Used
- Spring Boot
- Spring Data JPA
- Hibernate Validator
- H2 Database
