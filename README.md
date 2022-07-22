# githubApi-Searcher
1. Lista użytkowników mających dostęp do serwisu zapisana jest w pamięci aplikacji. Możliwość modyfikacji dostępna jest przez dodanie/usunięcia usera, jego roli i hasła w kodzie.
Dostępni użytkownicy:
login:user, password: user
login:admin, password: admin

2. Ze względu na paginację github api, endpointy i klasy odpowiedzialne za funkcjonalność podzielone zostały na zapytania z paginacją oraz bez (zawiera wszystkie dane ze wszystkich stron, bez podziału na strony)
Endpointy:

Z paginacją:
http://localhost:8080/page{numer strony}/{nazwa użytkownika}
http://localhost:8080/page{numer strony}/{nazwa użytkownika}/{sposób filtrowania}

np: http://localhost:8080/page1/octocat/1

Bez paginacji:
http://localhost:8080/{nazwa użytkownika}
http://localhost:8080/{nazwa użytkownika}/{sposób filtrowania}

np: http://localhost:8080/octocat/1

Gdzie sposoby filtrowania:
Pobierane są jako liczba całkowita:
"1" sortowanie po nazwie rosnąco
"2" sortowanie po nazwie malejąco
"3" sortowanie po projektach, które zostały zaaktualizowane w ciągu 3 miesięcy

Ze względu na możliwość zapytań do github api bez generowania tokena (Personal access tokens) w projekcie nie został on zastosowany.
