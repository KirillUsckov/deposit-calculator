
# Deposit Calculator

Веб-приложение для расчета доходности банковского вклада с ежемесячной капитализацией процентов.

## Технологии

**Backend**
- Java 17
- Spring Boot 3
- Maven
- Lombok
- JUnit 5, Easy Random

**Frontend**
- React
- JavaScript
- CSS

## API

`POST /api/calculate` - эндпоинт для расчета доходности банковского вкалада с учетом ежемесечной капитализации

Пример запроса:

```json
{
  "amount": 12000,
  "months": 1,
  "rate": 12
}
```

Пример ответа:

```json
{
  "total": 12120.00,
  "profit": 120.00
}
```

Расчёт производится по формуле:

**S = P × (1 + R / 1200)^N**

Где:
- P - первоначальная сумма;
- R - годовая процентная ставка;
- N - срок вклада в месяцах;
- S - итоговая сумма.

## Запуск

### Backend

```bash
cd deposit-calculator-api
mvn spring-boot:run
```
API будет доступно по ссылке `http://localhost:4111`

### Frontend

```bash
cd deposit-calculator-ui
npm install
npm start
```

## Тестирование

Юнит-тесты сервиса расчёта реализованы с использованием JUnit 5 и Easy Random и покрывают необходимую функциональность

Запуск:

```bash
cd deposit-calculator-api
mvn test
```
