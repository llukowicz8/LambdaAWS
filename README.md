

# Flight App

Aplikacja Flight App przedstawia statystyki dotyczące przestrzeni powietrznej. Pozwala śledzić w czasie rzeczywistym
statystyki ruchu powietrznego nad wybranymi krajami. Dane prezentowane są w przejrzystej formie wykresów.
Flight App jest zasilany danymi pochodzącymi z [OpenSky REST API](https://opensky-network.org/apidoc/rest.html#) 

# Funkcjonalność

Aplikacja umożliwia odczytanie z wykresów takich statystyk jak:

- aktualna liczba samolotów znajdująca się w powietrzu nad danym krajem
- wysokość samolotu lecącego najwyżej nad danym krajem
- szybkość najszybszego samolotu lecącego nad danym krajem

# Technologie

Aplikacja korzysta z możliwości Amazon Web Services (AWS)
Wykorzystane komponenty AWS :

* Amazon Elastic Compute Cloud (EC2)
* Lambda Functions
* API Gateway

Ponadto wykorzystane zostały takie technologie jak :

* Grafana
* InfluxDb

### EC2
Serwis dostarczający skalowalną moc obliczeniową w chmurze. Dostęp jest realizowny poprzez 
stronę WWW lub poprzez protokoł SSH. EC2 umożliwia korzystanie z maszyn wirtualnych, które oferują
szerokie możliwości dostosowania do własnych potrzeb i uruchamiania na nich własnych aplikacji. 
Instalacja jest prosta i polega na wybraniu obrazu systemu, następnie jego instalacji. Kolejnym krokiem jest
dostosowanie reguł bezpieczeństwa do własnych potrzeb tak, aby użyte komponenety mogły się komunikować ze sobą



### Lambda Functions

# Architektura

rysunek  + opis

# Uruchomienie
mvn clean package shade:shade
