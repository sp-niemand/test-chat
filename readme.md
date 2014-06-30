# Тестовое задание

## Задача

Написать socket сервер для чата. 

Клиентом выступает - telnet. 

Возможности чата: добавление нового пользователя, переписка в общей комнате, хранение последних 30 сообщений, личные сообщения. Для хранения данных использовать любое удобное хралище(файл, MySql, MongoDB, HBase, Hadoop, и т.д.).

## Реализация

Точка входа запускает класс ChatServer

chat.Server
    run() - слушает подключения на определенный порт. При новом подключении создаёт тред работы с клиентом, передавая в него открытый соккет
    все треды хранятся в hashMap

chat.ConnectionThread - получает сервер и соккет, слушает соккет, отдавая команду серверу
    public sendData() - отправляет данные в соккет 
    run() - слушает полученный соккет, отдавая приходящие команды серверу

chat.command.Protocol - получает на вход данные, пришедшие на сервер, возвращает команду

chat.command.ICommand
    execute(<карта коннекшнов>, <история>)

chat.command.Login
    execute - меняет имя с guest... на заданное

chat.command.SendAll
    execute - отправляет в общий чат сообщение

chat.command.GetHistory
    execute - отправляет клиенту массив реплик

chat.command.SendTo
    execute - отправляет клиенту сообщение

chat.History
    String[] getLastMessages(count)
    addMessage(message)
    cleanup()

### Команды:
* /login [<name>]
* /sendAll <text>
* /getHistory
* /sendTo <name> <text>