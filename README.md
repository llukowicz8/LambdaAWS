

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
Sieć OpenSky została zainicjowana w 2012 r. przez naukowców z armasuisse (Szwajcaria), Uniwersytetu Kaiserslautern (Niemcy) i University of Oxford (UK). Celem było i nadal jest dostarczanie naukowcom wysokiej jakości danych o ruchu lotniczym. Obecnie sieć OpenSky stała się stowarzyszeniem non-profit z siedzibą w Szwajcarii i jest wspierana przez rosnącą liczbę kontrybutorów z przemysłu i środowisk akademickich. Naukowcy z różnych obszarów korzystają z danych dostarczanych przez ludzi z całego świata.

Poniższa mapa pokazuje zasięg OpenSky, stan na 9.06.2019.
![zasiegOpenSky.png](https://github.com/llukowicz8/LambdaAWS/blob/master/zasiegOpenSky.png)

# Architektura
![architektura.png](https://github.com/llukowicz8/LambdaAWS/blob/master/architektura.png)

opis

# Uruchomienie

### Przygotowanie

Należy uruchomić instancje EC2. 
Zainstalować na niej [InfluxDb](https://docs.influxdata.com/influxdb/v1.3/introduction/installation/?fbclid=IwAR32tuy6wIiEVf8aUdzU-DpUDCr4BGIb6kzAqw-zBdx5Um9n4mCQEoUArkk#hosting-on-aws) 
```bash
cat <<EOF | sudo tee /etc/yum.repos.d/influxdb.repo
[influxdb]
name = InfluxDB Repository - RHEL \$releasever
baseurl = https://repos.influxdata.com/rhel/\$releasever/\$basearch/stable
enabled = 1
gpgcheck = 1
gpgkey = https://repos.influxdata.com/influxdb.key
EOF

sudo yum install influxdb
sudo service influxdb start
sudo yum install influxdb
sudo systemctl start influxdb
```
oraz [Grafana](https://grafana.com/docs/)
```bash
yum install -y https://s3-us-west-2.amazonaws.com/grafana-releases/release/grafana-5.0.3-1.x86_64.rpm
service grafana-server start
/sbin/chkconfig --add grafana-server
```


### Funkcja Lambda



Funkcje Lambda należy zbudować poniższym poleceniem.

    mvn clean package shade:shade
    
Nastepnie zbudowany plik .jar znajdujący się w katalogu /target należy wgrać jako funkcje Lambda w konosli AWS.

### Skrypty bash
W celu uruchomienia skryptu w tle w systemie Amazon Linux AMI postawionego na Amazon EC2 należy wpisać:
```bash
nohup ./nazwaSkryptu.sh &
```

### S3
Ostatnim etapem jest stworzenie bucketa w serwisie Amazon S3. Następnie wgrywamy do naszego bucketa plik index.html który zawiera elementy <iframe>, odwołujące się do Grafany uruchomionej w kontenerze EC2. Po wgraniu pliku przechodzimy do ustawień bucketa w sekcji 'Properties', tam włączamy opcję 'Static website hosting' z ustawionym 'Index document' na index.html. Należy także sprawdzić i ewentualnie sprawdzić ustawienia sekcji 'Permission' tak aby nasz bucket był dostępny publicznie.


# Wygląd aplikacji

![wygladAplikacji.png](https://github.com/llukowicz8/LambdaAWS/blob/master/wygladAplikacji.png)
