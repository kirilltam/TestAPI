#language: ru
  @TEST
  Функция: Погружение в API

    Сценарий: Тесты
      Когда Находим Morty Smith по id
      И Выбираем последний эпизод где появился Морти
      И Получаем последнего персонажа из последнего эпизода
      И Сохраняем расу и местонахождения последнего персонажа из последнего эпизода
      Тогда Сравниваем расу Морти с расой последнего персонажа из последнего эпизода
      Тогда Сравниваем местоположение Морти с местоположением последнего персонажа из последнего эпизода


