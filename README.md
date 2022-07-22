# githubApi-Searcher
1. Lista użytkowników mających dostęp do serwisu zapisana jest w pamięci aplikacji. Możliwość modyfikacji dostępna jest przez dodanie/usunięcia usera, jego roli i hasła w kodzie.
Dostępni użytkownicy:<br />
login:user, password: user<br />
login:admin, password: admin<br />

2. Ze względu na paginację github api, endpointy i klasy odpowiedzialne za funkcjonalność podzielone zostały na zapytania z paginacją oraz bez (zawiera wszystkie dane ze wszystkich stron, bez podziału na strony)
Endpointy:<br />

Z paginacją:<br />
http://localhost:8080/page{numer strony}/{nazwa użytkownika}<br />
http://localhost:8080/page{numer strony}/{nazwa użytkownika}/{sposób filtrowania}<br />

np: http://localhost:8080/page1/octocat/1<br />

Bez paginacji:<br />
http://localhost:8080/{nazwa użytkownika}<br />
http://localhost:8080/{nazwa użytkownika}/{sposób filtrowania}<br />

np: http://localhost:8080/octocat/1<br />

Gdzie sposoby filtrowania:<br />
Pobierane są jako liczba całkowita:<br />
"1" sortowanie po nazwie rosnąco<br />
"2" sortowanie po nazwie malejąco<br />
"3" sortowanie po projektach, które zostały zaaktualizowane w ciągu 3 miesięcy<br />

Ze względu na możliwość zapytań do github api bez generowania tokena (Personal access tokens) w projekcie nie został on zastosowany.<br />
Docker: https://hub.docker.com/repository/docker/klnstpr/github-api-searcher
