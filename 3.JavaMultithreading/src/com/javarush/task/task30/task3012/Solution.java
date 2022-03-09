package com.javarush.task.task30.task3012;

/* 
Реализуй метод createExpression(int number).
Метод createExpression вызывается с одним параметром number. Этот параметр - число от 1 до 3000 включительно.
Нужно вывести арифметическое выражение, результатом которого является число number.
Можно использовать числа: 1, 3, 9, 27, 81, 243, 729, 2187 не более, чем по одному разу.
Можно использовать знаки: "+" и "-" любое количество раз.
Обрати внимание, что перед каждым числом в искомой строке обязательно должен быть знак плюс или минус.
Перед выражением выведи [переданное число] =. (Смотри примеры вывода ниже).

Пример1:
Переданное число:
74
Ожидаемый вывод:
74 = - 1 + 3 - 9 + 81
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        String[] allowedNumbers = {"1", "3", "9", "27", "81", "243", "729", "2187"};
        StringBuilder sb = new StringBuilder(number + " = ");
        String inTernarySymmetric = convertToTernarySymmetric(number);
        for (int i = 0; i < inTernarySymmetric.length(); i++) {
            if (inTernarySymmetric.charAt(i) != '0') {
                sb.append(inTernarySymmetric.charAt(i));
                sb.append(" ");
                sb.append(allowedNumbers[i]);
                sb.append(" ");
            }
        }
        System.out.println(sb.toString().trim());
    }

    private String convertToTernarySymmetric(int number) {
        StringBuilder sb = new StringBuilder();
        while (number >= 2) {
            if (number % 3 == 0) {
                sb.append("0");
                number /= 3;
            } else if (number % 3 == 1) {
                sb.append("+");
                number /= 3;
            } else if (number % 3 == 2) {
                sb.append("-");
                number = (number / 3) + 1;
            }
        }
        if (number == 1) {
            sb.append("+");
        }
        return sb.toString();
    }
}