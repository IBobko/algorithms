def two_sum(nums, target):
    num_to_index = {}
    print(f"Начальное состояние: nums = {nums}, target = {target}")
    for i, num in enumerate(nums):
        complement = target - num
        print(f"\nИтерация {i}:")
        print(f"  Текущее число: {num}")
        print(f"  Индекс текущего числа: {i}")
        print(f"  Искомое дополнение до {target}: {complement}")
        print(f"  Текущее состояние словаря: {num_to_index}")
        if complement in num_to_index:
            print(f"  Найдено! {complement} уже был на позиции {num_to_index[complement]}")
            print(f"  Возвращаем индексы: [{num_to_index[complement]}, {i}]")
            return [num_to_index[complement], i]
        print(f"  {complement} не найден. Добавляем {num}: {i} в словарь.")
        num_to_index[num] = i
    print("\nНе найдено ни одной пары чисел, дающих в сумме target.")
    return []

result = two_sum([2, 7, 11, 15], 9)
print("Результат:", result)
