### Шаги по установке и запуску

1. **Клонирование репозитория**
   ```bash
   git clone https://github.com/sarinzhan/to-do-list

3. **Убедитесь что у вас установлен Docker Desktop**
   
4. **Запустить контейнер postgresql**
   ```bash
   docker run --name to-do-list -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=to-do-list -d postgres:10


5. **Запустите приложение, убедившись что контейнер postgresql успешно запущен**

После запуска приложения, Swagger доступен по адресу</br>
   ```bash
      http://localhost:8080/swagger-ui/index.html
   ```


