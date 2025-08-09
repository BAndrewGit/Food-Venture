# Food Venture

Food Venture is a full-stack web application for exploring, ordering, and managing restaurants and products. It provides separate interfaces and functionalities for regular users and administrators. The backend is built with Java and Spring Boot, while the frontend uses React with TypeScript, delivering a modern, scalable, and maintainable solution for a food delivery service.



## Technologies Used

### Backend
- Java 17 + Spring Boot: application framework  
- Spring Security + JWT: authentication and authorization  
- Spring Data JPA + Hibernate: ORM and database access  
- MySQL: relational database  
- Maven: build and dependency management  

### Frontend
- React 18 + TypeScript: frontend framework and language  
- React Router: client-side routing  
- Axios: HTTP client for API calls  
- CSS Modules / Global CSS: styling  
- Jest + React Testing Library: testing  



## Project Structure

### Backend (`Backend/src/main/java/com/fooddelivery/`)

- `FoodDeliveryApplication.java`: main Spring Boot application class.  
- `config/`: configuration classes for security (JWT, web security) and general app settings.  
- `controller/`: REST controllers handling HTTP requests for authentication, products, orders, and restaurants.  
- `service/`: business logic interfaces and implementations for products, orders, and users.  
- `repository/`: Spring Data JPA repositories for entities: Product, Order, User.  
- `entity/`: JPA entity classes modeling database tables: Product, Order, User, Restaurant.  
- `dto/`: Data Transfer Objects used for communication between layers and API requests/responses.  
- `mapper/`: classes responsible for mapping between entities and DTOs.  
- `domain/`: contains domain-specific service classes (e.g., `ProductDomainService.java`).  

### Frontend (`Frontend/src/`)

- `components/`: React UI components grouped by feature:  
  - `common/`: shared components like `Header`, `Footer`, and `Layout`.  
  - `auth/`: login and registration components.  
  - `product/`: product listing, cards, and detail views.  
  - `order/`: shopping cart, checkout process, and order history.  
- `services/`: modules for API communication encapsulating HTTP requests (e.g., `authService.ts`, `productService.ts`).  
- `types/`: TypeScript type definitions for models like Product, Order, and User.  
- `hooks/`: custom React hooks for authentication and API handling.  
- `context/`: React Context API for managing authentication state.  
- `utils/`: utility functions and constants.  
- `styles/`: global CSS styles.  
- `App.tsx`: main React component defining routes and app structure.  
- `index.tsx`: React app entry point.
