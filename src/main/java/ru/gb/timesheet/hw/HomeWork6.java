package ru.gb.timesheet.hw;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class HomeWork6 {

    /**
     *
     * 1. В LogginпAspect добавить логирование типов и значений аргументов
     * Например (пример вывода): TimesheetService.findById(Long = 3)
     * Эту информацию можно достать из JoinPoint.getArgs(значения)
     *
     * 2. * Создать аспект, который аспектирует методы, помеченные анноатцией Recover и делает следующее:
     * 2.1 Если в процессе выполнения метода был exception (любой),
     * то его нужно залогировать ("Recovering TimesheetService#findById after Exception[RuntimeException.class, "exception message"]) и вернуть default-значение наружу
     * Default-значения: для примитивов - значение по умолчанию, для ссылочных - null,
     * для void-методов возвращать не нужно.
     *
     * 3. *** В аннотацию Recover добавить атрибут Class<?>[] noRecoverFor, в которое можно записать список классов исключений,
     * которые НЕ нужно отлавливать
     * Это вхождение должно учитывать иерархию классов
     *
     *
     *  Пример:
     * @Recover(noRecoverFor = {NoSuchElementException.class, IllegalStateException.class})
     * public Timesheet getById(Long id) {...}
     *
     */

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Recove {
    }

}
