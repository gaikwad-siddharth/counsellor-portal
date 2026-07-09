# Counsellor Portal

A Spring Boot web application that helps counsellors manage student enquiries efficiently. The application provides secure login, enquiry management, filtering, dashboard statistics, and counsellor registration.

## Features

- Counsellor Registration
- Counsellor Login & Logout
- Dashboard with enquiry statistics
- Add Student Enquiry
- View All Enquiries
- Filter Enquiries
- Edit Existing Enquiry
- Session Management
- MySQL Database Integration

## Dashboard

The dashboard displays:

- Total Enquiries
- Open Enquiries
- Enrolled Enquiries
- Lost Enquiries

## Technologies Used

- Java 17+
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- MySQL
- Lombok
- Bootstrap 5
- Maven

## Project Structure

```
src
│
├── controller
│     ├── CounsellorController
│     └── EnquiryController
│
├── service
│     ├── CounsellorService
│     ├── CourseService
│     └── EnquiryService
│
├── service.impl
│
├── entity
│     ├── Counsellor
│     ├── Course
│     └── Enquiry
│
├── repo
│
├── dto
│
└── templates
      ├── index.html
      ├── register.html
      ├── dashboard.html
      ├── add-enq.html
      └── view-enqs.html
```

## Database Configuration

Update `application.properties` according to your MySQL configuration.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jrtp
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Database Tables

The application automatically creates:

- counsellor
- course
- enquiry

using Hibernate.

## Modules

### Counsellor Module

- Register
- Login
- Logout

### Dashboard Module

Displays enquiry statistics for the logged-in counsellor.

### Enquiry Module

- Add Enquiry
- View Enquiries
- Filter Enquiries
- Edit Enquiry

## Enquiry Filters

Users can filter enquiries by:

- Course
- Class Mode
- Enquiry Status

## Dashboard Statistics

The dashboard calculates:

- Total Enquiries
- Open Enquiries
- Enrolled Enquiries
- Lost Enquiries

## Running the Project

### Clone Repository

```bash
git clone https://github.com/your-username/counsellor-portal.git
```

### Navigate

```bash
cd counsellor-portal
```

### Run

```bash
mvn spring-boot:run
```

or run the main Spring Boot application from your IDE.

Visit

```
http://localhost:8080/
```

## Future Enhancements

- Delete Enquiry
- Search by Student Name
- Pagination
- Spring Security Authentication
- Email Notifications
- Password Encryption
- Export to Excel/PDF
- REST APIs
- Role Based Access

## Learning Outcomes

This project demonstrates:

- Spring Boot MVC Architecture
- CRUD Operations
- Spring Data JPA
- Entity Relationships
- Thymeleaf Templates
- Session Management
- DTO Pattern
- BeanUtils Property Mapping
- Dynamic Filtering using Query By Example
- Bootstrap UI Design

## Author

**Siddharth**

GitHub: https://github.com/gaikwad-siddharth

---

⭐ If you found this project useful, consider giving it a star on GitHub.
