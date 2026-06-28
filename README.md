# 📊 Spring Boot Excel Import System

A Spring Boot application that imports supply data from an Excel file into an H2 database. The system automatically creates supply records and updates product inventory based on the imported Excel data.

## 🚀 Features

* Import Excel (.xlsx) files
* Automatically create supply records
* Update product stock quantity after each import
* RESTful API
* H2 in-memory/file database
* Data validation during import
* Exception handling for invalid data

## 🛠️ Technologies

* Java  23
* Spring Boot 4
* Spring Data JPA (Hibernate)
* H2 Database
* Apache POI
* Maven

## 📁 Project Structure

```
src
├── controller
├── service
├── repository
├── model
├── dto
├── exception
├── config
└── resources
```

## 📦 Database

This project uses **H2 Database**.

Open the H2 Console:

```
http://localhost:8080/h2-console
```

Example configuration:

```
JDBC URL: jdbc:h2:file:./data/import-excel
Username: sa
Password:
```

## 📑 Excel Format

The Excel file should follow this format:

| Product | Supplier | Qty | Buy Price | Supply Date |
|---------|----------|---- |-----------|-------------|
| Phone   | TK       | 10  | 5.50      | 25-06-26    |




> The product and supplier name must be already exist in the database before importing the Excel file.
> If they don't exist it will show status and message


### Import Excel

```
POST /api/v1/supply/import-excel
```

**Request**

* Content-Type: `multipart/form-data`

Parameter:

```
file
```

Example using Postman:

```
POST http://localhost:8080/api/v1/supply/import-excel
```

Body → form-data

```
file : inventory.xlsx
```

## ▶️ Running the Project

Clone the repository:

```bash
git clone https://github.com/your-username/import-excel.git
```

Navigate to the project:

```bash
cd import-excel
```

Run the application:

```bash
mvn spring-boot:run
```

Or run directly from IntelliJ IDEA.


