# E-Commerce Product Management API

## Overview

This project is a RESTful API built with **Spring Boot** for managing products in an e-commerce system. It provides endpoints for creating, retrieving, updating (restoring), and soft-deleting products. The API uses a soft delete mechanism to mark products as deleted without removing them from the database, allowing for restoration if needed.

## Features

- **Create a Product**: Add a new product with name, description, and price.
- **Retrieve Products**: Get a list of all active (non-deleted) products or a specific product by ID.
- **Soft Delete**: Mark a product as deleted without removing it from the database.
- **Restore Product**: Restore a previously soft-deleted product.
- **Retrieve Deleted Products**: Get a list of all soft-deleted products.
- **Error Handling**: Custom exceptions (`ProductApiException`) for robust error management.
- **Logging**: Uses SLF4J for logging operations and errors.

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for building the RESTful API.
- **Spring Data JPA**: For database operations.
- **Lombok**: To reduce boilerplate code (e.g., getters, setters, builders).
- **SLF4J**: For logging.
- **Maven**: Dependency management (assumed, based on typical Spring Boot setup).

## Project Structure

```
com.ecommerce.product
├── controller
│   └── ProductController.java   # REST controller for handling API requests
├── service
│   └── ProductServiceImpl.java  # Service layer with business logic
├── dto
│   └── ProductDto.java         # Data Transfer Object for product data
├── entity
│   └── Product.java           # JPA entity for product
├── repos
│   └── ProductRepo.java       # JPA repository for database operations
└── error
    └── ProductApiException.java # Custom exception for API errors
```

## Setup Instructions

### Prerequisites

- **Java**: JDK 17 or higher
- **Maven**: For dependency management
- **Database**: A relational database (e.g., MySQL, PostgreSQL) configured with Spring Data JPA
- **IDE**: IntelliJ IDEA, Eclipse, or any Java IDE
- **Postman** or **cURL**: For testing API endpoints

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Mohamedgamaal16/product.git
   cd product
   ```

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   The API will be available at `http://localhost:8080/api/products`.

## API Endpoints

### 1. Create a Product
- **Endpoint**: `POST /api/products`
- **Request Body**:
  ```json
  {
    "name": "Product Name",
    "description": "Product Description",
    "price": 29.99
  }
  ```
- **Response**:
  - **201 Created**: `"Product Created Successfully"`
  - **400 Bad Request**: If the request body is invalid (e.g., missing fields)

### 2. Get All Active Products
- **Endpoint**: `GET /api/products`
- **Response**:
  - **200 OK**: List of products in JSON format
    ```json
    [
      {
        "id": "1",
        "name": "Product Name",
        "description": "Product Description",
        "price": 29.99
      }
    ]
    ```
  - **404 Not Found**: If no active products are found

### 3. Get Product by ID
- **Endpoint**: `GET /api/products/{id}`
- **Response**:
  - **200 OK**: Product details
    ```json
    {
      "id": "1",
      "name": "Product Name",
      "description": "Product Description",
      "price": 29.99
    }
    ```
  - **404 Not Found**: If the product is not found or is deleted

### 4. Soft Delete Product
- **Endpoint**: `DELETE /api/products/{id}`
- **Response**:
  - **204 No Content**: Product marked as deleted
  - **404 Not Found**: If the product is not found or already deleted

### 5. Restore Product
- **Endpoint**: `PUT /api/products/{id}/restore`
- **Response**:
  - **200 OK**: Product restored
  - **404 Not Found**: If the product is not found or not deleted

### 6. Get All Deleted Products
- **Endpoint**: `GET /api/products/deleted-products`
- **Response**:
  - **200 OK**: List of deleted products
    ```json
    [
      {
        "id": "1",
        "name": "Deleted Product",
        "description": "Deleted Product Description",
        "price": 19.99
      }
    ]
    ```
  - **200 OK**: Empty list if no deleted products are found

## Error Handling

The API uses a custom `ProductApiException` to handle errors gracefully. Common error responses include:

- **400 Bad Request**: Invalid input data
- **404 Not Found**: Product not found or deleted
- **500 Internal Server Error**: Unexpected server errors

Example error response:
```json
{
  "error": "No product found with id: 123"
}
```

## Logging

- The application uses SLF4J for logging key operations and errors.
- Logs are generated for:
  - Successful product creation, retrieval, deletion, and restoration.
  - Errors during operations, including unexpected exceptions.

## Assumptions

- The `ProductDto` class includes a `fromEntity` method to map `Product` entities to DTOs.
- The `Product` entity has fields for `id`, `name`, `description`, `price`, and `deleted` (boolean for soft delete).
- The `ProductRepo` extends `JpaRepository` with methods like `findAll()`, `findById()`, and `findByDeletedTrue()`.

## Future Improvements

- Add input validation for product fields (e.g., non-empty name, positive price).
- Implement authentication and authorization (e.g., JWT) for secure access.
- Add pagination for `GET /api/products` to handle large datasets.
- Include unit and integration tests using JUnit and MockMvc.
- Add Swagger/OpenAPI documentation for the API.

## Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m "Add your feature"`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For questions or feedback, reach out to [Mohamedgamaal16](https://github.com/Mohamedgamaal16).
