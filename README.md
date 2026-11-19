# Gästbok – 3.1.1 Databaskopplingar

Programmet är en gästbok skriven i Java med Swing och MySQL.
Användaren kan spara namn, e-post, hemsida och kommentar samt
visa alla sparade inlägg i ett separat fönster.

Text som innehåller HTML ersätts med `censur`. Databasfrågor
med användarens text använder PreparedStatement.

## Förutsättningar

- Java 25 eller senare.
- MySQL Server på localhost, port 3306.
- Databasen och tabellen skapade enligt instruktionerna nedan.

MySQL:s JDBC-drivrutin ingår i Guestbook.jar.

## Skapa databasen

Logga in i MySQL med en användare som har rätt att skapa databaser
och tabeller. Kör följande en gång:

```sql
CREATE DATABASE IF NOT EXISTS guestbook
    CHARACTER SET utf8mb4;

USE guestbook;

CREATE TABLE IF NOT EXISTS entries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    website VARCHAR(255) NOT NULL,
    comment TEXT NOT NULL
) DEFAULT CHARSET = utf8mb4;
```

Programmet ansluter med följande inställningar:

- Adress: jdbc:mysql://localhost:3306/guestbook
- Användare: root
- Lösenord: hämtas från miljövariabeln MYSQL_PASSWORD

## Starta programmet

Se till att MySQL-servern är igång. Öppna en terminal i katalogen
som innehåller Guestbook.jar.

### macOS med zsh

Ange MySQL-lösenordet när kommandot frågar efter det.
Lösenordet visas inte medan du skriver.

```sh
read -s "MYSQL_PASSWORD?MySQL password: "
export MYSQL_PASSWORD
java -jar Guestbook.jar
```

### Windows med PowerShell

```powershell
$password = Read-Host "MySQL password" -AsSecureString
$env:MYSQL_PASSWORD = [System.Net.NetworkCredential]::new("", $password).Password
java -jar Guestbook.jar
```

## Användning

1. Fyll i namn, e-post, hemsida och kommentar.
2. Klicka på **Save entry** för att spara inlägget.
3. Klicka på **Show entries** för att visa alla sparade inlägg.
4. Klicka på **Show entries** igen efter att ha sparat ett nytt inlägg
   för att uppdatera visningen.

Inläggen finns kvar i databasen när programmet stängs.

## Felsökning

Om programmet visar att ett inlägg inte kunde sparas eller hämtas,
kontrollera att:

- MySQL-servern är igång.
- Databasen guestbook och tabellen entries finns.
- MYSQL_PASSWORD är satt i terminalen där programmet startas.
- Lösenordet till MySQL-användaren root är korrekt.

Mer detaljerade felmeddelanden skrivs till terminalen.
