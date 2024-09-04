# 外送訂單系統

這是一個基於 Spring Boot 的外送訂單管理系統。系統包含多個實體類和對應的控制器，用於管理顧客、外送員、餐廳、訂單等信息。

## 主要功能
- 顧客管理
- 外送員管理
- 餐廳管理
- 訂單管理
- 訂單項目管理
- 菜單管理
- 餐點管理
- 評價管理

## 技術棧
- Spring Boot
- Spring Data JPA
- H2 數據庫
- Thymeleaf (用於 HTML 模板)

## 項目結構

```text
src/main/java/com/example/_20240903ordersys01/
├── controller/
│   ├── CustomerController.java
│   ├── DeliveryPersonnelController.java
│   ├── RestaurantController.java
│   ├── OrderController.java
│   ├── OrderItemController.java
│   ├── MenuController.java
│   ├── FoodItemController.java
│   └── ReviewController.java
├── model/
│   ├── Customer.java
│   ├── DeliveryPersonnel.java
│   ├── Restaurant.java
│   ├── Order.java
│   ├── OrderItem.java
│   ├── Menu.java
│   ├── FoodItem.java
│   └── Review.java
├── service/
│   ├── CustomerService.java
│   ├── DeliveryPersonnelService.java
│   ├── RestaurantService.java
│   ├── OrderService.java
│   ├── OrderItemService.java
│   ├── MenuService.java
│   ├── FoodItemService.java
│   └── ReviewService.java
└── Application.java

```
# 數據庫設計

系統使用 H2 內存數據庫，包含以下表:

- `customers`: 顧客信息
- `delivery_personnel`: 外送員信息
- `restaurants`: 餐廳信息
- `orders`: 訂單信息
- `order_items`: 訂單項目信息
- `menus`: 菜單信息
- `food_items`: 餐點信息
- `reviews`: 評價信息

## API 端點

每個實體都有對應的 CRUD 操作 API 端點，例如：

- `GET /api/{entity}`: 獲取所有實體
- `GET /api/{entity}/{id}`: 獲取特定實體
- `POST /api/{entity}`: 創建新實體
- `PUT /api/{entity}/{id}`: 更新特定實體
- `DELETE /api/{entity}/{id}`: 刪除特定實體

其中 `{entity}` 可以是 `customers`、`delivery-personnel`、`restaurants`、`orders`、`order-items`、`menus`、`food-items`、或 `reviews`。

## 配置

主要配置在 `application.properties` 文件中：

```properties
spring.datasource.url=jdbc:h2:file:./data/datalist
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

# 運行項目

1. 確保已安裝 Java 和 Maven。
2. 克隆項目到本地。
3. 在項目根目錄運行: `mvn spring-boot:run`。
4. 訪問 [http://localhost:8080](http://localhost:8080) 查看應用。

## 注意事項

- 確保 `data` 目錄中存在 `datalist.mv.db` 文件，或讓系統自動創建。
- 可以通過 [http://localhost:8080/h2-console](http://localhost:8080/h2-console) 訪問 H2 控制台。
- 初始數據可以通過 `data.sql` 文件或使用程序代碼插入。

