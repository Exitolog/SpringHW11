package ru.gb.timesheet;

public class REST {

    /**
     * HTTP - протокол
     * gRPC -протокол
     * WebSockets - протокол
     *
     *
     * Путь/эндпоинт/ручка/ресурс - /timesheet
     *
     * REST - Representation Stateful Transfer
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
     * API - спецификация, протокол, интерфейс, (формат), правила, договор/контракт
     * API - application programming interface
     *
     *
     * Shop
     * Product - продукт
     * -- Предусмотреть шаблон для создания продукта
     * GET /products - все продукты
     * GET /products/{id} - получить конкретный продукт
     * -- Предусмотреть запрос для поиска (ака like)
     * POST /products - создать продукт
     * PUT /products - обновить продукт
     * PATCH /products - обновить продукт (с выборочными полями)
     *
     * Profile - настройки пользователя
     * GET /profile/{id} - получить настройки пользователя
     * PUT /profile/{id} - обновить
     * -- Для получения данных текущего пользователя лучше передавать некий "токен" в заголовке
     *
     * Registration - регистрация нового пользователя
     * POST /registration body = {}
     *
     *
     * Cart - корзина пользователя
     * Все запросы должны содержать некий "токен", который подтверждает текущего пользователя
     * GET /cart - получить "корзину" текущего пользователя
     * POST /cart/{productId} - добавление товара в корзину
     * POST /cart/{productId}?newCount=X - сделать количество товара в корзине равным X
     * или POST /cart body = {productId = x, count = x, .....}
     * DELETE /cart/{productId} - удалить товар из корзины (с любым количеством)
     * DELETE /cart/{productId}?newCount=X - удалить
     *
     *
     *
     */
}
