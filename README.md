

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
AWS Lambda to serwis umożliwiający przetwarzanie w chmurze, który pozwala na uruchamianie funkcji bez potrzeby 
stawiania serwera i zarządzania nim. Zasoby są używane tylko wtedy, gdy funkcja lambda jest uruchamiana. 
Bardzo pożyteczną cechą funkcji lambda jest skalowalność, funkcja automatycznie wykrywa potrzebę użycia większej ilości 
zasobów i sama tym procesem zarządza. Jedynym zadaniem użytkownika jest więc dostarczenie kodu w jednym ze wspieranych przez
AWS Lambda jezyku programowania 


AWS Lambda is a compute service that lets you run code without provisioning or managing servers. 
AWS Lambda executes your code only when needed and scales automatically, from a few requests per day to thousands per second. 
You pay only for the compute time you consume - there is no charge when your code is not running. 
With AWS Lambda, you can run code for virtually any type of application or backend service - all with zero administration. 
AWS Lambda runs your code on a high-availability compute infrastructure and performs all of the administration of the compute resources, 
including server and operating system maintenance, capacity provisioning and automatic scaling, code monitoring and logging. 
All you need to do is supply your code in one of the languages that AWS Lambda support
# Architektura

rysunek  + opis

# Uruchomienie
mvn clean package shade:shade

# Wygląd aplikacji

![wygladAplikacji.png](https://github.com/llukowicz8/LambdaAWS/blob/master/wygladAplikacji.png)
