# 🌿 SATTVA — Fitness NGO Web Platform

<div align="center">

![SATTVA Banner](https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=1200&h=300&fit=crop)

**A full-stack web platform for SATTVA — a fitness-based social organization promoting personal fitness awareness and supporting underprivileged athletes across India.**

[![Live Website](https://img.shields.io/badge/🌐_Live_Website-sattvango.com-2D6A4F?style=for-the-badge)](https://sattvango.com)
[![Frontend](https://img.shields.io/badge/Frontend-Vercel-black?style=for-the-badge&logo=vercel)](https://vercel.com)
[![Backend](https://img.shields.io/badge/Backend-Render-46E3B7?style=for-the-badge&logo=render)](https://render.com)
[![Database](https://img.shields.io/badge/Database-MongoDB_Atlas-47A248?style=for-the-badge&logo=mongodb)](https://mongodb.com/atlas)

</div>

---

## 📋 Table of Contents

- [About SATTVA](#-about-sattva)
- [Live Demo](#-live-demo)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [Environment Variables](#-environment-variables)
- [API Documentation](#-api-documentation)
- [Deployment](#-deployment)
- [Screenshots](#-screenshots)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🏃 About SATTVA

**SATTVA** (Sanskrit for *purity* and *goodness*) is a fitness-based NGO that:

- 🏅 Promotes personal fitness awareness in communities
- 🗓️ Organizes events: Marathons, Workout Sessions, Basketball & Kickboxing
- 💛 Uses donations to support underprivileged athletes
- 🌍 Currently active across Punjab, India

> *"Fitness is not a privilege — it's a right for everyone."*

---

## 🌐 Live Demo

| Service | URL |
|---|---|
| 🖥️ Frontend (Website) | https://sattvango.com |
| ⚙️ Backend API | https://api.sattvango.com/api |
| 📊 API Health Check | https://api.sattvango.com/api/events |

---

## ✨ Features

### 👤 Public Features
- **Homepage** — Hero section, mission statement, stats, activities showcase
- **Events Page** — Dynamic event listing fetched from database with images, dates, locations
- **Event Registration** — Users register for events using their email
- **Donation System** — Razorpay payment integration with preset and custom amounts
- **Contact Page** — Contact form with email delivery
- **Fully Responsive** — Works on mobile, tablet, and desktop

### 🔐 Admin Features
- **Secure Login** — JWT-based authentication
- **Event Management** — Add, update, delete events
- **Image Upload** — Cloudinary-powered permanent image storage
- **Dashboard** — Overview of all events with stats

---

## 🧱 Tech Stack

### Frontend
| Technology | Purpose |
|---|---|
| **React 18** + Vite | UI framework & build tool |
| **Tailwind CSS** | Styling & responsive design |
| **React Router v6** | Client-side navigation |
| **Axios** | HTTP requests to backend |
| **React Hot Toast** | User notifications |
| **React Icons** | Icon library |

### Backend
| Technology | Purpose |
|---|---|
| **Java 17** | Programming language |
| **Spring Boot 3** | Backend framework |
| **Spring Security** | Authentication & authorization |
| **JWT (jjwt)** | Token-based auth |
| **Spring Data MongoDB** | Database ORM |
| **Cloudinary SDK** | Image upload & storage |
| **Razorpay Java SDK** | Payment processing |

### Infrastructure
| Service | Purpose |
|---|---|
| **MongoDB Atlas** | Cloud database (free tier) |
| **Cloudinary** | Image storage (free tier) |
| **Vercel** | Frontend hosting |
| **Render** | Backend hosting |
| **GoDaddy** | Domain registrar |

---

## 📁 Project Structure

```
sattva-website/
│
├── sattva-frontend/                    # React Application
│   ├── public/
│   ├── src/
│   │   ├── pages/
│   │   │   ├── Home.jsx               # Landing page
│   │   │   ├── Events.jsx             # Events listing + registration modal
│   │   │   ├── Donate.jsx             # Razorpay donation page
│   │   │   ├── Contact.jsx            # Contact form
│   │   │   ├── AdminLogin.jsx         # Admin authentication
│   │   │   └── AdminDashboard.jsx     # Event management panel
│   │   ├── components/
│   │   │   └── common/
│   │   │       ├── Navbar.jsx         # Responsive navigation bar
│   │   │       ├── Footer.jsx         # Footer with social links
│   │   │       └── ProtectedRoute.jsx # Auth guard for admin pages
│   │   ├── context/
│   │   │   └── AuthContext.jsx        # Global auth state (JWT)
│   │   ├── services/
│   │   │   └── api.js                 # All Axios API calls
│   │   ├── utils/
│   │   │   └── keepAlive.js           # Prevents Render free tier sleep
│   │   ├── App.jsx                    # Root component with routing
│   │   ├── main.jsx                   # Entry point
│   │   └── index.css                  # Global styles + Tailwind
│   ├── .env                           # Environment variables (not committed)
│   ├── tailwind.config.js
│   ├── vite.config.js
│   └── package.json
│
└── sattva-backend/                     # Spring Boot Application
    └── src/main/java/com/sattva/
        ├── config/
        │   ├── SecurityConfig.java    # Spring Security + CORS config
        │   ├── CloudinaryConfig.java  # Cloudinary bean setup
        │   └── WebConfig.java         # Static resource handler
        ├── controller/
        │   ├── EventController.java   # /api/events endpoints
        │   ├── AuthController.java    # /api/auth endpoints
        │   ├── PaymentController.java # /api/payments endpoints
        │   └── ContactController.java # /api/contact endpoint
        ├── model/
        │   ├── Event.java             # Event MongoDB document
        │   ├── EventRegistration.java # Registration document
        │   ├── Donation.java          # Donation document
        │   └── ContactMessage.java    # Contact message document
        ├── repository/                # MongoDB repositories (auto-CRUD)
        ├── service/
        │   ├── EventService.java      # Event business logic
        │   ├── AuthService.java       # JWT authentication logic
        │   ├── PaymentService.java    # Razorpay order & verification
        │   └── CloudinaryService.java # Image upload logic
        ├── security/
        │   ├── JwtUtil.java           # JWT generate/validate/extract
        │   └── JwtFilter.java         # Per-request JWT interceptor
        └── resources/
            └── application.properties # App configuration
```

---

## 🚀 Getting Started

### Prerequisites

Make sure you have these installed:

```bash
node --version    # v18+ required
java --version    # Java 17+ required
mvn --version     # Maven 3.8+ required
```

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/sattva-website.git
cd sattva-website
```

### 2. Setup the Backend

```bash
cd sattva-backend

# Install dependencies and build
mvn clean install

# Run the Spring Boot server
mvn spring-boot:run
```

Backend starts at: `http://localhost:8080`

### 3. Setup the Frontend

```bash
cd sattva-frontend

# Install dependencies
npm install

# Start development server
npm run dev
```

Frontend starts at: `http://localhost:5173`

---

## 🔐 Environment Variables

### Frontend — create `sattva-frontend/.env`

```env
VITE_API_URL=http://localhost:8080/api
VITE_RAZORPAY_KEY_ID=your_razorpay_key_id
```

### Backend — edit `src/main/resources/application.properties`

```properties
# Server
server.port=8080

# MongoDB Atlas
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/sattva_db?retryWrites=true&w=majority

# JWT
jwt.secret=YourLongSecretKeyHere
jwt.expiration=86400000

# File Upload
spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
file.upload-dir=./uploads

# Cloudinary
cloudinary.cloud-name=your_cloud_name
cloudinary.api-key=your_api_key
cloudinary.api-secret=your_api_secret

# Razorpay
razorpay.key.id=your_razorpay_key_id
razorpay.key.secret=your_razorpay_key_secret

# Admin credentials
admin.username=admin
admin.password=your_secure_password

# CORS
cors.allowed-origins=http://localhost:5173
```

> ⚠️ **Never commit real credentials to GitHub.** Add `.env` and `application.properties` to `.gitignore`.

---

## 📡 API Documentation

### Authentication

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/api/auth/login` | Admin login, returns JWT | ❌ |

**Request body:**
```json
{
  "username": "admin",
  "password": "your_password"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### Events

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `GET` | `/api/events` | Get all events | ❌ |
| `GET` | `/api/events/:id` | Get single event | ❌ |
| `POST` | `/api/events` | Create new event | ✅ Admin |
| `PUT` | `/api/events/:id` | Update event | ✅ Admin |
| `DELETE` | `/api/events/:id` | Delete event | ✅ Admin |
| `POST` | `/api/events/:id/register` | Register for event | ❌ |

**Create Event (multipart/form-data):**
```
name        = "SATTVA City Marathon"
date        = "2025-06-01"
location    = "Jalandhar, Punjab"
description = "A city-wide marathon..."
category    = "Marathon"
image       = [file upload]
```

---

### Payments (Razorpay)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/api/payments/create-order` | Create Razorpay order | ❌ |
| `POST` | `/api/payments/verify` | Verify payment signature | ❌ |

**Create Order:**
```json
{ "amount": 1000 }
```

**Verify Payment:**
```json
{
  "razorpay_order_id": "order_xxx",
  "razorpay_payment_id": "pay_xxx",
  "razorpay_signature": "signature_xxx",
  "donorName": "Rahul Sharma",
  "donorEmail": "rahul@example.com"
}
```

---

### Contact

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/api/contact` | Send contact message | ❌ |

```json
{
  "name": "Priya Singh",
  "email": "priya@example.com",
  "subject": "Volunteering",
  "message": "I'd like to volunteer..."
}
```

---

## 🌐 Deployment

### Deploy Backend → Render

```bash
cd sattva-backend
git init
git add .
git commit -m "Initial backend"
git remote add origin https://github.com/yourusername/sattva-backend.git
git push -u origin main
```

On [Render](https://render.com):
- **Build Command:** `mvn clean package -DskipTests`
- **Start Command:** `java -jar target/sattva-backend-0.0.1-SNAPSHOT.jar`
- Add all environment variables in the **Environment** tab

### Deploy Frontend → Vercel

```bash
cd sattva-frontend
git init
git add .
git commit -m "Initial frontend"
git remote add origin https://github.com/yourusername/sattva-frontend.git
git push -u origin main
```

On [Vercel](https://vercel.com):
- Import the GitHub repo
- Framework: **Vite**
- Add environment variables: `VITE_API_URL`, `VITE_RAZORPAY_KEY_ID`

### Connect Custom Domain (GoDaddy)

Add these DNS records in GoDaddy:

| Type | Name | Value |
|---|---|---|
| `A` | `@` | `76.76.21.21` (Vercel IP) |
| `CNAME` | `www` | `cname.vercel-dns.com` |
| `CNAME` | `api` | `sattva-backend-xxxx.onrender.com` |

---

## 🔒 Security

- All admin routes protected by **JWT Bearer token** authentication
- Tokens expire after **24 hours**
- Razorpay payments verified using **HMAC-SHA256 signature**
- **CORS** restricted to allowed origins only
- Passwords stored securely (not in version control)
- Input validation on all API endpoints

---

## 📱 Instagram

Follow SATTVA on Instagram: [@sattva.foundation.26](https://www.instagram.com/sattva.foundation.26)

---

## 🙌 Built With ❤️ By

**SATTVA Foundation Team**
- 🌐 Website: [sattvango.com](https://sattvango.com)
- 📸 Instagram: [@sattva.foundation.26](https://www.instagram.com/sattva.foundation.26)
- 📧 Email: info@sattvango.com

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

<div align="center">

Made with ❤️ for a fitter, healthier India 🇮🇳

**SATTVA — Fitness For All • Unity Through Sport**

</div>
