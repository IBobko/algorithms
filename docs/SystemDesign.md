По System Design тебе нужно знать не «всё подряд», а **каркас мышления**, по которому ты можешь разобрать почти любую систему.

Главное ядро:

**1. Требования**
Сначала выясняешь:
что система должна делать, для кого, какие основные сценарии, какие ограничения, сколько пользователей, сколько запросов, какая задержка допустима, нужна ли консистентность, что важнее — скорость, надёжность, стоимость или точность.

**2. Нагрузки**
Нужно уметь примерно считать:
requests per second, daily active users, размер данных, объём чтения/записи, storage growth, bandwidth.

Не идеально математически, а чтобы показать: ты не рисуешь архитектуру в пустоте.

**3. API**
REST/gRPC/WebSocket.
Нужно уметь предложить базовые endpoints:
создать объект, получить объект, список, обновить, удалить, выполнить действие.

**4. Data model**
Какие сущности есть в системе:
User, Project, File, Message, Order, Payment, Job, Event.
Связи между ними.
Что хранится в SQL, что в NoSQL, что в object storage.

**5. База данных**
Нужно понимать:
SQL vs NoSQL, индексы, транзакции, нормализация/денормализация, репликация, шардинг, eventual consistency.

Для интервью часто достаточно:
PostgreSQL как основная база,
Redis для кеша,
S3/object storage для файлов,
Kafka/RabbitMQ для асинхронных событий.

**6. Cache**
Где нужен кеш:
часто читаемые данные,
сессии,
профили,
ленты,
результаты тяжёлых вычислений.

Понимать:
cache aside, TTL, invalidation, stale data, Redis.

**7. Очереди и async**
Очень важный блок.
Когда операция долгая — не делать её синхронно.
Например: транскрибация, отправка email, генерация отчёта, обработка видео, ML-анализ.

Схема:
API принимает запрос → кладёт job в очередь → worker обрабатывает → статус хранится в базе → клиент polling/WebSocket получает результат.

Это прямо твоя зона с Антинаёбатором.

**8. Масштабирование**
Horizontal scaling:
несколько backend-инстансов за load balancer.

Нужно понимать:
stateless backend,
session storage outside app,
autoscaling,
database bottleneck.

**9. Надёжность**
Retries, idempotency, timeout, circuit breaker, dead letter queue, graceful degradation.

Особенно важно:
если клиент повторил запрос, система не должна создать 2 платежа, 2 заказа, 2 job без контроля.

**10. Consistency**
CAP theorem не как мантру, а практически:
где нужна строгая консистентность — платежи, баланс, права доступа.
где можно eventual consistency — лента, аналитика, уведомления, счётчики.

**11. Security**
Authentication, authorization, JWT/session, OAuth, RBAC/ABAC, rate limiting, encryption, audit logs.

Для senior/lead роли важно говорить не только «логин», а:
кто имеет право видеть/изменять объект, как это проверяется на уровне API и данных.

**12. Observability**
Logs, metrics, traces, alerts.
Нужно уметь сказать:
как понять, что система сломалась;
как найти bottleneck;
как расследовать проблему конкретного пользователя.

**13. Deployment**
Docker, Kubernetes, CI/CD, rolling deploy, blue-green/canary, config/secrets.

Не обязательно углубляться в DevOps, но архитектурно понимать надо.

**14. Типовые задачи**
Надо уметь разобрать хотя бы такие системы:

* URL shortener
* file storage / Google Drive-lite
* notification system
* chat / messenger
* news feed
* rate limiter
* job processing system
* video upload/processing
* payment/order system
* search/autocomplete
* logging/analytics pipeline

**15. Шаблон ответа на интервью**

Идёшь так:

1. Уточняю требования.
2. Оцениваю нагрузку.
3. Рисую high-level architecture.
4. Определяю API.
5. Определяю data model.
6. Выбираю storage.
7. Добавляю cache/queue.
8. Обсуждаю scaling.
9. Обсуждаю failure cases.
10. Обсуждаю security/monitoring.
11. Указываю trade-offs.

Самое важное: System Design — это не экзамен на знание модных слов. Это проверка, видишь ли ты **систему как живую причинную структуру**: где вход, где состояние, где узкое место, где сбой, где восстановление, где человек получает результат.
