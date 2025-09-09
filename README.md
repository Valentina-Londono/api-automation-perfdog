# API Automation - PerfDog * . +

This project demonstrates **API test automation** using **Rest Assured**, **TestNG**, and **Maven**.
The purpose is to validate several functionalities of the [Swagger Petstore API](https://petstore.swagger.io/), following the requested requirements.

---

## Functionalities Tested

The following API features are automated:

1. **Create a user**
2. **Login** with a newly created user
3. **List pets** that have the status `"available"`
4. **Get details** of a specific pet
5. **Create a purchase order** for a pet
6. **Logout** from the application

Each functionality has its **own independent test class**. Tests are not chained, so they can be executed separately.

---

## 🛠️ Tech Stack

* **Java 7+**
* **Maven** (dependency management & build)
* **Rest Assured** (API testing)
* **TestNG** (test framework)
* **Jackson** (JSON serialization/deserialization)

---

## ▶️ How to Run the Tests

1. **Clone the repository**

```bash
git clone [https://github.com/your-username/api-automation-perfdog.git](https://github.com/Valentina-Londono/api-automation-perfdog.git)
cd api-automation-perfdog
```

2. **Install dependencies**

```bash
mvn clean install
```

3. **Run all tests**

```bash
mvn test
```

---

##  Author

** . + * Valentina Londoño Dueñas + . ´ **
*API Test Automation - PerfDog Project*

