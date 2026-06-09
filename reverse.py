num = 123

def reverse(n):
    print(f"Начальное число: {n}")
    t = 0  # Это будет результат — перевернутое число
    step = 0  # Счётчик шагов
    while n != 0:
        step += 1
        last_digit = n % 10  # Получаем последнюю цифру числа
        print(f"\nШаг {step}:")
        print(f"  Последняя цифра (n % 10): {last_digit}")
        print(f"  Текущее t до обновления: {t}")
        t = t * 10 + last_digit  # Добавляем цифру к результату
        print(f"  Новое значение t: {t}")
        n = n // 10  # Удаляем последнюю цифру из n
        print(f"  Новое значение n (n // 10): {n}")
    print(f"\nИтоговое перевернутое число: {t}")
    return t

print("\nРезультат:")
print(reverse(num))
