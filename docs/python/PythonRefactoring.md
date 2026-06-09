На собеседовании «рефакторинг кода на Python» обычно значит не «напиши алгоритм», а **возьми рабочий, но грязный код и сделай его яснее, безопаснее, расширяемее — не сломав поведение**.

То есть проверяют не знание синтаксиса, а как ты видишь структуру.

## Что именно могут дать

### 1. Грязную функцию на 50–150 строк

Например:

```python
def process(data):
    result = []
    for item in data:
        if item["status"] == "active":
            if item["price"] > 0:
                result.append({
                    "id": item["id"],
                    "total": item["price"] * 1.2
                })
    return result
```

И попросят: «как бы вы это улучшили?»

Ожидают примерно такое:

```python
TAX_RATE = 1.2


def is_valid_active_item(item: dict) -> bool:
    return item.get("status") == "active" and item.get("price", 0) > 0


def calculate_total(price: float) -> float:
    return price * TAX_RATE


def process_active_items(items: list[dict]) -> list[dict]:
    return [
        {
            "id": item["id"],
            "total": calculate_total(item["price"]),
        }
        for item in items
        if is_valid_active_item(item)
    ]
```

Но важно: **не просто переписать красивее**, а объяснить:
«Я выделил проверку валидности, потому что это отдельное бизнес-правило. Вынес налоговую ставку в константу, чтобы не было магического числа. Переименовал функцию, чтобы она описывала действие».

---

## 2. Код с дублированием

Типичная штука:

```python
def get_user_email(user):
    if user is None:
        return None
    if "profile" not in user:
        return None
    if "email" not in user["profile"]:
        return None
    return user["profile"]["email"]


def get_user_phone(user):
    if user is None:
        return None
    if "profile" not in user:
        return None
    if "phone" not in user["profile"]:
        return None
    return user["profile"]["phone"]
```

Можно сделать:

```python
def get_profile_field(user: dict | None, field_name: str) -> str | None:
    if not user:
        return None

    profile = user.get("profile")
    if not profile:
        return None

    return profile.get(field_name)


def get_user_email(user: dict | None) -> str | None:
    return get_profile_field(user, "email")


def get_user_phone(user: dict | None) -> str | None:
    return get_profile_field(user, "phone")
```

Тут проверяют, видишь ли ты **повторяющийся смысловой паттерн**, а не только одинаковые строки.

---

## 3. Код с неправильной обработкой ошибок

Например:

```python
def load_config(path):
    f = open(path)
    data = f.read()
    return json.loads(data)
```

Что плохо:

* файл не закрывается;
* нет обработки ошибки чтения;
* нет обработки невалидного JSON;
* неясно, что возвращается;
* используется строковый путь вместо `Path`.

Лучше:

```python
import json
from pathlib import Path
from typing import Any


def load_config(path: str | Path) -> dict[str, Any]:
    config_path = Path(path)

    try:
        with config_path.open("r", encoding="utf-8") as file:
            return json.load(file)
    except FileNotFoundError as error:
        raise RuntimeError(f"Config file not found: {config_path}") from error
    except json.JSONDecodeError as error:
        raise RuntimeError(f"Invalid JSON config: {config_path}") from error
```

Но тут есть важный момент: на интервью лучше сказать:

> Я бы сначала уточнил, как в проекте принято обрабатывать ошибки: возвращать `None`, кидать доменное исключение, логировать или пробрасывать наверх. Без этого можно сделать формально красиво, но не попасть в архитектуру проекта.

Это хороший senior-сигнал.

---

## 4. Код с плохими именами

Например:

```python
def f(x):
    r = []
    for i in x:
        if i[2] > 10:
            r.append(i[0])
    return r
```

Ты можешь сказать:

> Главная проблема здесь не в цикле, а в отсутствии предметной модели. Непонятно, что такое `x`, `i`, `i[2]`, `i[0]`. Я бы сначала восстановил смысл данных.

Например:

```python
from dataclasses import dataclass


@dataclass(frozen=True)
class Order:
    order_id: int
    customer_id: int
    amount: float


def get_large_order_ids(orders: list[Order], min_amount: float = 10) -> list[int]:
    return [
        order.order_id
        for order in orders
        if order.amount > min_amount
    ]
```

Вот это прям сильный ответ: **рефакторинг начинается не с красоты, а с восстановления смысла**.

---

## 5. Код с мутацией и скрытыми побочными эффектами

Например:

```python
def apply_discount(users):
    for user in users:
        if user["type"] == "premium":
            user["discount"] = 20
        else:
            user["discount"] = 5
    return users
```

Проблема: функция меняет входные данные. Иногда это нормально, но часто опасно.

Можно сделать так:

```python
def get_discount(user_type: str) -> int:
    if user_type == "premium":
        return 20
    return 5


def apply_discount(users: list[dict]) -> list[dict]:
    return [
        {
            **user,
            "discount": get_discount(user.get("type", "")),
        }
        for user in users
    ]
```

