// Дана прямоугольная карта размера MxN (N, M меньше 20)
// На карте стоит фигура в точке(Х, Y), которая может ходить на одну клеточку вправо или вниз за один ход
// Посчитать количество маршрутов, которым фигура может попасть в нижнюю правую клетку
// * * На карте могут быть стены
// * ** M и N могут быть до 1000
// Задачу разбить на методы

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
// import java.util.Arrays;

public class task {

    public static void main(String[] args) {
        board(reader());

    }

    public static void board(List<Integer> list) {
        Integer[][] board = new Integer[list.get(1)][list.get(0)];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 1;
            }
        }

        // System.out.println(list);
        if (list.size() > 5) {
            for (int i = 5; i < list.size() - 1; i += 2) {
                board[list.get(i + 1)][list.get(i)] = 0;
                if (list.get(i) == 0) {
                    for (int j = list.get(i + 1) + 1; j < board[0].length; j++) {
                        board[j][0] = 0;
                    }
                }
                if (list.get(i + 1) == 0) {
                    for (int k = list.get(i) + 1; k < board.length; k++) {
                        board[0][k] = 0;
                    }
                }
            }
        }

        for (int i = list.get(2) + 1; i < list.get(1); i++) {
            for (int j = list.get(3) + 1; j < list.get(0); j++) {
                if (board[i][j] != 0) {
                    board[i][j] = board[i - 1][j] + board[i][j - 1];
                }
            }
        }
        // for (Integer[] i : board) {
        //     System.out.println(Arrays.toString(i));
        // }
        System.out.println(board[list.get(1) - 1][list.get(0) - 1]);
    }

    public static List<Integer> reader() {
        List<Integer> list = new ArrayList<>();
        Scanner console = new Scanner(System.in);
        int a = 0;
        String temp;

        do {
            System.out.print("Введите размер доски по горизонтали  ");
            temp = console.next();
            a = tryParseInt(temp);
        } while (a <= 0);
        list.add(a);

        do {
            System.out.print("Введите размер доски по вертикали  ");
            temp = console.next();
            a = tryParseInt(temp);
        } while (a <= 0);
        list.add(a);

        do {
            System.out.print("Введите координату по горизонтали фигуры  ");
            temp = console.next();
            a = tryParseInt(temp);
        } while (a <= 0 || a > list.get(0));
        list.add(a - 1);

        do {
            System.out.print("Введите координату по вертикали фигуры  ");
            temp = console.next();
            a = tryParseInt(temp);
        } while (a <= 0 || a > list.get(1));
        list.add(a - 1);

        do {
            System.out.print("Стены нужны ? 1 - НЕТ , 2 - ДА  ");
            temp = console.next();
            a = tryParseInt(temp);
        } while (a <= 0 || a > 3);
        list.add(a);
        if (list.get(4) == 2) {
            do {
                System.out.print("Введите количество сегментов стен  ");
                temp = console.next();
                a = tryParseInt(temp);
            } while (a <= 0 || a >= list.get(0) * list.get(1));
            list.set(4, a);
        }

        while (list.get(4) > 0) {
            do {
                System.out.print("Введите координату по горизонтали ячейки стены  ");
                temp = console.next();
                a = tryParseInt(temp);
            } while (a <= 0 || a > list.get(0));
            list.add(a - 1);

            do {
                System.out.print("Введите координату по вертикали ячейки стены  ");
                temp = console.next();
                a = tryParseInt(temp);
            } while (a <= 0 || a > list.get(1));
            list.add(a - 1);
            list.set(4, list.get(4) - 1);
        }
        console.close();
        return list;

    }

    public static Integer tryParseInt(String temp) {
        try {
            return Integer.parseInt(temp);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}