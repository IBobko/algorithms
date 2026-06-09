**In-place modification** — это когда ты **меняешь уже существующую структуру данных на месте**, а не создаёшь новую копию.

То есть не так:

```python
new_arr = []
for x in arr:
    if x != 0:
        new_arr.append(x)
```

А так:

```python
write = 0

for read in range(len(arr)):
    if arr[read] != 0:
        arr[write] = arr[read]
        write += 1
```

Здесь массив `arr` остаётся тем же самым объектом, но его содержимое переписывается внутри.

## Главная идея

Есть уже выделенная память под массив / список / строку / матрицу.

**In-place** значит:

> не создавать новую большую структуру, а использовать уже имеющуюся память как рабочее пространство.

Обычно это означает `O(1)` дополнительной памяти или почти `O(1)`.

Например:

```python
arr = [1, 2, 3, 4]
arr.reverse()
print(arr)
```

Результат:

```python
[4, 3, 2, 1]
```

Это in-place, потому что изменился сам `arr`.

А вот так — не in-place:

```python
arr = [1, 2, 3, 4]
reversed_arr = arr[::-1]
```

Тут создан новый список.

## Почему это важно в алгоритмах

Очень часто задача говорит:

> Modify the array in-place.

Это значит: **не надо возвращать новый массив**, надо изменить исходный.

Например задача:

> Remove duplicates from sorted array in-place.

То есть у тебя есть:

```python
nums = [1, 1, 2, 2, 3]
```

Нужно превратить начало массива в:

```python
[1, 2, 3, ...]
```

Но при этом не создавать новый массив.

Типичный паттерн:

```python
def remove_duplicates(nums):
    if not nums:
        return 0

    write = 1

    for read in range(1, len(nums)):
        if nums[read] != nums[read - 1]:
            nums[write] = nums[read]
            write += 1

    return write
```

После этого:

```python
nums = [1, 1, 2, 2, 3]
k = remove_duplicates(nums)

print(k)        # 3
print(nums[:k]) # [1, 2, 3]
```

Важно: хвост массива после `k` обычно считается мусором. Он может быть каким угодно:

```python
[1, 2, 3, 2, 3]
```

Смысл только в первых `k` элементах.

## Самый частый паттерн: read / write pointer

In-place modification очень часто делается через два указателя:

```python
read  # откуда читаем
write # куда пишем
```

`read` идёт по старым данным.

`write` показывает место, куда надо положить следующий правильный элемент.

Например убрать все нули:

```python
def move_zeroes(nums):
    write = 0

    for read in range(len(nums)):
        if nums[read] != 0:
            nums[write] = nums[read]
            write += 1

    while write < len(nums):
        nums[write] = 0
        write += 1
```

Было:

```python
[0, 1, 0, 3, 12]
```

Стало:

```python
[1, 3, 12, 0, 0]
```

И это сделано без нового массива.

## Ещё один паттерн: swap

Если порядок не важен, можно менять элементы местами.

Например удалить элемент `val`:

```python
def remove_element(nums, val):
    i = 0
    n = len(nums)

    while i < n:
        if nums[i] == val:
            nums[i] = nums[n - 1]
            n -= 1
        else:
            i += 1

    return n
```

Тут мы не сохраняем порядок. Если нашли плохой элемент, заменили его последним рабочим элементом и уменьшили рабочую длину.

Это тоже in-place.

## В чём подвох

In-place modification — это не просто “экономия памяти”. Это ещё и изменение самого объекта.

Например:

```python
a = [1, 2, 3]
b = a

a.reverse()

print(b)
```

Будет:

```python
[3, 2, 1]
```

Потому что `a` и `b` указывают на один и тот же список.

Вот тут важное различение:

```python
a = [1, 2, 3]
b = a
```

`b` — не копия. Это ещё одна ссылка на тот же объект.

А вот:

```python
b = a[:]
```

это уже копия.

## In-place не всегда значит “лучше”

Плюсы:

* меньше памяти;
* часто быстрее, потому что нет копирования;
* требуется в задачах на массивы;
* полезно для больших данных.

Минусы:

* исходные данные уничтожаются / меняются;
* сложнее рассуждать;
* больше риск багов с индексами;
* если на структуру есть другие ссылки, они тоже увидят изменения.

То есть in-place — это не моральная добродетель, а конкретный технический режим: **работаем внутри уже существующей формы**.

## Как это распознать в задачах

Фразы-маркеры:

```text
modify the array in-place
do not allocate extra space
use O(1) extra memory
return the length, not a new array
the order of elements may be changed
```

Если видишь такое — почти всегда нужен один из паттернов:

```text
two pointers
read/write pointer
swap with end
reverse in-place
partition
cyclic sort
```

## Простая формула

Не in-place:

```python
result = transform(original)
```

In-place:

```python
transform(original)
# original itself has changed
```

То есть в первом случае ты создаёшь новую реальность рядом.

Во втором — переписываешь текущую структуру изнутри.
