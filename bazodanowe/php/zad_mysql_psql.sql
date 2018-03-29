--DROP TABLE zestaw, wynik, uprawnienia, rola, podkategoria, konto, kategoria, jezyk;

CREATE TABLE jezyk (
  id SERIAL NOT NULL PRIMARY KEY,
  nazwa VARCHAR(50) NOT NULL
);

CREATE TABLE kategoria (
  id SERIAL NOT NULL PRIMARY KEY,
  nazwa VARCHAR(50) NOT NULL,
  opis TEXT NOT NULL,
  obrazek BLOB NULL
);

CREATE TABLE podkategoria (
  id SERIAL NOT NULL PRIMARY KEY,
  kategoria_id INTEGER NOT NULL REFERENCES kategoria(id),
  nazwa VARCHAR(50) NOT NULL,
  opis TEXT NOT NULL,
  obrazek BLOB NULL
);

CREATE TABLE rola (
  id SERIAL NOT NULL PRIMARY KEY,
  nazwa VARCHAR(50) NOT NULL,
  opis VARCHAR(300) NOT NULL
);

CREATE TABLE konto (
  id SERIAL NOT NULL PRIMARY KEY,
  rola_id INTEGER NOT NULL REFERENCES rola(id),
  imie VARCHAR(20) NOT NULL,
  nazwisko VARCHAR(30) NOT NULL,
  email VARCHAR(50) NOT NULL,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL
);

CREATE TABLE uprawnienia (
  konto_id INTEGER NOT NULL REFERENCES konto(id),
  podkategoria_id INTEGER NOT NULL REFERENCES podkategoria(id),
  PRIMARY KEY(konto_id, podkategoria_id)
);

CREATE TABLE wynik (
  id SERIAL NOT NULL PRIMARY KEY,
  konto_id INTEGER NOT NULL REFERENCES konto(id),
  zestaw_id INTEGER NOT NULL REFERENCES zestaw(id),
  data_wyniku DATE NOT NULL,
  wynik INTEGER NOT NULL
);

CREATE TABLE zestaw (
  id SERIAL NOT NULL,
  konto_id INTEGER NOT NULL REFERENCES konto(id),
  jezyk1_id INTEGER NOT NULL REFERENCES jezyk(id),
  jezyk2_id INTEGER NOT NULL REFERENCES jezyk(id),
  podkategoria_id INTEGER NOT NULL REFERENCES podkategoria(id),
  nazwa VARCHAR(200) NOT NULL,
  zestaw TEXT NOT NULL,
  ilosc_slowek INTEGER NOT NULL,
  data_dodania DATE NOT NULL,
  data_edycji DATE NULL
);

