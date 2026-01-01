# 🌦 Weather Search Application

A full-stack **Weather Search Application** built using **Java Spring Boot** that allows users to search for **current
weather information by city name**.  
The application integrates with the **OpenWeatherMap API**, implements **caching for performance**, and provides a 
**simple web-based UI** to display weather details.
---

## 🚀 Features

- Search current weather by city name
- REST ful backend API built with Spring Boot
- Integration with OpenWeatherMap external API for realtime weather update
- In-memory caching for faster repeated requests Using HashMap
- Proper handling of error scenarios (invalid city, API failure, etc.)
- Simple and clean UI using HTML, CSS, and JavaScript
- Display of detailed weather attributes
- Option to view raw API response get from OpenWeatherMap

---

## 🛠 Tech Stack

### Backend

- Java 17+
- Spring Boot
- Spring Web (REST APIs)
- RestTemplate
- Jackson (`JsonNode`)
- Lombok
- Maven

### Frontend

- HTML
- CSS
- JavaScript (Fetch API)

---

Perfect 👍
Below is a **clean, focused README section** that explains **ONLY how to run the project on a local machine**, written
in **simple, reviewer-friendly language**.

You can **paste this directly** into your existing `README.md` under a heading like **“How to Run Locally”**.

---

## ▶️ How to Run the Application Locally

### ✅ Prerequisites

Make sure the following are installed on your system:

* **Java 17 or higher**
* **Maven**
* **Internet connection** (to access OpenWeather API)

You can verify installation using:

```bash
java -version
mvn -version
```

---

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Shevadesuyash/weather-search-app-Fin-Factor.git
cd weather-search-app
```

---

### 2️⃣ Configure OpenWeather API Key

Open the file:

```
src/main/resources/application.yaml
```

Update the API key value:

```yaml
weather:
  api:
    base-url: http://api.openweathermap.org/data/2.5/weather
    key: YOUR_OPENWEATHER_API_KEY
    units: metric
```

> 🔑 You can generate a free API key from:
> [https://openweathermap.org/](https://openweathermap.org/)

---

### 3️⃣ Build and Run the Application

Run the following command from the project root directory:

```bash
mvn spring-boot:run
```

Spring Boot will start the application on **port 8080**.

---

### 4️⃣ Access the Application

* **Web UI**
  Open a browser and go to:

  ```
  http://localhost:8080
  ```

* **Backend API (Direct Test)**

  ```
  http://localhost:8080/api/weather?city=Pune
  ```

---

Response:

* Returns current weather details for the given city
* Includes temperature, humidity, wind speed, cloudiness, visibility, and raw API response

---

