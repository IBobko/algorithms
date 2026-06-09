## 1. База: сложность и структуры данных

Сначала не задачи, а понимание, чем ты вообще управляешь.

**Нужно знать:**

* `Big O`: `O(1)`, `O(log n)`, `O(n)`, `O(n log n)`, `O(n²)`
* массивы / списки
* строки
* hash map / hash set
* stack / queue / deque
* linked list
* heap / priority queue
* tree / binary tree / BST
* graph
* trie
* disjoint set union

Цель: видеть не «задачу», а структуру:
**что тут хранится, как быстро ищется, как обновляется.**

---

## 2. Паттерны задач, а не отдельные задачи

Вот это ключевое. LeetCode надо учить не как «1000 задач», а как набор повторяющихся схем.

### Блок 1. Arrays / Strings

* two pointers
* sliding window
* prefix sum
* difference array
* sorting + scan
* intervals
* in-place modification

Примеры тем:

* найти пару / тройку
* убрать дубликаты
* максимум/минимум окна
* подмассив с условием
* объединение интервалов

---

### Блок 2. Hashing

* frequency map
* seen set
* grouping
* lookup за `O(1)`

Примеры:

* anagram groups
* two sum
* longest consecutive sequence
* first unique character

---

### Блок 3. Stack / Queue

* monotonic stack
* valid parentheses
* next greater element
* min stack
* BFS queue

Особенно важен **monotonic stack** — он часто выглядит магией, пока не увидишь паттерн.

---

### Блок 4. Binary Search

Не только «найти число в массиве», а:

* binary search on answer
* lower bound / upper bound
* search in rotated array
* capacity / minimum possible maximum
* first true / last false

Это один из самых важных блоков для собесов.

---

### Блок 5. Recursion / Backtracking

* subsets
* permutations
* combinations
* DFS через выбор
* pruning

Важно понять схему:

```text
choose
explore
undo
```

---

### Блок 6. Trees

* DFS preorder / inorder / postorder
* BFS level order
* recursion over tree
* path sum
* lowest common ancestor
* validate BST
* serialize / deserialize tree

Деревья — это место, где recursion становится нормальной, а не страшной.

---

### Блок 7. Graphs

* BFS
* DFS
* visited set
* connected components
* cycle detection
* topological sort
* shortest path
* Dijkstra
* Union-Find

Графы — это не отдельная структура. Это модель отношений. Очень мощный блок.

---

### Блок 8. Dynamic Programming

Вот тут нельзя начинать сразу с хардов.

Идти надо так:

1. recursion
2. recursion + memo
3. bottom-up DP
4. space optimization

Темы:

* Fibonacci-like
* climbing stairs
* house robber
* coin change
* longest increasing subsequence
* longest common subsequence
* knapsack
* grid DP
* interval DP

Главная формула DP:

```text
state
transition
base case
answer
```

---

## 3. Порядок прохождения

Я бы сделал так:

### Этап 1 — 2 недели

База:

* Big O
* arrays
* strings
* hash map
* stack
* queue
* sorting
* two pointers
* sliding window

Цель: выйти из состояния «я не понимаю, что вообще происходит».

---

### Этап 2 — 3 недели

Средний фундамент:

* binary search
* linked list
* trees
* recursion
* backtracking

Цель: начать видеть повторяющиеся формы задач.

---

### Этап 3 — 3 недели

Сильная середина:

* graphs
* BFS / DFS
* topological sort
* heap
* intervals
* monotonic stack
* union-find

Цель: закрыть основное тело interview-задач.

---

### Этап 4 — 4 недели

DP:

* memoization
* 1D DP
* 2D DP
* knapsack
* LIS
* LCS
* grid DP

Цель: перестать бояться DP и видеть его как таблицу смысловых состояний.

---

## 4. Как заниматься каждый день

Не «решать побольше», а так:

### Один день:

1. Берёшь один паттерн.
2. Читаешь короткое объяснение.
3. Разбираешь 2 простые задачи.
4. Решaешь 2–3 сам.
5. После решения выписываешь шаблон.

Например:

```text
Sliding window:
- есть левый и правый указатель
- расширяем окно
- если условие нарушено — двигаем левый
- обновляем ответ
```

Вот это и есть настоящее обучение.

---

## 5. Минимальный набор задач

Не надо сразу 500.

Нужно примерно:

* 30 easy
* 80 medium
* 20 hard

Но не подряд, а по темам.

То есть не:

```text
случайная задача → случайная задача → случайная задача
```

А:

```text
10 задач на sliding window
10 задач на binary search
10 задач на trees
10 задач на graphs
...
```

---

## 6. Главная программа

Я бы назвал её так:

## **Algorithmic Core**

### Модуль 1. Complexity and Data Structures

### Модуль 2. Arrays and Strings

### Модуль 3. Hashing

### Модуль 4. Two Pointers and Sliding Window

### Модуль 5. Stack, Queue, Monotonic Structures

### Модуль 6. Binary Search

### Модуль 7. Recursion and Backtracking

### Модуль 8. Linked Lists

### Модуль 9. Trees

### Модуль 10. Graphs

### Модуль 11. Heap and Priority Queue

### Модуль 12. Intervals

### Модуль 13. Union-Find

### Модуль 14. Dynamic Programming

### Модуль 15. Mixed Interview Practice

---

Самое важное: тебе нужно не «натаскиваться», а **восстановить алгоритмическую карту**. Тогда каждая задача перестанет быть случайной стеной и станет вариантом уже известной формы.
