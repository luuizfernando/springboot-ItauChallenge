# Springboot - ItauChallenge

This code is a resolution of the challenge proposed by Itaú for a Backend vacancy built with **Springboot**.

---

## 🌐 Endpoints
- **POST** `/transacao`: Create a transaction.
- **DELETE** `/transacao`: Delete all transactions.
- **GET** `/estatistica`: Get the statistics of all transactions.

---

## 🛠️ Technologies
- **Java**
- **Springboot**
- **Jpa**
- **H2**
- **Swagger**
- **JUnit**
- **Mockito**
- **MockMvc**
- **Docker**

---

## 🚀 Running the Project with Docker

Follow the steps below to run the application in a containerized form.

### Prerequisites
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Execution

1. Clone this repository:
   ```bash
   git clone https://github.com/luuizfernando/springboot-ItauChallenge.git
   ```
2. Navigate to the project root:
   ```bash
   cd springboot-ItauChallenge
   ```
3. Run Docker Compose:
   ```bash
   docker-compose up --build
   ```
The `--build` command ensures that the Docker image will be built the first time or if there is any change in the code. After execution, the application will be ready to use.

---

## 📖 Accessing the Application

- **Swagger UI:** `http://localhost:8081/swagger-ui.html`

---

## 👤 Developer

Developed by [Luiz Fernando](https://www.linkedin.com/in/luizfernando-dalpra/). Feel free to get in touch and contribute to the project!

---

## 📫 Contact

- **Email:** [luizfernandosant26@gmail.com](mailto:luizfernandosant26@gmail.com)
- **LinkedIn:** [linkedin.com/in/luiz-fernando-dalpra](https://linkedin.com/in/luiz-fernando-dalpra)