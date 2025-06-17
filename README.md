
# 🏨 Hotel Reservation Management System

This project is a **Spring Boot** based hotel reservation management system that allows customers to browse rooms, make bookings, and generate invoices, while allowing administrators to add new rooms.

## 📂 Project Structure

### Key Packages:
- `controllers`: Handles incoming requests and maps them to service methods.
- `services`: Business logic layer.
- `models`: Contains entity classes like `User`, `Room`, `Booking`, `Invoice`, etc.
- `dtos`: Data Transfer Objects for safe and structured communication.
- `repositories`: Interfaces for database interaction using JPA.

---

## ✅ Features

### 1. **Room Management**
- **Add Room** (Admin only):
  - Validates admin access before room creation.
  - Requires `name`, `price`, `roomType`, and `description`.
- **View Rooms**:
  - Allows customers to fetch rooms with or without filtering by `RoomType`.

### 2. **Booking Management**
- **Make Booking**:
  - Initiates a booking for a user.
  - If the user doesn't have an active session, a new session is created.
  - Accepts a map of room IDs to quantity.

- **Generate Invoice**:
  - Aggregates all bookings made in an active session.
  - Ends the session after invoice generation.
  - Calculates:
    - Room cost
    - GST (18%)
    - Service Charge (10%)

---

## 🛠 Tech Stack

| Layer         | Technology                  |
|---------------|-----------------------------|
| Backend       | Spring Boot (Java)          |
| Persistence   | Spring Data JPA             |
| Data          | MySQL / H2 (assumed)        |
| Build Tool    | Maven / Gradle (assumed)    |
| Others        | Lombok (optional), Swagger (optional) |

---

## 📌 Endpoints (Conceptual)

| Functionality       | Controller           | Method              |
|---------------------|----------------------|---------------------|
| Add Room            | `RoomController`     | `addRoom()`         |
| Get Rooms           | `RoomController`     | `getRooms()`        |
| Make Booking        | `BookingController`  | `makeBooking()`     |
| Generate Invoice    | `BookingController`  | `generateInvoice()` |

> All methods accept DTOs and return structured responses with status and data.

---

## 🔐 Roles

- `UserType.ADMIN`: Can add rooms.
- `UserType.CUSTOMER`: Can view rooms, make bookings, and request invoices.

---

## ⚠️ Exceptions Handled

- `UserNotFoundException`
- `CustomerSessionNotFoundException`
- `InvalidRoomException`
- `UnAuthorizedAccess`

---

## 🧪 Sample Booking Flow

1. Customer browses rooms by type.
2. Selects room(s) and quantity.
3. Submits booking request → booking is created.
4. Generates invoice → calculates total + taxes and ends session.

---

## 🧾 Invoice Breakdown

| Item             | Description                        |
|------------------|------------------------------------|
| Room Cost        | Price × Quantity per room          |
| GST (18%)        | Applied on total room cost         |
| Service Charge   | 10% additional service charge       |
| Final Amount     | Room Cost + GST + Service Charge   |

---

## 🔧 Setup Instructions

1. Clone the repo
2. Import into IDE (like IntelliJ or Eclipse)
3. Configure your `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hotel_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Run the Spring Boot application
5. Hit endpoints using Postman / Swagger

---

## 📄 Note

- DTOs and model classes are assumed to exist based on the code structure.
- Repository interfaces like `BookingRepository`, `RoomRepository`, and `UserRepository` are assumed to be standard Spring Data JPA interfaces.

---

## 👨‍💻 Author

Made with ❤️ by [Your Name]  
_If using for academic/project submission, ensure proper citations if reused._
