# Task Management System

A full-stack web application for managing tasks with user authentication, real-time search, and status filtering capabilities.

## 🚀 Features

- **User Authentication**: Secure login and registration with Spring Security Basic Authentication
- **Task Management**: Create, read, update, and delete tasks with ease
- **Real-time Search**: Search tasks by title with instant results
- **Status Filtering**: Filter tasks by status (TODO, IN_PROGRESS, COMPLETED)
- **Responsive UI**: Modern, gradient-themed interface with smooth animations
- **Date Management**: Due date tracking for all tasks
- **Secure API**: RESTful endpoints with authentication and CORS configuration

## 🛠️ Technology Stack

### Frontend
- **Angular 18+** - Modern web framework with standalone components
- **TypeScript** - Type-safe JavaScript
- **RxJS** - Reactive programming with Observables
- **Angular Signals** - Efficient state management
- **Reactive Forms** - Form handling and validation
- **Custom CSS** - Gradient themes and responsive design

### Backend
- **Spring Boot 3.5.4** - Java framework for building REST APIs
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database persistence layer
- **Hibernate ORM** - Object-relational mapping
- **MariaDB** - Relational database
- **Maven** - Dependency management and build tool

## 📋 Prerequisites

Before running this project, ensure you have the following installed:

- **Node.js** (v18 or higher)
- **npm** (v9 or higher)
- **Angular CLI** (v18 or higher)
- **Java JDK** (v17 or higher)
- **Maven** (v3.8 or higher)
- **MariaDB** (v10.5 or higher)

## 🔧 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Seifehab11-8/Task-Management-System.git
cd Task-Management-System
```

### 2. Database Setup

1. Start MariaDB server
2. Create a database:
```sql
CREATE DATABASE task_management;
```

3. Update database credentials in `backend/Task_Management_System/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/task_management
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Backend Setup

```bash
cd backend/Task_Management_System
mvn clean install
mvn spring-boot:run
```

The backend server will start on `http://localhost:8080`

### 4. Frontend Setup

```bash
cd frontend/Task-Management-System
npm install
ng serve
```

The frontend application will be available at `http://localhost:4200`

## 📁 Project Structure

```
Task-Management-System/
├── backend/
│   └── Task_Management_System/
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/
│       │   │   │   └── com/orange/springtask/task_management_system/
│       │   │   │       ├── configuration/    # CORS and Security config
│       │   │   │       ├── controller/       # REST Controllers
│       │   │   │       ├── entities/         # JPA Entities
│       │   │   │       ├── repository/       # Data repositories
│       │   │   │       └── service/          # Business logic
│       │   │   └── resources/
│       │   │       └── application.properties
│       │   └── test/
│       └── pom.xml
├── frontend/
│   └── Task-Management-System/
│       ├── src/
│       │   └── app/
│       │       ├── create-task/      # Create task component
│       │       ├── dashboard/        # Task dashboard
│       │       ├── header/           # Navigation header
│       │       ├── home/             # Login page
│       │       ├── model/            # TypeScript models
│       │       ├── register/         # User registration
│       │       ├── view-task/        # Edit task component
│       │       ├── app.routes.ts     # Route configuration
│       │       └── task-service.ts   # HTTP service
│       ├── angular.json
│       └── package.json
└── README.md
```

## 🔐 API Endpoints

### Authentication
- **POST** `/api/users/register` - Register new user
- **GET** `/api/users/login` - User login (Basic Auth)

### Tasks
- **GET** `/tasks` - Get all tasks for authenticated user
- **GET** `/tasks/{id}` - Get task by ID
- **GET** `/tasks?status={status}` - Filter tasks by status
- **GET** `/tasks?title={title}` - Search tasks by title
- **POST** `/tasks` - Create new task
- **PUT** `/tasks/{id}` - Update existing task
- **DELETE** `/tasks/{id}` - Delete task

## 🎨 UI Features

- **Gradient Theme**: Purple gradient (#667eea to #764ba2) for modern look
- **Responsive Cards**: Task cards with hover effects and smooth transitions
- **Status Badges**: Color-coded status indicators (TODO, IN_PROGRESS, COMPLETED)
- **Search Bar**: Real-time search with SVG icons
- **Dropdown Filters**: Consistent styling across all form elements
- **Form Validation**: Client-side validation with error messages

## 🔒 Security Features

- **Basic Authentication**: Username/password authentication
- **Session Management**: Session storage for maintaining user state
- **CORS Configuration**: Configured for frontend-backend communication
- **Protected Routes**: Authentication guards on all routes
- **Password Encoding**: Secure password storage with BCrypt

## 📝 Usage

1. **Register**: Create a new account on the registration page
2. **Login**: Sign in with your credentials
3. **Dashboard**: View all your tasks with status filters and search
4. **Create Task**: Click "Create Task" to add a new task
5. **Edit Task**: Click on any task card to view and edit details
6. **Delete Task**: Remove tasks you no longer need
7. **Filter**: Use the dropdown to filter by status
8. **Search**: Type in the search bar to find tasks by title

## 🧪 Testing

### Backend Tests
```bash
cd backend/Task_Management_System
mvn test
```

### Frontend Tests
```bash
cd frontend/Task-Management-System
ng test
```

## 🚀 Deployment

### Backend Deployment
1. Build the JAR file:
```bash
mvn clean package
```
2. Run the JAR:
```bash
java -jar target/Task_Management_System-0.0.1-SNAPSHOT.jar
```

### Frontend Deployment
1. Build for production:
```bash
ng build --configuration production
```
2. Deploy the `dist/` folder to your hosting service

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Seif Ehab**
- GitHub: [@Seifehab11-8](https://github.com/Seifehab11-8)

**Built using Angular and Spring Boot**