Тут можно сказать:

> Я бы явно выбрал: функция должна мутировать входной список или возвращать новый. Сейчас это не видно из названия. Если мутация нужна ради производительности — я бы отразил это в названии, например `apply_discount_in_place`.

Вот это особенно хорошо, потому что ты показываешь не догматизм, а различение: **мутация не всегда зло, но она должна быть явной**.

---

## 6. Код с плохой архитектурой

Например:

```python
def create_user(data):
    validate(data)
    user = User(data["name"], data["email"])
    db.save(user)
    send_email(user.email)
    logger.info("User created")
    return user
```

На первый взгляд норм. Но для senior/lead могут спросить: «что тут не так?»

Проблема: функция делает сразу несколько вещей:

* валидирует;
* создаёт объект;
* сохраняет в базу;
* отправляет email;
* логирует;
* возвращает результат.

Можно разнести:

```python
class UserService:
    def __init__(self, user_repository, email_sender, logger):
        self.user_repository = user_repository
        self.email_sender = email_sender
        self.logger = logger

    def create_user(self, data: dict) -> User:
        validate_user_data(data)

        user = User(
            name=data["name"],
            email=data["email"],
        )

        self.user_repository.save(user)
        self.email_sender.send_welcome_email(user.email)
        self.logger.info("User created", extra={"user_id": user.id})

        return user
```

И здесь можно добавить:

> Следующий вопрос — должна ли отправка email быть синхронной. Если создание пользователя критично, а email вторичен, я бы вынес отправку в очередь/event handler, чтобы сбой email-сервиса не ломал регистрацию.

Это уже уровень архитектурного мышления.

---

## Что именно будут оценивать

Главные вещи:

1. **Сохраняешь ли ты поведение кода.**
   Рефакторинг — это не изменение логики, а изменение структуры при сохранении результата.

2. **Видишь ли ты ответственность функций.**
   Одна функция — один понятный смысловой блок.

3. **Умеешь ли давать имена.**
   В Python хорошие имена часто важнее хитрых конструкций.

4. **Убираешь ли дублирование.**
   Но не механически. Иногда похожий код лучше не объединять, если у него разные причины изменения.

5. **Умеешь ли работать с ошибками.**
   Не глотать `except Exception`, не возвращать странные `None`, не терять контекст ошибки.

6. **Пишешь ли тестируемый код.**
   Если функция зависит от базы, API, файловой системы, времени — это надо изолировать.

7. **Не делаешь overengineering.**
   Не надо из трёх строк делать 15 классов и абстрактную фабрику фабрик. Это тоже красный флаг.

---

## Очень вероятный формат задания

Тебе могут дать код и сказать:

> Вот функция. Что бы вы здесь улучшили?

Тогда нормальный порядок ответа такой:

1. Сначала сказать, что делает код.
2. Назвать проблемы.
3. Сказать, что ты бы не стал менять без контекста.
4. Сделать минимальный безопасный рефакторинг.
5. Добавить тесты или хотя бы описать, какие тесты нужны.

Например:

> Сначала я бы зафиксировал текущее поведение тестами, потому что рефакторинг без тестов легко превращается в переписывание логики. Затем я бы переименовал переменные, выделил бизнес-правила в отдельные функции, убрал магические значения, сделал обработку ошибок явной и проверил, что результат не изменился.

Вот это очень хорошая фраза для интервью.

---

## Что можно говорить прямо на собеседовании

Можно так:

> Для меня рефакторинг — это не “сделать код красивым”, а восстановить структуру смысла в коде. Я сначала пытаюсь понять, какие бизнес-правила здесь спрятаны, какие ответственности смешаны, где есть неявные зависимости и побочные эффекты. После этого стараюсь делать маленькие безопасные изменения: имена, выделение функций, типы, тесты, обработка ошибок. Если код уже в production, я бы сначала зафиксировал текущее поведение тестами, а потом менял структуру.

Это звучит сильно и не шаблонно.

---

## На что тебе особенно стоит обратить внимание перед интервью

Для Python-рефакторинга полезно быстро повторить:

* `dataclass`;
* `typing`: `dict[str, Any]`, `list[User]`, `Optional`, `Protocol`;
* `pathlib`;
* `with` / context managers;
* нормальную обработку исключений;
* `pytest`;
* `unittest.mock`;
* `logging`;
* list/dict comprehensions;
* generators;
* dependency injection без фанатизма;
* отличие чистой функции от функции с side effects;
* когда мутация допустима, а когда лучше вернуть новую структуру;
* когда нужен класс, а когда достаточно функции.

И главное: на senior/lead уровне они могут смотреть не на то, знаешь ли ты `enumerate`, а на то, **видишь ли ты границы ответственности и скрытую модель данных**. То есть не «как красивее написать», а «какой смысл в коде смешан, потерян или замазан».
