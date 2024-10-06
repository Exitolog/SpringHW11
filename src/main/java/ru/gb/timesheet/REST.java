package ru.gb.timesheet;

public class REST {

    /**
     * HTTP - протокол
     * gRPC -протокол
     * WebSockets - протокол
     *
     * Путь/эндпоинт/ручка/ресурс - /timesheet
     *
     * REST - набор соглашений, как оформлять или проэктировать ресурсы вашего сервиса
     * 1. не использовать глаголы в путях
     * /deleteTimesheet{id} - плохо
     * DELETE /timesheet/{id} - хорошо
     *
     * 2. Ресурсы должны быть вложены в друг друга
     * GET /user/{userId}/roles/{raleId} - получить РОЛЬ с идентификаторов roleId у юзера userId
     *
     * Получить юзеров, у которых имя содержит какую-то подстроку
     * GET /user?name-Like="sdsdsd" -> 204 No Content
     * GET /users?sortBy=age&sortOrder=desc - сортировка юзеров по возрасту по убыванию
     *
     * 3. Реомендация: использовать множественное число для ресурсов
     * Вместо /user использовать /users
     *
     * 4. Слова внутри ресурса соединяются дефисом
     * GET github.com/project/pull-requests/{id}
     *
     */
}
