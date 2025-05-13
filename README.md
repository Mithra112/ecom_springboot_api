**🛒 E-commerce Backend API (Spring Boot + MySQL)**

This is the backend API for an E-commerce application, developed using **Spring Boot** and connected to a **MySQL database**.

---

**🔧 Technologies Used**

* Java 17+
* Spring Boot
* Spring Data JPA
* MySQL
* Maven

---

**🔐 Features**

* Add Products
* JWT-based authentication
* Product CRUD APIs
* MySQL database connection

---

**🧪 How to Run**

1. Clone the repository:

```
git clone https://github.com/Mithra112/ecom_springboot_api.git
cd ecom_springboot_api
```

2. Set up MySQL and update your `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Run the project using:

```
./mvnw spring-boot:run
```

**🔌 Sample API Endpoints**

| Method | Endpoint           | Description             |
| ------ | ------------------ | ----------------------- |
| GET    | /api/products      | Get list of products    |
| POST   | /api/products      | Add new product (Admin) |

---

**📄 License**

This project is created for learning purposes only.

