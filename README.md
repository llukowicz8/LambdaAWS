

# Flight App

Aplikacja Flight App przedstawia statystyki dotyczące przestrzeni powietrznej. Pozwala śledzić w czasie rzeczywistym
statystyki ruchu powietrznego nad wybranymi krajami. Dane prezentowane są w przejrzystej formie wykresów.
Flight App jest zasilany danymi pochodzącymi z [OpenSky REST API](https://opensky-network.org/apidoc/rest.html#) 

# Funkcjonalność

Aplikacja umożliwia odczytanie z wykresów takich statystyk jak:

- aktualna liczba samolotów znajdująca się w powietrzu nad danym krajem
- wysokość samolotu lecącego najwyżej nad danym krajem
- szybkość najszybszego samolotu lecącego nad danym krajem

# Technologie i użyte narzędzia

Aplikacja korzysta z możliwości Amazon Web Services (AWS)
Wykorzystane komponenty AWS :

* Amazon Elastic Compute Cloud (EC2)
* Lambda Functions
* API Gateway
* S3

Ponadto wykorzystane zostały takie technologie jak :

* Grafana
* InfluxDb

Aplikacja jest zasilana danymi pozyskanymi z [OpenSky REST API](https://opensky-network.org/apidoc/rest.html#)

### EC2
Serwis dostarczający skalowalną moc obliczeniową w chmurze. Dostęp jest realizowny poprzez 
stronę WWW lub poprzez protokoł SSH. EC2 umożliwia korzystanie z maszyn wirtualnych, które oferują
szerokie możliwości dostosowania do własnych potrzeb i uruchamiania na nich własnych aplikacji. 
Instalacja jest prosta i polega na wybraniu obrazu systemu, następnie jego instalacji. Kolejnym krokiem jest
dostosowanie reguł bezpieczeństwa do własnych potrzeb tak, aby użyte komponenety mogły się komunikować ze sobą

### Funkcja Lambda
AWS Lambda to serwis umożliwiający przetwarzanie w chmurze, który pozwala na uruchamianie funkcji bez potrzeby 
stawiania serwera i zarządzania nim. Zasoby są używane tylko wtedy, gdy funkcja lambda jest uruchamiana. 
Bardzo pożyteczną cechą funkcji lambda jest skalowalność, funkcja automatycznie wykrywa potrzebę użycia większej ilości 
zasobów i sama tym procesem zarządza. Jedynym zadaniem użytkownika jest więc dostarczenie kodu w jednym ze wspieranych przez
AWS Lambda jezyku programowania 

### Amazon API Gateway
Amazon API Gateway jest serwisem, który umożliwia tworzenie, zarządzanie i monitorowanie API.
Serwis pozwala na integracje systemów zewnętrznych z systemami AWS-owymi. API Gateway zarządza ruchem,
autoryzuje końcowych użytkowników oraz pozwala na monitorowanie wywołań API

### OpenSky REST API
Sieć OpenSky została zainicjowana w 2012 r. przez naukowców z armasuisse (Szwajcaria), Uniwersytetu Kaiserslautern (Niemcy) i University of Oxford (UK). Celem było i nadal jest ostarczanie naukowcom wysokiej jakości danych o ruchu lotniczym. Obecnie sieć OpenSky stała się stowarzyszeniem non-profit z siedzibą w Szwajcarii i jest wspierana przez rosnącą liczbę kontrybutorów z przemysłu i środowisk akademickich. Naukowcy z różnych obszarów korzystają z danych dostarczanych przez ludzi z całego świata.

Poniższa mapa pokazuje zasięg OpenSky, stan na 9.06.2019.
![zasiegOpenSky.png](https://github.com/llukowicz8/LambdaAWS/blob/master/zasiegOpenSky.png)

# Architektura

rysunek  + opis

# Uruchomienie
mvn clean package shade:shade

# Wygląd aplikacji

![wygladAplikacji.png](https://github.com/llukowicz8/LambdaAWS/blob/master/wygladAplikacji.png)
